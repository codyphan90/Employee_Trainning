package com.synergix.training.hibernate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;

@ApplicationScoped
public class Resources {
	
	@Produces
	EntityManager produceEntityManager(){
		System.out.println("getEntity...");
		return EmLocator.getEntityManager();
	}
	
	@PreDestroy
	public void preDestroy(){
		
		System.out.println("PreDestroy Resource...");
		
	}
	@PostConstruct
public void postConstruct(){
		
		System.out.println("postConstruct Resource...");
		
	}
}
