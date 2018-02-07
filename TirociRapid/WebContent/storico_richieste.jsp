<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Storico richieste</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/azienda.css">

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="js/formcheck.js"></script>
<script type="text/javascript" src="js/selected_item_azienda.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>

</head>
<body onload="selectedItemAzienda();">

	<%@ include file="slider.jsp"%>

	<%@ include file="nav_azienda.jsp"%>


	<div class="container">
		<div class="col-sm-12">

			<div class="bs-calltoaction bs-calltoaction-default">
				<div class="row" id="divstorico">
					<div class="col-md-9 cta-contents">
						<h1 class="cta-title">Richiesta</h1>
						<div class="cta-desc">
							<h3>Studente:</h3>
							<p>
								Nome &nbsp; Cognome &emsp; 0512103653 &emsp;
								<button class="bottoni-conferma-azienda">Visualizza
									curriculum</button>
							</p>

							<h3>Tirocinio:</h3>
							<p>
								Nome &emsp;
								<button class="bottoni-conferma-azienda">Visualizza
									informazioni tirocinio</button>
							</p>
						</div>
					</div>

					<div id = "stato-storico">
						<h1>Stato:</h1>
						<h3>la capp kjusdbj j e hfrfjv llo</h3>
					</div>
				</div>
			</div>
		</div>
	</div>



	<%@include file="footer.jsp"%>
</body>
</html>