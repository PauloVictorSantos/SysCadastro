package br.com.nextln.managedbean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.criteria.Predicate;

import br.com.nextln.dao.Cadastro;
import br.com.nextln.dao.CadastroDAOimp;
import br.com.nextln.model.Pessoa;
import br.com.nextln.model.Telefone;
import br.com.nextln.utils.Util;

@ManagedBean(name = "listar")
@ViewScoped
public class ListarCadastroMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;
	private Telefone telefone;
	private Cadastro cadastroDao= new CadastroDAOimp();
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

	public ListarCadastroMB() {
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
		if (this.getNome() == null || this.getCpf() == null)
			return cadastroDao.listarPessoa().stream().filter(
			n->Objects.equals(n.getCpf(),"")?false:true
							).collect(Collectors.toList()
									);
		pessoa.setNome(this.getNome());
		pessoa.setCpf(this.getCpf());
		return cadastroDao.procurarPessoa(pessoa).stream().filter(n->Objects.equals(n.getCpf(),"")?false:true).collect(Collectors.toList());
	}

	public void excluir() {
		
		try {
			cadastroDao.excluirPessoa(pessoaSelecio);
			Util.setMensagem("Contato Excluido!", "Todos os dados foram excluídos!");
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
