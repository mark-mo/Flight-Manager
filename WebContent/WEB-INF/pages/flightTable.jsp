<script type="text/javascript">
function getFlights()
{
	var model = '${ company.id }';
  $.ajax({
    type: "GET",
    url: "/FlightManager/service1/flights",
    dataType: "json",
    success: function(data)
	{
      // Display Orders in the jQuery Data Table
      $('#flights').dataTable({
    	"responsive": true,
        "data": data,
        "columns": [
        	{ "data": "depCity", "responsivePriority": 2 },
        	{ "data": "depTime", "responsivePriority": 3 },
        	{ "data": "arrCity", "responsivePriority": 4 },
        	{ "data": "arrTime", "responsivePriority": 5 },
        	{ "data": "plane", "responsivePriority": 6 },
        	{ "data": "id", "responsivePriority":1, "visible":(model != -1 ? true:false),"render": function(data, type, full, meta) { 
        		// render allows the data in the table to change in each row
        		return '<a href="/FlightManager/product/view/' + data + '">Update</a>';
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
<table id="flights" class="display table-striped table-bordered" width="50%">
  <thead class="thead-inverse">
    <tr style="background-color:#A0A0A0">
      <td width="25%">Departure City</td>
      <td width="25%">Departure Time</td>
      <td width="25%">Arrival City</td>
      <td width="25%">Arrival Time</td>
      <td width="25%">Plane</td>
      <td width="25%">Update</td>
    </tr>
  </thead>
  <tbody>
  </tbody>
</table>