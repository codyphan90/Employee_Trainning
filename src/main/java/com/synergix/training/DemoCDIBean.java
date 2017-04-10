package com.synergix.training;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class DemoCDIBean implements Serializable {
	private static final long serialVersionUID = 1L;
//	private String username = "Peter";
	private String username ;
	
	
	
//	public DemoCDIBean() {
//	super();
////	this.username = username;
//}


	public String getUsername() {
		return this.username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void sayHello(){
//		DemoCDIBean demo = new DemoCDIBean();
		System.out.println("Hello "+username);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hi There!", "I know you are: "+username));
	}
	
	

	public String getCurrentTime() {        
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM-yyyy HH:mm:ss"));
    }
	@PreDestroy
	public void preDestroy(){
		
		System.out.println("PreDestroy...");
		
	}
	@PostConstruct
public void postConstruct(){
		
		System.out.println("postConstruct...");
		
	}
}
