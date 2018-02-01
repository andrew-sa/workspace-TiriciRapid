<%@page import="it.tirocirapid.classes.manager.AbstractStudenteManager"%>
<%@page import="it.tirocirapid.factory.DAOFactory"%>
<%@page import="it.tirocirapid.factory.AbstractManagerFactory"%>
<%@page import="it.tirocirapid.classes.model.Studente"%>
<%@page import="it.tirocirapid.classes.model.UserLoggato"%>
<%@page import="it.tirocirapid.classes.manager.StudenteDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Studente profilo</title>
<link rel="stylesheet" href="css/studente.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="js/selected_item_studente.js"></script>	
</head>
<body onload="selectedItemStudente();">

	<%@ include file="slider.jsp"%>

	<%@ include file="nav_studente.jsp"%>



	<%if(session.getAttribute("user")!=null){
		UserLoggato user = (UserLoggato) session.getAttribute("user");
		AbstractManagerFactory factory = new DAOFactory();
		AbstractStudenteManager managerStudente = factory.createStudenteManager();
		Studente studente = managerStudente.read(user.getId());	%>
	

	<div class="container">
	
		<div id="profilo-curriculum">
		
		<h3 style="text-align: center;"><span>Curriculum </span></h3>
		<div class="tabelle">
		
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Fax: </div> 
			<div class="elemento-parametri-studente"> bla </div>
			</div>
		
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Capacita competenze relazionali: </div> 
			<div class="elemento-parametri-studente"> bla </div>
			</div>
			
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Capacita competenze tecniche:</div> 
			<div class="elemento-parametri-studente"> bla asdnbncsj jk wuyg jwdbsj fcbcaj  dvsj zud jhg fduavzsjd cnuywyye hgsge fawg esu </div>
			</div>
			
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Capacita competenze artistiche: </div> 
			<div class="elemento-parametri-studente"> bla </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Capacita competenze personali: </div> 
			<div class="elemento-parametri-studente"> bla </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Capacita competenze organizzative: </div> 
			<div class="elemento-parametri-studente"> bla </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Altre capacità e competenze: </div> 
			<div class="elemento-parametri-studente"> bla </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Esperienza lavorativa:  </div> 
			<div class="elemento-parametri-studente"> bla </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Altre lingue </div> 
			<div class="elemento-parametri-studente"> bla </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Madrelingua: </div> 
			<div class="elemento-parametri-studente"> bla </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Patenti: </div> 
			<div class="elemento-parametri-studente"> bla </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Ulteriori informazioni: </div> 
			<div class="elemento-parametri-studente"> bla </div>
			</div>
		
		</div>
		
		 <div class="col-md-3 cta-button"id="bottone-curriculum">
                            <a href="#" class="btn btn-lg btn-block btn-default" >Modifica curriculum</a>
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
			<div class="elemento-parametri-studente"> bla </div>
			</div>
			
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Istruzione e formazione:  </div> 
			<div class="elemento-parametri-studente"> bla </div>
			</div>
			
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Email: </div> 
			<div class="elemento-parametri-studente"> bla </div>
			</div>
			
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Telefono: </div> 
			<div class="elemento-parametri-studente"> bla </div>
			</div>
			
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Indirizzo: </div> 
			<div class="elemento-parametri-studente"> bla </div>
			</div>
			
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Matricola:</div> 
			<div class="elemento-parametri-studente"> bla asdnbncsj jk wsvkj shjjkse fdbjkb xjkb ej vndbxjhv dkjd v vx kjj bs  ajuja  </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Username: </div> 
			<div class="elemento-parametri-studente"> bla </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Email istituzionale: </div> 
			<div class="elemento-parametri-studente"> bljjjgaya </div>
			</div>
			<div class="parametri-studente">
			<div class="elemento-parametri-studente"> Media voti: </div> 
			<div class="elemento-parametri-studente"> bla </div>
			</div>
			
			
		<%} %>
		
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>