insert into account (name,role,email,password) values ('Ma Ma','Teacher','mama@gmail.com','ma');
insert into account (name,role,email,password) values ('Zaw Zaw','Teacher','zaw@gmail.com','zaw');
insert into account (name,role,email,password) values ('Ko Ko','Teacher','ko@gmail.com','ko');
insert into account (name,role,email,password) values ('Leo','Student','leo@gmail.com','leo');
insert into account (name,role,email,password) values ('Suzy','Student','suzy@gmail.com','suzy');
insert into account (name,role,email,password) values ('Jennie','Student','jennie@gmail.com','jennie');

insert into teacher values (1,'09876543451','2022-12-01');
insert into teacher values (2,'09789754310','2022-10-26');
insert into teacher values (3,'0998764362','2022-03-19');

insert into classes (teacher_id,start_date,months,description) values (1,'2022-12-02',3,'Demo Class');
insert into classes (teacher_id,start_date,months,description) values (1,'2022-12-07',3,'Special Class');
insert into classes (teacher_id,start_date,months,description) values (1,'2022-12-10',3,'Free Class');
insert into classes (teacher_id,start_date,months,description) values (2,'2022-11-29',3,'Read Class');
insert into classes (teacher_id,start_date,months,description) values (2,'2022-11-24',3,'Basic Class');

insert into student values (4,'09790613318','Elementary');
insert into student values (5,'09790613322','inter');
insert into student values (6,'09790613333','Advanced');

insert into registration values (1,4,'2022-12-14');
insert into registration values (2,4,'2022-12-14');
insert into registration values (2,5,'2022-12-11');
insert into registration values (3,6,'2022-12-20');