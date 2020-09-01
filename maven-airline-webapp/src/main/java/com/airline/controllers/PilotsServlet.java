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

import com.airline.models.Pilot;
import com.airline.service.PilotServiceBean;

/**
 * Servlet implementation class PilotsServlet
 */
@WebServlet("/PilotsServlet")
public class PilotsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PilotServiceBean psb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PilotsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// casting here not required, but just in case we get funky input from the request
				List<Pilot> pList = (List<Pilot>) psb.getPilots();
				
				request.setAttribute("pilots_list", pList);
				
				// PrintWriter out = response.getWriter();
				
				// out.println("List of the passengers will be displayed here...");
				
				// RequestDispatcher will forward our results to a JSP page for client view
				
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/pilots_list.jsp");
				
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