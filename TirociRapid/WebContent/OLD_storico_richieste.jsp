<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="it.tirocirapid.classes.model.*, java.util.ArrayList, java.util.HashMap" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Storico richieste</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/professore.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/messaggi.css">

<script type="text/javascript" src="js/formcheck.js"></script>
<script type="text/javascript" src="js/selected_item_azienda.js"></script>
<script type="text/javascript" src="js/selected_item_professore.js"></script>
<script type="text/javascript" src="js/selected_item_responsabile_approvazioni.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>

</head>
<body onload="selectedItemAzienda(); selectedItemProfessore(); selectedItemResponsabileApprovazioni();">

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
	

	<div class="container" id="contenuto">
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
				<div class="row" id="divstorico">
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

					<div id = "stato-storico">
					
					<% 
                           String statoStorico = reqTir.getStato();
                           switch (statoStorico)
                           {
               					case "ConfAz":
               						statoStorico = "Richiesta in attesa della conferma da parte dell'azienda.";
               						break;
               					case "ScelTut":
               						statoStorico = "Richiesta in attesa della conferma del tutor interno da parte dello studente.";
               						break;
               					case "ConfTut":
               						statoStorico = "Richiesta in attesa della conferma da parte del professore scelto come tutor interno.";
               						break;
               					case "ConfResp":
               						statoStorico = "Richiesta  in attesa della conferma finale da parte del Responsabile Approvazioni.";
               						break;
               					case "Acc":
               						statoStorico = "Richiesta accettata dal Responsabile Approvazione.";
               						break;
               					case "RifResp":
               						statoStorico = "Richiesta rifiutata dal responsabile approvazioni";
               						break;
               					case "RifTut":
               						statoStorico = "Richiesta rifiutata dal tutor interno scelto e" 
               						+"in attesa di una nuova scelta da parte dello studente.";
               						break;
               					case "RifAz":
               						statoStorico = "Richiesta rifiutata dall'azienda";
               						break;
               					default:
               						break;
                           }                      
                           %>
					
						<h1>Stato:</h1>
						<h3><%=statoStorico%></h3>
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