package com.example.web_project.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.web_project.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

