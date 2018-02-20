package com.mfasia.onlineexamsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfasia.onlineexamsystem.entities.Reference;
import com.mfasia.onlineexamsystem.service.ReferencesService;

@RestController
@RequestMapping("/reference")
public class ReferencesController {

	@Autowired
	private ReferencesService refService;
	
	@GetMapping("/{courseId}")
	public List<Reference> getReferencesBycourseId (@PathVariable("courseId") Long courseId) {
		return refService.getReferencesBycourseId(courseId);
	}

}
