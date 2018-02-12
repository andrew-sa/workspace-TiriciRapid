<%@page import="it.tirocirapid.classes.model.UserLoggato"%>
<%@page import="it.tirocirapid.classes.model.Curriculum"%>
<%@page import="it.tirocirapid.classes.manager.AbstractCurriculumManager"%>
<%@page import="it.tirocirapid.factory.AbstractManagerFactory"%>
<%@page import="it.tirocirapid.factory.DAOFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Curriculum</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/studente.css">
<link rel="stylesheet" href="css/messaggi.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/errorcheck.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript" src="js/messaggi.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/script.js" type="text/javascript"></script>
<script src="js/formcheck.js" type="text/javascript"></script>
<script src="js/selected_item_studente.js" type="text/javascript"></script>


</head>
<body onload="nascondiMessaggiTop(); selectedItemStudente();">

	<%@ include file="slider.jsp"%>
	<%
	Curriculum curriculum = null;
	if(session.getAttribute("usernameStudente")!=null)
	{
	%>
		<%@ include file="nav_vuota.jsp"%>
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
	%>
		<div class="row">
			<form class="form-horizontal" onsubmit="return validateCurriculum(this)" action="inserisci_curriculum" method="post">
				<fieldset>
		<% 
	}
	else 
	{

		UserLoggato user = (UserLoggato)session.getAttribute("user");
		AbstractManagerFactory factory = new DAOFactory();
		AbstractCurriculumManager managerCurriculum = factory.createCurriculumManager();
		curriculum = managerCurriculum.read(user.getId());
	%>
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
	%>
	
		<div class="row">
			<form class="form-horizontal" onsubmit="return validateCurriculum(this)" action="modifica_curriculum"  method="post">
				<fieldset>
	<%} //FINE ELSE%>
	

					<!-- Form Name -->
					
					<legend>Compila il tuo curriculum:</legend>

					<!-- Capacita Competenze Relazionali input-->
					<div class="form-group">
						<label class="col-md-4 control-label"
							for="capacitaCompetenzeRelazionali">Capacita' Competenze
							Relazionali * </label>
						<div class="col-md-4" >
							<textarea onkeyup="maxlength(this,200,'conta1')" 
								style="resize: none;" id="capacitaCompetenzeRelazionali"
								name="capacitaCompetenzeRelazionali"
								class="form-control input-md" rows="4" cols="50"><%if(curriculum!=null){%><%= curriculum.getCapacitaCompetenzeRelazionali() %><% } %></textarea>
							Hai a disposizione ancora <span id='conta1'>200</span> caratteri:<br />
						</div>
						<p id="1" class="col-md-4 errorform"></p>
					</div>

					<!-- Capacita Competenze Tecniche input-->
					<div class="form-group">
						<label class="col-md-4 control-label"
							for="capacitaCompetenzeTecniche">Capacita' Competenze
							Tecniche * </label>
						<div class="col-md-4">
							<textarea onkeyup="maxlength(this,200,'conta2')"
								style="resize: none;" id="capacitaCompetenzeTecniche"
								name="capacitaCompetenzeTecniche" class="form-control input-md"
								rows="4" cols="50"><%if(curriculum!=null){%><%= curriculum.getCapacitaCompetenzeTecniche()%><% } %></textarea>
							Hai a disposizione ancora <span id='conta2'>200</span> caratteri:<br />
						</div>
						<p id="2" class="col-md-4 errorform"></p>
					</div>

					<!-- Capacita Competenze Artistiche-->
					<div class="form-group">
						<label class="col-md-4 control-label"
							for="capacitaCompetenzeArtistiche">Capacita' Competenze
							Artistiche *</label>
						<div class="col-md-4">
							<textarea onkeyup="maxlength(this,200,'conta3')"
								style="resize: none;" id="capacitaCompetenzeArtistiche"
								name="capacitaCompetenzeArtistiche"
								class="form-control input-md" rows="4" cols="50"><%if(curriculum!=null){%><%= curriculum.getCapacitaCompetenzeArtistiche() %><% } %></textarea>
							Hai a disposizione ancora <span id='conta3'>200</span> caratteri:<br />
						</div>
						<p id="3" class="col-md-4 errorform"></p>
					</div>

					<!-- Capacita Competenze Personali-->
					<div class="form-group">
						<label class="col-md-4 control-label"
							for="capacitaCompetenzePersonali">Capacita' Competenze
							Personali *</label>
						<div class="col-md-4">
							<textarea onkeyup="maxlength(this,200,'conta4')"
								style="resize: none;" id="capacitaCompetenzePersonali"
								name="capacitaCompetenzePersonali" class="form-control input-md"
								rows="4" cols="50"><%if(curriculum!=null){%><%= curriculum.getCapacitaCompetenzePersonali() %><% } %></textarea>
							Hai a disposizione ancora <span id='conta4'>200</span> caratteri:<br />
						</div>
						<p id="4" class="col-md-4 errorform"></p>
					</div>

					<!--CapacitaCompetenzeOrganizzative	-->
					<div class="form-group">
						<label class="col-md-4 control-label"
							for="capacitaCompetenzeOrganizzative">Capacita'
							Competenze Organizzative *</label>
						<div class="col-md-4">
							<textarea onkeyup="maxlength(this,200,'conta5')"
								style="resize: none;" id="capacitaCompetenzeOrganizzative"
								name="capacitaCompetenzeOrganizzative"
								class="form-control input-md" rows="4" cols="50"><%if(curriculum!=null){%><%= curriculum.getCapacitaCompetenzeOrganizzative() %><% } %></textarea>
							Hai a disposizione ancora <span id='conta5'>200</span> caratteri:<br />
						</div>
						<p id="5" class="col-md-4 errorform"></p>
					</div>

					<!-- AltreCapacitaCompetenze-->
					<div class="form-group">
						<label class="col-md-4 control-label"
							for="altreCapacitaCompetenze">Altre Capacita' Competenze
						</label>
						<div class="col-md-4">
							<textarea onkeyup="maxlength(this,200,'conta6')"
								style="resize: none;" id="altreCapacitaCompetenze"
								name="altreCapacitaCompetenze" class="form-control input-md"
								rows="4" cols="50"><%if(curriculum!=null){%><%= curriculum.getAltreCapacitaCompetenze() %><% } %></textarea>
							Hai a disposizione ancora <span id='conta6'>200</span> caratteri:<br />
						</div>
						<p id="6" class="col-md-4 errorform"></p>
					</div>


					<!-- Capacita Esperienza Lavorativa-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="esperienzaLavorativa">Esperienza
							Lavorativa *</label>
						<div class="col-md-4">
							<textarea onkeyup="maxlength(this,200,'conta7')"
								style="resize: none;" id="esperienzaLavorativa"
								name="esperienzaLavorativa" class="form-control input-md"
								rows="4" cols="50"><%if(curriculum!=null){%><%= curriculum.getEsperienzaLavorativa() %><% } %></textarea>
							Hai a disposizione ancora <span id='conta7'>200</span> caratteri:<br />
						</div>
						<p id="7" class="col-md-4 errorform"></p>
					</div>

					<!-- Madrelingua-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="madreLingua">Madrelingua *
						</label>
						<div class="col-md-4">
							<input id="madrelingua" name="madrelingua" type="text"
								class="form-control input-md" maxlength="20" value="<%if(curriculum!=null){%><%= curriculum.getMadrelingua() %><% } %>">
						</div>
						<p id="8" class="col-md-4 errorform"></p>
					</div>


					<!-- Altre Lingue-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="altreLingue">Altre
							Lingue *</label>
						<div class="col-md-4">
							<input id="altreLingue" name="altreLingue" type="text"
								class="form-control input-md" maxlength="100" value="<%if(curriculum!=null){%><%= curriculum.getAltreLingue() %><% } %>">
						</div>
						<p id="9" class="col-md-4 errorform"></p>
					</div>

					<!-- Patenti-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="patenti">Patenti *</label>
						<div class="col-md-4">
							<input id="patenti" name="patenti" type="text"
								class="form-control input-md" maxlength="50" value="<%if(curriculum!=null){%><%= curriculum.getPatenti() %><% } %>">
						</div>
						<p id="10" class="col-md-4 errorform"></p>
					</div>

					<!-- Ulteriori Informazioni-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="ulterioriInformazioni">Ulteriori
							Informazioni *</label>
						<div class="col-md-4">
							<!--<input id="ulterioriInformazioni" name="ulterioriInformazioni" type="text" placeholder="Ulteriori Informazioni..." class="form-control input-md" required=""> -->
							<textarea onkeyup="maxlength(this,200,'conta8')"
								style="resize: none;" id="ulterioriInformazioni"
								name="ulterioriInformazioni" class="form-control input-md"
								rows="4" cols="50"><%if(curriculum!=null){%><%= curriculum.getUlterioriInformazioni() %><% } %></textarea>
							Hai a disposizione ancora <span id='conta8'>200</span> caratteri:<br />
						</div>
						<p id="11" class="col-md-4 errorform"></p>
					</div>

					<!-- Fax input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="fax">Fax</label>
						<div class="col-md-4">
							<input id="fax" name="fax" type="text"
								class="form-control input-md" maxlength="11" value="<%if(curriculum!=null){%><%= curriculum.getFax() %><% } %>">
						</div>
						<p id="12" class="col-md-4 errorform"></p>
						<label class="col-md-4 control-label" for="fax">*questi campi sono obbligatori</label>
					</div>
					
					<!-- Button -->
					<div class="form-group">
						<label class="col-md-4 control-label" for="submit"></label>
						<div class="col-md-4">
							<button id="suSbmit" name="submit" type="submit" class="btn btn-primary">Invia
								Curriculum</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>

	<%@include file="footer.jsp"%>

</body>

</html>