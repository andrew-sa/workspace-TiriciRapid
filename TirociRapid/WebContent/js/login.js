var xhttp;
var inAttesa = false;

function readResultLogin()
{

	if (xhttp.readyState == 4 && xhttp.status == 200)
	{


		var responseXML = xhttp.responseXML;
		var xmlDoc = responseXML.documentElement; //prendo il tag radice della respose XML

		var tipi = xmlDoc.getElementsByTagName("type");
		var errori = xmlDoc.getElementsByTagName("errore");
		if(errori.length > 0)
		{
			errori[0].innerHTML;
			var div = $("<div>"+errori[0].innerHTML+"</div>");
			$("#messaggio").empty();
			$("#messaggio").append(div);
			$("#messaggio").css("color","red");
		}
	
		else if(tipi.length > 0)//Il login è avvenuto con successo
		{
			
			var tipo = tipi[0].innerHTML;
			var tipobell = tipo.replace('\n', '');
			alert(tipobell.length);
			if(tipobell == "Studente")
			{
				window.location.href="studente_profilo.jsp";
			}
			else if(tipi[0].innerHTML== "ResponsabileAzienda")
			{
				window.location.href = "azienda_profilo.jsp";
			}
			else if(tipi[0].innerHTML=="Professore")
			{
				window.location.href = "professore.jsp";
			}
			else if(tipi[0].innerHTML=="ResponsabileApprovazioni")
			{
				window.location.href = "responsabile_approvazioni_richieste_di_tutorato.jsp";
			}
			else
			{
				window.location.href = "index.jsp";
			}
		}
		else
		{
			var div = $("<div>Si &egrave; verificato un errore si prega di riprovare di nuovo</div>");
			$("#messaggio").empty();
			$("#messaggio").append(div);
		}

		document.body.style.cursor = "auto";
	}
}

function sendRequestLogin(form)
{

	inAttesa = true;
	document.body.style.cursor = "wait";
	xhttp = new XMLHttpRequest();
	xhttp.open("get", "login?id="+form.id.value+"&password="+form.password.value+"&type="+form.type.value, true);
	xhttp.setRequestHeader("connection", "close");
	xhttp.onreadystatechange = readResultLogin;
	xhttp.send(null);
}