var xhttp;
var inAttesa = false;

function readResultLogin()
{
	if (xhttp.readyState == 4 && xhttp.status == 200)
	{


		var responseXML = xhttp.responseXML;
		var xmlDoc = responseXML.documentElement; //prendo il tag radice della respose XML

		var tipo = xmlDoc.getElementsByTagName("type");
		var errore = xmlDoc.getElementsByTagName("errore");
		if(errore!= null )
		{
			alert("errore");
			var div = $("<div></div>");
			div.text(errore.innerHTML);
			$("#contenitore_login").append(div);
		}
		else if(tipo != null)//Il login è avvenuto con successo
		{
			alert("tipo");
			//FACCIO IL REDIRECT ALLA JSP DI QUEL TIPO
		}
		else
		{
			alert("else");
			var div = $("<div>Si &egrave; verificato un errore si prega di riprovare di nuova</div>");
			$("#contenitore_login").append(div);
		}

		document.body.style.cursor = "auto";
	}
} 
 
function sendRequestLogin(form)
{
	inAttesa = true;

	document.body.style.cursor = "wait";
	xhttp = new XMLHttpRequest();
	xhttp.open("get", "login?id="+form.identificativo.value+"&password="+form.password.value+"&type="+form.type.value, true);
	xhttp.setRequestHeader("connection", "close");
	xhttp.onreadystatechange = readResultLogin;
	xhttp.send(null);
}