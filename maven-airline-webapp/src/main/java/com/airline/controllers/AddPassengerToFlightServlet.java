package com.airline.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.service.FlightServiceBean;

/**
 * Servlet implementation class AddPassengerToFlightServlet
 */
@WebServlet("/AddPassengerToFlightServlet")
public class AddPassengerToFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	FlightServiceBean fsb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPassengerToFlightServlet() {
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
		
		
		String fid = request.getParameter("fid");
		String pid = request.getParameter("pid");
		
		fsb.addPassengerToFlight(pid, fid);
		
		
		
		
		
		response.sendRedirect("FlightsServlet");
		
		
		
	}

}
