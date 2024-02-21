package dev.cryss.apiautomatedtests.services;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import dev.cryss.apiautomatedtests.exceptions.ResourceNotFoundException;
import dev.cryss.apiautomatedtests.model.Person;
import dev.cryss.apiautomatedtests.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith (MockitoExtension.class)
public class PersonServicesTest {

    @Mock
    private PersonRepository repository;
    @InjectMocks
    private PersonServices service;

    private Person person0;

    @BeforeEach
    public void setup() {
        //Given / Arrange
        person0 = Person.builder ()
                .firstName ("Alex")
                .lastName ("Sans")
                .email ("alas@gmail.com")
                .address ("Rua b - 201 - RJ")
                .gender ("Male")
                .build ();
    }

    @DisplayName ("Junit test for Given Person Object when Save Person then Return Person Object")
    @Test
    void testGivenPersonObject_WhenSavePerson_thenReturnPersonObject() {
        //Given /Arrange
        given (repository.findByEmail (anyString ())).willReturn (Optional.empty ());
        given (repository.save (person0)).willReturn (person0);
        // When /Act
        Person savedPerson = service.create (person0);

        // Then /Assert
        assertNotNull (savedPerson);
        assertEquals ("alas@gmail.com", savedPerson.getEmail ());
      //  assertTrue (savedPerson.getId () > 0);
    }

    @DisplayName ("Junit test for Given Existing Email when Save Person then throws Exception")
    @Test
    void testGivenExistingEmail_WhenSave_thenThrowsException() {
        //Given /Arrange
        given (repository.findByEmail (anyString ())).willReturn (Optional.of (person0));

        // When /Act
        assertThrows ((ResourceNotFoundException.class), () -> {
            service.create (person0);
        });

        // Then /Assert
        verify (repository, never ()).save (any (Person.class));
    }

    @DisplayName("Junit test for Given Person List when FindAll Persons then Return Persons List")
    @Test
    void testGivenPersonList_whenFidAll_thenReturnsPersonList() {
        //Given /Arrange
        Person person1 = Person.builder ()
                .firstName ("Amelia")
                .lastName ("Alvez")
                .email ("ase@gmal.com")
                .address ("Minas - RJ")
                .gender ("Male")
                .build ();

        given (repository.findAll ()).willReturn (List.of (person0, person1));

        // When /Act
        List<Person> personList = service.findAll ();

        // Then /Assert
        assertNotNull (personList);
        assertEquals (2, personList.size ());
        assertEquals (person0.getLastName (), personList.get (0).getLastName ());
    }

    @DisplayName("Junit test for Given Empty List when FindAll Persons then Return Empty List")
    @Test
    void testGivenEmptyList_whenFindAll_thenReturnsEmptyList() {
        //Given /Arrange
        given (repository.findAll ()).willReturn (Collections.emptyList ());

        // When /Act
        List<Person> personList = service.findAll ();

        // Then /Assert
        assertTrue (personList.isEmpty ());
        assertEquals (0, personList.size ());
    }

    @DisplayName("Junit test for Given PersonId when FindById then Return Person Object")
    @Test
    void testGivenPersonId_whenFindById_thenReturnPersonObject() {
        //Given /Arrange
        given (repository.findById (anyLong ())).willReturn (Optional.of(person0));

        // When /Act
        Person personFound = service.findById (1L);

        // Then /Assert
        assertNotNull (personFound);
        assertEquals ("Alex", personFound.getFirstName ());
    }

    @DisplayName("Junit test for Given Person Object when Update Person then Return updated Person")
    @Test
    void testGivenPersonObject_whenUpdate_thenReturnUpdatedPerson() {
        //Given /Arrange
        person0.setId (1L);
        given (repository.findById (anyLong ())).willReturn (Optional.of(person0));

        person0.setFirstName ("Alan");
        person0.setEmail ("alb@gmail.com");

        given (repository.save (person0)).willReturn (person0);

        // When /Act
        Person updatedPerson = service.update (person0);

        // Then /Assert
        assertNotNull (updatedPerson);
        assertEquals ("Alan", updatedPerson.getFirstName ());
        assertEquals ("alb@gmail.com", updatedPerson.getEmail ());
    }

    @DisplayName("Junit test for Given PersonID when Delete Person then Do Nothing")
    @Test
    void testGivenPersonID_whenDeletePerson_thenDoNothing() {
        //Given /Arrange
        person0.setId (1L);
        given (repository.findById (anyLong ())).willReturn (Optional.of(person0));
        willDoNothing ().given (repository).delete (person0);


        // When /Act
         service.delete (person0.getId ());

        // Then /Assert
        verify (repository, times (1)).delete (person0);

    }

}