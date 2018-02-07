<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="it.tirocirapid.classes.model.*, java.util.ArrayList, java.util.HashMap" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Richieste effettuate</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Bolognese Cammarano Mogavero Napoli Tomeo">
<link rel="stylesheet" href="css/studente.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/messaggi.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="js/jquery.js"></script>
<script src="js/selected_item_studente.js"></script>
<script type="text/javascript" src="js/messaggi.js"></script>
</head>
<body onload="selectedItemStudente(); nascondiMessaggiTop();">

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
	
    <%@ include file="slider.jsp" %>

	<%@ include file="nav_studente.jsp"%>
	
	<div class="container">
		<%
			HashMap<Integer, String> states = (HashMap<Integer, String>) getServletContext().getAttribute("statesReqTir");
			if (request.getAttribute("richieste") != null)
			{
				ArrayList<RichiestaTirocinio> richieste = (ArrayList<RichiestaTirocinio>) request.getAttribute("richieste");
				if (richieste.size() == 0)
				{
		%>
			<div class="erroreMsg">Non hai effettuato richieste di tirocinio</div>
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
                           <h1 class="cta-title">
                      			<%= reqTir.getTirocinio().getNome() %>
                           </h1>
                           <div class="cta-desc">
                           
                           <% 
                           String statoAttuale = reqTir.getStato();
                           switch (statoAttuale)
                           {
               					case "ConfAz":
               						statoAttuale = "Richiesta in attesa della conferma da parte dell'azienda.";
               						break;
               					case "ScelTut":
               						statoAttuale = "Richiesta in attesa dell'attesa del tutor interno da parte dello studente.";
               						break;
               					case "ConfTut":
               						statoAttuale = "Richiesta in attesa della conferma da parte del professore scelto come tutor interno.";
               						break;
               					case "ConfResp":
               						statoAttuale = "Richiesta  in attesa della conferma finale da parte del Responsabile Approvazioni.";
               						break;
               					case "Acc":
               						statoAttuale = "Richiesta accettata dal Responsabile Approvazione.";
               						break;
               					case "RifResp":
               						statoAttuale = "Richiesta rifiutata dal responsabile approvazioni";
               						break;
               					case "RifTut":
               						statoAttuale = "Richiesta rifiutata dal tutor interno scelto e" 
               						+"in attesa di una nuova scelta da parte dello studente.";
               						break;
               					case "RifAz":
               						statoAttuale = "Richiesta rifiutata dall'azienda";
               						break;
               					default:
               						break;
                           }
                           
                           
                           %>
                               <p><span class="parametri-richiesta">Stato:</span> <%= statoAttuale %></p>
                               <%
                               		String stato = reqTir.getStato();
                               		if (states.get(4).equals(stato) || states.get(5).equals(stato) || states.get(-4).equals(stato))
                               		{
                               %>
                              <p> <span class="parametri-richiesta">Tutor interno:</span> <%=reqTir.getTutorInterno().getUsername()%></p>
                               <%
                               		}
                               		else if(states.get(2).equals(stato)||states.get(-3).equals(stato))
                               		{
                               %>
                                 	<div class="col-md-3 cta-button">
		                           		<a href="" class="btn btn-lg btn-block btn-default">Elimina!</a>
		                       		</div>
                               <%
                               		}
                               %>
                           </div>
                       </div>
                        <%
                        String path = "elimina_richiesta_tirocinio?nomeTirocinio="+ reqTir.getTirocinio().getNome().replace(" ", "+") +
                        			  "&partitaIVAAzienda="+reqTir.getTirocinio().getPartitaIVAAzienda()+"&stato="+reqTir.getStato();
                        if(states.get(-1).equals(stato) || states.get(-4).equals(stato))   
                        	
                        {       		
                        %>
		                       <div class="col-md-3 cta-button">
		                           <a href=<%=path %> class="btn btn-lg btn-block btn-default">Elimina!</a>
		                       </div>
                        <%
                         }
                        %>
                    </div>
               </div>
		</div>
		<%
					} //FINE FOR
				}
			}
		%>
	</div>
	<%@include file="footer.jsp" %>
</body>
</html>