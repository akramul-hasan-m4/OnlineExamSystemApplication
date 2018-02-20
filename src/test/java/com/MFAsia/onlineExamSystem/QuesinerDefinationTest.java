package com.MFAsia.onlineExamSystem;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mfasia.onlineexamsystem.controller.QuesinerDefinationController;
import com.mfasia.onlineexamsystem.entities.QuestionerDefination;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuesinerDefinationTest {

	@Autowired QuesinerDefinationController quesDefinationCtrl;
	
	@Test
	public void findByExamId() {
		List<QuestionerDefination> list = quesDefinationCtrl.findByExamId(1l);
		//list.forEach(l -> System.out.println(l.getExam().getExamId().toString())
			//	);
	}
}
