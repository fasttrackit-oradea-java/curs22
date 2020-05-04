package org.fasttrackit.curs22;

import org.fasttrackit.curs22.domain.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class PeopleReader {
    private final String fileLocation;
    private final Resource file;

    public PeopleReader(@Value("${file.location:default.txt}") String fileLocation) {
        this.fileLocation = fileLocation;
        this.file = new ClassPathResource(fileLocation);
        if (!file.exists()) {
            throw new RuntimeException("Could not find file in classpath " + fileLocation);
        }
    }

    public List<Person> read() {
        List<Person> result = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream()) {
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                var tokens = scanner.nextLine()
                        .split("[|]");
                result.add(new Person(tokens[0], tokens[2],
                        Integer.parseInt(tokens[1])));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

}
