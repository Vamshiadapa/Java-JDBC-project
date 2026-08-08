create database studentmanagementdb;

use studentmanagementdb;

create table students (
    student_id int primary key auto_increment,
    student_name varchar(100) not null,
    gender varchar(10),
    age int,
    phone varchar(15),
    email varchar(100),
    department varchar(50)
);

create table courses (
    course_id int primary key auto_increment,
    course_name varchar(100),
    duration varchar(30),
    trainer_name varchar(100),
    fees decimal(10,2)
);

create table enrollments (
    enrollment_id int primary key auto_increment,
    student_id int,
    course_id int,
    enrollment_date date,
    foreign key (student_id) references students(student_id),
    foreign key (course_id) references courses(course_id)
);

create table attendance (
    attendance_id int primary key auto_increment,
    student_id int,
    course_id int,
    attendance_date date,
    status varchar(10),
    foreign key (student_id) references students(student_id),
    foreign key (course_id) references courses(course_id)
);

create table marks (
    mark_id int primary key auto_increment,
    student_id int,
    course_id int,
    marks int,
    foreign key (student_id) references students(student_id),
    foreign key (course_id) references courses(course_id)
);

insert into students(student_name, gender, age, phone, email, department)
values
('vamshi', 'male', 21, '7989106423', 'vamshi@gmail.com', 'cse'),
('raji', 'female', 22, '9876543211', 'priya@gmail.com', 'ece'),
('raj', 'male', 20, '9876543212', 'kiran@gmail.com', 'eee'),
('sneha', 'female', 21, '9876543213', 'sneha@gmail.com', 'it'),
('arjun', 'male', 23, '9876543214', 'arjun@gmail.com', 'cse');

insert into courses(course_name, duration, trainer_name, fees)
values
('core java', '3 months', 'janani', 12000),
('sql', '2 months', 'rajesh', 8000),
('spring boot', '2 months', 'anil', 15000),
('hibernate', '1 month', 'ravi', 9000);

insert into enrollments(student_id, course_id, enrollment_date)
values
(1,1,'2026-08-01'),
(2,2,'2026-08-01'),
(3,1,'2026-08-02'),
(4,3,'2026-08-03'),
(5,4,'2026-08-03');

insert into attendance(student_id, course_id, attendance_date, status)
values
(1,1,'2026-08-05','present'),
(2,2,'2026-08-05','absent'),
(3,1,'2026-08-05','present'),
(4,3,'2026-08-05','present'),
(5,4,'2026-08-05','absent');

insert into marks(student_id, course_id, marks)
values
(1,1,92),
(2,2,74),
(3,1,83),
(4,3,95),
(5,4,68);

select s.student_name,
       c.course_name
from students s
join enrollments e
on s.student_id = e.student_id
join courses c
on e.course_id = c.course_id;

select s.student_name,
       c.course_name,
       a.attendance_date,
       a.status
from attendance a
join students s
on a.student_id = s.student_id
join courses c
on a.course_id = c.course_id;

select s.student_name,
       c.course_name,
       m.marks
from marks m
join students s
on m.student_id = s.student_id
join courses c
on m.course_id = c.course_id;

select s.student_name,
       s.department,
       c.course_name,
       m.marks
from students s
join enrollments e
on s.student_id = e.student_id
join courses c
on e.course_id = c.course_id
join marks m
on s.student_id = m.student_id
and c.course_id = m.course_id;

select
    student_id,
    marks,
    case
        when marks >= 90 then 'a+'
        when marks >= 80 then 'a'
        when marks >= 70 then 'b'
        when marks >= 60 then 'c'
        else 'fail'
    end as grade
from marks;