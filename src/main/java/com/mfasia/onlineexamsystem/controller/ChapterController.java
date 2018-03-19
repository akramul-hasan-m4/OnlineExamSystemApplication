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
import com.mfasia.onlineexamsystem.entities.Chapter;
import com.mfasia.onlineexamsystem.service.ChapterService;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

	
	@Autowired private ChapterService chaterService;
	@Autowired private MessageSource msgSource ;
	
	@GetMapping("/{bookId}")
	public ResponseEntity<List<Chapter>> findAllChaperByBookId (@PathVariable Long bookId) {
		List<Chapter> list = chaterService.findAllChaperByBookId(bookId);
		HttpHeaders headers = new HttpHeaders();
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.getAllErrorMsg", null, null));
		if (list.isEmpty()) {
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Chapter>> getAllChapters () {
		List<Chapter> list = chaterService.getAllChapters();
		HttpHeaders headers = new HttpHeaders();
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.getAllErrorMsg", null, null));
		if (list.isEmpty()) {
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Chapter> saveChapter(@RequestBody Chapter chapter, BindingResult result) {
		chaterService.saveChapter(chapter);
		HttpHeaders headers = new HttpHeaders();
		if (result.hasErrors()) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.saveErrorMsg", null, null));
			return ResponseEntity.noContent().headers(headers).build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(chapter.getChId()).toUri();
		headers.setLocation(location);
		headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.saveSuccessMsg", null, null));
		return ResponseEntity.created(location).headers(headers).build();
	} 
	
	@PutMapping("/{chapterId}")
	public ResponseEntity<Chapter> updateChapterInfo (@RequestBody Chapter chapter, @PathVariable Long chapterId) {
		Optional<Chapter> findChapter = chaterService.findByChapterId(chapterId);
		HttpHeaders headers = new HttpHeaders();
		if (!findChapter.isPresent()) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.findByidErrorMsg", null, null)+chapterId);
			return ResponseEntity.notFound().headers(headers).build();
		}
		headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.updatemsg", null, null));
		chaterService.saveChapter(chapter);
		return ResponseEntity.noContent().headers(headers).build();
	}
	
	@DeleteMapping("/{chapterId}")
	public ResponseEntity<Void> deleteChapter (@PathVariable Long chapterId) {
		Optional<Chapter> findChapter = chaterService.findByChapterId(chapterId);
		HttpHeaders headers = new HttpHeaders();
		if(findChapter.isPresent()) {
			headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.deleteSuccessMsg", null, null));
			chaterService.deleteChapterById(chapterId);
			return ResponseEntity.noContent().headers(headers).build();
		}
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.deleteFailedMsg ", null, null));
		return ResponseEntity.notFound().headers(headers).build();
	}
}
