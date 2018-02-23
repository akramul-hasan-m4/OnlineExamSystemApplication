-- Initial Data for users
INSERT INTO users(user_id,first_name,last_name,email,phone,password,photo,gender,status,current_address,permanent_address,security_question,security_ans)
SELECT 1 ,'Admin', 'admin', 'admin@gmail.com', '01919223344','1234', 'photo','male', 'Active', 'Uttara', 'Uttara dhaka', 'what is your role?', 'admin' where NOT EXISTS (SELECT * FROM users WHERE user_id = 1) ; 

INSERT INTO users(user_id,first_name,last_name,email,phone,password,photo,gender,status,current_address,permanent_address,security_question,security_ans)
SELECT 2 ,'Teacher', 'Teacher', 'Teacher@gmail.com', '01919223355','1234', 'photo','male', 'Active', 'Uttara', 'Uttara dhaka', 'what is your role?', 'Teacher' where NOT EXISTS (SELECT * FROM users WHERE user_id = 2) ; 

-- Initial Data for roles
INSERT INTO roles(role_id,role_name) SELECT 1 ,'Admin' where NOT EXISTS (SELECT * FROM roles WHERE role_id = 1);
INSERT INTO roles(role_id,role_name) SELECT 2,'Teacher' where NOT EXISTS (SELECT * FROM roles WHERE role_id = 2);

-- Initial Data for users_role
INSERT INTO users_roles(user_role_id, role_id, user_id) SELECT 1, 1, 1 where NOT EXISTS (SELECT * FROM users_roles WHERE user_role_id = 1);
INSERT INTO users_roles(user_role_id, role_id, user_id) SELECT 2, 2, 2 where NOT EXISTS (SELECT * FROM users_roles WHERE user_role_id = 2);

-- Initial Data for teachers
INSERT INTO teachers(teacher_id, user_id) SELECT 1, 1 where NOT EXISTS (SELECT * FROM teachers WHERE teacher_id = 1);
INSERT INTO teachers(teacher_id, user_id) SELECT 2, 2 where NOT EXISTS (SELECT * FROM teachers WHERE teacher_id = 2);

-- Initial Data for Courses
INSERT INTO courses (course_id, course_name, description) SELECT 1, 'Java', 'Basic java' where NOT EXISTS (SELECT * FROM courses WHERE course_id = 1);
INSERT INTO courses (course_id, course_name, description) SELECT 2, 'Python', 'Basic Python' where NOT EXISTS (SELECT * FROM courses WHERE course_id = 2);
INSERT INTO courses (course_id, course_name, description) SELECT 3, 'C++', 'Basic C++' where NOT EXISTS (SELECT * FROM courses WHERE course_id = 3);
INSERT INTO courses (course_id, course_name, description) SELECT 4, 'Android', 'Basic Android' where NOT EXISTS (SELECT * FROM courses WHERE course_id = 4);

-- Initial Data for books
INSERT INTO books (book_id, course_id, book_name, author_name, edition) SELECT 1, 1, 'basic java', 'M. A kalam', '6th' where NOT EXISTS (SELECT * FROM books WHERE book_id = 1);
INSERT INTO books (book_id, course_id, book_name, author_name, edition) SELECT 2, 1, 'Intruduction to java', 'Appress.com', '4th' where NOT EXISTS (SELECT * FROM books WHERE book_id = 2);
INSERT INTO books (book_id, course_id, book_name, author_name, edition) SELECT 3, 1, 'core java', 'James Gosling', '8th' where NOT EXISTS (SELECT * FROM books WHERE book_id = 3);

-- Initial Data for chapters
INSERT INTO chapters (ch_id, book_id, chapter_name) SELECT 1, 1, 'ch1' where NOT EXISTS (SELECT * FROM chapters WHERE ch_id = 1);
INSERT INTO chapters (ch_id, book_id, chapter_name) SELECT 2, 1, 'ch2' where NOT EXISTS (SELECT * FROM chapters WHERE ch_id = 2);
INSERT INTO chapters (ch_id, book_id, chapter_name) SELECT 3, 1, 'ch4' where NOT EXISTS (SELECT * FROM chapters WHERE ch_id = 3);
INSERT INTO chapters (ch_id, book_id, chapter_name) SELECT 4, 1, 'ch5' where NOT EXISTS (SELECT * FROM chapters WHERE ch_id = 4);
INSERT INTO chapters (ch_id, book_id, chapter_name) SELECT 5, 1, 'ch6' where NOT EXISTS (SELECT * FROM chapters WHERE ch_id = 5);

-- Initial Data for reference
INSERT INTO reference (ref_id, course_id, reference_header) SELECT 1, 1, 'Wikipedia' where NOT EXISTS (SELECT * FROM reference WHERE ref_id = 1);
INSERT INTO reference (ref_id, course_id, reference_header) SELECT 2, 1, 'Tutorials Point' where NOT EXISTS (SELECT * FROM reference WHERE ref_id = 2);
INSERT INTO reference (ref_id, course_id, reference_header) SELECT 3, 1, 'JavaTpoint' where NOT EXISTS (SELECT * FROM reference WHERE ref_id = 3);

