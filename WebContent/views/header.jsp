<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


 <header>

 
<ul class="nav nav-pills">
  <li role="presentation" class="active"><a href="../../../Warsztat_samochodowy/AllActualOrders">Strona główna</a></li> 

  <!-- Rozwijalne menu Klienci -->
  <li class="dropdown, active">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Klienci<span class="caret"></span></a>
		    <ul class="dropdown-menu">
		      <li><a href="../../../Warsztat_samochodowy/AllCustomers">Lista wszystkich klientów</a></li>
		      <li><a href="../../Warsztat_samochodowy/views/addCustomer.jsp">Dodaj nowego klienta</a></li>
		      <li><a href="../../Warsztat_samochodowy/views/customerBySurname.jsp">Wyszukaj klienta po nazwisku</a></li>
		    </ul>
	 </li>
  
  
   <!-- Rozwijalne menu Pracownicy -->
  <li class="dropdown, active">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Pracownicy<span class="caret"></span></a>
		    <ul class="dropdown-menu">
		      <li><a href="../../../Warsztat_samochodowy/AllEmployees">Lista wszystkich pracowników</a></li>
		      <li><a href="../../Warsztat_samochodowy/views/addEmployee.jsp">Dodaj nowego pracownika</a></li>
		    </ul>
	 </li>
  
  
     <!-- Rozwijalne menu Samochody -->
  <li class="dropdown, active">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Samochody<span class="caret"></span></a>
		    <ul class="dropdown-menu">
		      <li><a href="../../../Warsztat_samochodowy/AllVehicles">Lista wszystkich samochodów</a></li>
		      <li><a href="../../../Warsztat_samochodowy/SupportValuesToForms?action=addVehicle">Dodaj nowy samochód</a></li>
		    </ul>
 </li>
  
       <!-- Rozwijalne menu Zlecenia -->
  <li class="dropdown, active">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Zlecenia<span class="caret"></span></a>
		    <ul class="dropdown-menu">
		      <li><a href="../../../Warsztat_samochodowy/AllOrders">Lista wszystkich zleceń</a></li>
		      <li><a href="../../../Warsztat_samochodowy/SupportValuesToForms?action=addOrder">Dodaj nowe zlecenie</a></li>
		      <li><a href="../../Warsztat_samochodowy/SupportValuesToForms?action=oneOrderById">Wyszukaj zlecenie po nr id</a></li>
		    </ul>
 </li>
  

  <li role="presentation" class="active"><a href="#">Raporty</a></li>

</ul>
 
 
      
 </header>

</html>