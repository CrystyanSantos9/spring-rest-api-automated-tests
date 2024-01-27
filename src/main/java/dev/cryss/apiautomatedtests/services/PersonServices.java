package dev.cryss.apiautomatedtests.services;

import dev.cryss.apiautomatedtests.exceptions.ResourceNotFoundException;
import dev.cryss.apiautomatedtests.model.Person;
import dev.cryss.apiautomatedtests.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
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
@RequiredArgsConstructor
@Log4j2
public class PersonServices {

    private final PersonRepository repository;

    public List<Person> findAll(){
      return repository.findAll ();
    }
    
    public Person findById(Long id) {
        return repository.findById (id).orElseThrow (
                ()-> new ResourceNotFoundException ("No records found for this ID!")
        );
    }

    public Person create(Person person){
        log.info ("Creating a new person " + person);
        return repository.save (person);
    }

    public Person update(Person person) {
        log.info ("Updating person id" + person.getId ());

        var entity = repository.findById (person.getId ()).orElseThrow (
                () -> new ResourceNotFoundException ("No records found for this ID!")
        );

        entity.builder ()
                .firstName (person.getFirstName ())
                .lastName (person.getLastName ())
                .address (person.getAddress ())
                .gender (person.getGender ())
                .build ();

        return repository.save (person);
    }

    public void delete(Long id){
        log.info ("Removing person id " + id);
        var entity = repository.findById (id).orElseThrow (
                () -> new ResourceNotFoundException ("No records found for this ID!")
        );
        repository.delete (entity);
    }

//    private Person mockPerson(int i) {
//        Person person = Person.builder ()
//                .id (counter.incrementAndGet ())
//                .firstName ("Person " + i)
//                .lastName ("Last " + i)
//                .address ("Addres " + i)
//                .gender ("Gender " + i)
//                .build ();
//        return person;
//    }
}
