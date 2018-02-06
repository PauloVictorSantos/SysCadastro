package br.com.mv.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import br.com.mv.model.Telefone;

@ManagedBean
@ViewScoped
public class NomesBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Telefone nome;
	public NomesBean() {
		this.nome= new Telefone();
	}
	
	public void setNome(Telefone nome) {
		this.nome = nome;
	}

	private List<Telefone> nomes = new ArrayList<Telefone>();

	public void adicionar() {
		this.nomes.add(nome);
	}

	public List<Telefone> getNomes() {
		return nomes;
	}

	public List<Telefone> getlistTelefone(){
		return getNomes();
	}
	public void setNomes(List<Telefone> nomes) {
		this.nomes = nomes;
	}

	public Telefone getNome() {
		return nome;
	}
	
	
	
}
