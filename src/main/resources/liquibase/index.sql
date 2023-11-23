--liquibase formatted sql

--changeset mPankov: 1

CREATE INDEX student_name ON students(name);
CREATE INDEX faculty_name_color ON faculties(name, color);
