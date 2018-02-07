package br.com.mv.managedbean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.mv.model.Pessoa;
import br.com.mv.model.Telefone;
import br.com.mv.persistencia.CadastroDAOimp;

@ManagedBean(name = "listar")
@ViewScoped
public class ListarPessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;
	private Telefone telefone;
	private CadastroDAOimp cadastroDAOimp;
	private Pessoa pessoaSelecio;
	private String nome;
	private String cpf;
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Pessoa getPessoaSelecio() {
		return pessoaSelecio;
	}

	public void setPessoaSelecio(Pessoa pessoaSelecio) {
		this.pessoaSelecio = pessoaSelecio;
	}

	public ListarPessoa() {
		pessoa = new Pessoa();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public List<Pessoa> getlistarPessoa() {
		cadastroDAOimp = new CadastroDAOimp();
		if(this.getNome() == null || this.getCpf()== null)
			return cadastroDAOimp.listarPessoa();
		pessoa.setNome(this.getNome());
		pessoa.setCpf(this.getCpf());
		return cadastroDAOimp.procurarPessoa(pessoa);
	}

	public void excluir() {
		cadastroDAOimp = new CadastroDAOimp();
		try {
			cadastroDAOimp.excluirPessoa(pessoaSelecio);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void buscarPessoa() {
		this.getlistarPessoa();
	}

	public int sizeTel(Pessoa p) {
		return p.getTelefone().size();
	}

	public int idadePessoa(Pessoa p) {
		return (new Date().getYear() - p.getDataNascimento().getYear());
	}
}