-- Initial Data for questions_bank
INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 1, 1, 1, 1, 1, null, '2018-02-20', 'Java is a ........... language.', 'weakly typed', 'strogly typed', 'moderate typed', 'None of these', 2 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 1);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 2, 1, 1, 1, 1, null, '2018-02-20', 'How many primitive data types are there in Java?', '6', '7', '8', '9', 3 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 2);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 3, 1, 1, 1, 1, null, '2018-02-20', 'In Java byte, short, int and long all of these are', 'signed', 'unsigned', 'Both of the above', 'None of these', 1 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 3);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 4, 1, 1, 1, 1, null, '2018-02-20', 'Size of int in Java is', '16 bit', '32 bit', '64 bit', 'Depends on execution environment', 2 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 4);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 5, 1, 1, 1, 1, null, '2018-02-20', 'The smallest integer type is ......... and its size is ......... bits.', 'short, 8', 'byte, 8', 'short, 16', 'None of these', 2 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 5);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 6, 1, 1, 1, 2, null, '2018-02-20', 'In Java arrays are', 'objects', 'object references', 'primitive data type', 'None of the above', 1 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 6);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 7, 1, 1, 1, 2, null, '2018-02-20', 'Which one of the following is a valid statement?', 'char[] c = new char();', 'char[] c = new char[5];', 'char[] c = new char(4);', 'char[] c = new char[];', 2 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 7);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 8, 1, 1, 1, 2, null, '2018-02-20', 'When you pass an array to a method, the method receives ________ .', 'A copy of the array.', 'A copy of the first element.', 'The reference of the array.', 'The length of the array.', 3 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 8);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 9, 1, 1, 1, 2, null, '2018-02-20', 'Which will legally declare, construct, and initialize an array?', 'int [] myList = {};', 'int [] myList = (5, 8, 2);', 'int myList [] [] = {4,9,7,0};', 'int myList [] = {4, 3, 7};', 4 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 9);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 10, 1, 1, 1, 2, null, '2018-02-20', 'Which of the following for loops will be an infinite loop?', 'for(; ;)', 'for(i=0 ; i<1; i--)', 'for(i=0; ; i++)', 'All of the above', 4 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 10);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 11, 1, 1, 1, 3, null, '2018-02-20', 'Which of the following class definitions defines a legal abstract class?', 'class A { abstract void unfinished() { } }', 'class A { abstract void unfinished(); }', 'abstract class A { abstract void unfinished(); }', 'public class abstract A { abstract void unfinished(); }', 3 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 11);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 12, 1, 1, 1, 3, null, '2018-02-20', 'Which of the following declares an abstract method in an abstract Java class?', 'public abstract method();', 'public abstract void method();', 'public void abstract Method();', 'public void method() {}', 2 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 12);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 13, 1, 1, 1, 3, null, '2018-02-20', 'Suppose A is an abstract class, B is a concrete subclass of A, and both A and B have a default constructor. Which of the following is correct?', 'A a = new A();', ' A a = new B();', 'B b = new A();', 'BA b = new B();', 2 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 13);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 14, 1, 1, 1, 3, null, '2018-02-20', 'Which of the following statements regarding abstract classes are true?', 'An abstract class can be extended.', 'A subclass of a non-abstract superclass can be abstract.', 'An abstract class can be used as a data type', 'All of the above', 4 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 14);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 15, 1, 1, 1, 3, null, '2018-02-20', 'Which of the following is a correct interface?', 'interface A { void print() { } }', 'abstract interface A { print(); }', 'abstract interface A { abstract void print(); { }}', 'interface A { void print(); }', 4 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 15);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 16, 1, null, 1, null, 1, '2018-02-20', 'In Java, declaring a class abstract is useful', 'To prevent developers from further extending the class.', 'When default implementations of some methods are not desirable.', 'When it doesnt make sense to have objects of that class', 'When it makes sense to have objects of that class', 3 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 16);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 17, 1, null, 1, null, 1, '2018-02-20', 'Runnable is a _____ .', 'interface', 'class', 'vaiable', 'abstract class', 1 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 17);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 18, 1, null, 1, null, 1, '2018-02-20', 'The class at the top of exception class hierarchy is .................', 'Erithmatic Exception', 'Throwable', 'Object', 'Exception', 2 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 18);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 19, 1, null, 1, null, 1, '2018-02-20', 'In which of the following package Exception class exist?', 'java.util', 'java.file', 'java.io', 'java.lang', 4 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 19);

INSERT INTO questions_bank (qus_bank_id, teacher_id, book_id, course_id, ch_id, ref_id, question_created_date, question_title, option1, option2, option3, option4, ans) 
SELECT 20, 1, null, 1, null, 1, '2018-02-20', 'Exception generated in try block is caught in ........... block.', 'catch', 'throw', 'throws', 'finally', 1 where NOT EXISTS (SELECT * FROM questions_bank WHERE qus_bank_id = 20);


