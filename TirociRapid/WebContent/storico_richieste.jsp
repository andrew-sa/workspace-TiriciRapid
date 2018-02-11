<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="it.tirocirapid.classes.model.*, java.util.ArrayList, java.util.HashMap" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Storico richieste</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/professore.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/messaggi.css">
<link rel="stylesheet" href="css/pagine.css">

<script src="js/jquery.js"></script>
<script src="js/json2.js"></script>
<script type="text/javascript" src="js/formcheck.js"></script>
<script type="text/javascript" src="js/selected_item_azienda.js"></script>
<script type="text/javascript" src="js/selected_item_professore.js"></script>
<script type="text/javascript" src="js/selected_item_responsabile_approvazioni.js"></script>
<script src="js/messaggi.js"></script>
<script src="js/lista_storico_richieste.js"></script>

</head>
<body onload="selectedItemAzienda(); selectedItemProfessore(); selectedItemResponsabileApprovazioni(); caricaListaRichieste(); nascondiMessaggiTop();">

	<%@ include file="slider.jsp"%>

	<%
	UserLoggato user = (UserLoggato) session.getAttribute("user");
	if(user.getTipo().equals("Professore"))
	{	
	%>
		<%@ include file="nav_professore.jsp"%>
	<%
	}
	else if(user.getTipo().equals("ResponsabileApprovazioni"))
	{
	%>
		<%@ include file="nav_responsabile_approvazioni.jsp"%>
	<%
	}
	else if(user.getTipo().equals("ResponsabileAzienda"))
	{
	%>
		<%@ include file="nav_azienda.jsp"%>
	<%
	}
	%>

	<div class="container" id="contenuto">
	
	</div>

	<div id="numPages">
		
	</div>

	<%@include file="footer.jsp"%>
</body>
</html>