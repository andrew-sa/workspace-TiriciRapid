function resetInput(form) {
	for (let i = 1; i < (form.length - 1); i++) {
		form[i].setAttribute("title", "");
		document.getElementById(i).innerHTML = "";
	}
}

function checkEmpty(form) {
	var flag = 0;
	for (let i = 1; i < (form.length - 1); i++) {
		if (form[i].value.trim() === ""
				&& (form[i].type === "text" || form[i].type === "textarea" || form[i].type === "password")) {
			document.getElementById(i).innerHTML = "Non puoi lasciare vuoto";
			form[i].setAttribute("title", "Non puoi lasciare vuoto");
			flag = 1;
		}
	}
	if (flag == 1) {
		return false;
	} else {
		return true;
	}
}

function validateLogin(form) {
	for (let i = 1; i < (form.length - 1); i++) {
		form[i].style.border = "0.1em solid #00627d";
	}
	var flag = 0;
	for (let i = 1; i < form.length; i++) {
		if (form[i].value.trim() === ""
				&& (form[i].type === "text" || form[i].type === "password")) {
			form[i].setAttribute("placeholder", "Non puoi lasciare il campo vuoto");
			form[i].style.border = "0.1em solid red";
			flag = 1;
		}
	}
	if (flag == 1) {
		return false;
	} else {
		return true;
	}
}

