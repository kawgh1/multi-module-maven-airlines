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
 * Servlet implementation class AddPilotServlet
 */
@WebServlet("/AddPilotServlet")
public class AddPilotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// here we inject the PilotServiceBean into the servlet 
	@EJB
	PilotServiceBean psb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPilotServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		Pilot pilot = new Pilot();
		
		pilot.setFirstName("Griselda");
		pilot.setLastName("Cavendish");
		pilot.setPilotRank(PilotRank.Captain);
		pilot.setPilotLicenseNum(178245);
		
		psb.addPilot(pilot);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
