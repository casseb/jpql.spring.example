package com.bycasseb.jpql;

import com.bycasseb.jpql.model.Address;
import com.bycasseb.jpql.model.Person;
import com.bycasseb.jpql.repository.AddressRepository;
import com.bycasseb.jpql.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class JPQLTests extends JpqlApplicationTests {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    private Integer implicityNanoDifference;
    private Integer explicityNanoDiferrence;
    private Integer implicityNanoComplexDifference;
    private Integer explicityNanoComplexDiferrence;

    private Long implicitySecDifference;
    private Long explicitySecDiferrence;
    private Long implicitySecComplexDifference;
    private Long explicitySecComplexDiferrence;

    @Before
    public void before(){
        addressRepository.deleteAll();
        personRepository.deleteAll();

        for(int i = 0; i < 200000; i++){
            createAPerson();
        }
    }

    private void implictyTest(){
        Instant start = Instant.now();
        List<Person> persons = personRepository.findWithJpqlImplicity("123");
        Instant end = Instant.now();
        implicityNanoDifference = Duration.between(start, end).getNano();
        implicitySecDifference = Duration.between(start, end).getSeconds();
    }

    private void explicityTest(){
        Instant start = Instant.now();
        List<Person> persons = personRepository.findWithJpqlExplicity("123");
        Instant end = Instant.now();
        explicityNanoDiferrence = Duration.between(start, end).getNano();
        explicitySecDiferrence = Duration.between(start, end).getSeconds();
    }

    private void implictyComplexTest(){
        Instant start = Instant.now();
        List<Person> persons = personRepository.findWithComplexJpqlImplicity("123");
        Instant end = Instant.now();
        implicityNanoComplexDifference = Duration.between(start, end).getNano();
        implicitySecComplexDifference = Duration.between(start, end).getSeconds();
    }

    private void explicityComplexTest(){
        Instant start = Instant.now();
        List<Person> persons = personRepository.findWithComplexJpqlExplicity("123");
        Instant end = Instant.now();
        explicityNanoComplexDiferrence = Duration.between(start, end).getNano();
        explicitySecComplexDiferrence = Duration.between(start, end).getSeconds();
    }

    @Test
    public void compareTest(){
        log.info("Inicio consulta explicito");
        explicityTest();
        log.info("Fim consulta explicito");
        log.info("Inicio consulta implicito");
        implictyTest();
        log.info("Fim consulta implicito");
        log.info("Inicio consulta complexa explicito");
        explicityComplexTest();
        log.info("Fim consulta complexa explicito");
        log.info("Inicio consulta complexa implicito");
        implictyComplexTest();
        log.info("Fim consulta complexa implicito");
        log.info("{}:{}  : Implícito", implicitySecDifference, implicityNanoDifference);
        log.info("{}:{}  : Explícito", explicitySecDiferrence, explicityNanoDiferrence);
        log.info("{}:{}  : Implícito Complexo", implicitySecComplexDifference, implicityNanoComplexDifference);
        log.info("{}:{}  : Explícito Complexo", explicitySecComplexDiferrence, explicityNanoComplexDiferrence);
    }

    private Person createAPerson(){
        Address address = new Address();
        address.setPostcode("123");

        Person person = new Person();
        person.setName("Casseb");
        person.setAddress(address);
        person.setBusinessAddress(address);
        person.setHomeAddress(address);

        personRepository.save(person);
        return person;
    }
}
