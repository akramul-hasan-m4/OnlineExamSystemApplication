package com.mfasia.onlineexamsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfasia.onlineexamsystem.entities.Reference;
import com.mfasia.onlineexamsystem.repositories.ReferencesRepository;

@Service
public class ReferencesService {

	@Autowired
	private ReferencesRepository referenceRepo;
	
	@Transactional
	public List<Reference> getReferencesBycourseId (Long courseId) {
		List<Reference> list= new ArrayList<>();
		referenceRepo.findAllRefByCourseId(courseId).forEach(list::add);
		return list;
	}
	
	@Transactional
	public List<Reference> getAllReference (){
		return referenceRepo.findAll();
	}
	
	@Transactional
	public void saveRefrence (Reference reference) {
		referenceRepo.save(reference);
	}
	
	@Transactional
	public Optional<Reference> findByRefId (Long refId){
		return Optional.of(referenceRepo.findOne(refId));
	}
	
	@Transactional
	public void deleteReference (Long refId) {
		referenceRepo.delete(refId);
	}
}
