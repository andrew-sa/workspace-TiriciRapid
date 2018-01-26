<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Responsabile approvazioni richieste nuovi tirocini</title>
<link rel="stylesheet" href="css/studente.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/professore.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

	<%@ include file="slider.html"%>

	<nav class="navbar navbar-default"
		style="background-color: #f90; margin-top: 20px">
	<div class="container">

		<ul class="nav nav-tabs">

			<li role="presentation" ><a href="responsabileApprovazioniRichiestaDiTutorato.jsp">Richieste di
					tutorato</a></li>
					<li role="presentation" id="attiva"><a href="#">Richieste nuovi tirocini</a></li>
					<li role="presentation" ><a href="#">Conferma finale</a></li>
					<li role="presentation" ><a href="#">Aziende</a></li>
			<li role="presentation" class="navbar-right"><a href="#">Logout
					&nbsp<i class="fa fa-power-off"></i>
			</a></li>

		</ul>
	</div>

	</nav>
	<div class="container">
		<div class="col-sm-12">

			<div class="bs-calltoaction bs-calltoaction-default">
				<div class="row">
					<div class="col-md-9 cta-contents">
						<h1 class="cta-title">Richiesta</h1>
						<div class="cta-desc">
							<h3>Azienda:</h3>
							<p style="padding-left: 1em;">
								Nome &nbsp; sede &emsp;
								<button class="bottoni-conferma-professore">Visualizza
									informazioni azienda</button>
							</p>
							<h3>Tirocinio:</h3>
							<div class="tabelle">
							<div class="parametri-tirocinio">
								<div class="elemento-parametri-tirocinio">Nome:</div>
								<div class="elemento-parametri-tirocinio">bla</div>
							</div>

							<div class="parametri-tirocinio">
								<div class="elemento-parametri-tirocinio">Descrizione:</div>
								<div class="elemento-parametri-tirocinio">bla</div>
							</div>

							<div class="parametri-tirocinio">
								<div class="elemento-parametri-tirocinio">Offerta formativa:</div>
								<div class="elemento-parametri-tirocinio">bla asdnbncsj jk
									wuyg jwdbsj fcbcaj dvsj zud jhg fduavzsjd cnuywyye hgsge fawg
									esu</div>
							</div>
						</div>
					</div>
					
					

				</div>
				<div class="col-md-3 cta-button"  >
						<a href="#" class="btn btn-lg btn-block btn-default" id="bottone-professore-accetta" style="display: block;">Accetta</a>
					</div>
					<div class="col-md-3 cta-button" >
						<a href="#" class="btn btn-lg btn-block btn-default" id="bottone-professore-rifiuta" style="display: block;" >Rifiuta</a>
					</div>
			</div>
		</div>
	</div>
	</div>
	<%@include file="footer.html"%>
</body>
</html>