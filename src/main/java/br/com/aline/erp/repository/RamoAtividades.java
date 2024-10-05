package br.com.aline.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.aline.erp.model.RamoAtividade;

public class RamoAtividades implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;

	public RamoAtividades() {
	}
	
	public RamoAtividades(EntityManager em) {
		this.em = em;
	}
	
	public RamoAtividade porId(long id) {
		return this.em.find(RamoAtividade.class, id);
	}
	
	public List<RamoAtividade> pesquisar(String descricao) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		
		CriteriaQuery<RamoAtividade> criteriaQuery = builder.createQuery(RamoAtividade.class);
		Root<RamoAtividade> root = criteriaQuery.from(RamoAtividade.class);
		
		criteriaQuery.select(root);
		criteriaQuery.where(builder.like(root.get("descricao"), descricao + "%"));
		TypedQuery<RamoAtividade> query = em.createQuery(criteriaQuery);
		return query.getResultList();
	}
	
	public void guardar(RamoAtividade ra) {
		em.merge(ra);
	}
	
	public void remover(RamoAtividade ra) {
		ra = this.porId(ra.getId());
		em.remove(ra);
	}
}
