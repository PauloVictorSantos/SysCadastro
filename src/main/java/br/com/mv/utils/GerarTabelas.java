package br.com.mv.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import br.com.mv.model.Pessoa;

public class GerarTabelas {

	public static void main(String[] args) {
		try {
			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			// Log the exception.
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		System.out.println("Tabela " + Pessoa.class.getName() + " foi criado!");

	}

}
