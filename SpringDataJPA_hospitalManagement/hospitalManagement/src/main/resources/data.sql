DELETE FROM PATIENT WHERE NAME IN ('Peter Parker','Bruce Wayne','Pandit Gangadhar Shastri','Tony Shark');
INSERT INTO patient (name,gender, birth_date, email, blood_group)
values
        ('Peter Parker','MALE','1990-05-15','spiderman@gmail.com','O_POSITIVE'),
        ('Bruce Wayne','MALE','1991-11-05','batman@gmail.com','A_POSITIVE'),
        ('Pandit Gangadhar Shastri','MALE','1980-06-19','shaktiman@gmail.com','B_NEGATIVE'),
        ('Tony Shark','MALE','1985-01-24','ironman@gmail.com','AB_NEGATIVE');