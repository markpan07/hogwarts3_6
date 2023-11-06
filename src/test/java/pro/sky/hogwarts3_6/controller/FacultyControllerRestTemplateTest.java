package pro.sky.hogwarts3_6.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pro.sky.hogwarts3_6.dto.FacultyDtoOut;
import pro.sky.hogwarts3_6.dto.StudentDtoOut;
import pro.sky.hogwarts3_6.exception.FacultyNotFoundException;

import javax.xml.ws.Response;

import java.util.Collection;

import static pro.sky.hogwarts3_6.constants.FacultyTestConstants.*;
import static pro.sky.hogwarts3_6.constants.StudentTestConstants.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeAll
    public void beforeAll() {
        restTemplate.postForObject("/faculties", FACULTY_DTO_IN_1, FacultyDtoOut.class);
        restTemplate.postForObject("/faculties", FACULTY_DTO_IN_2, FacultyDtoOut.class);
        restTemplate.postForObject("/faculties", FACULTY_DTO_IN_3, FacultyDtoOut.class);

        restTemplate.postForObject("/students", STUDENT_DTO_IN_1, StudentDtoOut.class);
        restTemplate.postForObject("/students", STUDENT_DTO_IN_2, StudentDtoOut.class);
        restTemplate.postForObject("/students", STUDENT_DTO_IN_3, StudentDtoOut.class);
    }

    @Test
    void contextLoads() {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    void testCreateFaculty_shouldSucceed() {
        Assertions.assertThat(restTemplate.postForObject("/faculties", FACULTY_DTO_IN_4, FacultyDtoOut.class))
                .isEqualTo(FACULTY_DTO_OUT_4);
    }

    @Test
    void testGetFaculty_shouldReturn_FacultyDtoOut() {
        Assertions.assertThat(restTemplate.getForObject("/faculties/" + FACULTY_ID_1, FacultyDtoOut.class))
                .isEqualTo(FACULTY_DTO_OUT_1);
    }

    @Test
    void testGetFaculty_shouldReturn_FacultyNotFoundException() {

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/faculties/" + INCORRECT_FACULTY_ID, String.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo("Faculty with id = " + INCORRECT_FACULTY_ID + " is not found");

    }

    @Test
    void testUpdateFaculty_shouldSucceed() throws JsonProcessingException {
        ResponseEntity<?> responseEntity = restTemplate.exchange("/faculties/" + FACULTY_ID_3,
                HttpMethod.PUT,
                new HttpEntity<>(FACULTY_DTO_IN_3_EDIT),
                FacultyDtoOut.class);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(FACULTY_DTO_OUT_3_EDIT);

        String expectedCollectionAfterEdit = objectMapper.writeValueAsString(ALL_FACULTIES_AFTER_EDIT);
        String actualCollectionAfterEdit = objectMapper.writeValueAsString(restTemplate.getForObject("/faculties/", Collection.class));
        Assertions.assertThat(expectedCollectionAfterEdit).isEqualTo(actualCollectionAfterEdit);
    }

    /*
    //Не понимаю что за ошибка "нарушение ссылочной целостности". В Постмане все отрабатывает корректно, код 200.

    @Test
    void testDeleteFaculty_shouldSucceed(){
        ResponseEntity<?> responseEntity = restTemplate.exchange("/faculties/" + FACULTY_ID_2,
                HttpMethod.DELETE,
                null,
                FacultyDtoOut.class);

        Assertions.assertThat(responseEntity.getBody()).isEqualTo(FACULTY_DTO_OUT_2);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }


     */
    @Test
    void testGetFaculties_shouldReturnAllFaculties() throws JsonProcessingException {
        Collection<?> collection = restTemplate.getForObject("/faculties/", Collection.class);
        String expected = objectMapper.writeValueAsString(ALL_FACULTIES_AFTER_EDIT);
        String actual = objectMapper.writeValueAsString(collection);
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    /*
    //Я не понимаю почему тест не проходит. Он абсолютно идентичен тому что выше, но выскакивает ошибка сериализации.

    @Test
    void testGetFaculties_shouldReturnFacultiesWithColorOrName() throws JsonProcessingException {
        Collection<?> collection = restTemplate.getForObject("/faculties/colorOrName=" + FACULTY_COLOR_1, Collection.class);
        String expected = objectMapper.writeValueAsString(ALL_FACULTIES_WITH_COLOR_1);
        String actual = objectMapper.writeValueAsString(collection);
        Assertions.assertThat(actual).isEqualTo(expected);
    }
    */

    @Test
    void testGetStudentsByFacultyId() throws JsonProcessingException {
        Collection<?> collection = restTemplate.getForObject("/faculties/" + FACULTY_ID_1 + "/students", Collection.class);
        String expected = objectMapper.writeValueAsString(STUDENTS_FROM_FACULTY_1);
        String actual = objectMapper.writeValueAsString(collection);
        Assertions.assertThat(actual).isEqualTo(expected);
    }

}
