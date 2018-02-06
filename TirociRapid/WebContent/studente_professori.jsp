<%@page import="java.util.ArrayList"%>
<%@page import="it.tirocirapid.classes.model.Professore"%>
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
<script src="js/selected_item_studente.js"></script>
</head>
<body onload="selectedItemStudente();">

	<%@ include file="slider.jsp"%>

	<%@ include file="nav_studente.jsp"%>

	<div class="container">
		<%
			if (request.getAttribute("professori") != null) {
				ArrayList<Professore> professori = (ArrayList<Professore>) request.getAttribute("professori");
				for (Professore p : professori) {
		%>

		<div class="col-sm-12">

			<div class="bs-calltoaction bs-calltoaction-default">
				<div class="row">
					<div class="col-md-9 cta-contents">
						<h1 class="cta-title"><%=p.getNome() %> <%=p.getCognome() %></h1>
						<div class="cta-desc">
							<p>
								<span class="parametri-professore">Ambito:</span>
								<%=p.getAmbito() %>
							</p>
							<p>
								<span class="parametri-professore">Email Istituzionale: </span>
								<%=p.getEmailIstituzionale() %>
							</p>
							<p>
								<span class="parametri-professore">Email:</span> <%=p.getEmail() %>
							</p>
							<p>
								<span class="parametri-professore">Telefono:</span> <%=p.getTelefono() %>
							</p>

						</div>
					</div>
					<div class="col-md-3 cta-button">
						<a href="#" class="btn btn-lg btn-block btn-default">Scegli
							come tutor</a>
					</div>
				</div>
			</div>


		</div>
		<%
			} //FINE FOR

			} // FINE IF
			
			if(request.getAttribute("errore")!=null){
		%>
				<div style="color: red; text-align: center;"><%=request.getAttribute("errore").toString()%></div>		
		<%} %>
		
		
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>