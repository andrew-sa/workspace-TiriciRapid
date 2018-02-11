<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.ArrayList"%>
<%@page import="it.tirocirapid.classes.model.Azienda"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Aziende</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/studente.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/messaggi.css">
<link rel="stylesheet" href="css/pagine.css">
<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="js/jquery.js"></script>
<script src="js/json2.js"></script>
<script src="js/selected_item_studente.js"></script>
<script src="js/messaggi.js"></script>
<script src="js/lista_aziende.js"></script>
</head>
<body onload="selectedItemStudente(); caricaListaAziende(); nascondiMessaggiTop();">
	
	<%@ include file="slider.jsp"%>

	<%@ include file="nav_studente.jsp"%>

	<div class="container" id="contenuto">
	
	<%
		if (request.getAttribute("errore") != null)
		{
	%>
		<h1 class="erroreTop"><%= request.getAttribute("errore") %></h1>
	<%
		}
		else if (request.getAttribute("successo") != null)
		{
	%>
		<h1 class="successoTop"><%= request.getAttribute("successo") %></h1>
	<%
		}
	%>
	
	</div>
	
	<div id="numPages">
		
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>