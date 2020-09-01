package com.airline.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Passenger
 *
 */

// once we set an @Id (primary key), because this is an @Entity, it will create a table "Passenger" in our DB with PK @Id

@Entity
@XmlRootElement // this is for JAX-RS to return passenger objects as XML in PassengersWebService class, JAXB annotation
public class Passenger implements Serializable {

	// @Transient means it will prevent serialVersionUID from displaying in our
	// tables
	@Transient
	private static final long serialVersionUID = 1L;

	public Passenger() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String firstName;

	private String lastName;

	@Temporal(TemporalType.DATE)
	private Date dob;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	private FlightClass flightClass;
	
	// the flight tickets the passenger has, a passenger can have tickets for multiple flights
	// has to be mapped by "passengers" of the ManytoMany property in Flight class
	@ManyToMany(mappedBy = "passengers")
	private List<Flight> flights;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public FlightClass getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(FlightClass flightClass) {
		this.flightClass = flightClass;
	}
	
	

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	@Override
	public String toString() {
		return "Passenger [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", gender=" + gender + ", flightClass=" + flightClass + ", flights=" + flights + "]";
	}
	
	

}
