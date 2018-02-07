<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.tirocirapid.classes.model.Tirocinio"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Azienda visualizza tirocini</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="js/formcheck.js"></script>
<script type="text/javascript" src="js/selected_item_azienda.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>

</head>
<body onload="selectedItemAzienda();">

<%@ include file="slider.jsp" %>

	<%@ include file="nav_azienda.jsp" %>
	
		<div class="container">
		<%if ((request.getAttribute("successo") != null))
		{%>
	<div style="color: green; text-align: center;"><%=request.getAttribute("successo")%></div>
	<%}%>
	
		<%
			if (request.getAttribute("tirocini") != null) {
				ArrayList<Tirocinio> tirocini = (ArrayList<Tirocinio>) request.getAttribute("tirocini");
				for (Tirocinio t : tirocini) {
		%>
		<div class="col-sm-12">

			<div class="bs-calltoaction bs-calltoaction-default">
				<div class="row">
					<div class="col-md-9 cta-contents">
						<h1 class="cta-title"><%=t.getNome() %></h1>
						<div class="cta-desc">
							<p>
								<span class="parametri-azienda">Stato:</span> <%=t.getStato() %>
							</p>
							<p>
								<span class="parametri-azienda">Descrizione:</span> <%=t.getDescrizione() %>
							</p>
							<p>
								<span class="parametri-azienda">Offerta Formativa:</span> <%=t.getOffertaFormativa() %>
							</p>
						</div>
					</div>
					<div class="col-md-3 cta-button">
						<a href="elimina_tirocinio?nome=<%=t.getNome()%>" class="btn btn-lg btn-block btn-default">Elimina</a>
					</div>
				</div>
			</div>


		</div>
		
		<%
			} //FINE FOR

			} // FINE IF
			
			if(request.getAttribute("errore")!=null){
		%>
				<div style="color: red; text-align: center;"><%=request.getAttribute("errore")%></div>		
		<%} %>
		</div>
	<%@include file="footer.jsp" %>
</body>
</html>