CREATE TABLE car
(
    id      SERIAL PRIMARY KEY,
    company TEXT,
    brand   TEXT
);

CREATE TABLE person
(
    id            SERIAL PRIMARY KEY,
    name          TEXT,
    age           INT,
    drive_license BOOLEAN,
    car_id        INT REFERENCES car (id)
);

--

SELECT students.name, students.age, faculties.name
FROM students
INNER JOIN faculties ON students.faculty_id = faculties.id;

SELECT students.id, students.name, avatars.id
FROM avatars
INNER JOIN students ON avatars.student_id = students.id;