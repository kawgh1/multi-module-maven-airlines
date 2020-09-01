package com.airline.controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Passenger;
import com.airline.service.PassengerServiceBean;

/**
 * Servlet implementation class PassengersServlet
 */
@WebServlet("/PassengersServlet")
public class PassengersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PassengerServiceBean psb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassengersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// casting here not required, but just in case we get funky input from the request
				List<Passenger> pList = (List<Passenger>) psb.getPassengers();
				
				request.setAttribute("passengers_list", pList);
				
				// PrintWriter out = response.getWriter();
				
				// out.println("List of the passengers will be displayed here...");
				
				// RequestDispatcher will forward our results to a JSP page for client view
				
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/passengers_list.jsp");
				
				view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
