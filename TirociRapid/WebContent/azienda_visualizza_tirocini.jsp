<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Azienda visualizza tirocini</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<%@ include file="slider.html" %>

	<%@ include file="nav_azienda.html" %>
	
		<div class="container">
		<div class="col-sm-12">

			<div class="bs-calltoaction bs-calltoaction-default">
				<div class="row">
					<div class="col-md-9 cta-contents">
						<h1 class="cta-title">NomeTirocinio</h1>
						<div class="cta-desc">
							<p>
								<span class="parametri-azienda">Stato:</span> bla
							</p>
							<p>
								<span class="parametri-azienda">Descrizione:</span> blablabla
							</p>
							<p>
								<span class="parametri-azienda">Offerta Formativa:</span> blablabla
							</p>
						</div>
					</div>
					<div class="col-md-3 cta-button">
						<a href="#" class="btn btn-lg btn-block btn-default">Elimina</a>
					</div>
				</div>
			</div>


		</div>
		</div>
	
	
	
	<%@include file="footer.html" %>
</body>
</html>