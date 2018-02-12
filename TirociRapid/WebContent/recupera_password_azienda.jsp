<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Recupera password</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript" src="js/formcheck.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/selected_item_login.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>

</head>
<body>
	<%@ include file="slider.jsp"%>
	<%@ include file="nav_vuota.jsp"%>

	<div id="contenitore_login">
		<form action="recupera_password" method="get">
			<fieldset>
				<div>
					<input name="partitaIVA" type="text" id="username" placeholder="Partita IVA">
				</div>
			</fieldset>
			<button id="pulsante_login" type="submit">Recupera Password</button>
		</form>
	</div>

	<%@include file="footer.jsp"%>
</body>
</html>