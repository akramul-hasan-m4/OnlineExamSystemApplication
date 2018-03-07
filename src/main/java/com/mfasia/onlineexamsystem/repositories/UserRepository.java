package com.mfasia.onlineexamsystem.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mfasia.onlineexamsystem.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	Optional<User> findByFirstName(String firstName);
}
