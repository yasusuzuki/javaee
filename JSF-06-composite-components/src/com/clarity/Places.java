package com.clarity;

import java.util.ArrayList;

import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@ManagedBean()
@SessionScoped

public class Places {
  private ArrayList<Place> places = null;
  
  private static SelectItem[] zoomLevelItems = {
    new SelectItem("1"), new SelectItem("2"),
    new SelectItem("3"), new SelectItem("4"),
    new SelectItem("5"), new SelectItem("6"),
    new SelectItem("7"), new SelectItem("8"),
    new SelectItem("9"), new SelectItem("10"),
    new SelectItem("11"), new SelectItem("12")
  };
  
  public void addPlace(Place copyThis) {
	if (places == null)
      places  = new ArrayList<Place>();
	
    Place place = new Place(
    		copyThis.getStreetAddress(), copyThis.getCity(),
    		copyThis.getState(), copyThis.getZip(),
    		copyThis.getMapUrls(), copyThis.getWeather());
    
    places.add(place);
  }
  
  public ArrayList<Place> getPlacesList() { 
	return places; 
  }
  
  public void setPlacesList(ArrayList<Place> places) {
	this.places = places;
  }
  
  public SelectItem[] getzoomLevelItems() {
	return zoomLevelItems;
  }
  
  public String getShowContent() {
    String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
    if ("/views/login.xhtml".equals(viewId)
    	|| ("/views/showSource.xhtml").equals(viewId)
    	|| ("/views/places.xhtml".equals(viewId) && 
    			places != null && places.size() > 0)) {
      return "inline";
    }
    else 
      return "none";
  }
  
  public String logout() {
	FacesContext fc = FacesContext.getCurrentInstance();     
	ELResolver elResolver = fc.getApplication().getELResolver();
	     
	User user = (User)elResolver.getValue(
	           fc.getELContext(), null, "user");
	
	user.setName("");
	user.setPassword("");
	   
    setPlacesList(null);
	   
    return "login"; 
  }
  
  String toggle="one";
  public String getToggle() {
    if ("one".equals(toggle))
      toggle = "two";
    else
      toggle = "one";
    
    return toggle;
  }
}