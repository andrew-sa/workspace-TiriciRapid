var xhttp;
var azInAPage = 1;
var numPages = 1;

function AziendaStudenteHTML(partitaIVA, nome, ambito, sede, email, telefono)
{
	this.wrapper = $('<div class="col-sm-12"></div>');
	this.wrapper2 = $('<div class="bs-calltoaction bs-calltoaction-default"></div>');
	this.wrapper3 = $('<div class="row"></div>');
	
	this.contentWrapper = $('<div class="col-md-9 cta-contents"></div>');
	this.title = $('<h1 class="cta-title"></h1>');
	this.infoWrapper = $('<div class="cta-desc"></div>');
	this.ambito = $('<p></p>');
	this.sede = $('<p></p>');
	this.email = $('<p></p>');
	this.telefono = $('<p></p>');
	
	this.buttonWrapper = $('<div class="col-md-3 cta-button"></div>');
	
	this.title.html(nome);
	this.ambito.html('<span class="parametri-azienda">Ambito: </span>' + ambito);
	this.sede.html('<span class="parametri-azienda">Sede: </span>' + sede);
	this.email.html('<span class="parametri-azienda">Email: </span>' + email);
	this.telefono.html('<span class="parametri-azienda">Telefono: </span>' + telefono);
	
	this.buttonWrapper.html('<a href="tirocini_azienda?partitaIVA=' + partitaIVA + '" class="btn btn-lg btn-block btn-default">Visualizza Tirocini</a>');
	
	this.infoWrapper.append(this.ambito);
	this.infoWrapper.append(this.sede);
	this.infoWrapper.append(this.email);
	this.infoWrapper.append(this.telefono);
	
	this.contentWrapper.append(this.title);
	this.contentWrapper.append(this.infoWrapper);
	
	this.wrapper3.append(this.contentWrapper);
	this.wrapper3.append(this.buttonWrapper);
	this.wrapper2.append(this.wrapper3);
	this.wrapper.append(this.wrapper2);
}

function AziendaResponsabiliApprovazioniHTML(partitaIVA, nome, ambito, sede, email, telefono, stato)
{
	this.wrapper = $('<div class="responsabile-approvazione-azienda-profilo"></div>');
	
	this.title = $('<h2 style="text-align: center;"></h2>');
	this.infoWrapper = $('<div class="tabella-info-azienda"></div>');
	//RICOMINCIARE DA QUI
	this.ambito = $('<p></p>');
	this.sede = $('<p></p>');
	this.email = $('<p></p>');
	this.telefono = $('<p></p>');
	
	this.buttonWrapper = $('<div class="col-md-3 cta-button"></div>');
	
	this.title.html(nome);
	this.ambito.html('<span class="parametri-azienda">Ambito: </span>' + ambito);
	this.sede.html('<span class="parametri-azienda">Sede: </span>' + sede);
	this.email.html('<span class="parametri-azienda">Email: </span>' + email);
	this.telefono.html('<span class="parametri-azienda">Telefono: </span>' + telefono);
	
	this.buttonWrapper.html('<a href="tirocini_azienda?partitaIVA=' + partitaIVA + '" class="btn btn-lg btn-block btn-default">Visualizza Tirocini</a>');
	
	this.infoWrapper.append(this.ambito);
	this.infoWrapper.append(this.sede);
	this.infoWrapper.append(this.email);
	this.infoWrapper.append(this.telefono);
	
	this.contentWrapper.append(this.title);
	this.contentWrapper.append(this.infoWrapper);
	
	this.wrapper3.append(this.contentWrapper);
	this.wrapper3.append(this.buttonWrapper);
	this.wrapper2.append(this.wrapper3);
	this.wrapper.append(this.wrapper2);
}

function mostraNumeroPagine()
{
	var wrapperNumPages = $('#numPages');
	for (var i = 1; i <= numPages; i++)
	{
		var num = $('<span id="num' + i + '" onclick="caricaPaginaAziende(this)">' + i + '</span>');
		wrapperNumPages.append(num);
	}
}

function mostraListaAziende()
{
	if (xhttp.readyState == 4 && xhttp.status == 200)
	{
		var strJSON = xhttp.responseText;
		if (strJSON == "")
		{
			var messaggio = $('<div class="erroreMsg"></div>')
			messaggio.html("Non sono presenti aziende");
			$("#contenuto").prepend(messaggio);
		}
		else if (strJSON.indexOf('[') == -1)
		{
			var messaggio = $('<div class="erroreMsg"></div>')
			messaggio.html(strJSON);
			$("#contenuto").prepend(messaggio);
		}
		else
		{
			var aziende = JSON.parse(strJSON);
			
			var pathname = location.pathname;
			
			if (aziende.length % azInAPage == 0)
			{
				numPages = aziende.length / azInAPage;
			}
			else
			{
				numPages = (aziende.length / azInAPage) + 1;
			}
			
			for (var k = 0; k < numPages; k++)
			{
				var currentPage = $('<div id="page' + (k+1) + '"></div>');
				for (var j = 0; j < azInAPage; j++)
				{
					var i = (k * azInAPage) + j;
					if (i >= aziende.length)
					{
						break;
					}
					if (pathname.includes('studente'))
					{
						var aziendaStudenteHTML = new AziendaStudenteHTML(aziende[i].partitaIVA, aziende[i].nome, aziende[i].ambito, aziende[i].sede, aziende[i].email, aziende[i].telefono);
						currentPage.append(aziendaStudenteHTML.wrapper);
					}
					else
					{
						//creare aziendaResponsabileApprovazioniHTML
						var aziendaResponsabileApprovazioniHTML = new AziendaResponsabileApprovazioniHTML(aziende[i].partitaIVA, aziende[i].nome, aziende[i].ambito, aziende[i].sede, aziende[i].email, aziende[i].telefono, aziende[i].stato);
						currentPage.append(aziendaResponsabileApprovazioniHTML.wrapper);
					}
				}
				$("#contenuto").append(currentPage);
			}
			
			mostraNumeroPagine();
			caricaPaginaAziende(null);
		}
		document.body.style.cursor = "auto";
	}
}

function caricaListaAziende()
{
	document.body.style.cursor = "wait";
	xhttp = new XMLHttpRequest();
	xhttp.open("get", "lista_aziende", true);
	xhttp.setRequestHeader("connection", "close");
	xhttp.onreadystatechange = mostraListaAziende;
	xhttp.send(null);
}

function caricaPaginaAziende(fieldNumeroPagina)
{
//	console.log(numPages);
	document.body.style.cursor = "wait";
	if (fieldNumeroPagina == null)
	{
		for (var i = 1; i <= numPages; i++)
		{
			if (i == 1)
			{
//				console.log("Sono qui");
				$("#page" + i).show();
				$("#num" + i).attr("class", "currentPage");
			}
			else
			{
//				console.log("NON sono qui");
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