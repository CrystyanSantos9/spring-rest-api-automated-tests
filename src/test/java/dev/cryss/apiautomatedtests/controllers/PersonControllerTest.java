package dev.cryss.apiautomatedtests.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.cryss.apiautomatedtests.exceptions.ResourceNotFoundException;
import dev.cryss.apiautomatedtests.model.Person;
import dev.cryss.apiautomatedtests.services.PersonServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private PersonServices service;

    private Person person;

    private MockHttpServletRequest request;

    @BeforeEach
    public void setup(){
        person = Person.builder ()
                .firstName ("Amelia")
                .lastName ("Alvez")
                .email ("ase@gmal.com")
                .address ("Minas - RJ")
                .gender ("Male")
                .build ();

        this.request = new MockHttpServletRequest();
        this.request.setScheme("http");
        this.request.setServerName("localhost");
        this.request.setServerPort(-1);
        this.request.setRequestURI("/persons");
        this.request.setContextPath("/persons");

    }


    @Test
    @DisplayName ("JUnit test for given Person object when Create Person then returns Person saved")
    void testGivenPersonObject_When_Create_ShouldReturnsPersonSaved() throws JsonProcessingException, Exception {
        //Given /Arrange
        person.setId (1L);
        given(service.create (any (Person.class))).
                willAnswer ((invocation -> invocation.getArgument (0)));

        String uri = ServletUriComponentsBuilder.fromRequest(this.request)
                .path ("/{id}")
                        .build()
                .expand (person.getId ()).toUriString ();

        this.request.addHeader ("Location", uri);

        // When /Act
        ResultActions response =
                mockMvc
                .perform (post("/persons")
                        .contentType (MediaType.APPLICATION_JSON)
                        .content (mapper.writeValueAsString (person)));

        // Then /Assert
        response.andDo (print())
                .andExpect (status().isCreated ())
                .andExpect (jsonPath ("$.firstName", is (person.getFirstName ())))
                .andExpect (jsonPath ("$.email", is (person.getEmail ())));

        assertThat(request.getHeader ("Location")).isEqualTo("http://localhost/persons/1");

    }

    //test[System Under Test]_[Condition or State change]_[Expected Results]

    @Test
    @DisplayName("JUnit test given a person list when findAll should return a List of Person")
    void testGivenPersonList_When_FindALL_ShouldReturnsAPersonList() throws Exception {
        //Given /Arrange
        List<Person> personList = new ArrayList<> ();

        Person person2 = Person.builder ().firstName ("Amelia").lastName ("Alvez").email ("ase@gmal.com").address ("Minas - RJ").gender ("Male").build ();

        personList.add (person);
        personList.add (person2);

        given (service.findAll ()).willReturn (personList);

        // When /Act
        ResultActions response = mockMvc.perform (get ("/persons"));

        // Then /Assert
        response.andExpect (status ().isOk ()).andDo (print ()).andExpect (jsonPath ("$.size()", is (personList.size ())));
    }

    @Test
    @DisplayName("JUnit test given a person id when findById then returns Person Object")
    void testGivenPersonId_When_FindById_ThenReturnsPersonObjectt() throws Exception {
        //Given /Arrange
        long personId = 1L;

        given (service.findById (personId)).willReturn (person);

        // When /Act
        ResultActions response = mockMvc.perform (get ("/persons/{id}", personId));

        // Then /Assert
        response
                .andExpect (status ().isOk ())
                .andDo (print ())
                .andExpect (jsonPath ("$.firstName", is (person.getFirstName ())));
    }

    @Test
    @DisplayName("JUnit test given invalid person id when findById then returns status code not found")
    void testGivenIvalidPersonId_When_FindById_ThenReturnsNotFound() throws Exception {
        //Given /Arrange
        long personId = 1L;
        given (service.findById (personId)).willThrow (ResourceNotFoundException.class);

        // When /Act
        ResultActions response = mockMvc.perform (get ("/persons/{id}", personId));

        // Then /Assert
        response
                .andExpect (status ().isNotFound ())
                .andDo (print ());

    }

}