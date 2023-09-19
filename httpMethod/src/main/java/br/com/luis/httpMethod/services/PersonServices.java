package br.com.luis.httpMethod.services;

import br.com.luis.httpMethod.exceptions.ResourceNotFoundException;
import br.com.luis.httpMethod.model.Person;
import br.com.luis.httpMethod.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository personRepository;

    public List<Person> findAll() {
        logger.info("Finding one people!");
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        logger.info("Finding one person!");
        return personRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("No records found for this id"));
    }

    public Person create(Person person) {
        logger.info("Creating one person!");
        return personRepository.save(person);
    }

    public Person update(Person person) {
        Person entity = findById(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        logger.info("Updating one person!");
        return personRepository.save(entity);
    }

    public void delete(Long id) {
        Person entity = findById(id);

        logger.info("Deleting one person!");
        personRepository.delete(entity);
    }

}
