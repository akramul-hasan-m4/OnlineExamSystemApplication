package com.MFAsia.onlineExamSystem;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mfasia.onlineexamsystem.controller.QuestionBankController;
import com.mfasia.onlineexamsystem.entities.QuestionsBank;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionBankTests {

	@Autowired QuestionBankController qb ;
	
	@Test
	public void getAllbankInfo () {
		ResponseEntity<List<QuestionsBank>> list =  qb.getAllQuesions();
		((Iterable<QuestionsBank>) list).forEach(l -> System.out.println(l.getAns().toString()));
	}

}
