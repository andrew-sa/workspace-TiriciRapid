var xhttp;
var reqInAPage = 1;
var numPages = 1;

function stringaStato(stato)
{
	var str = "";
    switch (stato)
    {
			case 'ConfAz':
				str = 'Richiesta in attesa della conferma da parte dell\'azienda.';
				break;
			case 'ScelTut':
				str = 'Richiesta in attesa della conferma del tutor interno da parte dello studente.';
				break;
			case 'ConfTut':
				str = 'Richiesta in attesa della conferma da parte del professore scelto come tutor interno.';
				break;
			case 'ConfResp':
				str = 'Richiesta  in attesa della conferma finale da parte del Responsabile Approvazioni.';
				break;
			case 'Acc':
				str = 'Richiesta accettata dal Responsabile Approvazioni.';
				break;
			case 'RifResp':
				str = 'Richiesta rifiutata dal Responsabile Approvazioni.';
				break;
			case 'RifTut':
				str = 'Richiesta rifiutata dal tutor interno scelto e in attesa di una nuova scelta da parte dello studente.';
				break;
			case 'RifAz':
				str = "Richiesta rifiutata dall'azienda";
				break;
			default:
				break;
    }
    return str;
}

function RichiestaHTML(usernameStudente, partitaIVAAzienda, nomeTirocinio, stato)
{
	this.wrapper = $('<div class="col-sm-12"></div>');
	this.wrapper2 = $('<div class="bs-calltoaction bs-calltoaction-default"></div>');
	this.wrapper3 = $('<div class="row" id="divstorico"></div>');
	
	this.contentWrapper = $('<div class="col-md-9 cta-contents"></div>');
	this.title = $('<h1 class="cta-title"></h1>');
	this.infoWrapper = $('<div class="cta-desc"></div>');
	this.titleStudente = $('<h3>Studente:</h3>');
	this.studente = $('<p></p>');
	this.titleAzienda = $('<h3>Azienda:</h3>');
	this.azienda = $('<p></p>');
	this.titleTirocinio = $('<h3>Tirocinio:</h3>');
	this.tirocinio = $('<p></p>');
	
	this.statoWrapper = $('<div id="stato-storico"></div>');
	
	this.title.html('Richiesta');
	this.studente.html(usernameStudente + ' ' + '<a href="profilo_studente?username=' + usernameStudente + '"><button class="bottoni-conferma-professore" >Visualizza curriculum</button></a>');
	this.azienda.html(partitaIVAAzienda + ' ' + '<a href="dati_azienda?partitaIVA=' + partitaIVAAzienda + '"><button class="bottoni-conferma-professore">Visualizza informazioni azienda</button></a>');
	this.tirocinio.html(nomeTirocinio + ' ' + '<a href="carica_tirocinio?partitaIVA=' + partitaIVAAzienda + '&nomeTirocinio=' + nomeTirocinio + '"><button class="bottoni-conferma-professore">Visualizza informazioni tirocinio</button></a>');
	
	this.statoWrapper.html('<h1>Stato:</h1> <h3>' + stringaStato(stato) + '</h3>');
	
	this.infoWrapper.append(this.titleStudente);
	this.infoWrapper.append(this.studente);
	this.infoWrapper.append(this.titleAzienda);
	this.infoWrapper.append(this.azienda);
	this.infoWrapper.append(this.titleTirocinio);
	this.infoWrapper.append(this.tirocinio);
	
	this.contentWrapper.append(this.title);
	this.contentWrapper.append(this.infoWrapper);
	
	this.wrapper3.append(this.contentWrapper);
	this.wrapper3.append(this.statoWrapper);
	this.wrapper2.append(this.wrapper3);
	this.wrapper.append(this.wrapper2);
}

function mostraNumeroPagine()
{
	var wrapperNumPages = $('#numPages');
	for (var i = 1; i <= numPages; i++)
	{
		var num = $('<span id="num' + i + '" onclick="caricaPaginaRichieste(this)">' + i + '</span>');
		wrapperNumPages.append(num);
	}
}

function mostraListaRichieste()
{
	if (xhttp.readyState == 4 && xhttp.status == 200)
	{
		var strJSON = xhttp.responseText;
		if (strJSON == "")
		{
			var messaggio = $('<div class="erroreMsg"></div>')
			messaggio.html("Non sono state effettuate richieste di tirocinio");
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
			var richieste = JSON.parse(strJSON);
			
			if (richieste.length % reqInAPage == 0)
			{
				numPages = richieste.length / reqInAPage;
			}
			else
			{
				numPages = (richieste.length / reqInAPage) + 1;
			}
			
			for (var k = 0; k < numPages; k++)
			{
				var currentPage = $('<div id="page' + (k+1) + '"></div>');
				for (var j = 0; j < reqInAPage; j++)
				{
					var i = (k * reqInAPage) + j;
					if (i >= richieste.length)
					{
						break;
					}
					var richiestaHTML = new RichiestaHTML(richieste[i].usernameStudente, richieste[i].partitaIVAAzienda, richieste[i].nomeTirocinio, richieste[i].stato);
					currentPage.append(richiestaHTML.wrapper);
				}
				$("#contenuto").append(currentPage);
			}
			
			mostraNumeroPagine();
			caricaPaginaRichieste(null);
		}
		document.body.style.cursor = "auto";
	}
}

function caricaListaRichieste()
{
	document.body.style.cursor = "wait";
	xhttp = new XMLHttpRequest();
	xhttp.open("get", "storico_richieste", true);
	xhttp.setRequestHeader("connection", "close");
	xhttp.onreadystatechange = mostraListaRichieste;
	xhttp.send(null);
}

function caricaPaginaRichieste(fieldNumeroPagina)
{
	console.log(numPages);
	document.body.style.cursor = "wait";
	if (fieldNumeroPagina == null)
	{
		for (var i = 1; i <= numPages; i++)
		{
			if (i == 1)
			{
				console.log("Sono qui");
				$("#page" + i).show();
				$("#num" + i).attr("class", "currentPage");
			}
			else
			{
				console.log("NON sono qui");
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