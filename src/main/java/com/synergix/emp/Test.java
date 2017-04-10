package com.synergix.emp;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class Test {
	@Inject
    EntityManager entityManager;
	public void getEMP(){
		String str = "SELECT e FROM NICK_EMPLOYEE e";
		
		List<NickEmployee> employees = entityManager.createQuery(str, NickEmployee.class).getResultList();
		Iterator<NickEmployee> list = employees.iterator();
		while (list.hasNext()) {
			NickEmployee emp = list.next();
			System.out.println(emp.getEmployeeCode());
			System.out.println(emp.getEmployeeName());
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Test test = new Test();
				test.getEMP();
	}

}
