package xyz.andornot.domain;

import lombok.Data;

@Data
public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
