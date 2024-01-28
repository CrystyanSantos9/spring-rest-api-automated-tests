package dev.cryss.apiautomatedtests.repository;

import dev.cryss.apiautomatedtests.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
class PersonRepositoryTest {
    @Autowired
    private PersonRepository repository;

    @DisplayName("Given Person Object when Save then Return a Saved Person")
    @Test
    void testGivenPersonObject_WhenSave_thenReturnSavedPerson() {

        //Given /Arrange
        Person person0 = Person.builder ()
                .firstName ("Costa")
                .lastName ("Bezerro")
                .email ("be@gmal.com")
                .address ("Minas - RJ")
                .gender ("Female")
                .build ();
        //When /Act
        Person savedPerson = repository.save (person0);

        //Then / Assert
        assertNotNull (savedPerson);
        assertTrue (savedPerson.getId () > 0);
    }
}