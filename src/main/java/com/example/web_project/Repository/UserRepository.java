package com.example.web_project.Repository;

import com.example.web_project.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {
    User save(User user);
}
