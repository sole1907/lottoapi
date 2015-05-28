/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbc.arsenallotto.processor;

import com.bbc.arsenallotto.jsonrequest.Boards;
import com.bbc.arsenallotto.jsonrequest.OGameData;
import com.bbc.arsenallotto.model.BaLottoAgent;
import com.bbc.arsenallotto.model.BaLottoChannel;
import com.bbc.arsenallotto.model.BaLottoConfig;
import com.bbc.arsenallotto.model.BaLottoEntry;
import com.bbc.arsenallotto.model.BaLottoEntryDetails;
import com.bbc.arsenallotto.model.BaLottoMember;
import com.bbc.arsenallotto.model.BaLottoNotifications;
import com.bbc.arsenallotto.model.BaLottoResponse;
import com.bbc.arsenallotto.model.controller.BaLottoAgentJpaController;
import com.bbc.arsenallotto.model.controller.BaLottoChannelJpaController;
import com.bbc.arsenallotto.model.controller.BaLottoConfigJpaController;
import com.bbc.arsenallotto.model.controller.BaLottoEntryJpaController;
import com.bbc.arsenallotto.model.controller.BaLottoMemberJpaController;
import com.bbc.arsenallotto.model.controller.BaLottoNotificationsJpaController;
import com.bbc.arsenallotto.model.controller.BaLottoResponseJpaController;
import com.bbc.arsenallotto.model.controller.exceptions.NonexistentEntityException;
import com.bbc.arsenallotto.model.controller.exceptions.RollbackFailureException;
import com.bbc.arsenallotto.utilities.BaLottoWsRequest;
import com.bbc.arsenallotto.utilities.BaLottoWsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soconcepts.utilities.ReferenceGenerator;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.UserTransaction;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Soul
 */
public class RequestProcessor {

    private static HashMap<String, BaLottoConfig> configMap = null;
    private static HashMap<String, BaLottoResponse> responseMap = null;
    private static HashMap<String, BaLottoAgent> agentMap = null;
    private static HashMap<Integer, BaLottoChannel> channelMap = null;
    UserTransaction utx;
    EntityManagerFactory emf;
    HttpServletRequest request;
    EntityManager em;

    public RequestProcessor(EntityManager em, HttpServletRequest request) {
        this.em = em;
        this.request = request;
    }

    public RequestProcessor(UserTransaction utx, EntityManagerFactory emf, HttpServletRequest request) {
        this.emf = emf;
        this.utx = utx;
        this.request = request;
    }

