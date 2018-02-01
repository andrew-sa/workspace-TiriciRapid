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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript" src="js/formcheck.js"></script>
<script type="text/javascript" src="js/selected_item_azienda.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>

</head>
<body onload="selectedItemAzienda();">

	<%@ include file="slider.jsp"%>

</body>
<%@ include file="nav_azienda.jsp"%>

<div class="container">
	<div class="row">
		<form class="form-horizontal" onsubmit="return validateRegistrazione(this)">
		
			<fieldset>
				<!-- Form Name -->
				<legend>Immettere i dati Aziendali:</legend>

				<!-- PartitaIVA input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="partitaIVA">Partita
						IVA</label>
					<div class="col-md-4">
						<input id="partitaIVA" name="partitaIVA" type="text"
							class="form-control input-md" maxlength="11">
					</div>
					<p id="1" class="col-md-4 errorform">
					</p>
				</div>

				<!-- Nome Azienda input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="nome">Nome </label>
					<div class="col-md-4 ">
						<input id="nome" name="nome" type="text"
							class="form-control input-md" maxlength="50">
					</div>
					<p id="2" class="col-md-4 errorform" >
					</p>
				</div>

				<!-- Sede Aziendale input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="sede">Sede </label>
					<div class="col-md-4">
						<input id="sede" name="sede" type="text"
							class="form-control input-md" maxlength="39">
					</div>
					<p id="3" class="col-md-4 errorform" >
					</p>
				</div>

				<!-- Descrizione Ambito-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="descrizioneAmbito">Descrizione
						Ambito </label>
					<div class="col-md-4">
						<textarea onkeyup="maxlength(this,500,'conta3')"
							style="resize: none;"id="descrizioneAmbito"
							name="descrizioneAmbito" class="form-control input-md" rows="4"
							cols="50"></textarea>
						Hai a disposizione ancora <span id='conta3'>500</span> caratteri:<br />
					</div>
					<p id="4" class="col-md-4 errorform" >
					</p>
				</div>

				<!-- Numero Telefono-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="numeroTelefono">Numero
						di Telefono </label>
					<div class="col-md-4">
						<input id="numeroTelefono" name="numeroTelefono" type="text"
							class="form-control input-md" maxlength="13">
					</div>
					<p id="5" class="col-md-4 errorform" >
					</p>
				</div>

				<!-- Email-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="email">Email </label>
					<div class="col-md-4">
						<input id="email" name="email" type="text"
							class="form-control input-md" maxlength="50">
					</div>
					<p id="6" class="col-md-4 errorform" >
					</p>
				</div>

				<!-- Password-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="password">Password
					</label>
					<div class="col-md-4">
						<input id="password" name="password" type="text"
							class="form-control input-md" maxlength="20">
					</div>
					<p id="7" class="col-md-4 errorform" >
					</p>
				</div>

				<!-- Conferma Password-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="confPassword">Conferma
						Password </label>
					<div class="col-md-4">
						<input id="confPassword" name="confPassword" type="text"
							class="form-control input-md" maxlength="20">
					</div>
					<p id="8" class="col-md-4 errorform" >
					</p>
				</div>


				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="submit"></label>
					<div class="col-md-4">
						<button type="submit" id="submit" name="submit" class="btn btn-primary" >Invia
							dati aziendali</button>
					</div>
				</div>

			</fieldset>
		</form>

	</div>
</div>

<%@include file="footer.jsp"%>
<script type="text/javascript" src="js/formcheck.js"></script>

</body>

</html>