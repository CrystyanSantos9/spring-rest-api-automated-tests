package dev.cryss.apiautomatedtests.controllers;

import dev.cryss.apiautomatedtests.model.Person;
import dev.cryss.apiautomatedtests.services.PersonServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private final PersonServices service;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Person> findBAll() throws Exception {
        return service.findAll ();
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(
            @PathVariable(value = "id") String id) throws Exception {
        return service.findById (id);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person create(
            @RequestBody Person person
    ) {

        return service.create (person);

    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person update(
            @RequestBody Person person
    ) {

        return service.create (person);

    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public void delete(
            @PathVariable(value = "id") String id
    ) throws Exception {

        service.delete (id);

    }
}
