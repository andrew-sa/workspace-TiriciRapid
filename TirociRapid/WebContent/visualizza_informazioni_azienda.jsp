<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="it.tirocirapid.classes.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza informazioni azienda</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/professore.css">
<link rel="stylesheet" href="css/messaggi.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
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
	Azienda azienda = (Azienda) request.getAttribute("azienda");
	
	%>

		<div id="responsabile-approvazione-azienda-profilo">

			<h2 style="text-align: center;">
				<span><%=azienda.getNome() %>  </span>
			</h2>
			<div class="tabella-info-azienda">

				<div class="parametri-azienda">
					<div class="elemento-parametri-azienda">PartitaIVA:</div>
					<div class="elemento-parametri-azienda"><%=azienda.getPartitaIVA() %></div>
				</div>

				<div class="parametri-azienda">
					<div class="elemento-parametri-azienda">Sede:</div>
					<div class="elemento-parametri-azienda"><%=azienda.getSede() %></div>
				</div>

				<div class="parametri-azienda">
					<div class="elemento-parametri-azienda">Email:</div>
					<div class="elemento-parametri-azienda"><%=azienda.getEmail() %></div>
				</div>

				<div class="parametri-azienda">
					<div class="elemento-parametri-azienda">Numero di telefono:</div>
					<div class="elemento-parametri-azienda"><%=azienda.getNumeroTelefono() %></div>
				</div>

				<div class="parametri-azienda">
					<div class="elemento-parametri-azienda">Descrizione Ambito:</div>
					<div class="elemento-parametri-azienda"><%=azienda.getDescrizioneAmbito() %></div>
				</div>

				<div class="parametri-azienda">
					<div class="elemento-parametri-azienda">Stato:</div>
					<div class="elemento-parametri-azienda"><%=azienda.getStato() %></div>
				</div>
				
			</div>
		</div>
		
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>