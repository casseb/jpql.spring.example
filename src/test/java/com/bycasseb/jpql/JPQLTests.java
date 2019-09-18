package com.bycasseb.jpql;

import com.bycasseb.jpql.model.Address;
import com.bycasseb.jpql.model.Person;
import com.bycasseb.jpql.repository.AddressRepository;
import com.bycasseb.jpql.repository.PersonRepository;
import lombok.extern.java.Log;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * @author felipe-casseb
 * @version : $<br/>
 * : $
 * @since 18/09/19 16:32
 */
@Log
public class JPQLTests extends JpqlApplicationTests {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void before(){
        addressRepository.deleteAll();
        personRepository.deleteAll();

        for(int i = 0; i < 20000; i++){
            createAPerson();
        }
    }

    @Test
    public void ImplictyTest(){
        Instant start = Instant.now();
        List<Person> persons = personRepository.findWithJpqlImplicity("123");
        Instant end = Instant.now();
        Integer difference = Duration.between(start, end).getNano();
        log.info("Levou " + difference + "  milisegundos para a execução implicita");
    }

    @Test
    public void ExplicityTest(){
        Instant start = Instant.now();
        List<Person> persons = personRepository.findWithJpqlExplicity("123");
        Instant end = Instant.now();
        Integer difference = Duration.between(start, end).getNano();
        log.info("Levou " + difference + "  milisegundos para a execução explicita");
    }

    private Person createAPerson(){
        Address address = new Address();
        address.setPostcode("123");

        Person person = new Person();
        person.setName("Casseb");
        person.setAddress(address);

        personRepository.save(person);
        return person;
    }
}
