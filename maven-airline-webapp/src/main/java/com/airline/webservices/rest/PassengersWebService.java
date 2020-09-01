package com.airline.webservices.rest;

import java.util.List;
import java.net.URI;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.airline.models.Passenger;
import com.airline.service.PassengerServiceBean;

// JAXRS annotation
@Path("/passengers")
public class PassengersWebService {
	
	// URL path to all web services in this class ->   */airlineservices/rest/passengers
	
	
	// Injections
	
	@PersistenceContext(unitName = "airline") // allows us to create an entity manager to manage the datasource, through JPA to access the database
	EntityManager em;
	
	@EJB
	PassengerServiceBean psb;
	
	// this is a JAX-RS specific injection - this is used when we create new flights to connect outside sites with our application / flight info
				// example, it will provide a URL to flight information accessible by users outside our application
	@Context 
	UriInfo pUriInfo;
	
	
	public PassengersWebService() {
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML) // to return passengers as XML we must add @XMLRootElement to Passenger class
	public List<Passenger> getPassengers() {
		
		List<Passenger> pList = psb.getPassengers();
		
		
		return pList;
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("{passenger_id}")
	public Passenger getPassenger(@PathParam("passenger_id") Integer passengerId) {
		
		Passenger p = psb.getPassenger(passengerId);
		
		if(p == null) {
			throw new NotFoundException("The passenger with an id of " + passengerId + " was not found");
		}
		
		return p;
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPassenger(Passenger p) {
		
		p = psb.addPassenger(p);
		
		UriBuilder pUriBuilder = pUriInfo.getAbsolutePathBuilder();
		
		URI pUri = pUriBuilder.path( String.valueOf( p.getId() ) ).build();
		
		return Response.created(pUri).build();
		
	}
	
	@PUT
	@Path("/edit/{pId}")
	@Consumes("application/json")
	public Response updatePassenger(@PathParam("pId") Integer passengerId, Passenger pUpdated) {
		
		pUpdated = psb.updatePassenger(passengerId, pUpdated);
		
		if(pUpdated == null) {
			throw new NotFoundException("The passenger with an id of " + passengerId + " was not found.");
		}
		
		return Response.ok(pUpdated).build();
		
	}
	
	@PUT
	@Path("/edit2/{pId}")
	@Consumes("application/json")
	public Response updatePassenger2(@PathParam("pId") Integer passengerId, Passenger pUpdated) {
		
		pUpdated = psb.updatePassenger2(passengerId, pUpdated);
		
		if(pUpdated == null) {
			throw new NotFoundException("The passenger with an id of " + passengerId + " was not found.");
		}
		
		return Response.ok(pUpdated).build();
		
	}
	

}
