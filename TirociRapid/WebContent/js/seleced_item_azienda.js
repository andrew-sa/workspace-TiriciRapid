function selectedItemAzienda()
{
	var path = location.pathname;

	
	switch(path)
	{
		case "/TirociRapid/azienda_aggiunta_tirocinio.jsp":
			$("#item_aggiungi_tirocinio_azienda").attr("class","attiva");
			break;
		case "/TirociRapid/azienda_profilo.jsp":
			$("#item_profilo_azienda").addClass("attiva");
			break;
		case "/TirociRapid/azienda_richieste.jsp":
			$("#item_richieste_azienda").addClass("attiva");
			break;
		case "/TirociRapid/azienda_visualizza_tirocini.jsp":
			$("#item_visualizza_tirocini_azienda").addClass("attiva");
			break;
		default:
			break;
	}
}