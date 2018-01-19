<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="card">
	<div class="card-body">
		<h4 class="card-title">Register</h4>
		
		<form:form method = "POST" action="registerUser" modelAttribute="userProfile">
			<div class="form-group">
				<form:label path="username">Username</form:label>
				<form:input path="username" class="form-control" />
				<p class="text-danger small">
					<form:errors path="username" />
				</p>
			</div>
			<div class="form-group">
				<form:label path="password">Password</form:label>
				<form:input path="password" class="form-control" type="password" />
				<p class="text-danger small">
					<form:errors path="password" />
				</p>
			</div>
			<div class="form-group">
				<form:label path="firstName">First name</form:label>
				<form:input path="firstName" class="form-control" />
				<p class="text-danger small">
					<form:errors path="firstName" />
				</p>
			</div>
			<div class="form-group">
				<form:label path="lastName">Last name</form:label>
				<form:input path="lastName" class="form-control" />
				<p class="text-danger small">
					<form:errors path="lastName" />
				</p>
			</div>
			<div class="form-group">
				<form:label path="email">Email</form:label>
				<form:input path="email" class="form-control" />
				<p class="text-danger small">
					<form:errors path="email" />
				</p>
			</div>
			<div class="form-group">
				<form:label path="phoneNumber">Phone Number</form:label>
				<form:input path="phoneNumber" class="form-control"/>
				<p class="text-danger small">
					<form:errors path="phoneNumber" />
				</p>
			</div>
			<div class="form-group">
				<form:label path="dateOfBirth">Date of birth</form:label>
				<form:input path="dateOfBirth" class="form-control" />
				<p class="text-danger small">
					<form:errors path="dateOfBirth" />
				</p>
			</div>
			<div class="form-group">
				<form:label path="address">Address</form:label>
				<form:input path="address" class="form-control"/>
				<p class="text-danger small">
					<form:errors path="address" />
				</p>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>
</div>