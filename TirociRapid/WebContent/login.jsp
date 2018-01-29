<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TirociRapid</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>


</head>
<body>

	<%@include file ="slider.html"  %>


	<nav class="navbar navbar-default"
		style="background-color: #f90; margin-top: 20px">
		<div class="container">

			<ul class="nav nav-tabs">
				<li role="presentation" id="attiva"><a href="#">Studente</a></li>
				<li role="presentation"><a href="#">Azienda</a></li>
				<li role="presentation"><a href="#">Professore</a></li>
			</ul>

		</div>
		<!-- /.container-fluid -->
	</nav>

	<div class="container2">
		<div class="card card-container">

			<form class="form-signin">
				<span id="reauth-email" class="reauth-email"></span> <input
					type="email" id="inputEmail" class="form-control"
					placeholder="Esse3 Username" required autofocus> <input
					type="password" id="inputPassword" class="form-control"
					placeholder="Password" required>

				<button class="btn btn-lg btn-primary btn-block btn-signin"
					type="submit" style="background-color: #008db3;">Entra</button>

				<a href="#"> Password dimenticata? </a>
			</form>
			<!-- /form -->
		</div>
		<!-- /card-container -->
	</div>
	<!-- /container -->

	<%@include file ="footer.html"  %>



</body>

</html>