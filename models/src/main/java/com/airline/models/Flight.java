package com.airline.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Flight
 *
 */
@Entity
@NamedQuery(name="Flight.findById", query="SELECT f FROM Flight f WHERE f.id = :id")
public class Flight implements Serializable {

	// serialVersionUID is not part of the JPA entity
	private static final long serialVersionUID = 1L;

	public Flight() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private FlightDestinations flightOrigin;

	@Enumerated(EnumType.STRING)
	private FlightDestinations flightDestination;

	private Integer price;

	@Temporal(TemporalType.TIMESTAMP)
	private Date flightTime;

		// Using cascade PERSIST and REMOVE, allows us to  get Airplane details (from AddFlightServlet) without persisting the Airplane object
	@OneToOne( cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	// JoinColumn means create this column of airplane's ID as the Foreign Key of
	// the Flight
	// Thus each Flight will reference a unique airplane object by airplane ID
	@JoinColumn(name = "airplane_fk")
	private Airplane airplaneDetail;
	
	// Pilot list - one flight to many pilots
	@OneToMany(mappedBy = "flightForPilot")
	private List<Pilot> pilots;
	
	
	// list of passenger objects that the flight has
	@ManyToMany
	
	// this annotation creates a table "f_p_join" in background of flight foreign keys and passenger foreign keys to keep track of who's on what
	
//	table f_p_join
//	
//	flight_fk,    passenger_fk
//	3				210
//	3				211
//	3				212
//	3				213
//	4				211
//	4				212
//	5				211
//	6				212
	@JoinTable(name = "flight_passenger_join", joinColumns = @JoinColumn(name = "flight_fk"), inverseJoinColumns = @JoinColumn(name = "passenger_fk"))
	
	private List<Passenger> passengers;
	
	

	public List<Pilot> getPilots() {
		return pilots;
	}

	public void setPilots(List<Pilot> pilots) {
		this.pilots = pilots;
	}

	public Airplane getAirplaneDetail() {
		return airplaneDetail;
	}

	public void setAirplaneDetail(Airplane airplaneDetail) {
		this.airplaneDetail = airplaneDetail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FlightDestinations getFlightOrigin() {
		return flightOrigin;
	}

	public void setFlightOrigin(FlightDestinations flightOrigin) {
		this.flightOrigin = flightOrigin;
	}

	public FlightDestinations getFlightDestination() {
		return flightDestination;
	}

	public void setFlightDestination(FlightDestinations flightDestination) {
		this.flightDestination = flightDestination;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(Date flightTime) {
		this.flightTime = flightTime;
	}
	
	

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", flightOrigin=" + flightOrigin + ", flightDestination=" + flightDestination
				+ ", price=" + price + ", flightTime=" + flightTime + ", airplaneDetail=" + airplaneDetail + ", pilots="
				+ pilots + ", passengers=" + passengers + "]";
	}

}
