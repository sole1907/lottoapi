/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbc.arsenallotto.tests;

import com.bbc.arsenallotto.jsonrequest.AItems;
import com.bbc.arsenallotto.jsonrequest.AOrderItems;
import com.bbc.arsenallotto.jsonrequest.Boards;
import com.bbc.arsenallotto.jsonrequest.Data;
import com.bbc.arsenallotto.jsonrequest.OCustomer;
import com.bbc.arsenallotto.jsonrequest.OGameData;
import com.bbc.arsenallotto.jsonrequest.OOrder;
import com.bbc.arsenallotto.jsonrequest.OTransaction;
import com.bbc.arsenallotto.jsonrequest.OrderItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Noble
 */
public class TestXMLMapper {

    public static void main(String[] args) {
        List<Boards> boardsList = new ArrayList<Boards>();
        Boards boards = new Boards();

        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(12);
        numbers.add(15);
        numbers.add(23);
        numbers.add(31);
        numbers.add(37);
        numbers.add(40);

        boards.setNumbers(numbers);

        boardsList.add(boards);

        boards = new Boards();

        numbers = new ArrayList<Integer>();
        numbers.add(5);
        numbers.add(7);
        numbers.add(15);
        numbers.add(19);
        numbers.add(31);
        numbers.add(44);

        boards.setNumbers(numbers);

        boardsList.add(boards);

        OGameData oGameData = new OGameData();
        oGameData.setBoards(boardsList);

        List<Integer> day = new ArrayList<Integer>();
        day.add(3);
        day.add(6);

        oGameData.setDay(day);
        oGameData.setPlayDuration(9);

        OrderItem orderItem = new OrderItem();
        orderItem.setiGameID(1742);
        orderItem.setoGameData(oGameData);

        AOrderItems aOrderItems = new AOrderItems();
        aOrderItems.setOrderItem(orderItem);

        OOrder order = new OOrder();
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyyMMdd");
        //HHmmss");
        order.setdCreatedAt(sdfd.format(new Date()));
        order.setaOrderItems(aOrderItems);
        order.setfBalance(0f);
        order.setfTotal(300f);
        order.setiCurrency("46");

        AItems aItems = new AItems();
        aItems.setoOrder(order);

        OCustomer oCustomer = new OCustomer();
        oCustomer.setsEmail("jon@network649.com");
        oCustomer.setsFirstName("Jonathan");
        oCustomer.setsLastName("Patchett");
        oCustomer.setsPhone("07906234440");

        aItems.setoCustomer(oCustomer);

        OTransaction oTransaction = new OTransaction();
        oTransaction.setbApproved("true");
        oTransaction.setfAmount(200f);
        oTransaction.setsDetails("<![CDATA[... Response details from payment]]>");
        oTransaction.setsRef("49281777222");

        aItems.setoTransaction(oTransaction);

        Data data = new Data();
        data.setaItems(aItems);
        StringWriter writter = null;

        try {
            JAXBContext context = JAXBContext.newInstance(Data.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            ObjectMapper mapper = new ObjectMapper();
            writter = new StringWriter();
            marshaller.marshal(data, writter);
        } catch (Exception e) {
            Logger.getLogger(TestXMLMapper.class.getName()).log(Level.SEVERE, null, e);
        }

        System.out.println("Sending XML");
        System.out.println(writter);
        System.out.println("===================================================");

        //String urlParameters = "param1=a&param2=b&param3=c";
        String request = "http://api.aln.n649global.com/administration/API/order/order";
        URL url = null;
        try {
            url = new URL(request);
        } catch (MalformedURLException ex) {
            Logger.getLogger(TestXMLMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            try {
                connection.setRequestMethod("POST");
            } catch (ProtocolException ex) {
                Logger.getLogger(TestXMLMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection.setRequestProperty("Content-Type", "application/xml");
            connection.setRequestProperty("Accepts", "application/xml");
            connection.setRequestProperty("Preshared-Key", "ea34879b031f40b34e425b08975394ac");
            //connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            connection.setUseCaches(false);

            DataOutputStream wr = null;
            wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(writter.toString());
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            String decoded = "";
            String decodedString = "";
            while ((decodedString = in.readLine()) != null) {
                System.out.println(decodedString);
                decoded += decodedString;
            }

            System.out.println(decoded);
            in.close();

        } catch (IOException ex) {
            Logger.getLogger(TestXMLMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.disconnect();

    }

}
