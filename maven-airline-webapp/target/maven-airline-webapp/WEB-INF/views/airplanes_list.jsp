<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, com.airline.models.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="resources/css/jpaStyles.css" />
<title>Passengers List</title>
</head>
<body>
	<h1>List of Airplanes</h1>
	
	<form method="GET" action="FancyFormsServlet">
	
		<button type="submit"> Back </button>
	</form>

	<table>
		<tr>
			<th>ID</th>
			<th>Plane Make</th>
			<th>Plane Model</th>
			<th>Seating Capacity</th>
			



		</tr>

		<%
		
			List<Airplane> aList = (List<Airplane>) request.getAttribute("airplanes_list");
			
			for(Integer i = 0; i < aList.size(); i++) {
			
			
		
		 %>

		<tr>
			<td><%= aList.get(i).getId() %></td>
			<td><%= aList.get(i).getPlaneMake() %></td>
			<td><%= aList.get(i).getModelName() %></td>
			<td><%= aList.get(i).getSeatingCapacity() %></td>

		</tr>
		<!--  
		<tr>
			<td colspan="5"> 
			
				<%
					if(aList.get(i).getFlight() != null) {
					
						List<Flight> flightList = (List<Flight>) aList.get(i).getFlight(); 
								
						for(Integer k = 0; k < flightList.size(); k++) {
					
				 %>
						
						 <%= k+1 %> <%= flightList.get(k).getFlightOrigin() %> to <%= flightList.get(k).getFlightDestination() %> @ <%= flightList.get(k).getFlightTime() %> <br/>
				 			
				 	<% 
				 	
				 	} // for	
				 		
				 	} else {
				 	
				 	%> No flights for this plane yet.
				 	
				 	<% } %>

			
			</td>
		
		</tr>
		-->

		<%
			}
		%>

	</table>
	
	
	

		<hr />


		<form method="GET" action="FancyFormsServlet">
	
		<button type="submit"> Back </button>
	</form>


</body>
</html>