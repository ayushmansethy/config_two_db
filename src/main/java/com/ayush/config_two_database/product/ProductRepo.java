package com.ayush.config_two_database.product;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
@Qualifier("productEntityManeger")
public interface ProductRepo extends JpaRepository<Product,Integer > {
    
}
