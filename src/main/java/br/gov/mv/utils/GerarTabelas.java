package br.gov.mv.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import br.com.mv.model.Pessoa;
import br.com.mv.model.Telefone;

public class GerarTabelas {

	public static void main(String[] args) {
	try{
		 SessionFactory sessionFactory=  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    } catch (Throwable ex) {
        // Log the exception. 
        System.err.println("Initial SessionFactory creation failed." + ex);
        throw new ExceptionInInitializerError(ex);
    }
		
	
	System.out.println("Tabela "+Pessoa.class.getName()+ " foi criado!");
	
	
	}

}
