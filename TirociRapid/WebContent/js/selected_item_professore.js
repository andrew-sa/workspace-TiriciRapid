function selectedItemProfessore()
{
	var path = location.pathname;

	
	switch(path)
	{
		case "/TirociRapid/professore.jsp":
			$("#item_richieste_professore").attr("class","attiva");
			break;
		case "/TirociRapid/storico_richieste.jsp":
			$("#item_storico_professore").attr("class","attiva");
			break;
		default:
			break;
	}
}