function selectedItemStudente()
{
	var path = location.pathname;
	switch(path)
	{
		case "/TirociRapid/studente_aziende.jsp":
			$("#item_lista_aziende_studente").attr("class","attiva");
			break;
		case "/TirociRapid/studente_profilo.jsp" :case "/TirociRapid/profilo_studente":
			$("#item_profilo_studente").addClass("attiva");
			break;
		case "/TirociRapid/studente_richieste.jsp":
			$("#item_richieste_studente").addClass("attiva");
			break;
		case "/TirociRapid/studente_professori.jsp" :case "/TirociRapid/lista_professori":
			$("#item_lista_professori_studente").addClass("attiva");
			break;
		default:
			break;
	}
}