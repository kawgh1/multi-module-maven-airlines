package com.airline.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Airplane;
import com.airline.models.Flight;
import com.airline.models.FlightDestinations;
import com.airline.service.FlightServiceBean;

/**
 * Servlet implementation class AddFlight
 */
@WebServlet("/AddFlightServlet")
public class AddFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	@EJB
	FlightServiceBean fsb;

	public AddFlightServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Flight values from form (fancy_forms)

		Flight flight = new Flight();
		
		String from_destination = request.getParameter("from_destination");
		
		flight.setFlightOrigin(FlightDestinations.valueOf(from_destination));
		
		String to_destination = request.getParameter("to_destination");

		flight.setFlightDestination(FlightDestinations.valueOf(to_destination));
		
		String price = request.getParameter("price");

		flight.setPrice(Integer.parseInt(price));
		
		// Calendar from form (fancy_forms)
		
		Integer year = Integer.parseInt(request.getParameter("year"));
		Integer month = Integer.parseInt(request.getParameter("month"));
		Integer day = Integer.parseInt(request.getParameter("day"));
		Integer hour = Integer.parseInt(request.getParameter("hour"));
		Integer minute = Integer.parseInt(request.getParameter("minute"));
		
		
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		
		Date flightTime = cal.getTime();

		System.out.println(flightTime);

		flight.setFlightTime(flightTime);

		// Airplane details from form

		Airplane airplane = new Airplane();
		
		String planeMake = request.getParameter("airplane_make");
		String planeModelName = request.getParameter("airplane_model");
		Integer seating = Integer.parseInt(request.getParameter("airplane_seating"));

		airplane.setModelName(planeModelName);
		airplane.setPlaneMake(planeMake);
		airplane.setSeatingCapacity(seating);

		// connect the flight above to this airplane
		flight.setAirplaneDetail(airplane);

		System.out.println(flight);
		System.out.println(airplane);

		// Save our flight and airplane objects to the Database!
		fsb.addFlight(flight, airplane);
		
		// Redirect after form submitted
		
		response.sendRedirect("FlightsServlet");
		
		
		
//   HARD CODE EXAMPLE
//
//		flight.setFlightOrigin(FlightDestinations.valueOf(from_destination));
//
//		flight.setFlightOrigin(FlightDestinations.Los_Angeles);
//
//		flight.setFlightDestination(FlightDestinations.London);
//
//		flight.setPrice(500);

//		Calendar cal = Calendar.getInstance();
//
//		cal.set(Calendar.YEAR, 2020);
//		cal.set(Calendar.MONTH, 10);
//		cal.set(Calendar.DAY_OF_MONTH, 4);
//		cal.set(Calendar.HOUR_OF_DAY, 19);
//		cal.set(Calendar.MINUTE, 10);
//
//		Date flightTime = cal.getTime();
//
//		System.out.println(flightTime);
//
//		flight.setFlightTime(flightTime);
//
//		// Airplane
//
//		Airplane airplane = new Airplane();
//
//		airplane.setModelName("787");
//		airplane.setPlaneMake("Boeing");
//		airplane.setSeatingCapacity(250);
//
//		// connect the flight above to this airplane
//		flight.setAirplaneDetail(airplane);
//
//		System.out.println(flight);
//		System.out.println(airplane);
//
//		// Save our flight and airplane objects to the Database!
//		fsb.addFlight(flight, airplane);
	}

}
