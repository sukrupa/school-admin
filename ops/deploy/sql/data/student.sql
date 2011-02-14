INSERT INTO student (student_class,name,student_id,community_location,mother,gender,religion,date_of_birth,father) VALUES ('10 Std','Daniel Schufeldt','SK20010008','Buvaneshwari Slum','Mrs. Vijaya','Male','Hindu','1986-05-22','Mr. Manimuthu');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20010008' AND (talent.description = 'Drama');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20010008' AND (talent.description = 'Dancing');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20010008' AND (talent.description = 'Art');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,religion,date_of_birth,sub_caste,father) VALUES ('10 Std','Minno Dang','SK20030009','Chmandi Nagar','Mrs.Selvi','Female','Christian','1990-01-17','Devanger','Mr. Murugeshan');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20030009' AND (talent.description = 'Art');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20030009' AND (talent.description = 'Craft');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,religion,date_of_birth,father) VALUES ('10 Std','Meghnaisthe Coolestgirlaround','SK20020014','Buvaneshwari Slum','Mrs. Shivamma','Female','Muslim','1982-11-26','Mr. Veeraswamy');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20020014' AND (talent.description = 'Drawing');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20020014' AND (talent.description = 'Singing');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,father) VALUES ('7 Std','Kartik Yo','SK20030014','Chmandi Nagar','Mrs.Shanthi','Male','Pentacost','Christian','1997-03-23','Mr. Subramani');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20030014' AND (talent.description = 'Drama');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20030014' AND (talent.description = 'Drawing');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20030014' AND (talent.description = 'Quiz');
INSERT INTO student (student_class,name,student_id,community_location,mother,gender,caste,religion,date_of_birth,father) VALUES ('7 Std','Kartik Yo','SK20030013','Chmandi Nagar','Mrs.Shanthi','Female','Pentacost','Christian','1997-03-23','Mr. Subramani');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20030013' AND (talent.description = 'Drama');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20030013' AND (talent.description = 'Drawing');
INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = 'SK20030013' AND (talent.description = 'Quiz');
