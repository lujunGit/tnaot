package com.mongcent.tnaot.repository;

import com.mongcent.tnaot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserById(Long id);
    User getUserByName(String name);
}
