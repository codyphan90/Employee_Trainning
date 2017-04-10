package com.synergix.emp;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;


@ManagedBean
@SessionScoped
public class EmployeeBean {
	@Inject
    EntityManager entityManager;
	
	public List<NickEmployee> employees(){
		String str = "SELECT n FROM NickEmployee n";
		
		List<NickEmployee> employees = entityManager.createQuery(str, NickEmployee.class).getResultList();
		return employees;
	}
	public List<NickEmployee> filteremployees(){
		String str = "SELECT n FROM NickEmployee n";
		
		List<NickEmployee> employees = entityManager.createQuery(str, NickEmployee.class).getResultList();
		return employees;
	}
	
	
	public EmployeeBean() {
		super();
	}


	public void create(){
		//input code first 
		
	}
	public void close(){
		//back to summary
	}
	
	public void submit(){
		// change status from Draft ('D') --> outstanding ('O')
		// have confirmation mess
		
	}
	public void abort(){
		//delete then back to summary
		// have confirmation mess
	}
	
}
