function selectedItemLogin(elemento){

	//Qui seleziono l'elemento attivo

	$("#item_studente").removeClass("attiva");
	$("#item_azienda").removeClass("attiva");
	$("#item_professore").removeClass("attiva");
	$(elemento).attr("class","attiva");

	//qui cambio i parametri per farli diventare quelli del login selezionato

	var recupero_id = $(elemento).attr("id");   

	if(recupero_id==="item_studente")
	{
		$("#username").attr("placeholder","Esse3 Username");
		$("#username").attr("name","username");
		$("#tipo").attr("value","studente");
		$("#password_dimenticata").css("visibility","hidden");
	}

	if(recupero_id==="item_azienda")
	{
		$("#username").attr("placeholder","Partita IVA");
		$("#username").attr("name","partitaIVA");
		$("#tipo").attr("value","azienda");
		$("#password_dimenticata").css("visibility","visible");
	}

	if(recupero_id==="item_professore")
	{
		$("#username").attr("placeholder","Esse3 Username");
		$("#username").attr("name","username");
		$("#tipo").attr("value","professore");
		$("#password_dimenticata").css("visibility","hidden");
	}
}
