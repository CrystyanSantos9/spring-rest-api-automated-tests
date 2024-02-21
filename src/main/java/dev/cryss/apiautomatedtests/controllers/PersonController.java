package dev.cryss.apiautomatedtests.controllers;

import dev.cryss.apiautomatedtests.model.Person;
import dev.cryss.apiautomatedtests.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private final PersonServices service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findBAll() throws Exception {
        return service.findAll ();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> findById(@PathVariable(value = "id") Long id) {
        try{
            return ResponseEntity.ok (service.findById (id));
        }catch (Exception e){
            return ResponseEntity.notFound ().build ();
        }

    }


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> create(
            HttpServletRequest request,
            @RequestBody Person person
    ) {

        Person createdPerson = service.create (person);

        URI location = ServletUriComponentsBuilder
                .fromServletMapping (request)
                .path ("/persons/{id}")
                .build ()
                .expand (createdPerson.getId ()).toUri ();

        HttpHeaders headers = new HttpHeaders ();
        headers.setLocation (location);

        return new ResponseEntity<> (createdPerson, headers, HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@RequestBody Person person) {

        return service.create (person);
    }

    @Operation(summary = "Remove a person by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Person removed succesfull"), @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content), @ApiResponse(responseCode = "404", description = "Person not found", content = @Content)})
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long id) throws Exception {

        service.delete (id);

    }
}
