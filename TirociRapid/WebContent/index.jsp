<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TirociRapid Login</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/errorcheck.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="js/formcheck.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/selected_item_login.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
</head>
<body>


	<%@ include file="slider.jsp"%>
	<%@ include file="nav_login.jsp"%>

	<div id="contenitore_login">
		<div id="messaggio"></div>
		<form>
			<input id="tipo" type="hidden" name="type" value="studente">
			<div>
				<input name="id" type="text" id="username"
					placeholder="Esse3 Username">
			</div>
			<div>
				<input name="password" type="password" id="password"
					placeholder="Password">
			</div>
			<button id="pulsante_login" type="button"
				onclick="sendRequestLogin(this.form);">Entra</button>

		</form>

		<div id="password_dimenticata" style="visibility: hidden;">
			<a id="registra_azienda" href="azienda_iscrizione.jsp">Registrati</a> &ensp; &ensp; &ensp;
			<a id="link_password_dimenticata" href="recupera_password_azienda.jsp">Password dimenticata?</a>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>