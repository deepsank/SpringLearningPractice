DELETE FROM PATIENT WHERE NAME IN ('Peter Parker','Bruce Wayne','Pandit Gangadhar Shastri','Tony Shark');
INSERT INTO patient (name,gender, birth_date, email, blood_group)
values
        ('Peter Parker','MALE','1990-05-15','spiderman@gmail.com','O_POSITIVE'),
        ('Bruce Wayne','MALE','1991-11-05','batman@gmail.com','A_POSITIVE'),
        ('Pandit Gangadhar Shastri','MALE','1980-06-19','shaktiman@gmail.com','B_NEGATIVE'),
        ('Tony Shark','MALE','1985-01-24','ironman@gmail.com','AB_NEGATIVE');

INSERT INTO DOCTOR(name, specialization, email)
values
('Dr. Rakesh Mehta', 'Cardiology', 'rakesh.mehta@gmail.com'),
('Dr. Manoj Maheshwari', 'Dermatology', 'manoj.maheshwari@gmail.com'),
('Dr. Suvidha Maheshwari', 'Orthopedics', 'suvidha.maheshwari@gmail.com');

INSERT INTO appointment(appointment_time, reason, doctor_id, patient_id)
values
('2025-07-01 10:30:00', 'Genaral Checkup', 1 , 2),
('2025-07-02 10:30:00', 'Skin Rash', 2 , 2),
('2025-07-03 10:30:00', 'Knee Pain', 3 , 3),
('2025-07-04 10:30:00', 'Follow-up Visit', 1 , 1),
('2025-07-05 10:30:00', 'Consultation', 1 , 4),
('2025-07-06 10:30:00', 'Allergy Treatment', 2 , 3);