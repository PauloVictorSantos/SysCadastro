package br.com.nextln.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.nextln.model.Pessoa;
import br.com.nextln.model.Telefone;
import br.com.nextln.utils.HibernateUtil;

public class CadastroDAOimp implements Cadastro {
	private Session session = null;
	private Transaction transaction = null;

	@Override
	public boolean inserirCadastro(Telefone telefone, Pessoa pessoa) {
		boolean retorno = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			// telefone = new Telefone();
			// pessoa = new Pessoa();
			System.out.println(telefone.getDdd());
			session.persist(telefone);
			session.persist(pessoa);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return retorno;
	}

//melhorar a pequisa e usar funcões de agregação, olhar apostilha JPA/Hibernate
	@Override
	public List<Pessoa> listarPessoa() {
		List<Pessoa> list = null;
		final String sql = "SELECT pessoa FROM Pessoa pessoa";

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			list = (List<Pessoa>) session.createQuery(sql).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> procurarPessoa(Pessoa p) {
		List<Pessoa> list = null;
		final String sql = "SELECT pessoa FROM Pessoa pessoa WHERE pessoa.cpf= :cpf or pessoa.nome like :nome";

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			
			list = (List<Pessoa>) session.createQuery(sql).setParameter("cpf", p.getCpf())
					.setParameter("nome","%"+ p.getNome()+"%").list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			
			session.close();
		}
		return list;

	}

	@Override
	public boolean excluirPessoa(Pessoa p) {
		boolean retorno = false;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			session.delete(session.get(Pessoa.class, p.getId()));
			
			transaction.commit();
			retorno = true;
		} catch (HibernateException e) {
			transaction.rollback();
		} finally {
	
			session.close();
		}

		return retorno;
	}

	@Override
	public boolean alterarPessoa(Pessoa p) {
		boolean retorno = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			session.update(p);
			transaction.commit();
			retorno = true;
		} catch (HibernateException e) {
			transaction.commit();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return retorno;
	}

	public Pessoa pegarPessoa(Long d) {
		Pessoa pessoa = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			
			pessoa = (Pessoa) session.get(Pessoa.class, new Long(d));
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			
			session.close();
		}
		return pessoa;
	}

	
}
