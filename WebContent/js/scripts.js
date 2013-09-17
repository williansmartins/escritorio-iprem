function preencherUsuarioAdmin(){
	$('input[name="form-login:usuario"]').val("willians");
	$('input[name="form-login:senha"]').val("secreta");
}
function preencherUsuarioAluno(){
	$('input[name="form-login:usuario"]').val("aluno");
	$('input[name="form-login:senha"]').val("aluno");
}
function preencherDados(){
	$('input[name="form:nome"]').val("Willians Martins de Morais");
	$('input[name="form:cpf"]').val("305.809.118.40");
	$('input[name="form:telefone"]').val("(11) 4148-8592");
	$('input[name="form:nascimento"]').val("08/07/1982");
	$('input[name="form:usuario"]').val("will");
	$('input[name="form:senha"]').val("secreta");
	$('input[name="form:email"]').val("contato@williansmartins.com");
	$('input[name="form:estado"]').val("Sao Paulo");
	$('input[name="form:cidade"]').val("Jandira");
}

function inicio(){
	window.location = "../iprem/index.html";
}

function home(){
	window.location = "../iprem/index-gestor.jsf";
}
function contato(){
	window.location = "../iprem/contato.jsf";
}
function metodo(){
	alert(2);
	$('.files .ui-button').hide();
	$('.ui-progressbar').hide();
}
//funcao basica JQUERY - Sobressalente
$("html").ready(function () {
	//$('#titulo').css("color","red");
	//alert("opa");
//	$('.start').hide();
//	$('.cancel').hide();
	
});
$("#j_idt26").change(function () {
	alert("opa");
});
