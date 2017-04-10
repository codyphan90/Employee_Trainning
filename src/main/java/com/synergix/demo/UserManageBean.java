package com.synergix.demo;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@ManagedBean
@SessionScoped
public class UserManageBean {
	@Inject
    EntityManager entityManager;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	private String id;
	private String name;
	private String surname;
	private String message;
	private String selectedname;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	

	public String getSelectedname() {
		return selectedname;
	}

	public void setSelectedname(String selectedname) {
		this.selectedname = selectedname;
	}

	public String getMessage() {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("Name : ").append(this.getName());
		strBuff.append(", Surname : ").append(this.getSurname());
		this.setMessage(strBuff.toString());
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String save() {
		String result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		User user = new User();
		user.setName(this.getName());
		user.setSurname(this.getSurname());
		System.out.println("Create user: " + user.toString());

		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
//			log.debug("New Record : " + user + ", wasCommitted : " + tx.wasCommitted());
			result = SUCCESS;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				result = ERROR;
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		return result;
	}
	
	//delete
	public String deleteUser(int id){
		
		String result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
//		Query createQuery = session.createQuery("delete from Student s where s.id =:id");
		System.out.println("delete User ID: " +id);
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User user = (User) session.load(User.class, new Integer(id)); 
			session.delete(user);
			tx.commit();
		
			result = SUCCESS;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				result = ERROR;
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		return result;
	}
	//get by id
	public List<User> getbyid(int id){
//		String result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("get by User ID: " +id);
		Transaction tx = null;
		User user=new User();
        List<User> user1=new ArrayList();
		try {
			tx = session.beginTransaction();
			Query query=session.createQuery("from Student where id= :id");
            query.setParameter("id", id);
            user=(User)query.uniqueResult();
            user1=query.list();
			tx.commit();
		
//			result = SUCCESS;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
//				result = ERROR;
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		return user1;
	}
	
	//Update User
	public String updateUser(User user){
		String result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("Update User: ");
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
		
			result = SUCCESS;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				result = ERROR;
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		return result;
	}
	//Get all user
	public List<User> getUsers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
//		List<User> userList = session.createCriteria(User.class).list();
		List<User> userList = session.createQuery("from Student s").list();
		return userList;
	}

	public void reset() {
		this.setName("");
		this.setSurname("");
	}
}
