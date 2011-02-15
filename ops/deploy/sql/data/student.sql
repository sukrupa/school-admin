INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,father) VALUES ('5 Std','Abhishek','SK20090021','Chamundi Nagar','Mrs.Parvathamma','Male','SC','Christian','2001-11-19','Mr.Venkatesh');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090021' AND (talent.description = 'Reading');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090021' AND (talent.description = 'Writing');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,religion,date_of_birth,father) VALUES ('5 Std','Archana','SK20020005','Bhuvaneshwari Slum','Mrs. Pattu','Female','Hindu','2001-05-03','Mr. Manikyam');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20020005' AND (talent.description = 'Dance');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,father) VALUES ('5 Std','Balaji','SK20030001','Bhuvaneshwari Slum','Mrs. Chandramma','Male','Gownder','Hindu','2000-07-17','Mr. Kaliyappa');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20030001' AND (talent.description = 'Sports');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,father) VALUES ('5 Std','Darshan','SK20080033','Chamundi Nagar','Mrs. Prema','Male','SC','Hindu','2001-07-10','Mr. Jaganath');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20080033' AND (talent.description = 'Sports');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,father) VALUES ('5 Std','Divya','SK20090022','Chamundi Nagar','Mrs. Lakshmi','Female','Kumbara Shetty','Hindu','2001-08-08','Mr. Shankar');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090022' AND (talent.description = 'Dance');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090022' AND (talent.description = 'Art');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,religion,date_of_birth,father) VALUES ('5 Std','Esther','SK20090023','Bhuvaneshwari Slum','Mrs.Thabithal','Female','Christian','2001-01-16','Mr. Simon');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090023' AND (talent.description = 'Drawing');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,sub_caste,father) VALUES ('5 Std','Gowtham','SK20030002','Chamundi Nagar','Mrs. Yashodha','Male','SC','Hindu','2001-11-28','AK','Mr. Krishna Kumar');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20030002' AND (talent.description = 'Sports');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,sub_caste,father) VALUES ('5 Std','Jagadeesh Singh','SK20090024','Nageanahalli','Mrs. Usha Bai','Male','Rajputs','Hindu','2002-01-14','Singh','Mr. Krishna Singh');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090024' AND (talent.description = 'Art');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,father) VALUES ('5 Std','John Peter','SK20070002','Chamundi Nagar','Mrs. Jacintha Mary','Male','Roman Catholic','Christian','2002-10-25','Mr. Anthony Raj.B');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20070002' AND (talent.description = 'Drawing');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20070002' AND (talent.description = 'Sports');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,father) VALUES ('5 Std','Komathi','SK20070003','Chamundi Nagar','Mrs. Velthangam','Female','Nadar','Hindu','2002-05-13','Mr. Vellayudhan');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20070003' AND (talent.description = 'Dance');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,sub_caste,father) VALUES ('5 Std','Likitha','SK20090025','Chamundi Nagar','Mrs. Shashikala.D','Female','SC','Hindu','2001-09-13','AK','Mr. Ramesh');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090025' AND (talent.description = 'Dance');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090025' AND (talent.description = 'Sports');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,sub_caste,father) VALUES ('5 Std','Manohari','SK20090026','Chamundi Nagar','Mrs.Madhumalthi','Female','Ganiga','Hindu','2000-12-27','Shiva Jyothipana','Mr.Ramesh');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090026' AND (talent.description = 'Music');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090026' AND (talent.description = 'Krate');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090026' AND (talent.description = 'Art');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,father) VALUES ('5 Std','Mark Harizon.S','SK20090027','Chamundi Nagar','Mrs. Sujatha','Male','Roman Catholic','Christian','2000-05-16','Mr. Salomon');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090027' AND (talent.description = 'Art');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,father) VALUES ('5 Std','Medalakshmi','SK20090028','Chamundi Nagar','Mrs.Anitha','Female','SC','Hindu','2001-07-22','Mr. Shanmugham');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090028' AND (talent.description = 'Art');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,sub_caste,father) VALUES ('5 Std','Monica.M','SK20090029','Chamundi Nagar','Mrs. Dhanalakshmi','Female','SC','Hindu','2000-04-15','AK','Mr. Murugan');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090029' AND (talent.description = 'Music');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090029' AND (talent.description = 'Dance');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,sub_caste,father) VALUES ('5 Std','Monisha.M','SK20090030','Chamundi Nagar','Mrs. Dhanalakshmi','Female','SC','Hindu','2000-04-15','AK','Mr. Murugan');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090030' AND (talent.description = 'Sports');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090030' AND (talent.description = 'Dance');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,sub_caste,father) VALUES ('5 Std','Monisha.V','SK20090031','Chamundi Nagar','Mrs.Shanthi','Female','SC','Hindu','2000-06-13','AK','Mr.Veerabadra');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090031' AND (talent.description = 'Dance');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090031' AND (talent.description = 'Music');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,father) VALUES ('5 Std','Nandini','SK20030003','Bhuvaneshwari Slum','Mrs. Sakuntala','Female','Gownder','Hindu','2001-01-26','Mr. Irsappa');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20030003' AND (talent.description = 'Music');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20030003' AND (talent.description = 'Dance');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,father) VALUES ('5 Std','Nidhishreee','SK20100002','Chamundi Nagar','Mrs. Shardamma','Female','ST','Hindu','1999-03-09','Mr. Muniraj');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20100002' AND (talent.description = 'Music');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20100002' AND (talent.description = 'Dance');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,father) VALUES ('5 Std','Parthiban','SK20020006','Bhuvaneshwari Slum','Mrs.Govindamma','Male','SC','Hindu','1999-02-02','Mr.Thangadurai');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,father) VALUES ('5 Std','Sangeetha','SK20020007','Bhuvaneshwari Slum','Mrs. Seetha.G','Female','Gowda','Hindu','2001-03-13','Mr.Govindaswamy');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20020007' AND (talent.description = 'Sports');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,religion,date_of_birth,father) VALUES ('5 Std','Sharan','SK20020008','Chamundi Nagar','Mrs. Shanthi','Male','Hindu','2000-10-26','Mr. Kuppan');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20020008' AND (talent.description = 'Sports');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,father) VALUES ('5 Std','Sharanya','SK20090032','Chamundi Nagar','Mrs.Shanthi','Female','Roman Catholic','Christian','2000-09-29','Mr.Subramani');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090032' AND (talent.description = 'Sports');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090032' AND (talent.description = 'Dance');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,religion,date_of_birth,father) VALUES ('5 Std','Sowmya','SK20090033','Chamundi Nagar','Mrs. Manjula.N','Female','Hindu','2000-08-13','Mr. Nagaraj.G');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090033' AND (talent.description = 'Dance');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090033' AND (talent.description = 'Music');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,religion,date_of_birth,father) VALUES ('5 Std','Stella Mary','SK20010001','Residentail','Mrs. Maramma','Female','Christian','2002-11-18','Mr. Murugesh');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20010001' AND (talent.description = 'Music');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20010001' AND (talent.description = 'Dance');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,religion,date_of_birth,father) VALUES ('5 Std','Sudershan','SK20010002','Residentail','Mrs. Bhagyamma','Male','Hindu','2001-03-30','Mr.Gopal(L)');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20010002' AND (talent.description = 'Music');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20010002' AND (talent.description = 'Art');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,father) VALUES ('5 Std','Sunil.K','SK20090034','Chamundi Nagar','Mrs.Lalitha','Male','Nayak','Hindu','2001-05-10','Mr.Kumar');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20090034' AND (talent.description = 'Sports');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,religion,date_of_birth,father) VALUES ('5 Std','Vinod.M','SK20070004','Bhuvaneshwari Slum','Mrs.Madamma','Male','Hindu','2000-06-27','Mr. Madhaiyaa');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20070004' AND (talent.description = 'Sports');
