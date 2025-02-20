package br.com.aline.erp.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProcucer {
	private EntityManagerFactory factory;
	private	Map<String, String> env = System.getenv();
	private	Map<String, Object> configOverrides = new HashMap<String, Object>();

	public EntityManagerProcucer() {
		for (String envName : env.keySet()) {
    			if (envName.contains("DB_URL")) {
       	 			configOverrides.put("javax.persistence.jdbc.url", env.get(envName)));    
    			}
    			if (envName.contains("DB_USER")) {
       	 			configOverrides.put("javax.persistence.jdbc.user", env.get(envName)));    
    			}
			if (envName.contains("DB_PASSWORD")) {
       	 			configOverrides.put("javax.persistence.jdbc.password", env.get(envName)));    
    			}
		}
		this.factory = Persistence.createEntityManagerFactory("jsf1PU", configOverrides);
	}
	
	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {
		return this.factory.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}
}
