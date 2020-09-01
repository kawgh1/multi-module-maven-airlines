package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.airline.models.Airplane;

/**
 * Session Bean implementation class AirplaneServiceBean
 */
@Stateless
// @LocalBean replaces the need for this Bean to implement a local INTERFACE
@LocalBean
public class AirplaneServiceBean {

	/**
	 * Default constructor.
	 */
	public AirplaneServiceBean() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext(unitName = "airline")
	EntityManager em;

	
	
	public List<Airplane> getAirplanes() {
		
		// this query grabs everything from the Flight table reference and returns each row as a Flight object
		TypedQuery<Airplane> query = em.createQuery("SELECT a FROM Airplane a", Airplane.class);
		
		List<Airplane> results = query.getResultList();
		
		return results;
		
		
	}
	
		

}
