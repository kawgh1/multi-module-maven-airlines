package com.airline.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Pilot;
import com.airline.models.PilotRank;
import com.airline.service.PilotServiceBean;

/**
 * Servlet implementation class CreatePilotAndAddToFlightServlet
 */
@WebServlet("/CreatePilotAndAddToFlightServlet")
public class CreatePilotAndAddToFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PilotServiceBean psb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePilotAndAddToFlightServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String fName = request.getParameter("first_name");
		String lName = request.getParameter("last_name");
		Integer license = Integer.parseInt(request.getParameter("license"));
		String rank = request.getParameter("pilot_rank");
		
		
		String flightId = request.getParameter("fid");
		
		// Pilot
		
		Pilot pilot = new Pilot();
		
		pilot.setFirstName(fName);
		pilot.setLastName(lName);
		pilot.setPilotLicenseNum(license);
		pilot.setPilotRank(PilotRank.valueOf(rank));
		
		// Add Pilot to Flight
		psb.addNewPilotToFlight(pilot, flightId); // method from PilotServiceBean.java
		
		
		
		
		
		response.sendRedirect("FlightsServlet");
		
		
	}

}
