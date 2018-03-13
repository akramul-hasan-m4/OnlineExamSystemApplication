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
public class OnlineExamSystemApplicationTests {

	@Autowired
	QuesinerDefinationController qd;
	@Test
	public void contextLoads() {
		List<QuestionerDefination> list = (List<QuestionerDefination>) qd.getAllQuesionDefination();
		list.forEach(l-> System.out.println(
				" teacher id = "+l.getTeachers().getTeacherId().toString()
				+", exam id = "+l.getExam().getExamId()
				+", course id = "+l.getCourses().getCourseId()
				+", book id = "+l.getBooks().getBookId()
				+", ref id = "+l.getRef().getRefId()
				+", chapter id = "+l.getChapters().getChId()
				+", question limitation = "+l.getQusLimitation()));
	}

}
