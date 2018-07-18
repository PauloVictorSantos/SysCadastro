package br.com.mv.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.mv.dao.Cadastro;
import br.com.mv.dao.CadastroDAOimp;
import br.com.mv.model.Pessoa;
import br.com.mv.model.Telefone;
import br.com.mv.utils.Util;

@SessionScoped
@ManagedBean(name = "atualizar")
public class AtualizarMB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Telefone telefone;
	private Long idSelecionado;
	private Cadastro cadastroDao;
	private Pessoa pessoa;
	List<Telefone> telefoneList = new ArrayList<>();
	private String ddd;
	private String numero;

	public AtualizarMB() {
		pessoa = new Pessoa();
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

	public Cadastro getCadastroDao() {
		return cadastroDao;
	}

	public void setCadastroDao(Cadastro cadastroDao) {
		this.cadastroDao = cadastroDao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Long getIdSelecionado() {
		return idSelecionado;
	}

	public void setIdSelecionado(Long idSelecionado) {
		this.idSelecionado = idSelecionado;
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

	public String Alterar() {
		System.out.println(this.getIdSelecionado());
		// System.out.println(" di pessoa " + pessoa.getId());
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

	public void atualizar() {

		cadastroDao.alterarPessoa(pessoa);
		Util.setMensagem("Alterados!", "Dados alterados!");
		this.pessoa = new Pessoa();
		this.setDdd("");
		this.setNumero("");
		telefone = new Telefone();
		telefoneList = new ArrayList<>();
	}
}
