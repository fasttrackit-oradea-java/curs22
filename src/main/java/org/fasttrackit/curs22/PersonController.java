package org.fasttrackit.curs22;

import org.fasttrackit.curs22.domain.Person;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAll() {
        return personService.getAll();
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        return personService.add(person);
    }

    @PutMapping("{id}")
    public Person replacePerson(@PathVariable int id, @RequestBody Person person) {
        return personService.replace(id, person);
    }

    @DeleteMapping("{id}")
    public Person deletePerson(@PathVariable int id){
        return personService.delete(id);
    }

}
