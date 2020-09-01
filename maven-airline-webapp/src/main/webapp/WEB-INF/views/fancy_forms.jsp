<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>

<html>

<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="resources/css/jpaStyles.css" />

<style>
body {
	font-family: arial, sans-serif;
	font-size: 18px;
}
</style>

</head>

<body>

	<h1 style="text-decoration: underline;">Maven Airlines</h1>

	<h1>Add Flight and Airplane</h1>

	<form method="POST" action="AddFlightServlet">

		From: <select name="from_destination">
			<option value="San_Francisco">San Francisco</option>
			<option value="Los_Angeles">Los Angeles</option>
			<option value="New_York">New York</option>
			<option value="London">London</option>
			<option value="Paris">Paris</option>
			<option value="Rome">Rome</option>
			<option value="Amsterdam">Amsterdam</option>
			<option value="Dallas">Dallas</option>
			<option value="Taipei">Taipei</option>
		</select> <br />
		<br /> To: <select name="to_destination">
			<option value="San_Francisco">San Francisco</option>
			<option value="Los_Angeles">Los Angeles</option>
			<option value="New_York">New York</option>
			<option value="London">London</option>
			<option value="Paris">Paris</option>
			<option value="Rome">Rome</option>
			<option value="Amsterdam">Amsterdam</option>
			<option value="Dallas">Dallas</option>
			<option value="Taipei">Taipei</option>
		</select>

		<h4>Time of Flight:</h4>



		Year: <select name="year">
			<option value="2020">2020</option>
			<option value="2021">2021</option>
			<option value="2022">2022</option>
			<option value="2023">2023</option>
			<option value="2024">2024</option>
			<option value="2025">2025</option>
		</select> <br />
		<br /> Month: <select name="month">
			<option value="0">January</option>
			<option value="1">February</option>
			<option value="2">March</option>
			<option value="3">April</option>
			<option value="4">May</option>
			<option value="5">June</option>
			<option value="6">July</option>
			<option value="7">August</option>
			<option value="8">September</option>
			<option value="9">October</option>
			<option value="10">November</option>
			<option value="11">December</option>
		</select> <br />
		<br /> Day of Month: <input name="day" type="text" required></input> <br />
		<br /> Hour of Day (0 - 23): <input name="hour" type="text" required></input>

		<br />
		<br /> Minute (0 - 59): <input name="minute" type="text" required></input> <br />
		<br /> Price: <input name="price" type="text" required></input> <br />

		<hr style="width: 65%;" />

		<h4>Airplane:</h4>

		Plane Make: <input name="airplane_make" type="text" required></input> <br />
		<br /> Plane Model: <input name="airplane_model" type="text" required></input>

		<br />
		<br /> Seating: <input name="airplane_seating" type="text" required></input> <br />
		<br />

		<hr style="width: 35%;" />



		<button type="submit">Add Flight and Airplane</button>




	</form>

	
	<form method="GET" action="AirplanesServlet">
		<button type="submit">View Airplanes</button>

	</form>
	
	<form method="GET" action="FlightsServlet">
		<button type="submit">View Flights</button>

	</form>

	<hr style="width: 35%;" />

	<br />

	<h1>Add Pilot to Flight</h1>

	<form method="POST" action="CreatePilotAndAddToFlightServlet">



		First Name: <input name="first_name" type="text" required></input> <br />
		<br /> Last Name: <input name="last_name" type="text" required></input> <br />
		<br /> License: <input name="license" type="text" required></input> <br />
		<br /> Pilot Rank: <select name="pilot_rank">
			<option value="Captain">Captain</option>
			<option value="First_Officer">First Officer</option>
			<option value="Junior_Officer">Junior Officer</option>
		</select> <br />
		<br /> Flight ID: <input name="fid" type="text" required></input> <br />
		<br />

		<hr style="width: 28%;" />
		
		<button type="submit">Add Pilot to Flight</button>


		

	</form>
	
	<form method="GET" action="PilotsServlet">
		<button type="submit">View Pilots</button>

	</form>
	<hr style="width: 28%;" />

	<br />

	<h1>Add Passenger</h1>

	<form method="POST" action="AddPassengerServlet">

		First Name: <input name="first_name" type="text" required></input> <br />
		<br /> Last Name: <input name="last_name" type="text" required></input> <br />
		<br /> Date of Birth: <input name="dob" type="text"
			placeholder="MM/DD/YYYY" required></input> <br />
		<br /> Gender: <select name="gender">
			<option value="Female">Female</option>
			<option value="Male">Male</option>
		</select> <br /> <br />

		<hr style="width: 26%;" />
		
		<button type="submit">Add Passenger</button>

		

	</form>
	<form method="GET" action="PassengersServlet">
	
		<button type="submit">View Passengers</button>
	</form>
	<hr style="width: 26%;" />

	<br />

	<h1>Add Passenger to Flight</h1>

	<form method="POST" action="AddPassengerToFlightServlet">

		Add passenger with ID# <input name="pid" type="text" required></input> <br /> <br />

		To flight with ID# <input name="fid" type="text" required></input> <br />
		<br />

		<hr style="width: 35%;" />

		<button type="submit">Add Passenger to Flight</button>

		<hr style="width: 35%;" />


	</form>

	<br />

	<h1>Add Flight to Passenger</h1>

	<form method="post" action="AddFlightTicketToPassengerServlet">

		Add a ticket for a flight with ID# <input name="fid" type="text" required></input>
		<br /> <br /> To passenger with ID# <input name="pid" type="text" required></input>

		<br /> <br />


		<hr style="width: 35%;" />

		<button type="submit">Add Flight to Passenger</button>

		<hr style="width: 35%;" />


	</form>


	<br />
	<br />

</body>

</html>