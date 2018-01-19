<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="card">
	<div class="card-body">
		<h4 class="card-title">List new flight</h4>
		
		<form:form method = "POST" action="createFl" modelAttribute="flight">
			<div class="form-group">
				<form:label path="depCity">Departure city</form:label>
				<form:select path="depCity" class="form-control">
					<form:option value="0" label="Select" />
					<form:option value="Phoenix" label="Phoenix" />
					<form:option value="Atlanta" label="Atlanta" />
					<form:option value="San Francisco" label="San Francisco" />
					<form:option value="Flagstaff" label="Flagstaff" />
				</form:select>
			</div>
			
			<div class="form-group">
				<form:label path="depTime">Departure time</form:label>
				<form:input path="depTime" class="form-control" type="text" id="depTime" name="depTime" value="" />
			</div>
			
			<div class="form-group">
				<form:label path="arrCity">Arrival city</form:label>
				<form:select path="arrCity" class="form-control">
					<form:option value="0" label="Select" />
					<form:option value="Phoenix" label="Phoenix" />
					<form:option value="Atlanta" label="Atlanta" />
					<form:option value="San Francisco" label="San Francisco" />
					<form:option value="Flagstaff" label="Flagstaff" />
				</form:select>
			</div>
			
			<div class="form-group">
				<form:label path="arrTime">Arrival time</form:label>
				<form:input path="arrTime" class="form-control" type="text" id="arrTime" name="arrTime" value="" />
			</div>
			
			<div class="form-group">
				<form:label path="plane">Plane</form:label>
				<form:select path="plane" class="form-control">
					<form:option value="1" label="Airbus A330" />
                    <form:option value="2" label="Boeing 737" />
                    <form:option value="3" label="Boeing 747" />
                    <form:option value="4" label="Boeing 777" />
                    <form:option value="5" label="McDonnell Douglas DC-10" />
				</form:select>
			</div>
			
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>
</div>

<script>
$(function() {
	$('#depTime').datetimepicker({
        dateFormat: 'yy-mm-dd',
		timeFormat: "hh:mm",
        //onSelect: function(datetext){
        //	datetext = datetext + "T" + h + ":" + m;
        //    $('#depTime').val(datetext);
        //},
    });
});
$(function() {
	$('#arrTime').datetimepicker({
        dateFormat: 'yy-mm-dd',
		timeFormat: "hh:mm",
        onSelect: function(datetext){
        	datetext = datetext + h + ":" + m;
            $('#arrTime').val(datetext);
        },
    });
});
</script>