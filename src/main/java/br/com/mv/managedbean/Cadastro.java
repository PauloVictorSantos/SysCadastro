import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.mv.model.Pessoa;
import br.com.mv.model.Telefone;

@ManagedBean
@ViewScoped
public class Cadastro implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;
	private Telefone telefone;
	private String ddd;
	private String numero;
	List<Telefone> telefoneList = new ArrayList<>();
	
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
	
	public void adicionarTelefone(){
		telefone = new Telefone(this.ddd, this.numero);
		telefoneList.add(telefone);
	}
	
	
}