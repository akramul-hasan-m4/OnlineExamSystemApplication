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
import com.mfasia.onlineexamsystem.entities.Batch;
import com.mfasia.onlineexamsystem.service.BatchService;

@RestController
@RequestMapping("/batch")
public class BatchController {

	@Autowired private BatchService batchService;
	@Autowired private MessageSource msgSource ;
	
	@GetMapping
	public ResponseEntity<List<Batch>> getAllBatches () {
		List<Batch> list = batchService.getAllBatches();
		HttpHeaders headers = new HttpHeaders();
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.getAllErrorMsg", null, null));
		if (list.isEmpty()) {
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Batch> saveBatch(@RequestBody Batch batch, BindingResult result) {
		batchService.saveBatches(batch);
		HttpHeaders headers = new HttpHeaders();
		if (result.hasErrors()) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.saveErrorMsg", null, null));
			return ResponseEntity.noContent().headers(headers).build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(batch.getBatchId()).toUri();
		headers.setLocation(location);
		headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.saveSuccessMsg", null, null));
		return ResponseEntity.created(location).headers(headers).build();
	} 
	
	@PutMapping("/{batchId}")
	public ResponseEntity<Batch> updateBatch (@RequestBody Batch batchs, @PathVariable Long batchId) {
		Optional<Batch> batch = batchService.findByBatchId(batchId);
		HttpHeaders headers = new HttpHeaders();
		if (!batch.isPresent()) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.findByidErrorMsg", null, null)+batchId);
			return ResponseEntity.notFound().headers(headers).build();
		}
		headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.updatemsg", null, null));
		batchService.saveBatches(batchs);
		return ResponseEntity.noContent().headers(headers).build();
	}
	
	@DeleteMapping("/{batchId}")
	public ResponseEntity<Void> deleteBatch (@PathVariable Long batchId) {
		Optional<Batch> batch = batchService.findByBatchId(batchId);
		HttpHeaders headers = new HttpHeaders();
		if(batch.isPresent()) {
			headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.deleteSuccessMsg", null, null));
			batchService.deleteBatch(batchId);
			return ResponseEntity.noContent().headers(headers).build();
		}
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.deleteFailedMsg ", null, null));
		return ResponseEntity.notFound().headers(headers).build();
	}
}
