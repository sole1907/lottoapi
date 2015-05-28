/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbc.arsenallotto.rest;

import com.bbc.arsenallotto.processor.RequestProcessor;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Soul
 */
@Path("balottows")
@Stateless
@LocalBean
public class BalottowsResource {

    //@Resource
    //UserTransaction utx;
    //@PersistenceUnit(unitName = "BroadbasedArsenalLottoPU")
    //EntityManagerFactory emf;
    @PersistenceContext(unitName = "BroadbasedArsenalLottoPU",
            type = PersistenceContextType.TRANSACTION)
    EntityManager entityManager;
    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;

    /**
     * Creates a new instance of BalottowsResource
     */
    public BalottowsResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.bbc.arsenallotto.rest.BalottowsResource
     *
     * @return an instance of java.lang.String
     */
    /*@GET
     @Produces("application/xml")
     public String getXml() {
     //TODO return proper representation object
     throw new UnsupportedOperationException();
     }*/
    /**
     * PUT method for updating or creating an instance of BalottowsResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    /*@PUT
     @Consumes("application/xml")
     public void putXml(String content) {
     }*/
    /*private EntityManagerFactory getEntityManagerFactory() {
     if (factory == null) {
     factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
     }

     return factory;
     }*/
    /**
     * POST method for updating or creating an instance of BalottowsResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Path("register")
    @Consumes("application/xml")
    @Produces("application/xml")
    public String register(String xml) {
        RequestProcessor requestProcessor = new RequestProcessor(entityManager, request);
        requestProcessor.initialize();

        String result = requestProcessor.register(xml);
        Response response = Response.status(200).type(MediaType.TEXT_XML).entity(result).build();       
        return result;
    }

    /**
     * POST method for updating or creating an instance of BalottowsResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Path("play")
    @Consumes("application/xml")
    @Produces("application/xml")
    public Response play(String xml) {
        RequestProcessor requestProcessor = new RequestProcessor(entityManager, request);
        requestProcessor.initialize();

        String result = requestProcessor.play(xml);
        Response response = Response.status(200).type(MediaType.TEXT_XML).entity(result).build();
        return response;
    }

    /**
     * POST method for updating or creating an instance of BalottowsResource
     *
     * @param gameId
     * @param drawDate
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @GET
    @Path("notify/{gameid}/{drawdate}")
    @Produces("text/plain")
    public Response notify(@PathParam("gameid") String gameId, @PathParam("drawdate") String drawDate) {
        RequestProcessor requestProcessor = new RequestProcessor(entityManager, request);

        String result = requestProcessor.notify(gameId, drawDate);
        Response response = Response.status(200).type(MediaType.TEXT_PLAIN).entity(result).build();
        return response;
    }
}
