package com.airline.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Pilot
 *
 */
// @NamedQuery allows us to run a database query on the pilot entity, to ask the database for information
@NamedQuery(name="Pilot.findById", query="SELECT p FROM Pilot p WHERE p.id = :id")
@Entity

public class Pilot implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Pilot() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private Integer pilotLicenseNum;
	
	@Enumerated(EnumType.STRING)
	private PilotRank pilotRank;
	
	// flight for this current pilot
	// many different pilots to one flight
	@ManyToOne
	// means in the pilot table there will be a flight_fk column that ties pilots to their flights
	@JoinColumn(name = "flight_fk")
	private Flight flightForPilot;
	
	
		
		
		// list of flight objects that the pilot has
		@ManyToMany
		
		// this annotation creates a table "f_p_join" in background of flight foreign keys and passenger foreign keys to keep track of who's on what
		
//		table f_p_join
	//	
//		flight_fk,    pilot_fk
//		3				210
//		3				211
//		3				212
//		3				213
//		4				211
//		4				212
//		5				211
//		6				212
		@JoinTable(name = "flight_pilot_join", joinColumns = @JoinColumn(name = "flight_fk"), inverseJoinColumns = @JoinColumn(name = "pilot_fk"))
		
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

	public Integer getPilotLicenseNum() {
		return pilotLicenseNum;
	}

	public void setPilotLicenseNum(Integer pilotLicenseNum) {
		this.pilotLicenseNum = pilotLicenseNum;
	}

	public PilotRank getPilotRank() {
		return pilotRank;
	}

	public void setPilotRank(PilotRank pilotRank) {
		this.pilotRank = pilotRank;
	}

	public Flight getFlightForPilot() {
		return flightForPilot;
	}

	public void setFlightForPilot(Flight flightForPilot) {
		this.flightForPilot = flightForPilot;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	@Override
	public String toString() {
		return "Pilot [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", pilotLicenseNum="
				+ pilotLicenseNum + ", pilotRank=" + pilotRank + ", flightForPilot=" + flightForPilot + "]";
	}
	
	
   
}
