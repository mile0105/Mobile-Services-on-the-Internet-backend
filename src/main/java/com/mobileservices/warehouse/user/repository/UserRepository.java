package com.mobileservices.warehouse.user.repository;

import com.mobileservices.warehouse.user.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
