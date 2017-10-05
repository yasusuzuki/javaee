package com.clarity;

import javax.faces.context.FacesContext;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.el.ELResolver;
import java.util.ArrayList;

@ManagedBean()
@RequestScoped 

public class Place { 
  String streetAddress = "747 Howard St.",
         city = "San_Francisco", 
  		 state = "CA",
  		 zip = "94103",  
  		 weather = null ;
  
  String[] mapUrls = null;
  int zoomIndex = 1;
  
  // CONSTRUCTORS
  
  public Place() { System.out.println("Creating a new place: " + this);}
    
  public Place(String streetAddress, String city, String state, String zip,
		  String[] mapUrls, String weather) {
    setStreetAddress(streetAddress);
    setCity(city) ;
    setState(state);
    setMapUrls(mapUrls) ;
    setWeather(weather);
  } 
     
  // EVENT HANDLERS
  
  public String fetch() {
	FacesContext fc = FacesContext.getCurrentInstance()	  ;  
	ELResolver elResolver = fc.getApplication().getELResolver();
	
	MapService ms = (MapService) elResolver.getValue(
	  fc.getELContext(), null, "mapService");
	 
	mapUrls = ms.getMap(streetAddress, city, state);

	Places places = (Places) elResolver.getValue(
              fc.getELContext(), null, "places");

	WeatherService ws = (WeatherService) elResolver.getValue(
	              fc.getELContext(), null, "weatherService");

	weather = ws.getWeatherForZip(zip, true);
	
	places.addPlace(this);

    return null;
  }
  
  public void zoomChanged(ValueChangeEvent e) {
    // SelectItem a = e.getComponent();
	  String value = "1000";
    zoomIndex = (new Integer(value)).intValue();
  }
  
  // PROPERTY SETTER AND GETTERS
  
  public void setMapUrls(String[] mapUrls) {
	this.mapUrls = mapUrls;
  }
  public String[] getMapUrls() {
    return ((mapUrls == null) ? new String[]{""} : mapUrls);
  }
  
  public String getMapUrl() {
	return mapUrls == null ? "" : mapUrls[zoomIndex-1];
  }
  
  public String getZoomLevel() {
    return (new Integer(zoomIndex)).toString();
  }

  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }
  public String getStreetAddress() { return streetAddress; }
  
  public void setCity(String city) { this.city = city; }
  public String getCity() { return city; }
  
  public void setZip(String zip) { this.zip = zip; }
  public String getZip() { return zip; }

  public void setWeather(String weather) { this.weather = weather; }
  public String getWeather() { return weather; }

  public void setState(String state) { this.state = state; }
  public String getState() { return state; }
}
