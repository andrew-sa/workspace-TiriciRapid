<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



<title>Studente curriculum</title>
<link rel="stylesheet" href="css/studente.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src ="js/script.js" type="text/javascript"></script>
	
</head>
<body>

<%@ include file="slider.jsp" %>


	<%@ include file="nav_studente.jsp"%>

<div class="container">
	<div class="row">
		<form class="form-horizontal">
<fieldset>





<!-- Form Name -->
<legend>Compila il tuo curriculum:</legend>

<!-- Fax input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="fax">Fax</label>  
  <div class="col-md-4">
  <input id="fax" name="fax" type="text"  class="form-control input-md"  maxlength="11" >
  </div>
</div>

<!-- Capacita Competenze Relazionali input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="capacitaCompetenzeRelazionali">Capacita' Competenze Relazionali </label>  
  <div class="col-md-4">
  <textarea onkeyup="maxlength(this,200,'conta1')" style="resize: none;" id="capacitaCompetenzeRelazionali"  name="capacitaCompetenzeRelazionali"  class="form-control input-md"  rows="4" cols="50" >  </textarea>     
    Hai a disposizione ancora <span id='conta1'>200</span> caratteri:<br/>
  </div>
</div>

<!-- Capacita Competenze Tecniche input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="capacitaCompetenzeTecniche">Capacita' Competenze Tecniche </label>  
  <div class="col-md-4">
  <textarea onkeyup="maxlength(this,200,'conta2')" style="resize: none;" id="capacitaCompetenzeTecniche"  name="capacitaCompetenzeTecniche"  class="form-control input-md"  rows="4" cols="50">  </textarea>     
  Hai a disposizione ancora <span id='conta2'>200</span> caratteri:<br/>
  </div>
</div>

<!-- Capacita Competenze Artistiche-->
<div class="form-group">
  <label class="col-md-4 control-label" for="capacitaCompetenzeArtistiche">Capacita' Competenze Artistiche </label>  
  <div class="col-md-4">
	<textarea onkeyup="maxlength(this,200,'conta3')" style="resize: none;" id="capacitaCompetenzeArtistiche"  name="capacitaCompetenzeArtistiche"  class="form-control input-md"  rows="4" cols="50">  </textarea>     
  	Hai a disposizione ancora <span id='conta3'>200</span> caratteri:<br/>
  </div>
</div>

<!-- Capacita Competenze Personali-->
<div class="form-group">
  <label class="col-md-4 control-label" for="capacitaCompetenzePersonali">Capacita' Competenze Personali </label>  
  <div class="col-md-4">
	<textarea  onkeyup="maxlength(this,200,'conta4')" style="resize: none;" id="capacitaCompetenzePersonali"  name="capacitaCompetenzePersonali"  class="form-control input-md"  rows="4" cols="50">  </textarea> 
    Hai a disposizione ancora <span id='conta4'>200</span> caratteri:<br/>
  </div>
</div>

<!--CapacitaCompetenzeOrganizzative	-->
<div class="form-group">
  <label class="col-md-4 control-label" for="capacitaCompetenzeOrganizzative">Capacita' Competenze Organizzative </label>  
  <div class="col-md-4">
  <textarea onkeyup="maxlength(this,200,'conta5')" style="resize: none;" id="capacitaCompetenzeOrganizzative"  name="capacitaCompetenzeOrganizzative"  class="form-control input-md"  rows="4" cols="50">  </textarea> 
  Hai a disposizione ancora <span id='conta5'>200</span> caratteri:<br/>
  </div>
</div>

<!-- AltreCapacitaCompetenze-->
<div class="form-group">
  <label class="col-md-4 control-label" for="altreCapacitaCompetenze">Altre Capacita' Competenze </label>  
  <div class="col-md-4">
  <textarea onkeyup="maxlength(this,200,'conta6')" style="resize: none;" id="altreCapacitaCompetenze"  name="altreCapacitaCompetenze"  class="form-control input-md"  rows="4" cols="50">  </textarea>
  Hai a disposizione ancora <span id='conta6'>200</span> caratteri:<br/>
  </div>
</div>


<!-- Capacita Esperienza Lavorativa-->
<div class="form-group">
  <label class="col-md-4 control-label" for="esperienzaLavorativa">Esperienza Lavorativa </label>  
  <div class="col-md-4">
  <textarea onkeyup="maxlength(this,200,'conta7')"style="resize: none;" id="esperienzaLavorativa"  name="esperienzaLavorativa"  class="form-control input-md"  rows="4" cols="50"> </textarea>
     Hai a disposizione ancora <span id='conta7'>200</span> caratteri:<br/>
  </div>
</div>



<!-- Altre Lingue-->
<div class="form-group">
  <label class="col-md-4 control-label" for="altreLingue">Altre Lingue </label>  
  <div class="col-md-4">
  <input id="altreLingue" name="altreLingue" type="text"  class="form-control input-md"  maxlength="100" >    
  </div>
</div>


<!-- Madre Lingua-->
<div class="form-group">
  <label class="col-md-4 control-label" for="madreLingua">Madre Lingua </label>  
  <div class="col-md-4">
  <input id="madreLingua" name="madreLingua" type="text"  class="form-control input-md"  maxlength="20" >
     
  </div>
</div>

<!-- Patenti-->
<div class="form-group">
  <label class="col-md-4 control-label" for="patenti">Patenti</label>  
  <div class="col-md-4">
  <input id="patenti" name="patenti" type="text"  class="form-control input-md"  maxlength="50">
     
  </div>
</div>

<!-- Ulteriori Informazioni-->
<div class="form-group">
  <label class="col-md-4 control-label" for="ulterioriInformazioni">Ulteriori Informazioni </label>  
  <div class="col-md-4">
  <!--<input id="ulterioriInformazioni" name="ulterioriInformazioni" type="text" placeholder="Ulteriori Informazioni..." class="form-control input-md" required=""> -->
   <textarea onkeyup="maxlength(this,200,'conta8')"style="resize: none;" id="ulterioriInformazioni"  name="ulterioriInformazioni"  class="form-control input-md"  rows="4" cols="50"></textarea> 
  Hai a disposizione ancora <span id='conta8'>200</span> caratteri:<br/>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Invia Curriculum</button>
  </div>
</div>

</fieldset>
</form>

	</div>
</div>

	<%@include file = "footer.jsp" %>

</body>

</html>