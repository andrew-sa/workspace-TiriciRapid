function resetInput(form) {
	for (let i = 1; i < (form.length-1); i++) {
		//form[i].parentNode.childNodes[1].value;
		console.log(form);
		form[i].setAttribute("title", "");
		document.getElementById(i).innerHTML="";
	}
}
function checkEmpty(form) {
	for (let i = 1; i < form.length; i++) {
		let flag=0;
		if (form[i].value.trim() === "" && (form[i].type === "text" ||form[i].type === "textarea" || form[i].type === "password" )) {
			document.getElementById(i).innerHTML="Non puoi lasciare vuoto";
			form[i].setAttribute("title", "Non puoi lasciare vuoto");
			flag=1;
		}
	}
	if (flag=1){
		return false;
	}else{
		return true;
	}
}
function validateRegistrazione(form) {
	resetInput(form);
	let contr=0;
	
	if (!(form["partitaIVA"].value.trim().match(/^[0-9]{11}$/))) {
		form["partitaIVA"].setAttribute("title","La partita Iva \u00E8 formata da 11 numeri");
		document.getElementById(1).innerHTML="La partita Iva \u00E8 formata da 11 numeri";
		contr=1
	}
	if (!checkEmpty(form)) {contr=1}
	if(contr=0){return true}else{return false}
}