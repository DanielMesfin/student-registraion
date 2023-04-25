package com.daniel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	@Column(name="STUD_CITY")
	private String city;
	@Column(name="STUD_SUB_CITY")
	private String sub_city;
    public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSub_city() {
		return sub_city;
	}
	public void setSub_city(String sub_city) {
		this.sub_city = sub_city;
	}
	
    
}
