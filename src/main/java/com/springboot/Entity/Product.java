package com.springboot.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pro_id;
    private String name;
    private double uPrice;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getPro_id() {
		return pro_id;
	}
	public void setPro_id(Long pro_id) {
		this.pro_id = pro_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getuPrice() {
		return uPrice;
	}
	public void setuPrice(double uPrice) {
		this.uPrice = uPrice;
	}
	@Override
	public String toString() {
		return "Product [pro_id=" + pro_id + ", name=" + name + ", uPrice=" + uPrice + "]";
	} 

    
}