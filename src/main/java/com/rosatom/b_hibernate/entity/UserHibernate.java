package com.rosatom.b_hibernate.entity;

import javax.persistence.*;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "users")
public class UserHibernate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "age")
    private Integer age;
    @Column(name = "hobby")
    private String hobby;

    public UserHibernate() {}

    public UserHibernate(String name, String surname, Integer age, String hobby) {
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
        return "UserHibernate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
