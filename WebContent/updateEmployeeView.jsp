<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Servlet, JSP, JDBC and MVC Example</title>
	
	<link rel="stylesheet"
		href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
	<link rel="stylesheet"
		href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/employee.css">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="js/employee.js"></script>

<body>
	
	<!-- Edit Modal HTML -->
	<jsp:include page="addEmployeeView.jsp"></jsp:include>
	<!-- Edit Modal HTML -->
	<div id="editEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action ="/employee-crud-project/update">
					<div class="modal-header">
						<h4 class="modal-title">Edit Employee</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>Name</label> <input type="text" name = "name" ng-model = "employee.name" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Email</label> <input type="email" name = "email" ng-model = "employee.email" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Address</label>
							<textarea ng-model = "employee.address"  name = "address" class="form-control" required></textarea>
						</div>
						<div class="form-group">
							<label>Phone</label> <input type="text" name = "phone" ng-model = "employee.phone"  class="form-control" required>
						</div>
					</div>
					<div class="modal-footer">
						<input type="hidden" name = "id" value = "{{employee.id}}">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel"> 
						<input type="submit" class="btn btn-info" value="Save">
					</div>
				</form>
			</div>
		</div>
	</div>
	
</body>
</html>