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
		
		<h4>Zmodyfikuj dane klienta. </h4>
		<h5>Obecne dane zostały wpisane do odpowiednich pól formularza. </h5><br/>

		<form class="form-horizontal" action="../../../Warsztat_samochodowy/ModifyCustomer" method="post">
		
			<div class="form-group">
				<label for="inputId" class="col-sm-2 control-label">Id</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="disableInputId" name ="id" value = "${customer.idCustomer}" readonly>
					<span id="helpBlock" class="help-block">[brak możliwości edycji]</span>
				</div>
			</div>
		
			<div class="form-group">
				<label for="inputName" class="col-sm-2 control-label">Imię</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputName" name ="name" value = "${customer.name}" required>
					<span id="helpBlock" class="help-block">[pole wymagane]</span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputSurname" class="col-sm-2 control-label">Nazwisko</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputSurname" name="surname" value = "${customer.surname}" required>
					<span id="helpBlock" class="help-block">[pole wymagane]</span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputbirthDay" class="col-sm-2 control-label">Data urodzenia</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputbirthDay" placeholder="YYYY-MM-DD" name = "birthDay" value = "${customer.birthDay}" 
					pattern = "(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))">
					<span id="helpBlock" class="help-block">[pole opcjonalne]</span>
				</div>
			</div>
				
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Zmień</button>
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