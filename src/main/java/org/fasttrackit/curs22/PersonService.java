package org.fasttrackit.curs22;

import org.fasttrackit.curs22.domain.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Service
public class PersonService {
    private final List<Person> persons = new ArrayList<>();

    public PersonService(PeopleReader reader) {
        reader.read()
                .forEach(this::add);
    }

    public List<Person> getAll() {
        return Collections.unmodifiableList(persons);
    }

    public Person add(Person person) {
        Person newPerson = new Person(fetchLatestId(), person.getName(),
                person.getCity(), person.getAge());
        addPersonInList(newPerson);
        return newPerson;
    }

    private void addPersonInList(Person newPerson) {
        persons.add(newPerson.getId() - 1, newPerson);
    }

    private int fetchLatestId() {
        final Set<Integer> existingIds = persons.stream()
                .map(Person::getId)
                .collect(toSet());
        return Stream.iterate(1, i -> i + 1)
                .filter(id -> !existingIds.contains(id))
                .findFirst()
                .orElseThrow();
    }

    public Person replace(int id, Person person) {
        Person personToReplace = getOrThrow(id);

        persons.remove(personToReplace);
        var newPerson = new Person(id, person.getName(), person.getCity(), person.getAge());
        addPersonInList(newPerson);
        return newPerson;
    }

    private Person getOrThrow(final int id) {
        return persons.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Could not find person with id " + id));
    }

    public Person delete(final int id) {
        Person personToDelete = getOrThrow(id);
        persons.remove(personToDelete);
        return personToDelete;
    }
}
