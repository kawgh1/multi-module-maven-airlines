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

import com.airline.models.Flight;
import com.airline.models.Passenger;

/**
 * Session Bean implementation class PassengerService
 */
@Stateless
@LocalBean
public class PassengerServiceBean {

    /**
     * Default constructor. 
     */
    public PassengerServiceBean() {
        // TODO Auto-generated constructor stub
    }
    
    // Inject thru Context Dependency Injection the Persistence Unit in persistence.xml --> name="airline"
    // airline persistance unit
    
    @PersistenceContext(unitName="airline")
    private EntityManager em; // allows us to communicate with the DB thru its methods
    
    public Passenger addPassenger(Passenger passenger) {
    	
    	em.persist(passenger);
    	
    	return passenger;
    }
    
    public List<Passenger> getPassengers() {
    	
    	TypedQuery<Passenger> query = em.createQuery("SELECT p FROM Passenger p", Passenger.class);
    	
    	List<Passenger> pList = query.getResultList();
    	
    	return pList;
    }
    
    public void addFlightTicketToPassenger(String flightId, String passengerId) {
    	
    	// SEE FlightServiceBean/AddPassengerToFlight() method for more details on Criteria Builder
    	
    	// here we are starting with a passenger and wanting to add a flight to them
    	
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
			
			
			// Associate the flight with the passenger
			
			List<Flight> fList = passenger.getFlights();
			
			fList.add(flight);
			
			passenger.setFlights(fList);
			
			flight.getPassengers().add(passenger);
	
    }
    
public Passenger getPassenger(Integer passengerId) {
		
		// Getting the passenger by id
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		
		CriteriaQuery<Passenger> cqPassenger = builder.createQuery(Passenger.class);
		
		Root<Passenger> pRoot = cqPassenger.from(Passenger.class);
		
		cqPassenger.select(pRoot).where(builder.equal(pRoot.get("id").as(Integer.class), passengerId));
		
		TypedQuery<Passenger> pQuery = em.createQuery(cqPassenger);
		
		Passenger p = null;
		
		try {
			p = pQuery.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
		
		return p;
		
	}
	
	public Passenger updatePassenger(Integer passengerId, Passenger pUpdated) {
		
		Passenger p = em.find(Passenger.class, passengerId);
		
		if(p == null) {
			return null;
		}
		
		if(pUpdated.getFirstName() != null) {
			p.setFirstName( pUpdated.getFirstName() );
		}
		
		if(pUpdated.getLastName() != null) {
			p.setLastName( pUpdated.getLastName() );
		}
		
		if(pUpdated.getDob() != null) {
			p.setDob( pUpdated.getDob() );
		}
		
		if(pUpdated.getGender() != null) {
			p.setGender( pUpdated.getGender() );
		}
		
		return p;
		
	}
	
	public Passenger updatePassenger2(Integer passengerId, Passenger pUpdated) {
		
		pUpdated.setId(passengerId);
		
		Passenger pCheckExist = em.find(Passenger.class, passengerId);
		
		if(pCheckExist == null) {
			return null;
		}
		
		em.merge(pUpdated);
		
		return pUpdated;
		
	}

}
