package br.com.mv.persistencia;

import java.util.List;

import br.com.mv.model.Pessoa;
import br.com.mv.model.Telefone;

public interface Cadastro {
	public boolean inserirCadastro(Telefone telefone, Pessoa pessoa);

	public List<Pessoa> listarPessoa();

	public List<Pessoa> procurarPessoa(Pessoa p);

	public boolean excluirPessoa(Pessoa p);

	public boolean alterarPessoa(Pessoa p);

	public Pessoa pegarPessoa(Long p);

}
