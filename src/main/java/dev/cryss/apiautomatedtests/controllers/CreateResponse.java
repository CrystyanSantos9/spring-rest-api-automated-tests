package dev.cryss.apiautomatedtests.controllers;

import dev.cryss.apiautomatedtests.model.Person;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;

@Data
@AllArgsConstructor
@Getter
public class CreateResponse extends RepresentationModel<CreateResponse> {

    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;

    public Person toEntity(){
        return Person.builder ()
                .id (this.id)
                .firstName (this.firstName)
                .lastName (this.lastName)
                .address (this.address)
                .gender (this.gender)
                .build ();
    }
}
