package dev.cryss.apiautomatedtests.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

}
