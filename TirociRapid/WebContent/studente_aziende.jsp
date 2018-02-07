<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.ArrayList"%>
<%@page import="it.tirocirapid.classes.model.Azienda"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Studente Aziende</title>
<link rel="stylesheet" href="css/studente.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/messaggi.css">
<script type="text/javascript" src="js/messaggi.js"></script>
<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="js/selected_item_studente.js"></script>
</head>
<body onload="selectedItemStudente();nascondiMessaggiTop();">
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
	<%@ include file="slider.jsp"%>

	<%@ include file="nav_studente.jsp"%>

	<div class="container">
	<%
			if (request.getAttribute("aziende") != null) {
				ArrayList<Azienda> aziende = (ArrayList<Azienda>) request.getAttribute("aziende");
				for (Azienda a : aziende) {
					
					
		%>
		<div class="col-sm-12">

			<div class="bs-calltoaction bs-calltoaction-default">
				<div class="row">
					<div class="col-md-9 cta-contents">
						<h1 class="cta-title"><%=a.getNome() %></h1>
						<div class="cta-desc">
							<p>
								<span class="parametri-azienda">Ambito:</span> <%=a.getDescrizioneAmbito() %>
							</p>
							<p>
								<span class="parametri-azienda">Sede:</span> <%=a.getSede() %>
							</p>
							<p>
								<span class="parametri-azienda">Email:</span> <%=a.getEmail() %>
							</p>
							<p>
								<span class="parametri-azienda">Telefono:</span> <%=a.getNumeroTelefono() %>
							</p>

						</div>
					</div>
					<div class="col-md-3 cta-button">
						<a href="tirocini_azienda?partitaIVA=<%=a.getPartitaIVA() %>" class="btn btn-lg btn-block btn-default">Visualizza
							Tirocini</a>
					</div>
				</div>
			</div>


		</div>
<%
			} //FINE FOR

			} // FINE IF
			
		%>	
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>