package br.com.mv.persistencia;


import java.awt.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import br.com.mv.model.Pessoa;
import br.com.mv.model.Telefone;

public class Tests {

	public static void main(String[] args) {
		CadastroDAOimp cadastroDAOimp = new CadastroDAOimp();
		Telefone telefone = new Telefone();
		Pessoa pessoa = new Pessoa();
//		java.util.List<Telefone> telefonelist = new ArrayList<>();
//		
//		telefone.setDdd("8");
//		telefone.setNumero("985256702");
//		
//		telefonelist.add(telefone);
//		
//		pessoa.setTelefone(telefonelist);
//		pessoa.setCpf("985213123");
//		pessoa.setNome("Paulo");
//		pessoa.setDataNascimento(new Date());
//		pessoa.setEmail("paulovictor494@gmail.com");
//		
//		if(cadastroDAOimp.inserirCadastro(telefone, pessoa)){
//			System.out.println("funcionou");
//		}else{
//			System.out.println("pifou");
//		}
		
		java.util.List<Pessoa> list = cadastroDAOimp.listarPessoa();
		
		for(Pessoa p: list){
			System.out.println(p.getNome());
			System.out.println(p.getTelefone().size());
		}
		
	}

}
