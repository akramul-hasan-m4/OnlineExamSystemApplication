package com.MFAsia.onlineExamSystem;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mfasia.onlineexamsystem.controller.QuesinerDefinationController;
import com.mfasia.onlineexamsystem.entities.QuestionerDefination;
import com.mfasia.onlineexamsystem.entities.UserRole;
import com.mfasia.onlineexamsystem.models.CustomUserDetails;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuesinerDefinationTest {

	@Autowired QuesinerDefinationController quesDefinationCtrl;
	@Autowired CustomUserDetails cus;
	
	@Test
	public void findByExamId() {
		List<QuestionerDefination> list = quesDefinationCtrl.findByExamId(1l);
			list.forEach(l -> System.out.println(l.getExam().getExamId().toString())
			);
	}
	
	@Test
	public void findByRole() {
		List<UserRole> list = cus.getUsersRoles();
			list.forEach(l -> System.out.println(l.getRoles().toString())
			);
	}

}
