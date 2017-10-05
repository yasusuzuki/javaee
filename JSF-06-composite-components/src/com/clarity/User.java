package com.clarity;

import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent ;
import javax.faces.event.ValueChangeEvent ;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput ;
import javax.faces.application.FacesMessage;
 
@ManagedBean()   
@SessionScoped
   
public class User {	    
  private String name="My  Name", planet,email;
  private String password, nameError;
  private String action;
  
  public String action() {
	return action;
}

public String getEmail() { return email; }
  public void setEmail(String newValue) { email = newValue; }
 
  public String getName() { return name; }
  public void setName(String newValue) { name = newValue; }
  
  public void setNameError(String error) {nameError = error;}
  public String getNameError() {return nameError;}

  public String getPassword() { return password; }
  public void setPassword(String newValue) { password = newValue; }  
  
  public String login() {
	  return "/views/places";
  }  

  public String logout() {
	name = password = nameError = null;  
	return "/views/login";
  }

  public String goPlanet() {
	  return "/views/" + planet;
  }
  
  public String getPlanetText() { 
	  return "/resources/textfiles/" + (planet == null ? "mercury" : planet) + ".txt";
  } 
}
