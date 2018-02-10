<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.ArrayList"%>
<%@page import="it.tirocirapid.classes.model.Tirocinio"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Richieste nuovi tirocini</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/messaggi.css">
<link rel="stylesheet" href="css/professore.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript" src="js/selected_item_responsabile_approvazioni.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/messaggi.js"></script>

</head>
<body onload="selectedItemResponsabileApprovazioni(); nascondiMessaggiTop();">

	<%@ include file="slider.jsp"%>

	<%@ include file="nav_responsabile_approvazioni.jsp"%>
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
			<h1 class="successoTop"><%=request.getAttribute("successo") %></h1>
		<%
		}
			if (request.getAttribute("tirocini") != null) {
				ArrayList<Tirocinio> tirocini = (ArrayList<Tirocinio>) request.getAttribute("tirocini");
				if (tirocini.size()==0)
				{
				%>
					<div class="erroreMsg">Non ci sono nuovi tirocini da approvare</div>
				<%
				}
				else{
					for (Tirocinio t : tirocini) {					
			%>
		<div class="col-sm-12">

			<div class="bs-calltoaction bs-calltoaction-default">
				<div class="row">
					<div class="col-md-9 cta-contents">
						<h1 class="cta-title">Richiesta</h1>
						<div class="cta-desc">
							<h3>Azienda:</h3>
							<p style="padding-left: 1em;">
								<%=t.getPartitaIVAAzienda()%>&emsp;
								<a href="dati_azienda?partitaIVA=<%=t.getPartitaIVAAzienda()%>"><button class="bottoni-conferma-professore">Visualizza
									informazioni azienda</button></a>
							</p>
							<h3>Tirocinio:</h3>
							<div class="tabelle">
							<div class="parametri-tirocinio">
								<div class="elemento-parametri-tirocinio">Nome:</div>
								<div class="elemento-parametri-tirocinio"><%=t.getNome() %></div>
							</div>

							<div class="parametri-tirocinio">
								<div class="elemento-parametri-tirocinio">Descrizione:</div>
								<div class="elemento-parametri-tirocinio"><%=t.getDescrizione() %></div>
							</div>

							<div class="parametri-tirocinio">
								<div class="elemento-parametri-tirocinio">Offerta formativa:</div>
								<div class="elemento-parametri-tirocinio"><%=t.getOffertaFormativa() %></div>
							</div>
						</div>
					</div>
					
					

				</div>
				<% 
					String pathAccetta ="convalida_proposta_tirocinio?action=accetta&partitaIVA="+t.getPartitaIVAAzienda()+"&nome="+t.getNome();
					String pathRifiuta ="convalida_proposta_tirocinio?action=rifiuta&partitaIVA="+t.getPartitaIVAAzienda()+"&nome="+t.getNome();
				%>
				<div class="col-md-3 cta-button"  >
						<a href="<%=pathAccetta%>" class="btn btn-lg btn-block btn-default" id="bottone-professore-accetta" style="display: block;">Accetta</a>
					</div>
					<div class="col-md-3 cta-button" >
						<a href="<%=pathRifiuta%>" class="btn btn-lg btn-block btn-default" id="bottone-professore-rifiuta" style="display: block;" >Rifiuta</a>
					</div>
			</div>
		</div>
	</div>
	<%
					}//FINE FOR
				} //FINE ELSE
			} // FINE IF
			
		%>	
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>