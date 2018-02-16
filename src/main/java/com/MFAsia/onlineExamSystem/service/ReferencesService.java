package com.MFAsia.onlineExamSystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MFAsia.onlineExamSystem.entities.Reference;
import com.MFAsia.onlineExamSystem.repository.ReferencesRepository;

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
}
