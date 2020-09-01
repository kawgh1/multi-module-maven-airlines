package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.airline.models.Airplane;
import com.airline.models.Flight;
import com.airline.models.Passenger;
import com.airline.models.Pilot;

/**
 * Session Bean implementation class FlightServiceBean
 */
@Stateless
// @LocalBean replaces the need for this Bean to implement a local INTERFACE
@LocalBean
public class FlightServiceBean {

	/**
	 * Default constructor.
	 */
	public FlightServiceBean() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext(unitName = "airline")
	EntityManager em;

	public void addFlight(Flight flight, Airplane airplane) {

		em.persist(flight);
		// em.persist(airplane); -- propagated and cascaded from Flight class and saved automatically

	}

	public void addPilotToFlight(String pilotId, String flightId) {

		// NamedQuery from Flight class to locate a Flight in the DB by its ID
		TypedQuery<Flight> fQuery = em.createNamedQuery("Flight.findById", Flight.class);

		fQuery.setParameter("id", Integer.parseInt(pilotId));

		Flight flight = fQuery.getSingleResult();

		// Pilot Query to get a Pilot in the DB by their ID
		TypedQuery<Pilot> pQuery = em.createNamedQuery("Pilot.findById", Pilot.class);

		pQuery.setParameter("id", Integer.parseInt(pilotId));

		Pilot pilot = pQuery.getSingleResult();

		// add to List of Pilots
		List<Pilot> pList = flight.getPilots();

		pList.add(pilot);

		// update Flight list of Pilots

		flight.setPilots(pList);

		// update Pilot to show their flight

		pilot.setFlightForPilot(flight);

	}
	
	public List<Flight> getFlights() {
		
		// this query grabs everything from the Flight table reference and returns each row as a Flight object
		TypedQuery<Flight> query = em.createQuery("SELECT f FROM Flight f", Flight.class);
		
		List<Flight> results = query.getResultList();
		
		return results;
		
		
	}
	
	public void addPassengerToFlight(String passengerId, String flightId) {
		
		// since passenger object is already in the database when this is called,
		// we need to query to database to return the passenger object/details
		
		// Get passenger by ID - querying by criteria
		// we could have done typed query, this is just a different way
		
		// ONE OF THE REASONS DOING AN SQL QUERY LIKE THIS IS BECAUSE JAVA HAS A LOT MORE CONTROL
		// OVER THE QUERY, as these are mostly Java objects
		
		// WHEN A RAW SQL STRING IS PASSED TO A JAVA METHOD, IT HAS NOT WAY TO VERIFY
		// THE VALIDITY/SECURITY OF THAT SQL STRING
		
		// IN SUM, CRITERIA BUILDER IS CONSIDERED MORE QUERY-ERROR SAFE
		
		// the below code will not even compile if there's an error, but a SQL statement is not validated til runtime
		
		
		// get passenger by id
		CriteriaBuilder builder = em.getCriteriaBuilder();
		
		CriteriaQuery<Passenger> cqPassenger = builder.createQuery(Passenger.class);
		
		Root<Passenger> pRoot = cqPassenger.from(Passenger.class);
		
		cqPassenger.select(pRoot).where(builder.equal(pRoot.get("id").as(Integer.class), passengerId));
		
		TypedQuery<Passenger> pQuery = em.createQuery(cqPassenger);
		
		Passenger passenger = pQuery.getSingleResult();
		
		
		// get flight by id
		CriteriaBuilder builder2 = em.getCriteriaBuilder();
		
		CriteriaQuery<Flight> cqFlight = builder2.createQuery(Flight.class);
		
		Root<Flight> fRoot = cqFlight.from(Flight.class);
		
		cqFlight.select(fRoot).where(builder2.equal(fRoot.get("id").as(Integer.class), flightId));
		
		TypedQuery<Flight> fQuery = em.createQuery(cqFlight);
		
		Flight flight = fQuery.getSingleResult();
		
		
		// Associate the passenger with the flight
		
		List<Passenger> pList = flight.getPassengers();
		
		pList.add(passenger);
		
		flight.setPassengers(pList);
		
		passenger.getFlights().add(flight);
		
	}

	public Flight getFlight(Integer flightId) {
		
		TypedQuery<Flight> fQuery = em.createNamedQuery("Flight.findById",
				Flight.class);

		fQuery.setParameter("id", flightId);

		Flight f = null;
		
		try {
			f = fQuery.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
		
		return f;
	}

}
