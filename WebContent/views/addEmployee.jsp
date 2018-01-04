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
	<div class="panel panel-primary">
		<br />
		
		<h4>Wprowadź dane nowego pracownika </h4><br/>

		<form class="form-horizontal" action="../../../Warsztat_samochodowy/AddEmployee" method="post">
		
			<div class="form-group">
				<label for="inputName" class="col-sm-2 control-label">Imię</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputName" name ="name" required>
					<span id="helpBlock" class="help-block">[pole wymagane]</span>
				</div>
				
			</div>
			
			<div class="form-group">
				<label for="inputSurname" class="col-sm-2 control-label">Nazwisko</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputSurname" name="surname" required>
					<span id="helpBlock" class="help-block">[pole wymagane]</span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputPhone" class="col-sm-2 control-label">Telefon komórkowy</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputPhone" placeholder="000-000-000" 
					name = "phone" pattern = "[0-9]{3}-[0-9]{3}-[0-9]{3}" required>
					<span id="helpBlock" class="help-block">[pole wymagane]</span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputNote" class="col-sm-2 control-label">Notatka</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputNote" name = "note">
					<span id="helpBlock" class="help-block">[pole opcjonalne]</span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputManHour" class="col-sm-2 control-label">Koszt rb/h [zł/h]</label>
				<div class="col-sm-10">
					<input type="number" step="0.01" class="form-control" id="inputManHour" name = "manHour" min = "1.00" max = "9000.00" placeholder = "12,00" required>
					<span id="helpBlock" class="help-block">[pole wymagane]</span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputStreet" class="col-sm-2 control-label">Adres: ulica</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputStreet" name = "street" placeholder = "Lokalna 00/00" required>
					<span id="helpBlock" class="help-block">[pole wymagane]</span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputPostalCode" class="col-sm-2 control-label">Adres: kod pocztowy</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputPostalCode" name = "postalCode" placeholder = "00-000" pattern = "[0-9]{2}-[0-9]{3}" required>
					<span id="helpBlock" class="help-block">[pole wymagane]</span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputCity" class="col-sm-2 control-label">Adres: miasto</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputCity" name = "city" required>
					<span id="helpBlock" class="help-block">[pole wymagane]</span>
				</div>
			</div>
				
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Dodaj</button>
				</div>
			</div>
		</form>


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