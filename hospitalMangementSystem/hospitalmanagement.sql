create database HospitalManagementDB;
use HospitalManagementDB;

create table patients(
	patient_id int primary key auto_increment, patient_name varchar(100) not null, age int,gender varchar(10), phone varchar(15), address varchar(200)
);

create table doctors(
    doctor_id int primary key auto_increment, doctor_name varchar(100) not null, specialization varchar(100), phone varchar(15), experience int
);

create table appointments(
    appointment_id int primary key auto_increment, patient_id int, doctor_id int, appointment_date date, appointment_time time, reason varchar(200),
	foreign key (patient_id)
        references patients(patient_id),
    foreign key (doctor_id)
        references doctors(doctor_id)
);

create table prescriptions(
    prescription_id int primary key auto_increment,appointment_id int,medicine_name varchar(100), dosage varchar(50), duration varchar(50), instructions varchar(200),
    foreign key (appointment_id)
        references appointments(appointment_id)
);

insert into doctors(doctor_name, specialization, phone, experience)values
('rajesh kumar', 'cardiologist', '9876543210', 10),('suresh reddy', 'neurologist', '9876543211', 8),('priya sharma', 'dermatologist', '9876543212', 6),('anil kumar', 'orthopedic', '9876543213', 12),
('swathi rao', 'pediatrician', '9876543214', 7);