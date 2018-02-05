<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="it.tirocirapid.classes.model.Azienda"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Azienda profilo</title>

<link rel="stylesheet" href="css/azienda.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript" src="js/formcheck.js"></script>
<script type="text/javascript" src="js/selected_item_azienda.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>

</head>
<body onload="selectedItemAzienda();">
	<%@ include file="slider.jsp"%>

	<%@ include file="nav_azienda.jsp"%>

	<div class="container">

<%if(request.getAttribute("azienda")!=null){
		Azienda a = (Azienda)request.getAttribute("azienda");
	
		%>
		<div id="azienda-profilo">

			<h2 style="text-align: center;">
				<span>Dati aziendali : </span>
			</h2>
			<div class="tabelle">

				<div class="parametri-azienda">
					<div class="elemento-parametri-azienda">PartitaIVA:</div>
					<div class="elemento-parametri-azienda"><%=a.getPartitaIVA() %></div>
				</div>

				<div class="parametri-azienda">
					<div class="elemento-parametri-azienda">Nome:</div>
					<div class="elemento-parametri-azienda"><%=a.getNome() %></div>
				</div>

				<div class="parametri-azienda">
					<div class="elemento-parametri-azienda">Sede:</div>
					<div class="elemento-parametri-azienda"><%=a.getSede() %></div>
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
<div class="col-md-3 cta-button" id="bottone-dati-aziendali">
					<a href="#" class="btn btn-lg btn-block btn-default">Modifica
						informazioni</a>
				</div>
		

		</div>
			<%} if(request.getAttribute("errore")!=null){ 
		%>
				<div style="color: red; text-align: center;"><%=request.getAttribute("errore")%></div>		
		<%} %>
		
	</div>





	<%@include file="footer.jsp"%>
</body>
</html>