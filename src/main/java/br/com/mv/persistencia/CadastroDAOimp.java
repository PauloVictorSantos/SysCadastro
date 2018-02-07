package br.com.mv.persistencia;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.mv.model.Pessoa;
import br.com.mv.model.Telefone;
import br.com.mv.utils.HibernateUtil;

public class CadastroDAOimp implements Cadastro {
	private Session session = null;
	private Transaction transaction = null;

	@Override
	public boolean inserirCadastro(Telefone telefone, Pessoa pessoa) {
		boolean retorno = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
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

	@Override
	public List<Pessoa> listarPessoa() {
		List<Pessoa> list = null;
		final String sql = "SELECT pessoa FROM Pessoa pessoa";

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
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
		final String sql = "SELECT pessoa FROM Pessoa pessoa WHERE pessoa.cpf= :cpf or pessoa.nome=:nome";

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			list = (List<Pessoa>) session.createQuery(sql).setParameter("cpf", p.getCpf())
					.setParameter("nome", p.getNome()).list();
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
			transaction = session.beginTransaction();
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
			transaction = session.beginTransaction();
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
			transaction = session.beginTransaction();
			pessoa = (Pessoa) session.get(Pessoa.class, new Long(d));
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return pessoa;
	}

	
}