function validateRegistrazione(form) {
	var contr = 0;
	resetInput(form);

	if (!(form["partitaIVA"].value.trim().match(/^[0-9]{11}$/))) {
		form["partitaIVA"].setAttribute("title",
				"La partita Iva \u00E8 formata da 11 numeri");
		document.getElementById(1).innerHTML = "La partita Iva deve essere formata da 11 numeri";
		contr = 1
	}
	if (!((form["nome"].value.trim().length) <= 50)) {
		form["nome"].setAttribute("title", "Il nome \u00E8 troppo grande");
		document.getElementById(2).innerHTML = "Il nome \u00E8 troppo grande";
		contr = 1
	}
	if (!((form["descrizioneAmbito"].value.trim().length) <= 500)) {
		form["descrizioneAmbito"].setAttribute("title",
				"Stai inviando troppi dati");
		document.getElementById(4).innerHTML = "Stai inviando troppi dati";
		contr = 1
	}
	if (!(form["numeroTelefono"].value.trim().match(/^[0-9]{10}$/))) {
		form["numeroTelefono"].setAttribute("title",
				"Il numero di telefono deve essere formato da 10 numeri");
		document.getElementById(5).innerHTML = "Il numero di telefono deve essere formato da 10 numeri";
		contr = 1
	}
	if (!(form["email"].value.trim()
			.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})$/))) {
		form["email"]
				.setAttribute("title",
						"L'e-mail non \u00E8 nel formato corretto. Es: nome@dominio.it");
		document.getElementById(6).innerHTML = "L'e-mail non \u00E8 nel formato corretto. Es: nome@dominio.it";
		contr = 1
	}
	if (!(form["password"].value.trim().match(/^[A-Za-z0-9]{6,20}$/))) {
		form["password"].setAttribute("title",
				"La password inserita non \u00E8 nel formato corretto");
		document.getElementById(7).innerHTML = "La password inserita non \u00E8 nel formato corretto";
		contr = 1
	}
	if (!(form["confPassword"].value.trim() === form["password"].value.trim())) {
		form["confPassword"]
				.setAttribute("title", "Le password non combaciano");
		document.getElementById(8).innerHTML = "Le password non combaciano";
		contr = 1
	}
	if (!checkEmpty(form)) {
		contr = 1
	}
	if (contr == 0) {
		return true;
	} else {
		return false;
	}
}
function validateCurriculum(form) {
	var check = 0;
	resetInput(form);
	if (!((form["capacitaCompetenzeRelazionali"].value.trim().length) <= 200)) {
		form["capacitaCompetenzeRelazionali"].setAttribute("title",
				"Non puoi scrivere pi\u00F9 di 200 caratteri");
		document.getElementById(1).innerHTML = "Non puoi scrivere pi\u00F9 di 200 caratteri";
		contr = 1;
		console.log("errore ccr");
	}
	if (!((form["capacitaCompetenzeTecniche"].value.trim().length) <= 200)) {
		form["capacitaCompetenzeTecniche"].setAttribute("title",
				"Non puoi scrivere pi\u00F9 di 200 caratteri");
		document.getElementById(2).innerHTML = "Non puoi scrivere pi\u00F9 di 200 caratteri";
		contr = 1;
	}
	if (!((form["capacitaCompetenzeTecniche"].value.trim().length) <= 200)) {
		form["capacitaCompetenzeArtistiche"].setAttribute("title",
				"Non puoi scrivere pi\u00F9 di 200 caratteri");
		document.getElementById(3).innerHTML = "Non puoi scrivere pi\u00F9 di 200 caratteri";
		contr = 1;
	}
	if (!((form["capacitaCompetenzePersonali"].value.trim().length) <= 200)) {
		form["capacitaCompetenzePersonali"].setAttribute("title",
				"Non puoi scrivere pi\u00F9 di 200 caratteri");
		document.getElementById(4).innerHTML = "Non puoi scrivere pi\u00F9 di 200 caratteri";
		contr = 1;
	}
	if (!((form["capacitaCompetenzeOrganizzative"].value.trim().length) <= 200)) {
		form["capacitaCompetenzeOrganizzative"].setAttribute("title",
				"Non puoi scrivere pi\u00F9 di 200 caratteri");
		document.getElementById(5).innerHTML = "Non puoi scrivere pi\u00F9 di 200 caratteri";
		contr = 1;
	}
	if (!((form["altreCapacitaCompetenze"].value.trim().length) <= 200)) {
		form["altreCapacitaCompetenze"].setAttribute("title",
				"Non puoi scrivere pi\u00F9 di 200 caratteri");
		document.getElementById(6).innerHTML = "Non puoi scrivere pi\u00F9 di 200 caratteri";
		contr = 1;
	}
	if (!((form["esperienzaLavorativa"].value.trim().length) <= 200)) {
		form["esperienzaLavorativa"].setAttribute("title",
				"Non puoi scrivere pi\u00F9 di 200 caratteri");
		document.getElementById(7).innerHTML = "Non puoi scrivere pi\u00F9 di 200 caratteri";
		contr = 1;
	}
	if (!(form["madrelingua"].value.trim().match(/^[A-Za-z]{2,20}$/))) {
		form["madrelingua"]
				.setAttribute("title",
						"La tua madreLingua non pu\u00F2 essere formata da una sola lettera");
		document.getElementById(8).innerHTML = "La tua madreLingua non pu\u00F2 essere formata da una sola lettera";
		contr = 1
	}
	
	
	var x = form["altreLingue"].value.trim();
	if (!(x.trim().endsWith(","))) {
		x = x.concat(",")
	}
	if (!((x.match(/^([A-Za-z\s]{2,20},)+$/))||((x.trim().length) <= 200))) {
		form["altreLingue"].setAttribute("title",
				"Il formato di questo campo \u00E8 sbagliato");
		document.getElementById(9).innerHTML = "Il formato di questo campo deve essere 'nomelingua1, nomelingua2, nomelingua3,'";
		contr = 1
	}
	
	
	let patenti = [ "AM", "A1", "A2", "A", "B1", "B", "C1", "C", "D1", "D",
			"BE", "C1E", "CE", "D1E", "DE" ];
	let p = form["patenti"].value;
	if (p.endsWith(", ")) {
		p = p.slice(0, -2);
	} else if(p.endsWith(",")){
		p=p.slice(0, -1);
	}
	p = p.trim().split(",");
	for (let i = 0; i < p.length; i++) {
		y = p[i].trim();
		if (!(patenti.includes(y))) {
			form["patenti"].setAttribute("title",
					"Il formato di questo campo deve essere 'A, AM, A1, A2'");
			document.getElementById(10).innerHTML = "Questa patente non esiste "
					+ y + "Il formato di questo campo deve essere 'A, AM, A1, A2'";
		}
	}
	
	
	if (!((form["fax"].value.trim().match(/^[0-9]{11}$/)) || (form["fax"].value
			.trim() === ""))) {
		form["fax"].setAttribute("title",
				"Un numero di fax \u00E8 composto da 11 cifre");
		document.getElementById(12).innerHTML = "Un numero di fax \u00E8 composto da 11 cifre";
		check = 1;
	}
	for (let i = 1; i < (form.length - 2); i++) {
		if (form[i].value.trim() === ""
				&& (form[i].type === "text" || form[i].type === "textarea" || form[i].type === "password")) {
			document.getElementById(i).innerHTML = "Non puoi lasciare vuoto";
			form[i].setAttribute("title", "Non puoi lasciare vuoto");
			check = 1;
		}
	}
	if (check == 0) {
		return true;
	} else {
		return false;
	}
}

function validateAggiuntaTirocinio(form){
	var contr = 0;
	resetInput(form);
	if (!((form["nome"].value.trim().length) <= 50)) {
		form["nome"].setAttribute("nome",
				"Non puoi scrivere pi\u00F9 di 50 caratteri");
		document.getElementById(1).innerHTML = "Non puoi scrivere pi\u00F9 di 50 caratteri";
		contr = 1;
	}
	if (!((form["offertaFormativa"].value.trim().length) <= 500)) {
		form["offertaFormativa"].setAttribute("offertaFormativa",
				"Non puoi scrivere pi\u00F9 di 500 caratteri");
		document.getElementById(2).innerHTML = "Non puoi scrivere pi\u00F9 di 500 caratteri";
		contr = 1;
	}
	if (!((form["descrizione"].value.trim().length) <= 500)) {
		form["descrizione"].setAttribute("descrizione",
				"Non puoi scrivere pi\u00F9 di 500 caratteri");
		document.getElementById(3).innerHTML = "Non puoi scrivere pi\u00F9 di 500 caratteri";
		contr = 1;
	}
	if (!checkEmpty(form)) {
		contr = 1
	}
	if (contr == 0) {
		return true;
	} else {
		return false;
	}
}