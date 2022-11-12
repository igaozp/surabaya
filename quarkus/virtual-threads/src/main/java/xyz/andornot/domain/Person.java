package xyz.andornot.domain;

import io.vertx.mutiny.sqlclient.Row;

public class Person {
    private Long id;

    private String name;

    private Integer age;

    private Gender gender;

    private Integer externalId;

    public Person() {
    }

    public Person(Long id, String name, Integer age, Gender gender, Integer externalId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.externalId = externalId;
    }

    public static Person from(Row row) {
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getExternalId() {
        return externalId;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }
}
