INSERT INTO users(user_id,first_name,last_name,email,phone,password,photo,gender,status,current_address,permanent_address,security_question,security_ans)
SELECT 1 ,'Admin', 'admin', 'admin@gmail.com', '01919223344','1234', 'photo','male', 'Active', 'Uttara', 'Uttara dhaka', 'what is your role?', 'admin' where NOT EXISTS (SELECT 1 FROM users WHERE user_id = 1) ; 

INSERT INTO roles(role_id,role_name) SELECT 1 ,'Admin' where NOT EXISTS (SELECT 1 FROM roles WHERE role_id = 1);

INSERT INTO users_roles(user_role_id, role_id, user_id) SELECT 1, 1, 1 where NOT EXISTS (SELECT * FROM users_roles WHERE user_role_id = 1);

