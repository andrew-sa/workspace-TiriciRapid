function resetInput(form) {
	for (let i = 1; i < (form.length - 1); i++) {
		form[i].setAttribute("title", "");
		document.getElementById(i).innerHTML = "";
	}
}
function checkEmpty(form) {
	var flag = 0;
	for (let i = 1; i < (form.length - 1); i++) {
		if (form[i].value.trim() === "" && (form[i].type === "text" || form[i].type === "textarea" || form[i].type === "password")) {
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