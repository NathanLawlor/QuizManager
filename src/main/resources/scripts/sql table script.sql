CREATE  TABLE quizmanager.users (
  user_id int(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(70) NOT NULL ,
  active TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (user_id));
  
CREATE TABLE quizmanager.permissions (
  perm_id int(11) NOT NULL AUTO_INCREMENT,
  perm_name varchar(10) NOT NULL,
  perm_desc varchar(255) NOT NULL,
  PRIMARY KEY (perm_id));
  
CREATE TABLE quizmanager.user_permissions (
  user_id int(11) NOT NULL,
  perm_id int(11) NOT NULL,
  PRIMARY KEY (user_id,perm_id),
  KEY FK_user_perm (perm_id),
  CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES quizmanager.users(user_id),
  CONSTRAINT FK_user_perm FOREIGN KEY (perm_id) REFERENCES quizmanager.permissions(perm_id));
  
  
  
CREATE TABLE quizmanager.quizzes (
  quiz_id int(11) NOT NULL AUTO_INCREMENT,
  quiz_title varchar(100) NOT NULL,
  quiz_category varchar(100) NOT NULL,
  PRIMARY KEY (quiz_id));
  
CREATE TABLE quizmanager.questions (
  question_id int(11) NOT NULL AUTO_INCREMENT,
  quiz_id int(11) NOT NULL,
  question varchar(255) NOT NULL,
  PRIMARY KEY (question_id),
  FOREIGN KEY (quiz_id) REFERENCES quizmanager.quizzes(quiz_id));
  
CREATE TABLE quizmanager.answers (
  answer_id int(11) NOT NULL AUTO_INCREMENT,
  question_id int(11) NOT NULL,
  answer varchar(100) NOT NULL,
  correct TINYINT NOT NULL,
  PRIMARY KEY (answer_id),
  FOREIGN KEY (question_id) REFERENCES quizmanager.questions(question_id));



