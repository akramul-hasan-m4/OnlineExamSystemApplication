CREATE TABLE IF NOT EXISTS users ( 
			user_id INTEGER(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
			first_name varchar(45) NOT NULL,
			last_name varchar(45),
			email varchar(45) NOT NULL UNIQUE,
			phone varchar(45) NOT NULL UNIQUE,
			password varchar(45) NOT NULL,
			photo varchar(45) ,
			gender varchar(45),
			status varchar(45) ,
			current_address varchar(45), 
			permanent_address varchar(45),
			security_question varchar(45) NOT NULL, 
			security_ans varchar(45) NOT NULL 
			) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS roles (
			role_id INTEGER(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
			role_name varchar(45) NOT NULL
			) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS users_roles (
			user_role_id INTEGER(11) NOT NULL AUTO_INCREMENT,
			role_id INTEGER(11),
			user_id INTEGER(11),
			PRIMARY KEY (user_role_id),
  			KEY roleidfk_idx (role_id ASC),
			KEY useridfk_idx (user_id ASC),
			CONSTRAINT roleidfk FOREIGN KEY (role_id) REFERENCES roles (role_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
			CONSTRAINT useridfk FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
			) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS batchs (
			batch_id INTEGER(11) NOT NULL AUTO_INCREMENT,
			batch_no INTEGER(11) NOT NULL, 
			seat_limit INTEGER(11) NOT NULL,
			PRIMARY KEY (batch_id) 
			) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS teachers (
			teacher_id INTEGER(11) NOT NULL AUTO_INCREMENT,
			user_id INTEGER(11) NOT NULL,
			PRIMARY KEY (teacher_id),
			CONSTRAINT user_ID_fk FOREIGN KEY (user_id) REFERENCES users (user_id) 
			) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS students (
			student_id INTEGER(11) NOT NULL AUTO_INCREMENT, 
			batch_id INTEGER(11) NOT NULL, 
			user_id INTEGER(11) NOT NULL,
			selected_course varchar(45) NOT NULL,
			generated_st_id varchar(45) DEFAULT NULL,
			PRIMARY KEY (student_id),
			CONSTRAINT user_ID_student_fk FOREIGN KEY (user_id) REFERENCES users (user_id),
			CONSTRAINT batchfk FOREIGN KEY (batch_id) REFERENCES batchs (batch_id) 
			) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS courses ( 
			course_id INTEGER(11) NOT NULL AUTO_INCREMENT,
			course_name varchar(45) NOT NULL, 
			description varchar(45),
			PRIMARY KEY (course_id) 
			) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS books (
			book_id INTEGER(11) NOT NULL AUTO_INCREMENT, 
			course_id INTEGER(11) NOT NULL, 
			book_name varchar(45) NOT NULL,
			author_name varchar(45) NOT NULL, 
			edition varchar(45),
			PRIMARY KEY (book_id),
			CONSTRAINT courseidfk FOREIGN KEY (course_id) REFERENCES courses (course_id) 
			) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS chapters ( 
			ch_id INTEGER(11) NOT NULL AUTO_INCREMENT, 
			book_id INTEGER(11) NOT NULL, 
			chapter_name varchar(45) NOT NULL,
			PRIMARY KEY (ch_id),
			CONSTRAINT booksid_fk_chapter FOREIGN KEY (book_id) REFERENCES books (book_id)
			) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS reference ( 
			ref_id INTEGER(11) NOT NULL AUTO_INCREMENT, 
			course_id INTEGER(11) NOT NULL, 
			reference_header varchar(255) NOT NULL,
			PRIMARY KEY (ref_id),
			CONSTRAINT courseid_fk_ref FOREIGN KEY (course_id) REFERENCES courses (course_id) 
			) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS questions_bank (
			qus_bank_id INTEGER(11) NOT NULL AUTO_INCREMENT, 
			teacher_id INTEGER(11) NOT NULL,
			book_id varchar(11) ,
			course_id INTEGER(11)NOT NULL ,
			ch_id varchar(11),
			ref_id varchar(11),
			question_created_date varchar(255) ,
			question_title Text NOT NULL, 
			option1 varchar(255),
			option2 varchar(255),
			option3 varchar(255),
			option4 varchar(255), 
			ans INTEGER(11) NOT NULL,
			PRIMARY KEY (qus_bank_id),
			CONSTRAINT coursefkqb FOREIGN KEY (course_id) REFERENCES courses (course_id), 
			CONSTRAINT teacherfkqb FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id), 
			CONSTRAINT CHK_book_ref CHECK ((book_id IS NOT NULL AND ref_id IS NULL) OR (book_id IS NULL AND ref_id IS NOT NULL))
			) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS exam_board (
			exam_id INTEGER(11) NOT NULL AUTO_INCREMENT,
			course_id INTEGER(11), 
			exam_date date ,
			total_question INTEGER(11),
			exam_duration time ,
			total_mark INTEGER(11) DEFAULT 100,
			pass_mark INTEGER(11) DEFAULT 33,
			PRIMARY KEY (exam_id),
			CONSTRAINT coursefk FOREIGN KEY (course_id) REFERENCES courses (course_id)
			) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS questioner_definations ( 
			defination_id INTEGER(11) NOT NULL AUTO_INCREMENT,
			exam_id INTEGER(11) NOT NULL,
			teacher_id INTEGER(11),
			course_id INTEGER(11) ,
			book_id INTEGER(11),
			ch_id INTEGER(11),
			ref_id INTEGER(11) ,
			qus_limitation varchar(45) ,
			PRIMARY KEY (`defination_id`),
			CONSTRAINT booksId_FK_qd FOREIGN KEY (book_id) REFERENCES books (book_id), 
			CONSTRAINT chID_FK_qd FOREIGN KEY (ch_id) REFERENCES chapters (ch_id), 
			CONSTRAINT courseID_fk_qd FOREIGN KEY (course_id) REFERENCES courses (course_id),
			CONSTRAINT examID_FK_qd FOREIGN KEY (exam_id) REFERENCES exam_board (exam_id),
			CONSTRAINT refID_FK_qd FOREIGN KEY (ref_id) REFERENCES reference (ref_id), 
			CONSTRAINT techerID_Fk_qd FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id)
			) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS question_paper ( 
			qus_id INTEGER(11) NOT NULL AUTO_INCREMENT,
			exam_id INTEGER(11) NOT NULL,
			student_id INTEGER(11) NOT NULL,
			qus_bank_id INTEGER(11) NOT NULL,
			mark_question varchar(15),
			collected_ans INTEGER(11),
			PRIMARY KEY (qus_id),
			CONSTRAINT question_bankidFK_Qpaper FOREIGN KEY (qus_bank_id) REFERENCES questions_bank (qus_bank_id),
			CONSTRAINT exam_ID_FK_Qpaper FOREIGN KEY (exam_id) REFERENCES exam_board (exam_id),
			CONSTRAINT studentID_FK_Qpaper FOREIGN KEY (student_id) REFERENCES students (student_id)
			) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS exam_info (
			info_id INTEGER(11) NOT NULL AUTO_INCREMENT,
			student_id INTEGER(11) NOT NULL,
			generated_st_id varchar(45) NOT NULL,
			start_time time,
			end_time time,
			date date NOT NULL,
			score varchar(255),
			grade varchar(255), 
			PRIMARY KEY (info_id),
			CONSTRAINT studentIdFk_ES FOREIGN KEY (student_id) REFERENCES students (student_id) 
			) ENGINE=InnoDB DEFAULT CHARSET=utf8;
