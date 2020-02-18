INSERT INTO quizmanager.users(user_id,username,password,active)
VALUES (1,'quizeditor','$2a$13$T9YzCYpVOWe083hQA95fI.9FO8z3BP1wNf1yw4ACUyX.niwoY4vgq', true);  
-- password=1234

INSERT INTO quizmanager.users(user_id,username,password,active)
VALUES (2,'quizviewer','$2a$13$T9YzCYpVOWe083hQA95fI.9FO8z3BP1wNf1yw4ACUyX.niwoY4vgq', true);  
-- password=1234

INSERT INTO quizmanager.users(user_id,username,password,active)
VALUES (3,'quizuser','$2a$13$T9YzCYpVOWe083hQA95fI.9FO8z3BP1wNf1yw4ACUyX.niwoY4vgq', true);  
-- password=1234


INSERT INTO quizmanager.permissions(perm_id,perm_name,perm_desc)
VALUES (1, "EDIT", "Users of this permission level can create, edit or remove quizzes");

INSERT INTO quizmanager.permissions(perm_id,perm_name,perm_desc)
VALUES (2, "VIEW", "Users of this permission level can view questions and answers");

INSERT INTO quizmanager.permissions(perm_id,perm_name,perm_desc)
VALUES (3, "RESTRICTED", "Users of this permission level can only view questions");


INSERT INTO quizmanager.user_permissions(user_id,perm_id) VALUES (1, 1);
INSERT INTO quizmanager.user_permissions(user_id,perm_id) VALUES (2, 2);
INSERT INTO quizmanager.user_permissions(user_id,perm_id) VALUES (3, 3);



