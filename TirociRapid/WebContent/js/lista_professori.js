var xhttp;
var profInAPage = 1;
var numPages = 1;

function ProfessoreHTML(username, nome, cognome, ambito, emailIstituzionale, email, telefono, isReqTir)
{
	this.wrapper = $('<div class="col-sm-12"></div>');
	this.wrapper2 = $('<div class="bs-calltoaction bs-calltoaction-default"></div>');
	this.wrapper3 = $('<div class="row"></div>');
	
	this.contentWrapper = $('<div class="col-md-9 cta-contents"></div>');
	this.title = $('<h1 class="cta-title"></h1>');
	this.infoWrapper = $('<div class="cta-desc"></div>');
	this.ambito = $('<p></p>');
	this.emailIstituzionale = $('<p></p>');
	this.email = $('<p></p>');
	this.telefono = $('<p></p>');
	
	this.buttonWrapper = $('<div class="col-md-3 cta-button"></div>');
	
	this.title.html(nome + ' ' + conogme);
	this.ambito.html('<span class="parametri-professore">Ambito: </span>' + ambito);
	this.emailIstituzionale.html('<span class="parametri-professore">Email Istituzionale: </span>' + emailIstituzionale);
	this.email.html('<span class="parametri-professore">Email: </span>' + email);
	this.telefono.html('<span class="parametri-professore">Telefono: </span>' + telefono);
	
	if (isReqTir)
	{
		var queryString = window.location.href.substring(window.location.href.indexOf('?') + 1);
		var path = window.location.hostname + "/scegli_tutor?" + queryString + "&professore=" + username;
		buttonWrapper.html('<a href="' + path + '" class="btn btn-lg btn-block btn-default">Scegli come tutor</a>')
	}
	
	this.infoWrapper.append(this.ambito);
	this.infoWrapper.append(this.emailIstituzionale);
	this.infoWrapper.append(this.email);
	this.infoWrapper.append(this.telefono);
	
	this.contentWrapper.append(this.infoWrapper);
	if (isReqTir)
	{
		this.contentWrapper.append(this.buttonWrapper);
	}
	
	this.wrapper3.append(this.contentWrapper);
	this.wrapper2.append(this.wrapper3);
	this.wrapper.append(this.wrapper2);
}

function mostraNumeroPagine()
{
	var wrapperNumPages = $('#numPages');
	for (var i = 1; i <= numPages; i++)
	{
		var num = $('<span id="num' + i + '" onclick="caricaPaginaProdotti(this)">' + i + '</span>');
		wrapperNumPages.append(num);
	}
}

function mostraListaProfessori()
{
	if (xhttp.readyState == 4 && xhttp.status == 200)
	{
		var strJSON = xhttp.responseText;
		if (strJSON == "")
		{
			var messaggio = $('<h1 class="erroreTop"></h1>')
			messaggio.html("Non sono stati trovati professori<br>Si prega di ricaricare la pagina");
			$("div.container").prepend(messaggio);
		}
		else
		{
			var professori = JSON.parse(strJSON);
			
			var isReqTir = false;
			if (window.location.href.indexOf('?') != -1)
			{
				isReqTir = true;
			}
			
			if (professori.length % profInAPage == 0)
			{
				numPages = professori.length / profInAPage;
			}
			else
			{
				numPages = (professori.length / profInAPage) + 1;
			}
			
			for (var k = 0; k < numPages; k++)
			{
				for (var j = 0; j < profInAPage; j++)
				{
					var currentPage = $('<div id="page' + (k+1) + '"></div>');
					var i = (k * (profInAPage - 1)) + j;
					if (i >= professore.length)
					{
						break;
					}
					var professoreHTML = new ProfessoreHTML(professori[i].username, professori[i].nome, professori[i].cognome, professori[i].ambito, professori[i].emailIstituzionale, professori[i].email, professori[i].telefono, isReqTir);
					currentPage.append(professoreHTML.wrapper);
				}
				$("div.container").append(currentPage);
			}
			
			mostraNumeroPagine();
		}
		document.body.style.cursor = "auto";
	}
}

function caricaListaProfessori()
{
	document.body.style.cursor = "wait";
	xhttp = new XMLHttpRequest();
	xhttp.open("get", "lista_professori", true);
	xhttp.setRequestHeader("connection", "close");
	xhttp.onreadystatechange = mostraListaProfessori;
	xhttp.send(null);
}

function caricaPaginaProfessori(fieldNumeroPagina)
{
	document.body.style.cursor = "wait";
	if (fieldNumeroPagina == null)
	{
		for (var i = 1; i <= numPages; i++)
		{
			if (i == 1)
			{
				$("#page" + i).show();
				$("#num" + i).attr("class", "currentPage");
			}
			else
			{
				$("#page" + i).hide();
				$("#num" + i).removeAttr("class");
			}
		}
	}
	else
	{
		var nextPage = fieldNumeroPagina.innerHTML;
		for (var i = 1; i <= numPages; i++)
		{
			if (i == nextPage)
			{
				$("#page" + i).show();
				$("#num" + i).attr("class", "currentPage");
			}
			else
			{
				$("#page" + i).hide();
				$("#num" + i).removeAttr("class");
			}
		}
	}
	document.body.style.cursor = "auto";
}