<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.tirocirapid.classes.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza informazioni tirocinio</title>


<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/professore.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script type="text/javascript" src="js/messaggi.js"></script>
</head>
<body onload="nascondiMessaggiTop();">

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
		<%@ include file="nav_responsabile_approvazioni.jsp"%>
	<%
	}
	%>
	
	
	
		<div class="container">
<%
	Tirocinio tirocinio = (Tirocinio) request.getAttribute("tirocinio");
	
	%>
		<div id="responsabile-approvazione-azienda-profilo">

			<h2 style="text-align: center;">
				<span><%=tirocinio.getNome() %></span>
			</h2>
			<div class="tabella-info-azienda">

				<div class="parametri-azienda">
					<div class="elemento-parametri-azienda">Offerta formativa:</div>
					<div class="elemento-parametri-azienda"><%=tirocinio.getOffertaFormativa() %></div>
				</div>

				<div class="parametri-azienda">
					<div class="elemento-parametri-azienda">Descrizione:</div>
					<div class="elemento-parametri-azienda"><%=tirocinio.getDescrizione() %></div>
				</div>
	
			</div>

		

		</div>
		
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>