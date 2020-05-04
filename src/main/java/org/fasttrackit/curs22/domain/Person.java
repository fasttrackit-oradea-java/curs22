package org.fasttrackit.curs22.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Person {
    private final int id;
    private final String name;
    private final String city;
    private final int age;

    public Person(
            @JsonProperty("name") String name,
            @JsonProperty("city") String city,
            @JsonProperty("age") int age) {
        this(0, name, city, age);
    }

    public Person(int id, String name, String city, int age) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Person person = (Person) o;
        return id == person.id &&
                age == person.age &&
                Objects.equals(name, person.name) &&
                Objects.equals(city, person.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, age);
    }
}
