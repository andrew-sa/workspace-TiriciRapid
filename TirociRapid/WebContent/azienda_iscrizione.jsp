<%@page import="it.tirocirapid.classes.model.UserLoggato"%>
<%@page import="it.tirocirapid.classes.model.Azienda"%>
<%@page import="it.tirocirapid.classes.manager.AbstractAziendaManager"%>
<%@page import="it.tirocirapid.factory.AbstractManagerFactory"%>
<%@page import="it.tirocirapid.factory.DAOFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Azienda iscrizione</title>
<link rel="stylesheet" href="css/errorcheck.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/messaggi.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript" src="js/formcheck.js"></script>
<script type="text/javascript" src="js/selected_item_azienda.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="js/messaggi.js"></script>


</head>
<body onload="selectedItemAzienda();nascondiMessaggiTop();">
	
<%@ include file="slider.jsp"%>

	<%
	Azienda azienda = null;
	boolean loggato = false;
	if(session.getAttribute("user")==null)
	{ 
		loggato = false;
	%>
		<%@ include file="nav_vuota.jsp"%>
		
	<%
	}
	else
	{
		loggato = true;
		UserLoggato user = (UserLoggato)session.getAttribute("user");
		AbstractManagerFactory factory = new DAOFactory();
		AbstractAziendaManager managerAzienda = factory.createAziendaManager();
		azienda = managerAzienda.read(user.getId());
	%>
		<%@ include file="nav_azienda.jsp"%>
	<%
	}
	%>

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
		<%
		if(loggato) 
		{
		%>
		<form action="modifica_dati_azienda" method="post"
			class="form-horizontal" onsubmit="return validateRegistrazione(this)">
		<%
		}
		else
		{
		%>
		<form action="registrazione_azienda" method="post"
			class="form-horizontal" onsubmit="return validateRegistrazione(this)">
		<%
		}
		%>
			<fieldset>
				<!-- Form Name -->
				<legend>Immettere i dati Aziendali:</legend>

				<!-- PartitaIVA input-->
				<%
				if(!loggato)
				{ 
				%>
				<div class="form-group">
					<label class="col-md-4 control-label" for="partitaIVA">Partita
						IVA</label>
					<div class="col-md-4">
						<input id="partitaIVA" name="partitaIVA" type="text"
							class="form-control input-md" maxlength="11">
					</div>
					<p id="1" class="col-md-4 errorform"></p>
				</div>
				<%
				}
				%>

				<!-- Nome Azienda input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="nome">Nome </label>
					<div class="col-md-4 ">
						<input id="nome" name="nome" type="text"
							class="form-control input-md" maxlength="50" value=<%if(loggato){%><%=azienda.getNome() %> <% }%> >
					</div>
					<p id="2" class="col-md-4 errorform"></p>
				</div>
				<%
				if(loggato)
				{
				%>
				<!-- Stato Aziendale input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="sede">Stato </label>
					<div class="col-md-4">
						<!--  <select>
						  <option name="stato" value="AccettaRichieste">Accetta Richieste</option>
						  <option value="NonAccettaRichieste">Non Accetta Richieste</option>
						</select>-->
						<input id="sede" name="stato" type="text"
							class="form-control input-md" maxlength="39" value=<%if(loggato){%><%=azienda.getStato() %> <% }%>>
					</div>
					<p id="3" class="col-md-4 errorform"></p>
				</div>
				
				
				<%
				}
				%>
				<!-- Sede Aziendale input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="sede">Sede </label>
					<div class="col-md-4">
						<input id="sede" name="sede" type="text"
							class="form-control input-md" maxlength="39" value=<%if(loggato){%><%=azienda.getSede() %> <% }%>>
					</div>
					<p id="3" class="col-md-4 errorform"></p>
				</div>

				<!-- Descrizione Ambito-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="descrizioneAmbito">Descrizione
						Ambito </label>
					<div class="col-md-4">
						<textarea onkeyup="maxlength(this,500,'conta3')"
							style="resize: none;" id="descrizioneAmbito"
							name="descrizioneAmbito" class="form-control input-md" rows="4"
							cols="50"><%if(loggato){%><%=azienda.getDescrizioneAmbito() %><% }%></textarea>
						Hai a disposizione ancora <span id='conta3'>500</span> caratteri:<br />
					</div>
					<p id="4" class="col-md-4 errorform"></p>
				</div>

				<!-- Numero Telefono-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="numeroTelefono">Numero
						di Telefono </label>
					<div class="col-md-4">
						<input id="numeroTelefono" name="numeroTelefono" type="text"
							class="form-control input-md" maxlength="13" value=<%if(loggato){%><%=azienda.getNumeroTelefono() %><% }%>>
					</div>
					<p id="5" class="col-md-4 errorform"></p>
				</div>

				<!-- Email-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="email">Email </label>
					<div class="col-md-4">
						<input id="email" name="email" type="text"
							class="form-control input-md" maxlength="50" value=<%if(loggato){%><%=azienda.getEmail() %><% }%>>
					</div>
					<p id="6" class="col-md-4 errorform"></p>
				</div>

				<!-- Password-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="password">Password
					</label>
					<div class="col-md-4">
						<input id="password" name="password" type="password"
							class="form-control input-md" maxlength="20" value=<%if(loggato){%><%=azienda.getPassword() %><% }%>>
					</div>
					<p id="7" class="col-md-4 errorform"></p>
				</div>

				<!-- Conferma Password-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="confPassword">Conferma
						Password </label>
					<div class="col-md-4">
						<input id="confPassword" name="confPassword" type="password"
							class="form-control input-md" maxlength="20" value=<%if(loggato){%><%=azienda.getPassword() %><% }%>>
					</div>
					<p id="8" class="col-md-4 errorform"></p>
				</div>


				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="submit"></label>
					<div class="col-md-4">
						<button type="submit" id="submit" name="submit"
							class="btn btn-primary">Invia dati aziendali</button>
					</div>
				</div>

			</fieldset>
		</form>

	</div>
</div>

<%@include file="footer.jsp"%>

</body>

</html>