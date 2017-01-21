/**
 * JavaScript responsável pela Lógica e Organização das Mascaras
 */

$(document).ready(function(){
		
	//Mascaras Gerais
	$('.mascaraDataHora').mask('00/00/0000 00:00:00', {selectOnFocus: true});
	$('.mascaraData').mask('00/00/0000', {selectOnFocus: true});
	$('.mascaraPercentual').mask("00,00", {selectOnFocus: true, reverse:true});
	$('.mascaraMonetaria').mask('000.000,00', {selectOnFocus: true, reverse:true});
	
});


function bloqueiaEnter(codigoTecla) {
	if (codigoTecla == 13) { return false; } else {return true;}
}