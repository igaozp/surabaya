package xyz.andornot.domain;

import io.vertx.mutiny.sqlclient.Row;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Long id;

    private String name;

    private Integer age;

    private Gender gender;

    private Integer externalId;

    public static Person from(Row row) {
        return null;
    }
}