    public String register(String xml) {
        BaLottoWsRequest lottoRequest = null;

        Logger.getLogger(RequestProcessor.class.getName()).log(Level.INFO, "Register Request :: {0}", xml);

        try {
            JAXBContext context = JAXBContext.newInstance(BaLottoWsRequest.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            lottoRequest = (BaLottoWsRequest) unmarshaller.unmarshal(reader);
        } catch (Exception e) {
            lottoRequest = null;
            Logger.getLogger(RequestProcessor.class.getName()).log(Level.SEVERE, null, e);
        }

        String mobile = lottoRequest.getMobile();

        int responseCode = -1;

        if (lottoRequest == null || mobile == null || mobile.isEmpty()) {
            responseCode = 14; // XML Parse Error, Invalid Request
        }

        if (responseCode < 0) {
            String clientIp = request.getRemoteAddr();
            Logger.getLogger(RequestProcessor.class.getName()).log(Level.INFO, "Client IP :: {0}", clientIp);

            BaLottoAgent agent = agentMap.get(lottoRequest.getUsername());

            if (agent == null) {
                responseCode = 1; // invalid agent username
            } else {
                if (agent.getValidateIp() && !agent.getIpAddress().equalsIgnoreCase(clientIp)) {
                    responseCode = 2; // invalid ip address
                }

                if (responseCode < 0) {
                    if (!lottoRequest.getPassword().equalsIgnoreCase(agent.getPassword())) {
                        responseCode = 3; // invalid agent password
                    }
                }
            }

            if (responseCode < 0) {
                String checkdigit = mobile + (lottoRequest.getLastname() == null ? "" : lottoRequest.getLastname()) + (lottoRequest.getFirstname() == null ? "" : lottoRequest.getFirstname()) + (lottoRequest.getSex() == null ? "" : lottoRequest.getSex()) + (lottoRequest.getEmail() == null ? "" : lottoRequest.getEmail()) + (lottoRequest.getDob() == null ? "" : lottoRequest.getDob());
                Logger.getLogger(RequestProcessor.class.getName()).log(Level.INFO, "Register Check Digit :: {0}", checkdigit);
                checkdigit = getMD5(checkdigit);

                if (!checkdigit.equalsIgnoreCase(lottoRequest.getRequestcode())) {
                    responseCode = 4; //invalid secure code
                }

                if (responseCode < 0) {
                    String countryCode = "234";
                    BaLottoConfig countryCodeConfig = configMap.get("COUNTRY_CODE");

                    if (countryCodeConfig != null) {
                        countryCode = countryCodeConfig.getParameterValue().trim();
                    }

                    if (mobile.startsWith("0")) {
                        mobile = countryCode + mobile.substring(1);
                    }

                    BaLottoMemberJpaController memberController = null;
                    if (utx == null) {
                        memberController = new BaLottoMemberJpaController(em);
                    } else {
                        memberController = new BaLottoMemberJpaController(utx, emf);
                    }

                    BaLottoMember member = memberController.findByMobile(mobile);
                    boolean create = false;

                    if (member == null || member.getAgentID() == null) {
                        if (member == null) {
                            create = true;
                            member = new BaLottoMember();
                        }

                        member.setAgentID(agent.getId());
                        member.setCreated(new Date());
                        if (lottoRequest.getDob() != null && !lottoRequest.getDob().isEmpty()) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = null;
                            try {
                                date = sdf.parse(lottoRequest.getDob());
                            } catch (ParseException ex) {
                                Logger.getLogger(RequestProcessor.class.getName()).log(Level.SEVERE, null, ex);
                                responseCode = 15; // invalid dob format
                            }
                            member.setDob(date);

                            int ageRestriction = 0;
                            BaLottoConfig ageRestrictionCfg = configMap.get("AGE_RESTRICTION");
                            if (ageRestrictionCfg != null) {
                                ageRestriction = Integer.parseInt(ageRestrictionCfg.getParameterValue());
                            }
                            long dateDiff = System.currentTimeMillis() - date.getTime();
                            if (dateDiff < (ageRestriction * 365 * 24 * 60 * 60 * 1000)) {
                                responseCode = 16; // not of legal age
                            }
                        }
                        if (lottoRequest.getEmail() != null && !lottoRequest.getEmail().isEmpty()) {
                            member.setEmail(lottoRequest.getEmail());
                        }
                        if (lottoRequest.getFirstname() != null && !lottoRequest.getFirstname().isEmpty()) {
                            member.setFirstname(lottoRequest.getFirstname());
                        }
                        if (lottoRequest.getLastname() != null && !lottoRequest.getLastname().isEmpty()) {
                            member.setLastname(lottoRequest.getLastname());
                        }
                        member.setMobileNumber(mobile);
                        if (lottoRequest.getSex() != null && !lottoRequest.getSex().isEmpty()) {
                            if (lottoRequest.getSex().equalsIgnoreCase("Male")) {
                                member.setSex('M');
                            } else if (lottoRequest.getSex().equalsIgnoreCase("Female")) {
                                member.setSex('F');
                            }
                        }

                        if (responseCode < 0) {
                            if (create) {
                                try {
                                    memberController.create(member);
                                    responseCode = 0;
                                } catch (Exception ex) {
                                    responseCode = 10;
                                    Logger.getLogger(RequestProcessor.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                try {
                                    memberController.edit(member);
                                    responseCode = 0;
                                } catch (NonexistentEntityException ex) {
                                    responseCode = 10;
                                    Logger.getLogger(RequestProcessor.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (RollbackFailureException ex) {
                                    responseCode = 10;
                                    Logger.getLogger(RequestProcessor.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (Exception ex) {
                                    responseCode = 10;
                                    Logger.getLogger(RequestProcessor.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    } else {
                        responseCode = 5;
                    }
                }
            }
        }

        BaLottoWsResponse lottoWsResponse = new BaLottoWsResponse();
        String responseMsg = "";
        lottoWsResponse.setResponseCode(responseCode);
        lottoWsResponse.setMobile(mobile);
        BaLottoResponse lottoResponse = responseMap.get("REG" + responseCode);
        if (lottoResponse == null) {
            lottoResponse = responseMap.get("DEFAULT" + responseCode);
        }
        if (lottoResponse != null) {
            responseMsg = lottoResponse.getResponseMessage();
        }
        responseMsg = responseMsg.replace("%mobile%", mobile);
        lottoWsResponse.setResponseMessage(responseMsg);

        StringWriter writter = null;

        try {
            JAXBContext context = JAXBContext.newInstance(BaLottoWsResponse.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            writter = new StringWriter();
            marshaller.marshal(lottoWsResponse, writter);
        } catch (Exception e) {
            Logger.getLogger(RequestProcessor.class.getName()).log(Level.SEVERE, null, e);
        }

        return writter.toString();
    }

    public String play(String xml) {
        int responseCode = -1;
        String responseMsg = "";
        String lottoReference = "";
        int minEntry = 1;
        int maxEntry = 49;
        int minJack = 0;
        int maxJack = 9;
        double amtCfg = 100d;
        double expectedAmount = 100d;
        
        Logger.getLogger(RequestProcessor.class.getName()).log(Level.INFO, "Play Request :: {0}", xml);

        BaLottoWsResponse lottoWsResponse = new BaLottoWsResponse();

        String clientIp = request.getRemoteAddr();
        Logger.getLogger(RequestProcessor.class.getName()).log(Level.INFO, "Client IP :: {0}", clientIp);

        BaLottoWsRequest lottoRequest = null;

        try {
            JAXBContext context = JAXBContext.newInstance(BaLottoWsRequest.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            lottoRequest = (BaLottoWsRequest) unmarshaller.unmarshal(reader);
        } catch (Exception e) {
            lottoRequest = null;
            Logger.getLogger(RequestProcessor.class.getName()).log(Level.SEVERE, null, e);
        }

        String mobile = lottoRequest.getMobile();
        String username = lottoRequest.getUsername();
        String password = lottoRequest.getPassword();
        String reference = lottoRequest.getReference();
        String amount = lottoRequest.getAmount();
        String requestcode = lottoRequest.getRequestcode();
        int channelid = lottoRequest.getChannelid();

        int gameId = lottoRequest.getGameId();

        if (gameId != 0 && (gameId < 1742 || gameId > 1743)) {
            responseCode = 8;
        }

        OGameData gameData = lottoRequest.getOGameData();

        String gameCheck = gameData.getPlayDuration() + "";

        for (int day : gameData.getDay()) {
            gameCheck += day;
        }

        for (Boards boards : gameData.getBoards()) {
            for (int number : boards.getNumbers()) {
                gameCheck += number;
            }
        }

        BaLottoAgent agent = agentMap.get(username);

        if (agent == null) {
            responseCode = 1; // invalid agent username
        } else {
            if (agent.getValidateIp() && !agent.getIpAddress().equalsIgnoreCase(clientIp)) {
                responseCode = 2; // invalid ip address
            }

            if (responseCode < 0) {
                if (!password.equalsIgnoreCase(agent.getPassword())) {
                    responseCode = 3; // invalid agent password
                }
            }
        }

        if (responseCode < 0) {
            String checkdigit = mobile + amount + gameCheck + reference + gameId + channelid;
            Logger.getLogger(RequestProcessor.class.getName()).log(Level.INFO, "Play Check Digit :: {0}", checkdigit);
                
            checkdigit = getMD5(checkdigit);

            if (!checkdigit.equalsIgnoreCase(requestcode)) {
                responseCode = 4; //invalid secure code
            }
        }

        if (gameId == 0) {
            gameId = 1742;
            lottoRequest.setGameId(gameId);
        }

        if (responseCode < 0) {
            BaLottoEntry entry = null;
            BaLottoEntryJpaController entryJpaController = null;
            if (utx == null) {
                entryJpaController = new BaLottoEntryJpaController(em);
            } else {
                entryJpaController = new BaLottoEntryJpaController(utx, emf);
            }
            entry = entryJpaController.isDuplicate(agent.getId(), reference);
            if (entry != null) {
                responseCode = 5; //duplicate entry
            }
        }
        BaLottoChannel channel = null;
        if (responseCode < 0) {
            channel = channelMap.get(channelid);
            if (channel == null) {
                responseCode = 6; //invalid channelid
            }
        }

        BaLottoMember lottoMember = null;
        BaLottoEntry lottoEntry = null;
        double amountd = 0d;

        if (responseCode < 0) {
            String countryCode = "234";
            BaLottoConfig countryCodeConfig = configMap.get("COUNTRY_CODE");

            if (countryCodeConfig != null) {
                countryCode = countryCodeConfig.getParameterValue().trim();
            }

            if (mobile.startsWith("0")) {
                mobile = countryCode + mobile.substring(1);
            }

            BaLottoMemberJpaController memberController = null;
            if (utx == null) {
                memberController = new BaLottoMemberJpaController(em);
            } else {
                memberController = new BaLottoMemberJpaController(utx, emf);
            }

            lottoMember = memberController.findByMobile(mobile);
            int ageRestriction = 0;

            if (lottoMember == null) {
                BaLottoConfig ageRestrictionCfg = configMap.get("AGE_RESTRICTION");
                if (ageRestrictionCfg != null) {
                    ageRestriction = Integer.parseInt(ageRestrictionCfg.getParameterValue());
                }

                if (ageRestriction > 0) {
                    responseCode = 9; // must be registered 
                } else {
                    lottoMember = new BaLottoMember();
                    lottoMember.setCreated(new Date());
                    lottoMember.setMobileNumber(mobile);
                    try {
                        memberController.create(lottoMember);
                    } catch (Exception ex) {
                        Logger.getLogger(RequestProcessor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            if (responseCode < 0) {
                lottoEntry = new BaLottoEntry();

                BaLottoConfig amtConfig = configMap.get("AMOUNT");
                if (amtConfig != null) {
                    amtCfg = Double.parseDouble(amtConfig.getParameterValue().trim());
                }

                /*BaLottoConfig maxAmtConfig = configMap.get("MAXIMUM_AMOUNT");
                 if (maxAmtConfig != null) {
                 maxAmt = Double.parseDouble(maxAmtConfig.getParameterValue().trim());
                 }*/
                try {
                    amountd = Double.parseDouble(amount);

                    expectedAmount = amtCfg * gameData.getPlayDuration() * gameData.getDay().size() * gameData.getBoards().size();
                    if (amountd != expectedAmount) {
                        responseCode = 7; //invalid amount
                    }
                } catch (Exception e) {
                    Logger.getLogger(RequestProcessor.class.getName()).log(Level.SEVERE, "Unable to parse amount - " + amount + " :: " + e.getMessage());
                    responseCode = 7; //invalid amount
                }
            }
        }

        String response = "";

        if (responseCode < 0) {
            BaLottoConfig minEntryConfig = configMap.get("MINIMUM_ENTRY_" + gameId);
            if (minEntryConfig != null) {
                minEntry = Integer.parseInt(minEntryConfig.getParameterValue().trim());
            }

            BaLottoConfig maxEntryConfig = configMap.get("MAXIMUM_ENTRY_" + gameId);
            if (maxEntryConfig != null) {
                maxEntry = Integer.parseInt(maxEntryConfig.getParameterValue().trim());
            }

            BaLottoConfig minEntryJack = configMap.get("MINIMUM_JACK");
            if (minEntryJack != null) {
                minJack = Integer.parseInt(minEntryJack.getParameterValue().trim());
            }

            BaLottoConfig maxEntryJack = configMap.get("MAXIMUM_JACK");
            if (maxEntryJack != null) {
                maxJack = Integer.parseInt(maxEntryJack.getParameterValue().trim());
            }

            /*try {
             entry1i = Integer.parseInt(entry1.trim());
             entry2i = Integer.parseInt(entry2.trim());
             entry3i = Integer.parseInt(entry3.trim());
             entry4i = Integer.parseInt(entry4.trim());
             entry5i = Integer.parseInt(entry5.trim());
             entry6i = Integer.parseInt(entry6.trim());
             if (jackpot != null && !jackpot.trim().equals("")) {
             jackpoti = Integer.parseInt(jackpot.trim());
             }

             if (entry1i < minEntry || entry1i > maxEntry || entry2i < minEntry || entry2i > maxEntry || entry3i < minEntry || entry3i > maxEntry || entry4i < minEntry || entry4i > maxEntry || entry5i < minEntry || entry5i > maxEntry || entry6i < minEntry || entry6i > maxEntry || (jackpot != null && !jackpot.trim().equals("") && (jackpoti < minJack || jackpoti > maxJack))) {
             responseCode = 8; //invalid lotto entry
             }
             } catch (Exception e) {
             logger.log(Level.SEVERE, "Unable to parse an entry :: " + e.getMessage());
             responseCode = 8; //invalid lotto entry
             }
             }*/

            /*if (responseCode < 0) {
             int weekEnd = 1;

             BaLottoConfig weekendConfig = configMap.get("WEEK_END");
             if (weekendConfig != null) {
             weekEnd = Integer.parseInt(weekendConfig.getParameterValue().trim());
             }

             boolean allowDuplicateEntries = false;

             BaLottoConfig duplicateEntryConfig = configMap.get("ALLOW_WEEKLY_DUPLICATES");
             if (duplicateEntryConfig != null) {
             allowDuplicateEntries = duplicateEntryConfig.getParameterValue().trim().equals("1");
             }

             if (!allowDuplicateEntries) {
             Calendar cal = Calendar.getInstance();
             int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
             int nextWeekAdd = 0;
             int lastWeekSub = 0;
             Date nextCycle = null;
             Date previousCycle = null;
             boolean next = false;

             if (dayOfWeek <= weekEnd) {
             nextWeekAdd = weekEnd - dayOfWeek;
             next = true;
             } else {
             lastWeekSub = dayOfWeek - weekEnd;
             }

             if (next) {
             cal.add(Calendar.DAY_OF_MONTH, nextWeekAdd);
             cal.set(Calendar.HOUR_OF_DAY, 23);
             cal.set(Calendar.MINUTE, 59);
             cal.set(Calendar.SECOND, 59);
             nextCycle = cal.getTime();
             cal.add(Calendar.DAY_OF_MONTH, -7);
             cal.set(Calendar.HOUR_OF_DAY, 0);
             cal.set(Calendar.MINUTE, 0);
             cal.set(Calendar.SECOND, 0);
             previousCycle = cal.getTime();
             } else {
             cal.add(Calendar.DAY_OF_MONTH, -lastWeekSub);
             cal.set(Calendar.HOUR_OF_DAY, 0);
             cal.set(Calendar.MINUTE, 0);
             cal.set(Calendar.SECOND, 0);
             previousCycle = cal.getTime();
             cal.add(Calendar.DAY_OF_MONTH, 7);
             cal.set(Calendar.HOUR_OF_DAY, 23);
             cal.set(Calendar.MINUTE, 59);
             cal.set(Calendar.SECOND, 59);
             nextCycle = cal.getTime();
             }

             if (new BaLottoEntryHelper(logger).isDuplicateEntry(mobile, previousCycle, nextCycle, entry1, entry2, entry3, entry4, entry5, entry6)) {
             responseCode = 9;  //duplicate lott entry
             }
             }
             }*/
            lottoEntry.setGameId(gameId);
            lottoEntry.setAmount(BigDecimal.valueOf(amountd));
            lottoEntry.setAgentId(agent);
            lottoEntry.setAttempts((short) 0);
            lottoEntry.setChannelId(channel);
            lottoEntry.setMemberId(lottoMember);
            lottoEntry.setCheckDigit(requestcode);
            lottoEntry.setEntryDate(new Date());
            lottoEntry.setPaymentReference(reference);
            lottoEntry.setDuration((short) gameData.getPlayDuration());
            List<Integer> days = gameData.getDay();
            if (days.size() == 2) {
                lottoEntry.setDays((short) 0);
            } else {
                lottoEntry.setDays(days.get(0).shortValue());
            }

            List<BaLottoEntryDetails> entryDetailsList = new ArrayList<BaLottoEntryDetails>();

            for (Boards boards : gameData.getBoards()) {
                BaLottoEntryDetails entryDetails = new BaLottoEntryDetails();
                Iterator it = boards.getNumbers().iterator();
                int entry = 0;
                if (it.hasNext()) {
                    entry = (Integer) it.next();
                    if (entry < minEntry || entry > maxEntry) {
                        responseCode = 8;
                        break;
                    }
                    entryDetails.setEntry1(entry);
                }

                if (it.hasNext()) {
                    entry = (Integer) it.next();
                    if (entry < minEntry || entry > maxEntry) {
                        responseCode = 8;
                        break;
                    }
                    entryDetails.setEntry2(entry);
                }

                if (it.hasNext()) {
                    entry = (Integer) it.next();
                    if (entry < minEntry || entry > maxEntry) {
                        responseCode = 8;
                        break;
                    }
                    entryDetails.setEntry3(entry);
                }

                if (it.hasNext()) {
                    entry = (Integer) it.next();
                    if (entry < minEntry || entry > maxEntry) {
                        responseCode = 8;
                        break;
                    }
                    entryDetails.setEntry4(entry);
                }

                if (it.hasNext()) {
                    entry = (Integer) it.next();
                    if (entry < minEntry || entry > maxEntry) {
                        responseCode = 8;
                        break;
                    }
                    entryDetails.setEntry5(entry);
                }

                if (gameId == 1742) {
                    if (it.hasNext()) {
                        entry = (Integer) it.next();
                        if (entry < minEntry || entry > maxEntry) {
                            responseCode = 8;
                            break;
                        }
                        entryDetails.setEntry6(entry);
                    }

                    if (it.hasNext()) {
                        entry = (Integer) it.next();
                        if (entry < minJack || entry > maxJack) {
                            responseCode = 8;
                            break;
                        }
                        entryDetails.setLegend(entry);
                    }
                }
                
                if (gameId == 1743) {
                    if (it.hasNext()) {
                        entry = (Integer) it.next();
                        if (entry < minJack || entry > maxJack) {
                            responseCode = 8;
                            break;
                        }
                        entryDetails.setLegend(entry);
                    }
                }
                
                entryDetails.setEntryId(lottoEntry);
                entryDetailsList.add(entryDetails);
            }

            lottoEntry.setBaLottoEntryDetailsCollection(entryDetailsList);

            if (responseCode < 0) {
                responseCode = 0;
            }

            if (responseCode == 0) {
                lottoReference = ReferenceGenerator.getUniqueReference("", "");
                Logger.getLogger(RequestProcessor.class.getName()).log(Level.INFO, "Lotto Reference :: " + lottoReference);
                lottoEntry.setLottoReference(lottoReference);
                lottoEntry.setResponseCode(responseCode);
                BaLottoResponse lottoResponse = responseMap.get("PLAY" + responseCode);
                if (lottoResponse == null) {
                    lottoResponse = responseMap.get("DEFAULT" + responseCode);
                }
                if (lottoResponse != null) {
                    responseMsg = lottoResponse.getResponseMessage();
                }
                responseMsg = responseMsg.replace("%mobile%", mobile);
                responseMsg = responseMsg.replace("%reference%", lottoReference);
                responseMsg = responseMsg.replace("%min%", minEntry + "");
                responseMsg = responseMsg.replace("%max%", maxEntry + "");
                responseMsg = responseMsg.replace("%expectedamt%", expectedAmount + "");
                //responseMsg = responseMsg.replace("%maxamt%", maxAmt + "");
                lottoEntry.setResponseMessage(responseMsg);
                lottoEntry.setResponseDate(new Date());

                BaLottoEntryJpaController lottoEntryController = null;
                if (utx == null) {
                    lottoEntryController = new BaLottoEntryJpaController(em);
                } else {
                    lottoEntryController = new BaLottoEntryJpaController(utx, emf);
                }
                try {
                    lottoEntry = lottoEntryController.create(lottoEntry);
                    if (lottoEntry != null) {
                        responseCode = 0; //success
                        //new LottoProcessor(lottoRequest, lottoEntry, lottoMember, utx, emf).start();
                    } else {
                        responseCode = 10; //sytem error
                        responseMsg = "System Error";
                    }
                } catch (Exception ex) {
                    responseCode = 10; //sytem error
                    Logger.getLogger(RequestProcessor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        lottoWsResponse.setResponseCode(responseCode);
        lottoWsResponse.setLottoReference(lottoReference);
        lottoWsResponse.setMobile(mobile);
        BaLottoResponse lottoResponse = responseMap.get("PLAY" + responseCode);
        if (lottoResponse == null) {
            lottoResponse = responseMap.get("DEFAULT" + responseCode);
        }
        if (lottoResponse != null) {
            responseMsg = lottoResponse.getResponseMessage();
        }
        responseMsg = responseMsg.replace("%mobile%", mobile);
        responseMsg = responseMsg.replace("%reference%", lottoReference);
        responseMsg = responseMsg.replace("%min%", minEntry + "");
        responseMsg = responseMsg.replace("%max%", maxEntry + "");
        responseMsg = responseMsg.replace("%expectedamt%", expectedAmount + "");
        //responseMsg = responseMsg.replace("%maxamt%", maxAmt + "");
        lottoWsResponse.setResponseMessage(responseMsg);
        lottoWsResponse.setPaymentReference(reference);

        StringWriter writter = null;

        try {
            JAXBContext context = JAXBContext.newInstance(BaLottoWsResponse.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            ObjectMapper mapper = new ObjectMapper();
            writter = new StringWriter();
            marshaller.marshal(lottoWsResponse, writter);
            response = writter.toString();
        } catch (Exception e) {
            Logger.getLogger(RequestProcessor.class.getName()).log(Level.SEVERE, "Oops! Something least expected has gine wrong ... " + e.getMessage());
        }

        return response;
    }

    public void initialize() {
        System.out.println("User Txn :: " + utx);
        System.out.println("EMF Txn :: " + emf);
        if (responseMap == null) {
            responseMap = new HashMap<String, BaLottoResponse>();
            BaLottoResponseJpaController baLottoResponseController = null;
            if (utx == null) {
                baLottoResponseController = new BaLottoResponseJpaController(em);
            } else {
                baLottoResponseController = new BaLottoResponseJpaController(utx, emf);
            }

            List<BaLottoResponse> responseList = baLottoResponseController.findBaLottoResponseEntities();
            for (BaLottoResponse response : responseList) {
                responseMap.put(response.getToken().toUpperCase() + response.getResponseCode(), response);
            }
        }

        if (agentMap == null) {
            agentMap = new HashMap<String, BaLottoAgent>();
            BaLottoAgentJpaController baLottoAgentController = null;
            if (utx == null) {
                baLottoAgentController = new BaLottoAgentJpaController(em);
            } else {
                baLottoAgentController = new BaLottoAgentJpaController(utx, emf);
            }
            List<BaLottoAgent> agentList = baLottoAgentController.findBaLottoAgentEntities();
            for (BaLottoAgent agent : agentList) {
                agentMap.put(agent.getUsername(), agent);
            }
        }

        if (configMap == null) {
            configMap = new HashMap<String, BaLottoConfig>();
            BaLottoConfigJpaController baLottoConfigController = null;
            if (utx == null) {
                baLottoConfigController = new BaLottoConfigJpaController(em);
            } else {
                baLottoConfigController = new BaLottoConfigJpaController(utx, emf);
            }
            List<BaLottoConfig> configList = baLottoConfigController.findBaLottoConfigEntities();
            for (BaLottoConfig config : configList) {
                configMap.put(config.getParameterName().toUpperCase(), config);
            }
        }

        if (channelMap == null) {
            channelMap = new HashMap<Integer, BaLottoChannel>();
            BaLottoChannelJpaController baLottoChannelController = null;
            if (utx == null) {
                baLottoChannelController = new BaLottoChannelJpaController(em);
            } else {
                baLottoChannelController = new BaLottoChannelJpaController(utx, emf);
            }
            List<BaLottoChannel> channelList = baLottoChannelController.findBaLottoChannelEntities();
            for (BaLottoChannel lchannel : channelList) {
                channelMap.put(lchannel.getId(), lchannel);
            }
        }

    }

    public String notify(String gameId, String drawDate) {
        String result = "-1";

        BaLottoNotificationsJpaController notificationController = new BaLottoNotificationsJpaController(em);

        if (notificationController.isDuplicate(Short.parseShort(gameId), drawDate)) {
            result = "0";
        } else {
            BaLottoNotifications notification = new BaLottoNotifications();
            notification.setCreated(new Date());
            notification.setDrawDate(drawDate);
            notification.setGameId(Short.parseShort(gameId));
            notification.setProcessed(false);

            try {
                if (notificationController.create(notification)) {
                    result = "0";
                }
            } catch (Exception ex) {
                Logger.getLogger(RequestProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    private String getMD5(String input) {
        String hashtext = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;

            }
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(RequestProcessor.class
                    .getName()).log(Level.SEVERE, null, e);
        }

        return hashtext;
    }
}
