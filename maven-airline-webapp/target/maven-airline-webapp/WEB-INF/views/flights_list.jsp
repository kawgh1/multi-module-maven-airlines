<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, com.airline.models.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="resources/css/jpaStyles.css" />
<title>Flights List</title>
</head>
<body>
	<h1>List of Flights</h1>
	
	<form method="GET" action="FancyFormsServlet">
	
		<button type="submit"> Back </button>
	</form>
	
	

	<table>
		<tr>
			<th>ID</th>
			<th>From</th>
			<th>To</th>
			<th>Time</th>
			<th>Price</th>
			<th>Airplane</th>
			<th>Seating</th>
			<th>Number of Pilots</th>
			<th>Pilot Names</th>



		</tr>

		<%
		
			List<Flight> fList = (List<Flight>) request.getAttribute("flights_list");
			
			for(Integer i = 0; i < fList.size(); i++) {
			
			
		
		 %>

		<tr>
			<td><%= fList.get(i).getId() %></td>
			<td><%= fList.get(i).getFlightOrigin() %></td>
			<td><%= fList.get(i).getFlightDestination() %></td>
			<td><%= fList.get(i).getFlightTime() %></td>
			<td><%= fList.get(i).getPrice() %></td>


			<td><%= fList.get(i).getAirplaneDetail().getPlaneMake() + " " + fList.get(i).getAirplaneDetail().getModelName() %></td>
			<td><%= fList.get(i).getAirplaneDetail().getSeatingCapacity() %></td>


			<td>
				<%		 					
		 			if(fList.get(i).getPilots() != null) {
		 						
		 		 %> <%= fList.get(i).getPilots().size() %> pilots <%
 	
 					}else{
 
 				%> No pilots yet <% } %>

			</td>

			<td>
				<%
					if(fList.get(i).getPilots()!=null){
					
					List<Pilot> pList = (List<Pilot>) fList.get(i).getPilots();
					
					for (Integer j = 0; j < pList.size(); j++) {
					
					%>
					
					<%= (j+1) + ") " + pList.get(j).getFirstName() + " " + pList.get(j).getLastName()
					
					+ " (" +  pList.get(j).getPilotRank() + ")" + "<br/>"%>
					
					<%
					} // for
					
					} //if
					 %>
			

		
			</td>


		</tr>
		
		<tr>
			<td colspan="9">
				
				<%
					if(fList.get(i).getPassengers().size() > 0) {
					
					
						List<Passenger> passengerList = (List<Passenger>) fList.get(i).getPassengers();
						
						for(Integer k = 0; k < passengerList.size(); k++) {
					
				 %>
				 
				 		<%= k+1  %> ) <%= (String) passengerList.get(k).getFirstName() %> <%= (String) passengerList.get(k).getLastName() %> <br/>
				 		
				 		<%
				 		
				 			} // for
				 			
				 			} else {
				 		
				 		 %>
				 		 No passengers on this flight yet.
				 		 <%
				 		 
				 		 } //else
				 		  %>
			
			</td>
		</tr>

		<%
			}
		%>

	</table>
	
	

		<form method="GET" action="FancyFormsServlet">
	
		<button type="submit"> Back </button>
	</form>

	

</body>
</html>