package dev.cryss.apiautomatedtests.services;

import dev.cryss.apiautomatedtests.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong ();
    private Logger logger = Logger.getLogger (PersonServices.class.getName ());

    public Set<Person> findAll(){
        logger.info ("Finding all people");
        Set<Person> personSet = new HashSet<> ();
        for(int i = 0; i<8; i++){
            Person mockedPerson = mockPerson(i);
            personSet.add (mockedPerson);
        }
        return personSet;
    }
    
    public Person findById(String id) {
        logger.info ("Finding one person");

        Person person = Person.builder ()
                .id (counter.incrementAndGet ())
                .firstName ("Beto")
                .lastName ("Uru")
                .address ("Rua Vila Maria - São Paulo - Brasil")
                .gender ("Male")
                .build ();

        return person;
    }

    public Person create(Person person){
        logger.info ("Creating a new person " + person);
        return person;
    }

    public Person update(Person person){
        logger.info ("Update a person " + person);
        return person;
    }

    public void delete(String id){
        logger.info ("Removing personId " + id);
    }

    private Person mockPerson(int i) {
        Person person = Person.builder ()
                .id (counter.incrementAndGet ())
                .firstName ("Person " + i)
                .lastName ("Last " + i)
                .address ("Addres " + i)
                .gender ("Gender " + i)
                .build ();
        return person;
    }
}
