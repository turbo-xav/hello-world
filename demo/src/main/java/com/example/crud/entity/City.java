package com.example.crud.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name="city", schema="public")
@NamedQueries({
	@NamedQuery(name = "City.findAll", query = " SELECT c FROM City c ORDER BY c.name "),
	@NamedQuery(name = "City.deleteById", query = " DELETE FROM City c WHERE c.id = :id") })
public class City {
    
	@Transient
	public String attr = null; 
	
	@OneToMany(mappedBy = "city", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	
	private List<Monument> monuments = new ArrayList<Monument>();	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name="id")
	private Long id;
	
	@Column(name="name", length=100,nullable=false)
	private String name;
	
	@Column(name="latitude", nullable=false)
	private Double latitude;
	
	@Column(name="longitude", nullable=false)
	private Double longitude;

    public City() {
    }
    
    public City(String name, double latitude, double longitude) {
	  this(null, name, latitude, longitude);
    }
    public City(Long id, String name, double latitude, double longitude) {
	  
      this.id= id;
	  this.name= name;
	  this.latitude= latitude;
	  this.longitude= longitude;
    }
    public Long getId() {
	  return id;
    }

    public void setId(Long id) {
	  this.id = id;
    }

    public String getName() {
	  return name;
    }

    public void setName(String nom) {
	  this.name = nom;
    }

    public Double getLongitude() {
	  return this.longitude;
    }

    public void setLongitude(Double longitude) {
	  this.longitude = longitude;
    }

    public Double getLatitude() {
	  return this.latitude;
    }

    public void setLatitude(Double latitude) {
	  this.latitude = latitude;
    }

    @Override
    public String toString() {
	  return "Adres :"+this.hashCode()+" City [id=" + id + ", name=" + name + ", latitude=" + latitude
	      + ", longitude=" + longitude + "]";
    }

	/**
	 * @return the monuments
	 */
	public List<Monument> getMonuments() {
		return monuments;
	}

	/**
	 * @param monuments the monuments to set
	 */
	public void setMonuments(List<Monument> monuments) {
		this.monuments = monuments;
	}
	
	/**
	 * @param monuments the monuments to set
	 */
	public void addMonument(Monument monument) {
		this.monuments.add(monument);
		monument.setCity(this);
	}
	
	
	
}
