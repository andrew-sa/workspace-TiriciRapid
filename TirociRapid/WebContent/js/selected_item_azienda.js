function selectedItemAzienda()
{
	var path = location.pathname;

	if (path.includes("elimina_tirocinio"))
	{
		$("#item_visualizza_tirocini_azienda").addClass("attiva");
		return;
	}
	
	switch(path)
	{
		case "/TirociRapid/azienda_aggiunta_tirocinio.jsp": case "/TirociRapid/crea_proposta_tirocinio":
			$("#item_aggiungi_tirocinio_azienda").attr("class","attiva");
			break;
		case "/TirociRapid/storico_richieste.jsp":
			$("#item_storico_richieste_azienda").attr("class","attiva");
			break;
		case "/TirociRapid/azienda_profilo.jsp": case "/TirociRapid/dati_azienda": 
		case "/TirociRapid/azienda_iscrizione.jsp": case "/TirociRapid/modifica_dati_azienda":
			$("#item_profilo_azienda").addClass("attiva");
			break;
		case "/TirociRapid/azienda_richieste.jsp": case"/TirociRapid/richieste":
			$("#item_richieste_azienda").addClass("attiva");
			break;
		case "/TirociRapid/azienda_visualizza_tirocini.jsp": case "/TirociRapid/tirocini_azienda":
			$("#item_visualizza_tirocini_azienda").addClass("attiva");
			break;
		default:
			break;
	}
}