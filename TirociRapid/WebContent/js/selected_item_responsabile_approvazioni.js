function selectedItemResponsabileApprovazioni()
{
	var path = location.pathname;

	
	switch(path)
	{
		case "/TirociRapid/responsabile_approvazioni_richieste_di_tutorato.jsp":
			$("#item_richieste_di_tutorato_responsabile").attr("class","attiva");
			break;
		case "/TirociRapid/responsabile_approvazioni_richieste_nuovi_tirocini.jsp":
			$("#item_richieste_nuovi_tirocini_responsabile").addClass("attiva");
			break;
		case "/TirociRapid/responsabile_approvazioni_conferma_finale.jsp":
			$("#item_conferma_finale_responsabile").addClass("attiva");
			break;
		case "/TirociRapid/responsabile_approvazioni_aziende.jsp":
			$("#item_lista_aziende_responsabile").addClass("attiva");
			break;
		default:
			break;
	}
}