
<%@page import="it.tirocirapid.classes.model.Curriculum"%>

<%@page import="it.tirocirapid.classes.model.Studente"%>
<%@page import="it.tirocirapid.classes.model.UserLoggato"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Profilo</title>

<link rel="stylesheet" href="css/studente.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/messaggi.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="js/selected_item_studente.js"></script>	
<script type="text/javascript" src="js/messaggi.js"></script>

</head>
<body onload="selectedItemStudente(); nascondiMessaggiTop();">

	<%@ include file="slider.jsp"%>

	<%@ include file="nav_studente.jsp"%>

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
		if(request.getAttribute("studente")!=null)
		{
			Studente studente =  (Studente)request.getAttribute("studente");
			Curriculum curriculum = studente.getCurriculum();
		%>
		<div id="profilo-curriculum">
		
		<h3 style="text-align: center;"><span>Curriculum </span></h3>
		<div class="tabelle">
		
		<%if(curriculum.getFax()!= null){ %>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Fax: </div> 
			<div class="elemento-parametri-studente"> <%=curriculum.getFax() %> </div>
			</div>
		<%} %>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Capacita competenze relazionali: </div> 
			<div class="elemento-parametri-studente"> <%=curriculum.getCapacitaCompetenzeRelazionali() %></div>
			</div>
			
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Capacita competenze tecniche:</div> 
			<div class="elemento-parametri-studente"> <%=curriculum.getCapacitaCompetenzeTecniche() %></div>
			</div>
			
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Capacita competenze artistiche: </div> 
			<div class="elemento-parametri-studente"> <%=curriculum.getCapacitaCompetenzeArtistiche() %></div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Capacita competenze personali: </div> 
			<div class="elemento-parametri-studente"> <%=curriculum.getCapacitaCompetenzePersonali() %> </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Capacita competenze organizzative: </div> 
			<div class="elemento-parametri-studente"> <%=curriculum.getCapacitaCompetenzeOrganizzative() %> </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Altre capacità e competenze: </div> 
			<div class="elemento-parametri-studente"> <%=curriculum.getAltreCapacitaCompetenze() %> </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Esperienza lavorativa:  </div> 
			<div class="elemento-parametri-studente"> <%=curriculum.getEsperienzaLavorativa() %> </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Madrelingua: </div> 
			<div class="elemento-parametri-studente"> <%=curriculum.getMadrelingua() %> </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Altre lingue </div> 
			<div class="elemento-parametri-studente"> <%=curriculum.getAltreLingue() %> </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Patenti: </div> 
			<div class="elemento-parametri-studente"> <%=curriculum.getPatenti() %> </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Ulteriori informazioni: </div> 
			<div class="elemento-parametri-studente"><%=curriculum.getUlterioriInformazioni() %></div>
			</div>
		
		</div>
		
		 <div class="col-md-3 cta-button"id="bottone-curriculum">
                            <a href="studente_curriculum.jsp" class="btn btn-lg btn-block btn-default" >Modifica curriculum</a>
                        </div>

		</div>
		
		
		<div id="profilo-dati-anagrafici">
		<h3 style="text-align: center;"><span>Dati Anagrafici </span></h3>
		
		<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Nome: </div> 
			<div class="elemento-parametri-studente"> <%=studente.getNome()%> </div>
			</div>
		
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Cognome: </div> 
			<div class="elemento-parametri-studente"> <%=studente.getCognome() %> </div>
			</div>
			
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Istruzione e formazione:  </div> 
			<div class="elemento-parametri-studente"> <%=studente.getIstruzioneFormazione()  %> </div>
			</div>
			
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Email: </div> 
			<div class="elemento-parametri-studente"> <%=studente.getEmail()%> </div>
			</div>
			
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Telefono: </div> 
			<div class="elemento-parametri-studente"> <%=studente.getTelefono()%></div>
			</div>
			
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Indirizzo: </div> 
			<div class="elemento-parametri-studente"> <%=studente.getIndirizzo() %></div>
			</div>
			
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Matricola:</div> 
			<div class="elemento-parametri-studente"> <%=studente.getMatricola() %>  </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Username: </div> 
			<div class="elemento-parametri-studente"> <%=studente.getUsername() %> </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Email istituzionale: </div> 
			<div class="elemento-parametri-studente"> <%=studente.getEmailIstituzionale() %> </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Media voti: </div> 
			<div class="elemento-parametri-studente"> <%=studente.getMediaVoti() %> </div>
			</div>
			
			
		<%} 
		%>
			
		
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>