package com.ea.project.respository;

import com.ea.project.entity.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends ListCrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
