package com.mfasia.onlineexamsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfasia.onlineexamsystem.entities.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long>{

}
