function selectedItemResponsabileApprovazioni()
{
	var path = location.pathname;
	var queryString = window.location.href.substring(window.location.href.indexOf('?'));
	console.log(queryString);
	
	if(queryString.includes("tutor"))
	{
		
		$("#item_richieste_di_tutorato_responsabile").attr("class","attiva");
		return;
	}
	if(queryString.includes("all"))
	{
		$("#item_conferma_finale_responsabile").attr("class","attiva");
		return;
	}
	
	
	switch(path)
	{
		case "/TirociRapid/responsabile_approvazioni_richieste_di_tutorato.jsp": case "/TirociRapid/richieste":
			$("#item_richieste_di_tutorato_responsabile").attr("class","attiva");
			break;
		case "/TirociRapid/responsabile_approvazioni_nuove_proposte_tirocinio.jsp": 
		case "/TirociRapid/carica_proposte_tirocinio":
			$("#item_richieste_nuovi_tirocini_responsabile").addClass("attiva");
			break;
		case "/TirociRapid/responsabile_approvazioni_conferma_finale.jsp": case "/TirociRapid/richieste":
			$("#item_conferma_finale_responsabile").addClass("attiva");
			break;
		case "/TirociRapid/responsabile_approvazioni_aziende.jsp": case "/TirociRapid/lista_aziende":
			$("#item_lista_aziende_responsabile").addClass("attiva");
			break;
		case "/TirociRapid/storico_richieste.jsp": case "/TirociRapid/storico_richieste":
			$("#item_storico_responsabile").attr("class","attiva");
			break;
		default:
			break;
	}
}