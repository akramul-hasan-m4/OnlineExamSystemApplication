package com.mfasia.onlineexamsystem.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mfasia.onlineexamsystem.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
