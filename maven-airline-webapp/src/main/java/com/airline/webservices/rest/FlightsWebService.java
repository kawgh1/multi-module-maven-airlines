package com.airline.webservices.rest;

import java.util.List;







import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;

import com.airline.models.Flight;
import com.airline.service.FlightServiceBean;

//JAXRS annotation
@Path("/flights")
@Transactional
public class FlightsWebService {
	
	// URL path to all web services in this class ->   */airlineservices/rest/flights
	
	
		// Injections

	@PersistenceContext(unitName = "airline") // allows us to create an entity manager to manage the datasource, through JPA to access the database
	EntityManager em;
	
	@EJB
	FlightServiceBean fsb;
	
	
	// this is a JAX-RS specific injection - this is used when we create new flights to connect outside sites with our application / flight info
	// example, it will provide a URL to flight information accessible by users outside our application
	@Context
	UriInfo fUriInfo;
	
	public FlightsWebService() {
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> getFlights() {
		
		List<Flight> fList = fsb.getFlights();
		
		return fList;
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{flight_id}")
	public Flight getFlight(@PathParam("flight_id") Integer flightId) {
		
		Flight f = fsb.getFlight(flightId);
		
		if(f == null) {
			throw new NotFoundException("The flight with an id of " + flightId + " was not found");
		}
		
		return f;
		
	}
	
	@DELETE
	@Path("{flight_id}")
	public Response deleteFlight(@PathParam("flight_id") Integer flightId) {
		
		Flight flightToRemove = em.find(Flight.class, flightId);
		
		if(flightToRemove == null) {
			throw new NotFoundException("The flight with an id of " + flightId + " was not found.");
		}
		
		em.remove(flightToRemove);
		
		return Response.noContent().build();
		
		
	}
	
}
