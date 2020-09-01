package com.airline.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Airplane;
import com.airline.service.AirplaneServiceBean;

/**
 * Servlet implementation class AirplanesServlet
 */
public class AirplanesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	AirplaneServiceBean asb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AirplanesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		System.out.println("got to the AirplanesServlet    GET method");
		
		
		
		// casting here not required, but just in case we get funky input from the request	
		List<Airplane> aList = (List<Airplane>) asb.getAirplanes();
		
		request.setAttribute("airplanes_list", aList);
		
		 
		
		 System.out.println("List of the airplanes will be displayed here...");
		 
		 System.out.println(aList);
		
		// RequestDispatcher will forward our results to a JSP page for client view
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/airplanes_list.jsp");
		
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
