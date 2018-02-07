package br.com.mv.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import br.com.mv.dao.CadastroDao;
import br.com.mv.model.Pessoa;
import br.com.mv.model.Telefone;
import br.com.mv.persistencia.CadastroDAOimp;

@ManagedBean(name = "cadastro")
@SessionScoped
public class Cadastro implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;
	private Telefone telefone;
	private String ddd;
	private String numero;
	List<Telefone> telefoneList = new ArrayList<>();
	private CadastroDAOimp cadastroDao;
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

	public Cadastro() {
		this.pessoa = new Pessoa();
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
	}

	public void selecionarLinha(Telefone telefone) {
		telefoneList.remove(telefone);
	}

	public void salvar() {
		cadastroDao = new CadastroDAOimp();
		pessoa.setTelefone(telefoneList);

		if (pessoa.getId() == null) {
			cadastroDao.inserirCadastro(telefone, pessoa);
			System.out.println("funcionou");
		} else {
			cadastroDao.alterarPessoa(pessoa);
			System.out.println("alterou");
		}
	}

	public String Alterar() {
		System.out.println(this.getIdSelecionado() + " di pessoa " + pessoa.getId());
		cadastroDao = new CadastroDAOimp();
		Pessoa p = cadastroDao.pegarPessoa(this.getIdSelecionado());
		if(p!=null){
			
		}
		System.out.println(this.getIdSelecionado() + " di pessoa " + p.getId());
		return "atualizar";
	}
}