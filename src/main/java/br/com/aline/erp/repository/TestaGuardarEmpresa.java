package br.com.aline.erp.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.aline.erp.model.Empresa;
import br.com.aline.erp.model.RamoAtividade;
import br.com.aline.erp.model.TipoEmpresa;

public class TestaGuardarEmpresa {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jsf1PU");
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		Empresas empresas = new Empresas(em);
		RamoAtividades ramoAtividades = new RamoAtividades(em);
		
		Empresa e = new Empresa();
		RamoAtividade ra = ramoAtividades.porId(4);
		
		e.setCnpj("00.000.0000/0001-11");
		e.setDataFundacao(new Date());
		e.setNomeFantasia("Empresa 2 00000");
		e.setRamoAtividade(ra);
		e.setRazaoSocial("Empresa 2");
		e.setTipo(TipoEmpresa.MEI);
		
		empresas.guardar(e);
		
		em.getTransaction().commit();
		List<Empresa> list = empresas.pesquisar("");
		System.out.println(list);
		
		em.close();
		factory.close();
	}
}
