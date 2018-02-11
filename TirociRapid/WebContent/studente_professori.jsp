<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="it.tirocirapid.classes.model.Professore, java.util.ArrayList" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="author" content="Bolognese Cammarano Mogavero Napoli Tomeo">
		<title>Lista Professori</title>
		
		
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/studente.css">
		<link rel="stylesheet" href="css/style.css">
		<link rel="stylesheet" href="css/messaggi.css">
		<link rel="stylesheet" href="css/pagine.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		
		<script src="js/jquery.js"></script>
		<script src="js/json2.js"></script>
		<script src="js/selected_item_studente.js"></script>
		<script src="js/messaggi.js"></script>
		<script src="js/lista_professori.js"></script>
		
	</head>
	<body onload="selectedItemStudente(); caricaListaProfessori(); nascondiMessaggiTop();">
	
		<%@ include file="slider.jsp"%>
	
		<%@ include file="nav_studente.jsp"%>
	
		<div class="container" id="contenuto">
			
		</div>
		
		<div id="numPages">
		
		</div>
		
		<%@include file="footer.jsp"%>
	</body>
</html>