package com.mobileservices.warehouse.user.repository;

import com.mobileservices.warehouse.user.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  @Query("SELECT * FROM USERS WHERE USERNAME = :username")
  Optional<User> findByUsername(String username);
}
