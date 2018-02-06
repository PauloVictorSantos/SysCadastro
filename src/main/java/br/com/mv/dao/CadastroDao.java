package br.com.mv.dao;

import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.mv.model.Pessoa;
import br.com.mv.utils.HibernateUtil;



public class CadastroDao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void save(Pessoa livro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(livro);
		t.commit();
	}
	
}
