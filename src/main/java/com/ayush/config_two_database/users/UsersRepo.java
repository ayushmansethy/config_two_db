package com.ayush.config_two_database.users;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
@Qualifier("usersEntityManeger")
public interface UsersRepo  extends JpaRepository<Users,Integer>{
    
}
