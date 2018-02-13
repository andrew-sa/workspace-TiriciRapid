<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.tirocirapid.classes.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza informazioni tirocinio</title>


<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/informazioni.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/messaggi.css">
	
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
		<%@ include file="nav_azienda.jsp"%>
	<%
	}
	else if(user.getTipo().equals("Studente"))
	{
	%>
		<%@ include file="nav_studente.jsp"%>
	<%
	}
	%>
	<div class="container">
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
	
	if(request.getAttribute("tirocinio")!=null)
	{
	Tirocinio tirocinio = (Tirocinio) request.getAttribute("tirocinio");
	%>
		<div id="info-tirocinio">

			<h2 style="text-align: center;">
				<span><%=tirocinio.getNome() %></span>
			</h2>
			<div class="tabella-info-tirocinio">

				<div class="parametri-tirocinio">
					<div class="elemento-parametri-tirocinio">Offerta formativa:</div>
					<div class="elemento-parametri-tirocinio"><%=tirocinio.getOffertaFormativa() %></div>
				</div>

				<div class="parametri-tirocinio">
					<div class="elemento-parametri-tirocinio">Descrizione:</div>
					<div class="elemento-parametri-tirocinio"><%=tirocinio.getDescrizione() %></div>
				</div>
	
			</div>

		

		</div>
	<%
	}
	%>		
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>