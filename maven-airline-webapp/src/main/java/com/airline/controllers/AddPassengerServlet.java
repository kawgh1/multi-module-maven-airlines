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

import com.airline.models.Gender;
import com.airline.models.Passenger;
import com.airline.service.PassengerServiceBean;

/**
 * Servlet implementation class AddPassengerServlet
 */
@WebServlet("/AddPassengerServlet")
public class AddPassengerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Inject PassengerService Bean onto the AddPassengerServlet
	@EJB
	PassengerServiceBean psb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPassengerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// read form data and make new passenger object in db
		String fName = request.getParameter("first_name");
		String lName = request.getParameter("last_name");
		String dob_raw = request.getParameter("dob"); // 5/7/1990
		String gender = request.getParameter("gender");
		

		// create the passenger object, and user PassengerServiceBean instance to save the Passenger object onto the Database
	
		Passenger passenger = new Passenger();
		
		passenger.setFirstName(fName);
		passenger.setLastName(lName);
		
		String[] dobArr = dob_raw.split("\\/");
		
		
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.YEAR, Integer.parseInt(dobArr[2]));
		cal.set(Calendar.MONTH, Integer.parseInt(dobArr[0]) - 1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dobArr[1]));
		
		Date dob = cal.getTime();
		
		// Date formatting to remove time
//		DateFormat dateFormat;
//		
//		String newDob = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(dob);
//		
		passenger.setDob(dob);
		
		passenger.setGender(Gender.valueOf(gender));
		
		System.out.println(passenger);
		
		// PassengerServiceBean
		psb.addPassenger(passenger);
		
		response.sendRedirect("PassengersServlet");
		
		
		
		
		
		
		
		
		
		
		
		// HARD CODE EXAMPLE
//				Passenger passenger = new Passenger();
//				
//				passenger.setFirstName("Daniel");
//				passenger.setLastName("Chermetz");
//				
//				Calendar cal = Calendar.getInstance();
//				
//				cal.set(Calendar.YEAR, 1986);
//				cal.set(Calendar.MONTH, 9);
//				cal.set(Calendar.DAY_OF_MONTH, 10);
//				
//				Date dob = cal.getTime();
//				
//				passenger.setDob(dob);
//				
//				passenger.setGender(Gender.Male);
//				
//				passenger.setFlightClass(FlightClass.Coach);
//				
//				System.out.println(passenger);
//				
//				// PassengerServiceBean
//				psb.addPassenger(passenger);
	}

}
