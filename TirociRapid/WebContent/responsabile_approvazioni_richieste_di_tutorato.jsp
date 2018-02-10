<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="it.tirocirapid.classes.model.*, java.util.ArrayList, java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Richieste di tutorato</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/professore.css">
<link rel="stylesheet" href="css/messaggi.css">
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
			<h1 class="successoTop"><%= request.getAttribute("successo") %></h1>
	<%
		}
	HashMap<Integer, String> states = (HashMap<Integer, String>) getServletContext().getAttribute("statesReqTir");
	if (request.getAttribute("richieste") != null)
	{
		ArrayList<RichiestaTirocinio> richieste = (ArrayList<RichiestaTirocinio>) request.getAttribute("richieste");
		if (richieste.size() == 0)
		{
	%>
				<div class="erroreMsg">Non hai richieste.</div>
	<%
		}
		else
		{
			for (RichiestaTirocinio reqTir: richieste)
			{
	%>
		<div class="col-sm-12">

			<div class="bs-calltoaction bs-calltoaction-default">
				<div class="row">
					<div class="col-md-9 cta-contents">
						<h1 class="cta-title">Richiesta</h1>
						<div class="cta-desc">
							<h3>Studente:</h3>
							<p>
								<%=reqTir.getStudente().getUsername() %>
								<a href="profilo_studente?username=<%=reqTir.getStudente().getUsername()%>"><button class="bottoni-conferma-professore" >Visualizza
									curriculum</button></a>
							</p>
							<h3>Azienda:</h3>
							<p>
								<%=reqTir.getTirocinio().getPartitaIVAAzienda() %>
								<a href="dati_azienda?partitaIVA=<%=reqTir.getTirocinio().getPartitaIVAAzienda()%>"><button class="bottoni-conferma-professore">Visualizza
									informazioni azienda</button></a>
							</p>
							<h3>Tirocinio:</h3>
							<p>
								<%=reqTir.getTirocinio().getNome() %>
								<a href="carica_tirocinio?partitaIVA=<%=reqTir.getTirocinio().getPartitaIVAAzienda()%>&nomeTirocinio=<%=reqTir.getTirocinio().getNome() %>"><button class="bottoni-conferma-professore">Visualizza
									informazioni tirocinio</button></a>
							</p>
						</div>
					</div>
					
					<div class="col-md-3 cta-button"  >
					<% 
					String pathAccetta ="convalida_richiesta_tirocinio?tipoRichieste=tutor&action=accetta&partitaIVAAzienda="+reqTir.getTirocinio().getPartitaIVAAzienda()+
										"&nomeTirocinio="+reqTir.getTirocinio().getNome()+"&usernameStudente="+reqTir.getStudente().getUsername();
					String pathRifiuta ="convalida_richiesta_tirocinio?tipoRichieste=tutor&action=rifiuta&partitaIVAAzienda="+reqTir.getTirocinio().getPartitaIVAAzienda()+
							"&nomeTirocinio="+reqTir.getTirocinio().getNome()+"&usernameStudente="+reqTir.getStudente().getUsername();
					%>
						<a href="<%=pathAccetta%>" class="btn btn-lg btn-block btn-default" id="bottone-professore-accetta" style="display: block;">Accetta</a>
					</div>
					<div class="col-md-3 cta-button" >
						<a href="<%=pathRifiuta%>" class="btn btn-lg btn-block btn-default" id="bottone-professore-rifiuta" style="display: block;" >Rifiuta</a>
					</div>

				</div>
			</div>
		</div>
	<%
				} //FINE FOR
			}
		}
	%>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>