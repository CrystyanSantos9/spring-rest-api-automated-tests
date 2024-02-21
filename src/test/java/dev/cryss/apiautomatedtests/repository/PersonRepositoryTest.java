package dev.cryss.apiautomatedtests.repository;

import dev.cryss.apiautomatedtests.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
class PersonRepositoryTest {
    @Autowired
    private PersonRepository repository;

    private Person person0;

    @BeforeEach
    public void setup(){

        //Given /Arrange
        person0 = Person.builder ()
                .firstName ("Costa")
                .lastName ("Bezerro")
                .email ("be@gmal.com")
                .address ("Minas - RJ")
                .gender ("Female")
                .build ();
    }

    @DisplayName("Given Person Object when Save then Return a Saved Person")
    @Test
    void testGivenPersonObject_WhenSave_thenReturnSavedPerson() {

        //When /Act
        Person savedPerson = repository.save (person0);

        //Then / Assert
        assertNotNull (savedPerson);
        assertTrue (savedPerson.getId () > 0);
    }

    @DisplayName("Given Person List Object when Save then Return a Saved Person List")
    @Test
    void testGivenPersonList_WhenFindAll_thenReturnPersonList() {

        //Given /Arrange
        Person person1 = Person.builder ()
                .firstName ("Amelia")
                .lastName ("Alvez")
                .email ("ase@gmal.com")
                .address ("Minas - RJ")
                .gender ("Male")
                .build ();

        repository.save (person0);
        repository.save (person1);

        //When /Act
        Set<Person> personSet = new HashSet<> (repository.findAll ());

        //Then / Assert
        assertNotNull (personSet);
        assertEquals (2, personSet.size ());
    }

    @DisplayName("Given Person Object when FindById then Return Person Object")
    @Test
    void testGivenPersonObject_WhenFindById_ReturnsPersonObject() {

        //Given /Arrange
        repository.save (person0);

        //When /Act
        Person savedPerson = repository.findById (person0.getId ()).get ();

        //Then / Assert
        assertNotNull (savedPerson);
        assertEquals (savedPerson.getId (), person0.getId ());
    }

    @DisplayName("Given Person Object when FindByEmail then Return Person Object")
    @Test
    void testGivenPersonObject_WhenFindByEmail_ReturnsPersonObject() {

        //Given /Arrange
        Person person0 = Person.builder ()
                .firstName ("Costa")
                .lastName ("Bezerro")
                .email ("be@gmal.com")
                .address ("Minas - RJ")
                .gender ("Female")
                .build ();

        repository.save (person0);

        //When /Act
        Person savedPerson = repository.findByEmail (person0.getEmail ()).get ();

        //Then / Assert
        assertNotNull (savedPerson);
        assertEquals (savedPerson.getId (), person0.getId ());
        assertEquals (savedPerson.getEmail (), person0.getEmail ());
    }

    @DisplayName("Given Person Object when Update then Return Person Update Object")
    @Test
    void testGivenPersonObject_WhenUpdatePerson_ReturnsPersonUpdateObject() {

        //Given /Arrange
        repository.save (person0);

        //When /Act
        Person savedPerson = repository.findById (person0.getId ()).get ();
        savedPerson.setAddress ("Boa Vista - Taquara");
        savedPerson.setEmail ("velas@gmail.com");

        Person updatedPerson = repository.save (savedPerson);

        //Then / Assert
        assertNotNull (savedPerson);
        assertEquals (savedPerson.getId (), updatedPerson.getId ());
        assertEquals ("Boa Vista - Taquara", updatedPerson.getAddress ());
        assertEquals ("velas@gmail.com", updatedPerson.getEmail ());
    }

    @DisplayName("Given Person Object when DeleteById then remove Person")
    @Test
    void testGivenPersonObject_WhenDeleteById_ThenRemovePerson() {

        //Given /Arrange
        repository.save (person0);


        //When /Act
        repository.deleteById (person0.getId ());

        Optional<Person> personOptional = repository.findById (person0.getId ());

        //Then / Assert
       assertTrue (personOptional.isEmpty ());
    }

    @DisplayName("Given FirstName and LastName when FindByJQPL then returns Person Object")
    @Test
    void testGivenFirstNameAndLastName_WhenFindByJPQL_ThenReturnsPersonObject() {

        //Given /Arrange
        repository.save (person0);

        String firstName = person0.getFirstName ();
        String lastName = person0.getLastName ();

        //When /Act
        Person savedPerson = repository.findByJPQL (firstName, lastName);

        //Then / Assert
        assertNotNull (savedPerson);
        assertEquals (person0.getFirstName (), savedPerson.getFirstName ());
        assertEquals (person0.getLastName (), savedPerson.getLastName ());
    }

    @DisplayName("Given FirstName and LastName when FindByJPQLNamedParameters then returns Person Object")
    @Test
    void testGivenFirstNameAndLastName_WhenFindByJPQLNamedParameters_ThenReturnsPersonObject() {

        //Given /Arrange
        repository.save (person0);

        String firstName = person0.getFirstName ();
        String lastName = person0.getLastName ();

        //When /Act
        Person savedPerson = repository.findByJPQLNamedParameters (firstName, lastName);

        //Then / Assert
        assertNotNull (savedPerson);
        assertEquals (person0.getFirstName (), savedPerson.getFirstName ());
        assertEquals (person0.getLastName (), savedPerson.getLastName ());
    }

    @DisplayName("Given FirstName and LastName whenFindByJPQLNativeSQL then returns Person Object")
    @Test
    void testGivenFirstNameAndLastName_WhenFindByJPQLNativeSQL_ThenReturnsPersonObject() {

        //Given /Arrange
        repository.save (person0);

        String firstName = person0.getFirstName ();
        String lastName = person0.getLastName ();

        //When /Act
        Person savedPerson = repository.findByNativeSQL (firstName, lastName);

        //Then / Assert
        assertNotNull (savedPerson);
        assertEquals (person0.getFirstName (), savedPerson.getFirstName ());
        assertEquals (person0.getLastName (), savedPerson.getLastName ());
    }

    @DisplayName("Given FirstName and LastName when findByNativeSQLWithNamedParameters then returns Person Object")
    @Test
    void testGivenFirstNameAndLastName_WhenfindByNativeSQLWithNamedParameters_ThenReturnsPersonObject() {

        //Given /Arrange
        repository.save (person0);

        String firstName = person0.getFirstName ();
        String lastName = person0.getLastName ();

        //When /Act
        Person savedPerson = repository.findByNativeSQLWithNamedParameters (firstName, lastName);

        //Then / Assert
        assertNotNull (savedPerson);
        assertEquals (person0.getFirstName (), savedPerson.getFirstName ());
        assertEquals (person0.getLastName (), savedPerson.getLastName ());
    }

}