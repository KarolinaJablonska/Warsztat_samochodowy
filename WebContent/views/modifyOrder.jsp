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
		
		<h4>Zmodyfikuj dane zamówienia. </h4>
		<h5>Obecne dane zostały wpisane do odpowiednich pól formularza. </h5><br/>

		<form class="form-horizontal" action="../../../Warsztat_samochodowy/ModifyOrder" method="post">
		
			<div class="form-group">
				<label class="col-sm-2 control-label">Id</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name ="idOrder" value="${order.idOrder}" readonly>
					<span id="helpBlock" class="help-block">[brak możliwości edycji]</span>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">Data przyjęcia do naprawy</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" placeholder = "YYYY-MM-DD"
					pattern = "(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
					name ="acceptanceForRepairDate" value="${order.acceptanceForRepairDate}" required>
					<span id="helpBlock" class="help-block">[pole wymagane]</span>
				</div>
				
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">Planowana data rozpoczęcia naprawy</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" placeholder = "YYYY-MM-DD" 
					pattern = "(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
					name="plannedRepairDate" value="${order.plannedRepairDate}">
					<span id="helpBlock" class="help-block">[pole opcjonalne]</span>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">Data rozpoczęcia naprawy</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" placeholder = "YYYY-MM-DD"
					pattern = "(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
					name = "startRepairDate" value="${order.startRepairDate}">
					<span id="helpBlock" class="help-block">[pole opcjonalne]</span>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">Obsługujący pracownik</label>
				<div class="col-sm-10">
					<select name = "servingEmployeeId" value="${order.servingEmployeeId}" required>
						<c:forEach items="${employees}" var="employee">
							<option value="${employee.idEmployee}" 
									<c:if test="${order.servingEmployeeId==employee.idEmployee}">selected</c:if>>
									id: ${employee.idEmployee} |  
									imię: ${employee.name} | 
									nazwisko: ${employee.surname} |  
									koszt zł/h: ${employee.manHour}.
							</option>	
						</c:forEach>
					</select>
					<span id="helpBlock" class="help-block">[pole wymagane]</span>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">Opis problemu</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name = "problemDescription" value="${order.problemDescription}">
					<span id="helpBlock" class="help-block">[pole opcjonalne]</span>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">Opis naprawy</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name = "repairDescription" value="${order.repairDescription}">
					<span id="helpBlock" class="help-block">[pole opcjonalne]</span>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">Status</label>
				<div class="col-sm-10">
					<select name = "status" value="${order.status}" required>
						<option <c:if test="${order.status eq 'Przyjęty'}">selected</c:if> value="Przyjęty">Przyjęty</option>
						<option <c:if test="${order.status eq 'Zatwierdzony koszt naprawy'}">selected</c:if> value="Zatwierdzony koszt naprawy">Zatwierdzony koszt naprawy</option>
						<option <c:if test="${order.status eq 'W naprawie'}">selected</c:if> value="W naprawie">W naprawie</option>
						<option <c:if test="${order.status eq 'Gotowy do odbioru'}">selected</c:if> value="Gotowy do odbioru">Gotowy do odbioru</option>
						<option <c:if test="${order.status eq 'Rezygnacja'}">selected</c:if> value="Rezygnacja">Rezygnacja</option>
					</select>
					<span id="helpBlock" class="help-block">[pole wymagane]</span>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">Id samochodu</label>
				<div class="col-sm-10">
					<select name = "repairedVehicleId" value="${order.repairedVehicleId}" required>
						<c:forEach items="${vehicles}" var="vehicle">
							<option value="${vehicle.idVehicle}" 
									<c:if test="${order.repairedVehicleId==vehicle.idVehicle}">selected</c:if>>
									id: ${vehicle.idVehicle} |  
									model: ${vehicle.model} | 
									marka: ${vehicle.mark} |  
									nr rejestracyjny: ${vehicle.registrationNr}.  
							</option>	
						</c:forEach>
					</select>
					<span id="helpBlock" class="help-block">[pole wymagane]</span>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">Koszt naprawy dla klienta</label>
				<div class="col-sm-10">
					<input type="number" step="0.01" min="0.00" placeholder="0,00" class="form-control" 
					name = "costForCustomer" value="${order.costForCustomer}">
					<span id="helpBlock" class="help-block">[pole opcjonalne]</span>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">Koszt wykorzystanych części</label>
				<div class="col-sm-10">
					<input type="number" step="0.01" min="0.00" placeholder="0,00" class="form-control" 
					name = "costOfParts" value="${order.costOfParts}">
					<span id="helpBlock" class="help-block">[pole opcjonalne]</span>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">Koszt roboczogodziny</label>
				<div class="col-sm-10">
					<input type="number" step="0.01" min="0.00" placeholder="0,00" class="form-control" 
					name = "manHourCost" value="${order.manHourCost}">
					<span id="helpBlock" class="help-block">[pole opcjonalne]</span>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">Ilość roboczogodzin</label>
				<div class="col-sm-10">
					<input type="number" step="0.01" min="0.00" placeholder="0,00" class="form-control" 
					name = "manHourQuantity" value="${order.manHourQuantity}">
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