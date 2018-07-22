package br.com.nextln.dao;

import java.util.List;

import br.com.nextln.model.Pessoa;
import br.com.nextln.model.Telefone;

public interface Cadastro {
	public boolean inserirCadastro(Telefone telefone, Pessoa pessoa);

	public List<Pessoa> listarPessoa();

	public List<Pessoa> procurarPessoa(Pessoa p);

	public boolean excluirPessoa(Pessoa p);

	public boolean alterarPessoa(Pessoa p);

	public Pessoa pegarPessoa(Long p);

}
