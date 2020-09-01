package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.airline.models.Flight;
import com.airline.models.Pilot;

/**
 * Session Bean implementation class PilotServiceBean
 */
@Stateless
// Using LocalBean annotation is easier than implementing a LOCAL INTERFACE,
	// but it does the same thing
@LocalBean
public class PilotServiceBean {
	
	// this references our persistence unit airline in persistence.xml
	@PersistenceContext(unitName = "airline")
	EntityManager em;

    /**
     * Default constructor. 
     */
    public PilotServiceBean() {
        // TODO Auto-generated constructor stub
    }
    
    public void addPilot(Pilot pilot) {
    	
    	em.persist(pilot);
    }
    
    public void addNewPilotToFlight(Pilot pilot, String flightId) {
    	
    	// a new pilot has been created when this method is called, 
    	// we need to persist that pilot to the database
    	em.persist(pilot);
    	
    	// NamedQuery from Flight class to locate a Flight in the DB by its ID
    			TypedQuery<Flight> fQuery = em.createNamedQuery("Flight.findById", Flight.class);

    			fQuery.setParameter("id", Integer.parseInt(flightId));

    			Flight flight = fQuery.getSingleResult();

    			

    			// add to List of Pilots
    			List<Pilot> pList = flight.getPilots();

    			pList.add(pilot);

    			// update Flight list of Pilots

    			flight.setPilots(pList);

    			// update Pilot to show their flight

    			pilot.setFlightForPilot(flight);

    	
    }
    
public List<Pilot> getPilots() {
    	
    	TypedQuery<Pilot> query = em.createQuery("SELECT p FROM Pilot p", Pilot.class);
    	
    	List<Pilot> pList = query.getResultList();
    	
    	return pList;
    }
    
    

}
