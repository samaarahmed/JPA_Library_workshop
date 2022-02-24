package se.lexicon.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.model.Details;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@Transactional

class DetailsDAOimplTest {

    @Autowired
    DetailsDAOimpl testObject;

    @Autowired
    TestEntityManager entityManager;
    public List<Details> detailsList(){
        return Arrays.asList(
                new Details("sa1","samaar",LocalDate.parse("1900-01-01")),
                new Details("su1","Susane",LocalDate.parse("1900-02-02")),
                new Details("to1","Tobias",LocalDate.parse("1900-03-03"))

        );
    }
    private List<Details> persistedList;

    @BeforeEach
    void setUp() {
        persistedList = detailsList().stream()
                .map(entityManager::persist)
                .collect(Collectors.toList());
    }

        @Test
    void create() {
        Details details = new Details("sam@123.com","Samaar", LocalDate.parse("1985-01-01"));
       Details result =  testObject.create(details);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void update() {
        Details details = new Details("tob@123.com","Tobias",LocalDate.parse("1960-01-01"));

        Details result = testObject.update(details);
        assertNotNull(result);
        System.out.println(result);

    }

    @Test
    void findById() {
        int id = persistedList.get(2).getDetailsId();
        System.out.println(id);
        Details foundDetails = testObject.findById(id);
        System.out.println(foundDetails);
        assertEquals(foundDetails.getDetailsId(), id);
    }

    @Test
    void findAll() {
        int expected = 3;
        Collection<Details> userFound = testObject.findAll();
        System.out.println(userFound);
        assertEquals(expected, userFound.size());

    }

    @Test
    void delete() {
        int id = persistedList.get(2).getDetailsId();

        testObject.delete(id);

        assertNull(entityManager.find(Details.class, id));

    }
}