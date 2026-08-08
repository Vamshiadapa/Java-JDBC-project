create database exam_db;
use exam_db;

create table students (
student_id int auto_increment primary key, name varchar(100) not null, email varchar(120) unique not null, password varchar(100) not null, roll_number varchar(30) unique not null
);

create table exams (
exam_id int auto_increment primary key,  title varchar(150) not null, subject varchar(80), total_marks int not null, duration_minutes int not null, created_on timestamp default current_timestamp
);

create table questions (
question_id int auto_increment primary key, exam_id int not null, question_text varchar(500) not null,  option_a varchar(150) not null, option_b varchar(150) not null, option_c varchar(150) not null,
option_d varchar(150) not null, correct_option char(1) not null,   -- 'a', 'b', 'c' or 'd'
marks int not null default 1, constraint fk_question_exam foreign key (exam_id) references exams(exam_id)
on delete cascade
);

create table results (
result_id int auto_increment primary key, student_id int not null, exam_id int not null, score int not null, submitted_on timestamp default current_timestamp,
constraint fk_result_student foreign key (student_id) references students(student_id)
 on delete cascade,
 constraint fk_result_exam foreign key (exam_id)
references exams(exam_id)
on delete cascade
);
insert into students (name, email, password, roll_number) values
('vamshi', 'rahul.mehta@example.com', 'pass123', 'R101'),
('krishna', 'priya.nair@example.com', 'pass456', 'R102');
 
insert into exams (title, subject, total_marks, duration_minutes) values
('Java Basics Test', 'Java', 20, 30),
('Database Fundamentals', 'DBMS', 10, 20);
 
insert into questions (exam_id, question_text, option_a, option_b, option_c, option_d, correct_option, marks) values
(1, 'Which keyword is used to inherit a class in Java?', 'implements', 'extends', 'inherits', 'super', 'b', 5),
(1, 'Which of these is not a primitive type?', 'int', 'boolean', 'String', 'char', 'c', 5),
(2, 'Which sql clause filters rows before grouping?', 'having', 'where', 'group by', 'order by', 'b', 5),
(2, 'Which key uniquely identifies a row in a table?', 'foreign key', 'candidate key', 'primary key', 'index', 'c', 5);