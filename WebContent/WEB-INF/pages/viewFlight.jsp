<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<p><h3>Flight information</h3></p>

<div class="card">
	<div class="card-body">
		<dl class="row">
			<dt class="col-sm-3">Flight ID</dt>
			<dd class="col-sm-9">${flight.id}</dd>
		
			<dt class="col-sm-3">Departure city</dt>
			<dd class="col-sm-9">${flight.depCity}</dd>
		
			<dt class="col-sm-3">Arrival city</dt>
			<dd class="col-sm-9">${flight.arrCity}</dd>
		
			<dt class="col-sm-3">Departure time</dt>
			<dd class="col-sm-9">${flight.depTime}</dd>
			
			<dt class="col-sm-3">Arrival time</dt>
			<dd class="col-sm-9">${flight.arrTime}</dd>
		
			<dt class="col-sm-3">Plane</dt>
			<dd class="col-sm-9">${flight.plane}</dd>
		</dl>
		
		<a class="btn btn-primary btn-md" href="../../product/flights" role="button">&lt; back to flights</a>
		<a class="btn btn-primary btn-md" href="../update/${flight.id}" role="button">Edit</a>
		<a class="btn btn-primary btn-md" href="../delete/${flight.id}" role="button">Delete</a>
	</div>
</div>