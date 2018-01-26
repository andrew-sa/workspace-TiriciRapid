<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Studente Professori</title>
<link rel="stylesheet" href="css/studente.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

	<%@ include file="slider.html"%>

	<nav class="navbar navbar-default"
		style="background-color: #f90; margin-top: 20px">
	<div class="container">

		<ul class="nav nav-tabs">
			<li role="presentation"><a href="studenteRichieste.jsp">Richieste</a></li>
			<li role="presentation" ><a href="#">Aziende</a></li>
			<li role="presentation"id="attiva"><a href="#">Professori</a></li>
			<li role="presentation"><a href="studenteProfilo.jsp">Profilo</a></li>
			<li role="presentation" class="navbar-right"><a href="#">Logout
					&nbsp<i class="fa fa-power-off"></i>
			</a></li>

		</ul>
	</div>

	<!-- /.container-fluid --> </nav>

	<div class="container">
		<div class="col-sm-12">

			<div class="bs-calltoaction bs-calltoaction-default">
				<div class="row">
					<div class="col-md-9 cta-contents">
						<h1 class="cta-title">Nome Cognome Prof</h1>
						<div class="cta-desc">
							<p>
								<span class="parametri-professore">Ambito:</span> blabladaskjhbasdn
								vkjfadfbvk ajhndfvlk,n dfvlk jnaafhbvn, ansskjvf adgadbmkajb
								vtffubla
							</p>
							<p>
								<span class="parametri-professore">Email Istituzionale</span> blablabla
							</p>
							<p>
								<span class="parametri-professore">Email:</span> blablabla
							</p>
							<p>
								<span class="parametri-professore">Telefono:</span> blablabla
							</p>

						</div>
					</div>
					<div class="col-md-3 cta-button">
						<a href="#" class="btn btn-lg btn-block btn-default">Scegli come tutor</a>
					</div>
				</div>
			</div>


		</div>

	</div>
	<%@include file="footer.html"%>
</body>
</html>