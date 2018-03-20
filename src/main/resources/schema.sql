
CREATE TABLE IF NOT EXISTS users ( 
			user_id serial PRIMARY KEY,
			first_name varchar(45) NOT NULL,
			last_name varchar(45),
			email varchar(60) NOT NULL UNIQUE,
			phone varchar(45) NOT NULL UNIQUE,
			password varchar(255) NOT NULL,
			photo varchar(255) ,
			gender varchar(45),
			status varchar(45) ,
			current_address varchar(255), 
			permanent_address varchar(255),
			security_question varchar(255) NOT NULL, 
			security_ans varchar(255) NOT NULL 
			);
			
CREATE TABLE IF NOT EXISTS roles (
			role_id serial PRIMARY KEY,
			role_name varchar(45) NOT NULL
			);

CREATE TABLE IF NOT EXISTS users_roles (
			user_roleid serial,
			role_id INTEGER,
			user_id INTEGER,
			PRIMARY KEY (user_roleid),
			CONSTRAINT roleidfk FOREIGN KEY (role_id) REFERENCES roles (role_id),
			CONSTRAINT useridfk FOREIGN KEY (user_id) REFERENCES users (user_id)
			);

CREATE TABLE IF NOT EXISTS batchs (
			batch_id serial,
			batch_no INTEGER NOT NULL, 
			seat_limit INTEGER NOT NULL,
			PRIMARY KEY (batch_id) 
			);			
			
CREATE TABLE IF NOT EXISTS teachers (
			teacher_id serial,
			user_id INTEGER NOT NULL,
			PRIMARY KEY (teacher_id),
			CONSTRAINT user_ID_fk FOREIGN KEY (user_id) REFERENCES users (user_id) 
			);
			
CREATE TABLE IF NOT EXISTS students (
			student_id serial, 
			batch_id INTEGER NOT NULL, 
			user_id INTEGER NOT NULL,
			selected_course varchar(45) NOT NULL,
			generated_st_id varchar(45) DEFAULT NULL,
			PRIMARY KEY (student_id),
			CONSTRAINT user_ID_student_fk FOREIGN KEY (user_id) REFERENCES users (user_id),
			CONSTRAINT batchfk FOREIGN KEY (batch_id) REFERENCES batchs (batch_id) 
			);

CREATE TABLE IF NOT EXISTS courses ( 
			course_id serial,
			course_name varchar(45) NOT NULL, 
			description varchar(45),
			PRIMARY KEY (course_id) 
			);

CREATE TABLE IF NOT EXISTS books (
			book_id serial, 
			course_id INTEGER NOT NULL, 
			book_name varchar(45) NOT NULL,
			author_name varchar(45) NOT NULL, 
			edition varchar(45),
			PRIMARY KEY (book_id),
			CONSTRAINT courseidfk FOREIGN KEY (course_id) REFERENCES courses (course_id) 
			);

CREATE TABLE IF NOT EXISTS chapters ( 
			ch_id serial, 
			book_id INTEGER NOT NULL, 
			chapter_name varchar(45) NOT NULL,
			PRIMARY KEY (ch_id),
			CONSTRAINT booksid_fk_chapter FOREIGN KEY (book_id) REFERENCES books (book_id)
			);

CREATE TABLE IF NOT EXISTS reference ( 
			ref_id serial, 
			course_id INTEGER NOT NULL, 
			reference_header varchar(255) NOT NULL,
			PRIMARY KEY (ref_id),
			CONSTRAINT courseid_fk_ref FOREIGN KEY (course_id) REFERENCES courses (course_id) 
			);

CREATE TABLE IF NOT EXISTS questions_bank (
			qus_bank_id serial, 
			teacher_id INTEGER NOT NULL,
			book_id varchar(11) ,
			course_id INTEGER NOT NULL ,
			ch_id varchar(11),
			ref_id varchar(11),
			question_created_date varchar(255) ,
			question_title Text NOT NULL, 
			option1 varchar(255),
			option2 varchar(255),
			option3 varchar(255),
			option4 varchar(255), 
			ans INTEGER NOT NULL,
			PRIMARY KEY (qus_bank_id),
			CONSTRAINT coursefkqb FOREIGN KEY (course_id) REFERENCES courses (course_id), 
			CONSTRAINT teacherfkqb FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id), 
			CONSTRAINT CHK_book_ref CHECK ((book_id IS NOT NULL AND ref_id IS NULL) OR (book_id IS NULL AND ref_id IS NOT NULL))
			);

CREATE TABLE IF NOT EXISTS exam_board (
			exam_id serial,
			course_id INTEGER, 
			exam_date date ,
			total_question INTEGER,
			exam_duration varchar (80) ,
			exam_status varchar (50),
			total_mark INTEGER DEFAULT 100,
			pass_mark INTEGER DEFAULT 33,
			PRIMARY KEY (exam_id),
			CONSTRAINT coursefk FOREIGN KEY (course_id) REFERENCES courses (course_id)
			);

CREATE TABLE IF NOT EXISTS questioner_definations ( 
			defination_id serial,
			exam_id INTEGER NOT NULL,
            course_id INTEGER,
			teacher_id INTEGER,
			book_id INTEGER,
			ch_id INTEGER,
			ref_id INTEGER ,
			qus_limitation varchar(45) ,
			PRIMARY KEY (defination_id),
			CONSTRAINT booksId_FK_qd FOREIGN KEY (book_id) REFERENCES books (book_id), 
			CONSTRAINT chID_FK_qd FOREIGN KEY (ch_id) REFERENCES chapters (ch_id), 
			CONSTRAINT courseID_fk_qd FOREIGN KEY (course_id) REFERENCES courses (course_id),
			CONSTRAINT examID_FK_qd FOREIGN KEY (exam_id) REFERENCES exam_board (exam_id),
			CONSTRAINT refID_FK_qd FOREIGN KEY (ref_id) REFERENCES reference (ref_id), 
			CONSTRAINT techerID_Fk_qd FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id)
			);

CREATE TABLE IF NOT EXISTS question_paper ( 
			qus_id serial,
			exam_id INTEGER NOT NULL,
			student_id INTEGER NOT NULL,
			qus_bank_id INTEGER NOT NULL,
			mark_question varchar(15),
			collected_ans INTEGER,
			PRIMARY KEY (qus_id),
			CONSTRAINT question_bankidFK_Qpaper FOREIGN KEY (qus_bank_id) REFERENCES questions_bank (qus_bank_id),
			CONSTRAINT exam_ID_FK_Qpaper FOREIGN KEY (exam_id) REFERENCES exam_board (exam_id),
			CONSTRAINT studentID_FK_Qpaper FOREIGN KEY (student_id) REFERENCES students (student_id)
			);

CREATE TABLE IF NOT EXISTS exam_info (
			info_id serial,
			student_id INTEGER NOT NULL,
			generated_st_id varchar(45) NOT NULL,
			start_time time,
			end_time time,
			date date NOT NULL,
			score varchar(255),
			grade varchar(255), 
			PRIMARY KEY (info_id),
			CONSTRAINT studentIdFk_ES FOREIGN KEY (student_id) REFERENCES students (student_id) 
			);

