package xyz.andornot.domain;

import lombok.Data;

/**
 * Employee domain class
 *
 * @author igaozp
 * @since  2021/11/10
 */
@Data
public class Employee {
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;
}
