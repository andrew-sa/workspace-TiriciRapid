<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
	<%@page import="java.util.ArrayList"%>
<%@page import="it.tirocirapid.classes.model.Azienda"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Responsabile Approvazioni Aziende</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/professore.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript" src="js/selected_item_responsabile_approvazioni.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>

</head>
<body onload="selectedItemResponsabileApprovazioni();">

	<%@ include file="slider.jsp"%>
	<%@ include file="nav_responsabile_approvazioni.jsp"%>
	
		<div class="container">
<%
			if (request.getAttribute("aziende") != null) {
				ArrayList<Azienda> aziende = (ArrayList<Azienda>) request.getAttribute("aziende");
				for (Azienda a : aziende) {
					
					
		%>
		<div id="responsabile-approvazione-azienda-profilo">

			<h2 style="text-align: center;">
				<span><%=a.getNome()%></span>
			</h2>
			<div class="tabella-info-azienda">

				<div class="parametri-azienda">
					<div class="elemento-parametri-azienda">PartitaIVA:</div>
					<div class="elemento-parametri-azienda"><%=a.getPartitaIVA()%></div>
				</div>

				<div class="parametri-azienda">
					<div class="elemento-parametri-azienda">Sede:</div>
					<div class="elemento-parametri-azienda"><%=a.getSede()%></div>
				</div>

				<div class="parametri-azienda">
					<div class="elemento-parametri-azienda">Email:</div>
					<div class="elemento-parametri-azienda"><%=a.getEmail() %></div>
				</div>

				<div class="parametri-azienda">
					<div class="elemento-parametri-azienda">Numero di telefono:</div>
					<div class="elemento-parametri-azienda"><%=a.getNumeroTelefono() %></div>
				</div>

				<div class="parametri-azienda">
					<div class="elemento-parametri-azienda">Descrizione Ambito:</div>
					<div class="elemento-parametri-azienda"><%=a.getDescrizioneAmbito() %></div>
				</div>

				<div class="parametri-azienda">
					<div class="elemento-parametri-azienda">Stato:</div>
					<div class="elemento-parametri-azienda"><%=a.getStato() %></div>
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
	<%@include file="footer.jsp"%>
</body>
</html>