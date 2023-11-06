package pro.sky.hogwarts3_6.constants;

import pro.sky.hogwarts3_6.dto.StudentDtoIn;
import pro.sky.hogwarts3_6.dto.StudentDtoOut;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static pro.sky.hogwarts3_6.constants.FacultyTestConstants.*;


public class StudentTestConstants {

    public static final Long STUDENT_ID_1 = 1L;
    public static final Long STUDENT_ID_2 = 2L;
    public static final Long STUDENT_ID_3 = 3L;
    public static final Long INCORRECT_STUDENT_ID = 25L;
    public static final String STUDENT_NAME_1 = "StudentName1";
    public static final String STUDENT_NAME_2 = "StudentName2";
    public static final String STUDENT_NAME_3 = "StudentName3";
    public static final String STUDENT_NAME_EDIT = "EditedStudentName";
    public static final int STUDENT_AGE_1 = 12;
    public static final int STUDENT_AGE_2 = 11;
    public static final int STUDENT_AGE_EDIT = 15;
    public static final StudentDtoIn STUDENT_DTO_IN_1 = new StudentDtoIn(STUDENT_NAME_1, STUDENT_AGE_1, FACULTY_ID_1);
    public static final StudentDtoIn STUDENT_DTO_IN_2 = new StudentDtoIn(STUDENT_NAME_2, STUDENT_AGE_2, FACULTY_ID_2);
    public static final StudentDtoIn STUDENT_DTO_IN_3 = new StudentDtoIn(STUDENT_NAME_3, STUDENT_AGE_1, FACULTY_ID_1);
    public static final StudentDtoIn STUDENT_DTO_IN_3_EDIT = new StudentDtoIn(STUDENT_NAME_EDIT, STUDENT_AGE_EDIT, FACULTY_ID_4);
    public static final StudentDtoIn INCORRECT_STUDENT_DTO_IN = new StudentDtoIn(STUDENT_NAME_EDIT, STUDENT_AGE_EDIT, INCORRECT_FACULTY_ID);
    public static final StudentDtoOut STUDENT_DTO_OUT_1 = new StudentDtoOut(STUDENT_ID_1, STUDENT_NAME_1, STUDENT_AGE_1, FACULTY_DTO_OUT_1);
    public static final StudentDtoOut STUDENT_DTO_OUT_2 = new StudentDtoOut(STUDENT_ID_2, STUDENT_NAME_2, STUDENT_AGE_2, FACULTY_DTO_OUT_2);
    public static final StudentDtoOut STUDENT_DTO_OUT_3 = new StudentDtoOut(STUDENT_ID_3, STUDENT_NAME_3, STUDENT_AGE_1, FACULTY_DTO_OUT_1);
    public static final StudentDtoOut STUDENT_DTO_OUT_3_EDIT = new StudentDtoOut(STUDENT_ID_3, STUDENT_NAME_EDIT, STUDENT_AGE_EDIT, FACULTY_DTO_OUT_4);
    public static final Collection<StudentDtoOut> STUDENTS_FROM_FACULTY_1 = new ArrayList<>(List.of(
            STUDENT_DTO_OUT_1,
            STUDENT_DTO_OUT_3
    ));
    public static final Collection<StudentDtoOut> ALL_STUDENTS = new ArrayList<>(List.of(
            STUDENT_DTO_OUT_1,
            STUDENT_DTO_OUT_2,
            STUDENT_DTO_OUT_3
    ));
    public static final Collection<StudentDtoOut> ALL_STUDENTS_AFTER_EDIT = new ArrayList<>(List.of(
            STUDENT_DTO_OUT_1,
            STUDENT_DTO_OUT_2,
            STUDENT_DTO_OUT_3_EDIT
    ));
    public static final Collection<StudentDtoOut> ALL_STUDENTS_AFTER_REMOVE = new ArrayList<>(List.of(
            STUDENT_DTO_OUT_1,
            STUDENT_DTO_OUT_3_EDIT
    ));
    public static final Collection<StudentDtoOut> STUDENTS_WITH_AGE_1 = new ArrayList<>(List.of(
            STUDENT_DTO_OUT_1,
            STUDENT_DTO_OUT_3
    ));
}
