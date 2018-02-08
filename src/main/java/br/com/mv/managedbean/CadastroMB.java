package br.com.mv.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.mv.dao.Cadastro;
import br.com.mv.dao.CadastroDAOimp;
import br.com.mv.model.Pessoa;
import br.com.mv.model.Telefone;

@ManagedBean(name = "cadastro")
@SessionScoped
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
	private Cadastro cadastroDao;
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

	public String salvar() {
		cadastroDao = new CadastroDAOimp();
		pessoa.setTelefone(telefoneList);

		if (pessoa.getId() == null) {
			cadastroDao.inserirCadastro(telefone, pessoa);
			this.setMensagem("Cadastro Salvo!", "Dados do cadastro salvo!");
			this.pessoa = new Pessoa();
			this.telefone = new Telefone();
			telefoneList = new ArrayList<>();
		} else {
			cadastroDao.alterarPessoa(pessoa);
			this.setMensagem("Alterados!", "Dados alterados!");
			this.pessoa = new Pessoa();
			telefoneList = new ArrayList<>();
		}
		return "/listaCadastro?faces-redirect=true";
	}

	public String Alterar() {
		System.out.println(this.getIdSelecionado() + " di pessoa " + pessoa.getId());
		cadastroDao = new CadastroDAOimp();
		Pessoa p = cadastroDao.pegarPessoa(this.getIdSelecionado());

		if (p != null) {
			this.pessoa.setId(p.getId());
			this.pessoa.setNome(p.getNome());
			this.pessoa.setEmail(p.getEmail());
			this.pessoa.setCpf(p.getCpf());
			this.pessoa.setDataNascimento(p.getDataNascimento());
			telefoneList = new ArrayList<>();
			for (Telefone telef : p.getTelefone()) {

				telefone = new Telefone(telef.getDdd(), telef.getNumero());
				this.setDdd("");
				this.setNumero("");
				telefoneList.add(telefone);
			}
			this.pessoa.setTelefone(telefoneList);
		}
		System.out.println(this.getIdSelecionado() + " di pessoa " + p.getId());
		return "atualizar?faces-redirect=true";
	}

	public void setMensagem(String titulo, String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensagem));
	}

	public String cadastrar() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/cadastro?faces-redirect=true";
	}

}