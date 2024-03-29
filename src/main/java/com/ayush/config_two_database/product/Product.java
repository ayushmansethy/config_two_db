package com.ayush.config_two_database.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class Product {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Integer prductId ;
    String prductName  ;
    public Product() {
    }
    public Product(Integer prductId, String prductName) {
        this.prductId = prductId;
        this.prductName = prductName;
    }
    public Integer getPrductId() {
        return prductId;
    }
    public void setPrductId(Integer prductId) {
        this.prductId = prductId;
    }
    public String getPrductName() {
        return prductName;
    }
    public void setPrductName(String prductName) {
        this.prductName = prductName;
    }
    
}
