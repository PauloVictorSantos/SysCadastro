package br.com.nextln.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.nextln.dao.Cadastro;
import br.com.nextln.dao.CadastroDAOimp;
import br.com.nextln.model.Pessoa;
import br.com.nextln.model.Telefone;
import br.com.nextln.utils.Util;

@ManagedBean(name = "cadastro")
@ViewScoped
public class CadastroMB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;
	private Telefone telefone;
	private String ddd;
	private String numero;
	List<Telefone> telefoneList = new ArrayList<>();
	private Cadastro cadastroDao = new CadastroDAOimp();
	private Pessoa pessoaSelecio;
	private Long idSelecionado;
	private Pessoa p;

	public Long getIdSelecionado() {
		return idSelecionado;
	}

	public void setIdSelecionado(Long idSelecionado) {
		this.idSelecionado = idSelecionado;
	}

	public Pessoa getPessoaSelecio() {
		return pessoaSelecio;
	}

	public void setPessoaSelecio(Pessoa pessoaSelecio) {
		 this.pessoaSelecio = pessoaSelecio;
	}

	public CadastroMB() {
		this.pessoa = new Pessoa();
		this.telefone = new Telefone();
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

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public List<Telefone> getTelefoneList() {
		return telefoneList;
	}

	public void setTelefoneList(List<Telefone> telefoneList) {
		this.telefoneList = telefoneList;
	}

	public void adicionarTelefone() {
		telefone = new Telefone(this.ddd, this.numero);
		telefoneList.add(telefone);
		this.setDdd("");
		this.setNumero("");
	}

	public void selecionarLinha(Telefone telefone) {
		telefoneList.remove(telefone);
	}

	public void salvar() {
	
		pessoa.setTelefone(telefoneList);

		if (pessoa.getId() == null) {

			cadastroDao.inserirCadastro(telefone, pessoa);
			Util.setMensagem("Cadastro Salvo!", "Dados do cadastro salvo!");
			limpar();
		}

	}

	public void limpar() {
		if (pessoa != null && telefone != null) {
			pessoa = new Pessoa();
			this.setDdd("");
			this.setNumero("");
			telefone = new Telefone();
			telefoneList = new ArrayList<>();

		}
	}
}