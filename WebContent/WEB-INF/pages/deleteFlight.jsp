<script type="text/javascript">
function getFlights()
{
  $.ajax({
    type: "GET",
    url: "/FlightManager/service1/flights",
    dataType: "json",
    success: function(data)
	{
      var link = 
      // Display Orders in the jQuery Data Table
      $('#flights').dataTable({
        "data": data,
        "columns": [
        	{ "data": "depCity" },
        	{ "data": "depTime" },
        	{ "data": "arrCity" },
        	{ "data": "arrTime" },
        	{ "data": "plane" },
        	{ "data": "id", "render": function(data, type, full, meta) { // render allows the data in the table to change in each row
        		return '<a href="/FlightManager/product/delete/' + data + '">Update</a>';
        	} }
        ]
      });
    },
    error: function (xhr, ajaxOptions, thrownError) 
    {
	  alert(xhr.status);
	  alert(thrownError);
    },
  })
}

$(document).ready(getFlights);
</script>
<table id="flights" width="50%" border="1" class="display thread-striped">
  <thead class="thead-default">
    <td width="25%">Departure City</td>
    <td width="25%">Departure Time</td>
    <td width="25%">Arrival City</td>
    <td width="25%">Arrival Time</td>
    <td width="25%">Plane</td>
    <td width="25%">Delete</td>
  </thead>
  <tbody>
  </tbody>
</table>