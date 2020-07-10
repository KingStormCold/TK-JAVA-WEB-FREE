INSERT INTO tk_java_web_free.users
(user_name, createdby, createddate, modifiedby, modifieddate, address, email, full_name, image, online, pass_word, phone)
VALUES('admin', 'admin', '2020-06-17 16:45:01.337', 'admin', '2020-06-17 16:45:01.000', '123123123', 'admin@gmail.com', 'a', '123123123123', true, '$2a$10$ZloNY/N4gBqyFQNSZwSDmegu82VM6VvBZ32xgJPS/b.dGfrksRdyy', '123123123');

INSERT INTO tk_java_web_free.roles
(role_id, desciption)
VALUES('USER', 'This is role for User');
INSERT INTO tk_java_web_free.roles
(role_id, desciption)
VALUES('ADMIN', 'This is role for Admin');
INSERT INTO tk_java_web_free.roles
(role_id, desciption)
VALUES('ROLE_ALL', 'This is role for Insert Update Delete(Role)');
INSERT INTO tk_java_web_free.roles
(role_id, desciption)
VALUES('USER_ALL', 'This is role for Insert Update Delete(User)');

INSERT INTO tk_java_web_free.user_role
(role_id, user_name)
VALUES('ADMIN', 'admin');
INSERT INTO tk_java_web_free.user_role
(role_id, user_name)
VALUES('ROLE_ALL', 'admin');
INSERT INTO tk_java_web_free.user_role
(role_id, user_name)
VALUES('USER_ALL', 'admin');
