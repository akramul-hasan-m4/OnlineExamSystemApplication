package com.mfasia.onlineexamsystem.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mfasia.onlineexamsystem.commons.Messages;
import com.mfasia.onlineexamsystem.entities.Reference;
import com.mfasia.onlineexamsystem.service.ReferencesService;

/**
 * @author Akramul
 */
@RestController
@RequestMapping(ReferencesController.REFERENCE_MAPPING)
public class ReferencesController {
	
	public static final String REFERENCE_MAPPING= "/reference";
	
	@Autowired private ReferencesService refService;
	@Autowired private MessageSource msgSource ;

	@GetMapping("/{courseId}")
	public ResponseEntity<List<Reference>> getReferencesBycourseId (@PathVariable Long courseId) {
		List<Reference> list = refService.getReferencesBycourseId(courseId);
		HttpHeaders headers = new HttpHeaders();
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.getAllErrorMsg", null, null));
		if (list.isEmpty()) {
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Reference>> getAllReference () {
		List<Reference> list = refService.getAllReference();
		HttpHeaders headers = new HttpHeaders();
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.getAllErrorMsg", null, null));
		if (list.isEmpty()) {
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Reference> saveReference(@RequestBody Reference reference, BindingResult result) {
		refService.saveRefrence(reference);
		HttpHeaders headers = new HttpHeaders();
		if (result.hasErrors()) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.saveErrorMsg", null, null));
			return ResponseEntity.noContent().headers(headers).build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(reference.getRefId()).toUri();
		headers.setLocation(location);
		headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.saveSuccessMsg", null, null));
		return ResponseEntity.created(location).headers(headers).build();
	} 
	
	@PutMapping("/{refId}")
	public ResponseEntity<Reference> updateReference (@RequestBody Reference reference, @PathVariable Long refId) {
		Optional<Reference> findReference = refService.findByRefId(refId);
		HttpHeaders headers = new HttpHeaders();
		if (!findReference.isPresent()) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.findByidErrorMsg", null, null)+refId);
			return ResponseEntity.notFound().headers(headers).build();
		}
		headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.updatemsg", null, null));
		refService.saveRefrence(reference);
		return ResponseEntity.noContent().headers(headers).build();
	}
	
	@DeleteMapping("/{refId}")
	public ResponseEntity<Void> deleteReference (@PathVariable Long refId) {
		Optional<Reference> findReference = refService.findByRefId(refId);
		HttpHeaders headers = new HttpHeaders();
		if(findReference.isPresent()) {
			headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.deleteSuccessMsg", null, null));
			refService.deleteReference(refId);
			return ResponseEntity.noContent().headers(headers).build();
		}
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.deleteFailedMsg ", null, null));
		return ResponseEntity.notFound().headers(headers).build();
	}

}
