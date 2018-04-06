package com.mfasia.onlineexamsystem.controller;

import java.net.URI;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
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
import com.mfasia.onlineexamsystem.entities.Book;
import com.mfasia.onlineexamsystem.service.BooksService;

@RestController
@RequestMapping("/books")
public class BooksController {
	
	@Autowired private BooksService booksService;
	@Autowired private MessageSource msgSource ;
	
	@GetMapping("/principal")
	public Principal prin (Principal principle) {
		return principle;
	}
	
	   @RequestMapping("/user")
	   @SuppressWarnings("unchecked")
	    public String user(OAuth2Authentication authentication) {
			LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();
	       
			return (String) properties.get("email")+","+properties.get("name")+","+properties.get("picture");
	    }
	
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks () {
		List<Book> list = booksService.getAllBooks();
		HttpHeaders headers = new HttpHeaders();
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.getAllErrorMsg", null, null));
		if (list.isEmpty()) {
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Book> saveBook(@RequestBody Book book, BindingResult result) {
		booksService.saveBook(book);
		HttpHeaders headers = new HttpHeaders();
		if (result.hasErrors()) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.saveErrorMsg", null, null));
			return ResponseEntity.noContent().headers(headers).build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(book.getBookId()).toUri();
		headers.setLocation(location);
		headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.saveSuccessMsg", null, null));
		return ResponseEntity.created(location).headers(headers).build();
	} 
	
	@GetMapping("/{courseId}")
	public ResponseEntity<List<Book>> getAllBooksByCourseId (@PathVariable Long courseId) {
		List<Book> list = booksService.getBooksBycourseId(courseId);
		HttpHeaders headers = new HttpHeaders();
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.getAllErrorMsg", null, null));
		if (list.isEmpty()) {
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PutMapping("/{bookId}")
	public ResponseEntity<Book> updateBookInfo (@RequestBody Book book, @PathVariable Long bookId) {
		Optional<Book> findBook = booksService.findByBookId(bookId);
		HttpHeaders headers = new HttpHeaders();
		if (!findBook.isPresent()) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.findByidErrorMsg", null, null)+bookId);
			return ResponseEntity.notFound().headers(headers).build();
		}
		headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.updatemsg", null, null));
		booksService.saveBook(book);
		return ResponseEntity.noContent().headers(headers).build();
	}
	
	@DeleteMapping("/{bookId}")
	public ResponseEntity<Void> deleteBook (@PathVariable Long bookId) {
		Optional<Book> findBook = booksService.findByBookId(bookId);
		HttpHeaders headers = new HttpHeaders();
		if(bookId != null && findBook.isPresent()) {
			headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.deleteSuccessMsg", null, null));
			booksService.deleteBook(bookId);
			return ResponseEntity.noContent().headers(headers).build();
		}
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.deleteFailedMsg ", null, null));
		return ResponseEntity.notFound().headers(headers).build();
	}

}
