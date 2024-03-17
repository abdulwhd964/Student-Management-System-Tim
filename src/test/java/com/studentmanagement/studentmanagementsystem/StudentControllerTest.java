/**
 * Author: Abdul Wahid
 * Date:24/02/2024
 * Time:4:24 PM
 */
package com.studentmanagement.studentmanagementsystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.studentmanagement.entity.Student;
import com.studentmanagement.presentation.Response;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@FieldDefaults(level = AccessLevel.PRIVATE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * test all students api
     *
     * @throws Exception
     */
    @Test
    public void testGetAllStudents() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/students").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * test get student by id
     *
     * @throws Exception
     */
    @Test
    public void testGetStudentById() throws Exception {
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.get("/students/1").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());

        Student student = convertResultActionsToStudent(resultActions);

        assertAll(() -> assertNotNull(student), () -> assertEquals("abdul", student.getName()));
    }

    /**
     * test save student
     *
     * @throws Exception
     */
    @Test
    @Order(0)
    public void testSaveStudent() throws Exception {
        Student mockStudent = new Student(1L, "abdul@gmail.com", "abdul", 15, LocalDate.of(2008, 5, 12));
        // Performing the POST request
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.post("/students").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockStudent)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Student student = convertResultActionsToStudent(resultActions);

        assertAll(() -> assertNotNull(student), () -> assertEquals(mockStudent, student),
                () -> assertEquals(mockStudent.getName(), student.getName()));
    }

    /**
     * test update student
     *
     * @throws Exception
     */
    @Test
    @Order(1)
    public void testUpdateStudentByEmail() throws Exception {
        // Mocking the service response
        Student mockStudent = new Student(1L, "abdulUpdate@gmail.com", "abdul", 15, LocalDate.of(2008, 5, 12));

        // Creating a sample request
        String requestBody = objectMapper.writeValueAsString(mockStudent);

        // Performing the PUT request
        ResultActions resultActions = mvc.perform(
                        MockMvcRequestBuilders.put("/students/1").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Student student = convertResultActionsToStudent(resultActions);

        assertAll(() -> assertNotNull(student), () -> assertEquals(mockStudent, student),
                () -> assertEquals(mockStudent.getEmail(), student.getEmail()));

    }

    /**
     * test age validation
     *
     * @throws Exception
     */
    @Test
    public void testShouldNotCreateSuccessfullyForAge() throws Exception {
        Student mockStudent = new Student(1L, "abdul@gmail.com", "abdul", 10, LocalDate.of(2008, 5, 12));
        // Performing the POST request
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.post("/students").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockStudent)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        assertAll(() -> assertEquals("Age must be at least 15", JsonPath.read(responseBody, "$.message")));
    }

    /**
     * test date of birth
     *
     * @throws Exception
     */
    @Test
    public void testShouldNotCreateSuccessfullyForDateOfBirth() throws Exception {
        Student mockStudent = new Student(1L, "abdul@gmail.com", "abdul", 16, null);
        // Performing the POST request
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.post("/students").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockStudent)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        assertAll(() -> assertEquals("Date of birth is required", JsonPath.read(responseBody, "$.message")));
    }

    /**
     * test delete student
     *
     * @throws Exception
     */
    @Test
    public void testRemoveStudent() throws Exception {
        // Performing the DELETE request
        mvc.perform(MockMvcRequestBuilders.delete("/students/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    private Student convertResultActionsToStudent(final ResultActions resultActions)
            throws UnsupportedEncodingException, JsonProcessingException {
        return objectMapper.readValue(objectMapper.writeValueAsString(objectMapper
                        .readValue(resultActions.andReturn().getResponse().getContentAsString(), Response.class).getData()),
                Student.class);
    }

}
