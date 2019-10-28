package com.example.batchprocessing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

/**
 * com.example.batchprocessing.PersonItemProcessor, created on 28/10/2019 08:13 <p>
 * @author AlessandroA
 */
@Slf4j
public class PersonItemProcessor implements ItemProcessor<Person, Person> {


  @Override public Person process(Person person) throws Exception {
    final String firstName = person.getFirstName().toUpperCase();
    final String lastName = person.getLastName().toUpperCase();

    final Person transformedPerson = new Person(firstName, lastName);

    log.info("Converting {} into {}", person, transformedPerson);

    return transformedPerson;
  }
}
