ALTER TABLE students
    ADD CONSTRAINT age CHECK ( age > 16 );

ALTER TABLE students
    ALTER COLUMN name SET NOT NULL;

ALTER TABLE students
    ADD CONSTRAINT name_unique unique (name);

ALTER TABLE faculties
    ADD CONSTRAINT name_color_unique unique (name, color);

ALTER TABLE students
    ALTER COLUMN age SET DEFAULT 20;