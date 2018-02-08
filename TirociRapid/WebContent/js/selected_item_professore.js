function selectedItemProfessore()
{
	var path = location.pathname;

	
	switch(path)
	{
		case "/TirociRapid/professore.jsp": case"/TirociRapid/richieste":
			$("#item_richieste_professore").attr("class","attiva");
			break;
		case "/TirociRapid/storico_richieste.jsp": case"/TirociRapid/storico_richieste":
			$("#item_storico_professore").attr("class","attiva");
			break;
		default:
			break;
	}
}