package com.mfasia.onlineexamsystem.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfasia.onlineexamsystem.entities.Batch;
import com.mfasia.onlineexamsystem.repositories.BatchRepository;

@Service
public class BatchService {

	@Autowired private BatchRepository batchRepo;
	
	@Transactional
	public List<Batch> getAllBatches (){
		return batchRepo.findAll();
	}
	
	@Transactional
	public void saveBatches (Batch batch) {
		batchRepo.save(batch);
	}
	
	@Transactional
	public void deleteBatch (Long batchId) {
		batchRepo.delete(batchId);
	}
}
