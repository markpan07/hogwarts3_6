package pro.sky.hogwarts3_6.constants;

import pro.sky.hogwarts3_6.dto.FacultyDtoIn;
import pro.sky.hogwarts3_6.dto.FacultyDtoOut;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FacultyTestConstants {

    public static final Long FACULTY_ID_1 = 1L;
    public static final Long FACULTY_ID_2 = 2L;
    public static final Long FACULTY_ID_3 = 3L;
    public static final Long FACULTY_ID_4 = 4L;
    public static final Long INCORRECT_FACULTY_ID = 4155L;
    public static final String FACULTY_NAME_1 = "FacultyName1";
    public static final String FACULTY_NAME_2 = "FacultyName2";
    public static final String FACULTY_NAME_3 = "FacultyName3";
    public static final String FACULTY_NAME_4 = "FacultyName4";
    public static final String FACULTY_NAME_EDIT = "EditedFacultyName";
    public static final String FACULTY_COLOR_1 = "Color1";
    public static final String FACULTY_COLOR_2 = "Color2";
    public static final String FACULTY_COLOR_4 = "Color4";
    public static final String FACULTY_COLOR_EDIT = "EditedColor";

    public static final FacultyDtoIn FACULTY_DTO_IN_1 = new FacultyDtoIn(FACULTY_NAME_1, FACULTY_COLOR_1);
    public static final FacultyDtoIn FACULTY_DTO_IN_2 = new FacultyDtoIn(FACULTY_NAME_2, FACULTY_COLOR_2);
    public static final FacultyDtoIn FACULTY_DTO_IN_3 = new FacultyDtoIn(FACULTY_NAME_3, FACULTY_COLOR_1);
    public static final FacultyDtoIn FACULTY_DTO_IN_4 = new FacultyDtoIn(FACULTY_NAME_4, FACULTY_COLOR_4);
    public static final FacultyDtoIn FACULTY_DTO_IN_3_EDIT = new FacultyDtoIn(FACULTY_NAME_EDIT, FACULTY_COLOR_EDIT);

    public static final FacultyDtoOut FACULTY_DTO_OUT_1 = new FacultyDtoOut(FACULTY_ID_1, FACULTY_NAME_1, FACULTY_COLOR_1);
    public static final FacultyDtoOut FACULTY_DTO_OUT_2 = new FacultyDtoOut(FACULTY_ID_2, FACULTY_NAME_2, FACULTY_COLOR_2);
    public static final FacultyDtoOut FACULTY_DTO_OUT_3 = new FacultyDtoOut(FACULTY_ID_3, FACULTY_NAME_3, FACULTY_COLOR_1);
    public static final FacultyDtoOut FACULTY_DTO_OUT_4 = new FacultyDtoOut(FACULTY_ID_4, FACULTY_NAME_4, FACULTY_COLOR_4);
    public static final FacultyDtoOut FACULTY_DTO_OUT_3_EDIT = new FacultyDtoOut(FACULTY_ID_3, FACULTY_NAME_EDIT, FACULTY_COLOR_EDIT);

    public static final Collection<FacultyDtoOut> ALL_FACULTIES = new ArrayList<>(List.of(
            FACULTY_DTO_OUT_1,
            FACULTY_DTO_OUT_2,
            FACULTY_DTO_OUT_3
            //FACULTY_DTO_OUT_4
    ));
    public static final Collection<FacultyDtoOut> ALL_FACULTIES_WITH_COLOR_1 = new ArrayList<>(List.of(
            FACULTY_DTO_OUT_1,
            FACULTY_DTO_OUT_3
    ));
    public static final Collection<FacultyDtoOut> ALL_FACULTIES_AFTER_EDIT = new ArrayList<>(List.of(
            FACULTY_DTO_OUT_1,
            FACULTY_DTO_OUT_2,
            //FACULTY_DTO_OUT_4,
            FACULTY_DTO_OUT_3_EDIT
    ));
    public static final Collection<FacultyDtoOut> ALL_FACULTIES_AFTER_REMOVE = new ArrayList<>(List.of(
            FACULTY_DTO_OUT_1,
            //FACULTY_DTO_OUT_4,
            FACULTY_DTO_OUT_3_EDIT
    ));

}
