package br.com.aline.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.aline.erp.model.Empresa;

public class Empresas implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;

	public Empresas() {
	}
	
	public Empresas(EntityManager em) {
		this.em = em;
	}
	
	public Empresa porId(long id) {
		return this.em.find(Empresa.class, id);
	}
	
	public List<Empresa> pesquisar(String nome) {
		TypedQuery<Empresa> query = em.createQuery("from Empresa where nomeFantasia like :nome", Empresa.class);
		query.setParameter("nome", nome + "%");
		return query.getResultList();
	}
	
	public List<Empresa> todas() {
		return em.createQuery("from Empresa", Empresa.class).getResultList();
	}
	
	public void guardar(Empresa empresa) {
		em.merge(empresa);
	}
	
	public void remover(Empresa empresa) {
		empresa = this.porId(empresa.getId());
		em.remove(empresa);
	}
}
