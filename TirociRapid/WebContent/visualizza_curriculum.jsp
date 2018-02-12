<%@page import="it.tirocirapid.classes.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza Curriculum</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/informazioni.css">
<!-- <link rel="stylesheet" href="css/professore.css"> -->
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
	else if(user.getTipo().equals("ResponsabileAzienda"))
	{
	%>
		<%@ include file="nav_azienda.jsp"%>
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
	if(request.getAttribute("studente")!= null){
	Studente studente = (Studente) request.getAttribute("studente");
	Curriculum curriculum = studente.getCurriculum();
	
	%>
		<div id="info-studente">

			<h2 style="text-align: center;">
				<span><%=studente.getNome()+" "+studente.getCognome() %></span>
			</h2>
			<div class="tabella-info-studente">

			<%if(curriculum.getFax()!=null) 
			 {
			 %>
				<div class="parametri-studente">
					<div class="elemento-parametri-studente">Fax:</div>
					<div class="elemento-parametri-studente"><%=curriculum.getFax() %></div>
				</div>
			<%
			} 
			%>
				<div class="parametri-studente">
					<div class="elemento-parametri-studente">Capacit&agrave;
						competenze relazionali:</div>
					<div class="elemento-parametri-studente"><%=curriculum.getCapacitaCompetenzeRelazionali() %></div>
				</div>

				<div class="parametri-studente">
					<div class="elemento-parametri-studente">Capacit&agrave;
						competenze tecniche:</div>
					<div class="elemento-parametri-studente"><%=curriculum.getCapacitaCompetenzeTecniche() %></div>
				</div>

				<div class="parametri-studente">
					<div class="elemento-parametri-studente">Capacit&agrave;
						competenze artistiche:</div>
					<div class="elemento-parametri-studente"><%=curriculum.getCapacitaCompetenzeArtistiche() %></div>
				</div>

				<div class="parametri-studente">
					<div class="elemento-parametri-studente">Capacit&agrave;
						competenze personali:</div>
					<div class="elemento-parametri-studente"><%=curriculum.getCapacitaCompetenzePersonali() %></div>
				</div>

				<div class="parametri-studente">
					<div class="elemento-parametri-studente">Capacit&agrave;
						competenze organizzative:</div>
					<div class="elemento-parametri-studente"><%=curriculum.getCapacitaCompetenzeOrganizzative() %></div>
				</div>
				<div class="parametri-studente">
					<div class="elemento-parametri-studente">AltreCapacit&agrave;
						e competenze:</div>
					<div class="elemento-parametri-studente"><%=curriculum.getAltreCapacitaCompetenze() %></div>
				</div>
				<div class="parametri-studente">
					<div class="elemento-parametri-studente">Altre lingue:</div>
					<div class="elemento-parametri-studente"><%=curriculum.getAltreLingue() %></div>
				</div>
				<div class="parametri-studente">
					<div class="elemento-parametri-studente">Madrelingua:</div>
					<div class="elemento-parametri-studente"><%=curriculum.getMadrelingua() %></div>
				</div>
				<div class="parametri-studente">
					<div class="elemento-parametri-studente">Patenti:</div>
					<div class="elemento-parametri-studente"><%=curriculum.getPatenti() %></div>
				</div>
				<div class="parametri-studente">
					<div class="elemento-parametri-studente">Ulteriori
						informazioni:</div>
					<div class="elemento-parametri-studente"><%=curriculum.getUlterioriInformazioni() %></div>
				</div>
			</div>



		</div>
<%} %>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>