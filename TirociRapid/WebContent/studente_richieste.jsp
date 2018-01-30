<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Studente richieste</title>
<link rel="stylesheet" href="css/studente.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="js/selected_item_studente.js"></script>
</head>
<body onload="selectedItemStudente();">

    <%@ include file="slider.jsp" %>

	<%@ include file="nav_studente.jsp"%>
	
	<div class="container">
            <div class="col-sm-12">

                <div class="bs-calltoaction bs-calltoaction-default">
                    <div class="row">
                        <div class="col-md-9 cta-contents">
                            <h1 class="cta-title">Richiesta 1</h1>
                            <div class="cta-desc">
                                <h3>Stato: blablabla</h3>
                                <p>Describe the action hereahjfsdbdfbadjcba jsbxjkasb djav sx mnzc asmnx basxb msbx cmx dbs dbjabskj b asd b xjbadj bj bjmz bxvbz.</p>
                                
                            </div>
                        </div>
                        <div class="col-md-3 cta-button">
                            <a href="#" class="btn btn-lg btn-block btn-default">Elimina!</a>
                        </div>
                     </div>
           </div>
		
		
		</div>
		 <div class="col-sm-12">

                <div class="bs-calltoaction bs-calltoaction-default">
                    <div class="row">
                        <div class="col-md-9 cta-contents">
                            <h1 class="cta-title">Richiesta 2</h1>
                            <div class="cta-desc">
                                <h3>Stato: blablabla</h3>
                                <p>Describe the action hereahjfsdbdfbadjcba jsbxjkasb djav sx mnzc asmnx basxb msbx cmx dbs dbjabskj b asd b xjbadj bj bjmz bxvbz.</p>
                                
                            </div>
                        </div>
                        <div class="col-md-3 cta-button">
                            <a href="#" class="btn btn-lg btn-block btn-default">Elimina!</a>
                        </div>
                     </div>
           </div>
		
		
		</div>
		
	</div>
	<%@include file="footer.jsp" %>
</body>
</html>