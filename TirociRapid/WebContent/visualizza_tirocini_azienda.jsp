<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
<%@page import="it.tirocirapid.classes.model.Tirocinio"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza tirocini azienda</title>
<link rel="stylesheet" href="css/studente.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/messaggi.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script type="text/javascript" src="js/messaggi.js"></script>
	<script type="text/javascript" src="js/selected_item_studente.js"></script>
</head>
<body onload="nascondiMessaggiTop(); selectedItemStudente();">
	<%@ include file="slider.jsp"%>

	<%@ include file="nav_studente.jsp"%>

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

	<div class="container"> <!-- Contenitore Grande -->
		<%
			if (request.getAttribute("tirocini") != null) {
				ArrayList<Tirocinio> tirocini = (ArrayList<Tirocinio>) request.getAttribute("tirocini");
				for (Tirocinio t : tirocini) {
		%>
		<div class="col-sm-12">
			<div class="bs-calltoaction bs-calltoaction-default">
				<div class="row">
					<div class="col-md-9 cta-contents">
						<h1 class="cta-title">Tirocinio </h1>
						<div class="cta-desc">
							<p>
								<span class="parametri-tirocinio">Nome:</span> <%=t.getNome() %>
							</p>
							<p>
								<span class="parametri-tirocinio">Descrizione:</span> <%=t.getDescrizione()%>
							</p>
							<p>
								<span class="parametri-tirocinio">Offerta formativa:</span> <%=t.getOffertaFormativa()%>
							</p>
							
						</div>
					</div>
					
					<div class="col-md-3 cta-button">
					<%
					 String path = "invia_richiesta?nomeTirocinio="+ t.getNome().replace(" ", "+") +"&partitaIVAAzienda="+t.getPartitaIVAAzienda();
					
					%>
						<a href=<%=path %> class="btn btn-lg btn-block btn-default"> Scegli tirocinio</a>
					</div>
				</div>
			</div>
		</div>
		
			<%
			} //FINE FOR

			} // FINE IF
		
		%>
	</div><!-- Contenitore Grande -->
	<%@include file="footer.jsp"%>
</body>
</html>