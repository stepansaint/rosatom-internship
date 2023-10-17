package com.rosatom.b_Hibernate.entity;

import static java.util.Objects.requireNonNull;

public class User {
    private final Long id;
    private String name;
    private String surname;
    private Integer age;
    private String hobby;

    public User(Long id, String name, String surname, Integer age, String hobby) {
        this.id = requireNonNull(id, "Field id can't be null");
        this.name = requireNonNull(name, "Field name can't be null");
        this.surname = requireNonNull(surname, "Field surname can't be null");
        this.age = requireNonNull(age, "Field age can't be null");
        this.hobby = requireNonNull(hobby, "Field hobby can't be null");
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = requireNonNull(name, "Field name can't be null");
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = requireNonNull(surname, "Field surname can't be null");
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = requireNonNull(age, "Field surname can't be null");
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = requireNonNull(hobby, "Field hobby can't be null");
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
