package br.com.aline.erp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.aline.erp.model.RamoAtividade;

public class TestaGuardarRamoAtividade {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jsf1PU");
		EntityManager em = factory.createEntityManager();
		
		
		em.getTransaction().begin();
		RamoAtividades ramoAtividades = new RamoAtividades(em);
		
		RamoAtividade ra = new RamoAtividade();
		ra.setDescricao("Exemplo 2");
		ramoAtividades.guardar(ra);
		
		em.getTransaction().commit();
		List<RamoAtividade> list = em.createQuery("from RamoAtividade", RamoAtividade.class).getResultList();
		System.out.println(list);
		
		List<RamoAtividade> listRA = ramoAtividades.pesquisar("");
		
		System.out.println(listRA);
		
		em.close();
	}

}
