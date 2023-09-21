package br.com.luis.httpMethod.services;

import br.com.luis.httpMethod.controller.PersonController;
import br.com.luis.httpMethod.data.vo.v1.PersonVO;
import br.com.luis.httpMethod.exceptions.ResourceNotFoundException;
import br.com.luis.httpMethod.mapper.DozerMapper;
import br.com.luis.httpMethod.model.Person;
import br.com.luis.httpMethod.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final Logger logger = Logger.getLogger(PersonServices.class.getName());
    private PersonVO vo;

    @Autowired
    PersonRepository personRepository;

    public List<PersonVO> findAll() {
        logger.info("Finding one people!");
        List<PersonVO> persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
        persons.forEach(
                p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel())
        );

        return persons;
    }

    public PersonVO findById(Long id) {
        logger.info("Finding one person!");

        var entity = personRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("No records found for this id"));
        vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person) {
        logger.info("Creating one person!");

        var entity = DozerMapper.parseObject(person, Person.class);
        vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public PersonVO update(PersonVO person) {
        logger.info("Updating one person!");

        var entity = personRepository.findById(person.getKey()).orElseThrow(() ->
                new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        var entity = personRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("No records found for this id"));

        personRepository.delete(entity);
    }

}
