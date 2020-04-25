function excluirRegistro(){ 
	if(!confirm("Deseja realmente excluir o registro?")) {
		return true;
	}
	return false;
} 

function cancelar(msg) {
	if(confirm(msg)) {
		return true;
	}
	return false;
}

function cancelarCadastro() {
	if(confirm("Deseja sair sem salvar os dados?")) {
		return true;
	}
	return false;
}

function confirmar(msg) {
	if(!confirm(msg)) {
		return true;
	}
	return false;
}

function closeModal() {
	window.close();
}

function insere_linha(id)
{
	if (id == null) id = 'int_carencia';
	var tabela = document.getElementById(id);
	var novoTR = tabela.insertRow(tabela.rows.length);
	nova_linha(novoTR);
}

function nova_linha(nTR)
{
	var nTD = document.createElement('td');
	nTD.innerHTML = "Entre os dias <input type='text' size='3' maxlength='2' name='diaInicio' onKeyUp='unsigned(this)'> e <input type='text' size='3' maxlength='2' name='diaFim' onKeyUp='unsigned(this)'> do mês, carência de <input type='text' size='4' maxlength='3' name='carenciaMinima' onKeyUp='unsigned(this)'> a <input type='text' size='4' maxlength='3' name='carenciaMaxima' onKeyUp='unsigned(this)'> dias. <span onclick='remove_linha(this)'>[remover]</span>";
	nTR.appendChild(nTD);
}
