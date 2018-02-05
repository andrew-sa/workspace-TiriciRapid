<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Azienda tirocinio</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript" src="js/formcheck.js"></script>
<script type="text/javascript" src="js/selected_item_azienda.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/script.js"></script>

</head>
<body onload="selectedItemAzienda();">
<%@ include file="slider.jsp" %>

<%if ((request.getAttribute("successo") != null) && (request.getAttribute("successo").toString().
		equals("Inserisici una proposta di tirocinio per salvare l'azienda")) 
		 )
		{ %>
		<%@ include file="nav_vuota.jsp" %>
		<%} else{%>
<%@ include file="nav_azienda.jsp" %>
<%} %>

<div class="container">
	<div class="row">
	<%if ((request.getAttribute("successo") != null))
		{%>
	<div style="color: green; text-align: center;"><%=request.getAttribute("successo")%></div>
	<%}%>
		<%if ((request.getAttribute("errore") != null))
		{%>
	<div style="color: red; text-align: center;"><%=request.getAttribute("errore")%></div>
	<%}%>
		<form method="post" action="crea_proposta_tirocinio" class="form-horizontal">
		<fieldset>
		<!-- Form Name -->
		<legend>Tirocinio:</legend>
		
		<!-- Nome-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="nome">Nome</label>  
		  <div class="col-md-4">
		  <input id="nome" name="nome" type="text"  class="form-control input-md"  maxlength="50" >
		  </div>
		</div>
		
		<!-- Offerta Formativa input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="offertaFormativa">Offerta Formativa</label>  
		  <div class="col-md-4">
		  <textarea onkeyup="maxlength(this,500,'conta1')" style="resize: none;" id="offertaFormativa"  name="offertaFormativa"  class="form-control input-md"  rows="4" cols="50" ></textarea>     
		    Hai a disposizione ancora <span id='conta1'>500</span> caratteri:<br/>
		  </div>
		</div>
		
		<!-- Descrizione-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="descrizione">Descrizione </label>  
		  <div class="col-md-4">
		  <textarea onkeyup="maxlength(this,500,'conta2')" style="resize: none;" id="descrizione"  name="descrizione"  class="form-control input-md"  rows="4" cols="50"></textarea>     
		  Hai a disposizione ancora <span id='conta2'>500</span> caratteri:<br/>
		  </div>
		</div>
		
		
		<!-- Button -->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="submit"></label>
		  <div class="col-md-4">
		    <button id="submit" name="submit" class="btn btn-primary">Aggiungi Tirocinio</button>
		  </div>
		</div>
		
		</fieldset>
	</form>
	</div>
</div>

	<%@include file ="footer.jsp" %>


</body>

</html>