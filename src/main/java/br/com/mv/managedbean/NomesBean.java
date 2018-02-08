package br.com.mv.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import br.com.mv.model.Telefone;

@ManagedBean(name="name")
@ViewScoped
public class NomesBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String cadastrar() {

		return "cadastro?faces-redirect=true";
	}

}
