
function maxlength(area,max,id_campo){

	var conta = max - area.value.length;

	if(id_campo!=null){

		document.getElementById(id_campo).innerHTML=conta;

	}

	if(conta < 0){

		area.value = area.value.substring(0,max);

		if(id_campo!=null){

			document.getElementById(id_campo).innerHTML = '0';

		}

	}

}

