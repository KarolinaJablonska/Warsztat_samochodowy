<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pl_PL">

<%@	include file="header.jsp"%>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Warsztat samochodowy</title>

<!-- ------------------------------------------------------------------------------------------------------------------------ -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- ------------------------------------------------------------------------------------------------------------------------ -->

</head>

<body>
	<br/>
	<h4>Lista wszystkich samochodów:</h4><br/>
	${message}

		<div class="panel panel-primary">
			<!-- Table -->
			<table class="table table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th>Model</th>
						<th>Marka</th>
						<th>rok produkcji</th>
						<th>Numer rejestracyjny</th>
						<th>Data kolejnego przeglądu technicznego</th>
						<th>Akcja</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${vehicles}" var="vehicle">
						<tr>
							<th>${vehicle.idVehicle}</th>
							<td>${vehicle.model}</td>
							<td>${vehicle.mark}</td>
							<td>${vehicle.productionYear}</td>
							<td>${vehicle.registrationNr}</td>
							<td>${vehicle.nextTechnicalReviewDate}</td>
							<td>
								<a href='<c:url value = '/DeleteVehicle?idVehicle=${vehicle.idVehicle}'/>'>usuń</a>
								<a href='<c:url value = '/ModifyVehicle?idVehicle=${vehicle.idVehicle}'/>'>edytuj</a>
								
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!-- ------------------------------------------------------------------------------------------------------------------------ -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
		crossorigin="anonymous"></script>
	<!-- ------------------------------------------------------------------------------------------------------------------------ -->

</body>
<%@	include file="footer.jsp"%>
</html>