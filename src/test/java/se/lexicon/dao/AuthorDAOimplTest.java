package se.lexicon.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.model.Author;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@Transactional
class AuthorDAOimplTest {

    @Autowired
    AuthorDAOimpl testObject;

    @Autowired
    TestEntityManager entityManager;

    public List<Author> authorList(){
        return Arrays.asList(
                new Author("Jan","Olsson"),
                new Author("Mike","Nilsson"),
                new Author("Ashfaq","Ahmed")
        );
    }
    private List<Author> persistedList;

    @BeforeEach
    void setUp() {
        persistedList = authorList().stream()
                .map(entityManager::persist)
                .collect(Collectors.toList());
    }

    @Test
    void create() {
        Author author = new Author("Jan","Olsson");
        Author result =  testObject.create(author);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void update() {
        Author author = new Author("Ashfaq","Ahmed");
        Author result =  testObject.update(author);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void findById() {
        int id = persistedList.get(2).getAuthorID();
        System.out.println(id);
        Author foundDetails = testObject.findById(id);
        System.out.println(foundDetails);
        assertEquals(foundDetails.getAuthorID(), id);
    }

    @Test
    void findAll() {
        int expected = 3;
        Collection<Author> userFound = testObject.findAll();
        System.out.println(userFound);
        assertEquals(expected, userFound.size());
    }

    @Test
    void delete() {
        int id = persistedList.get(2).getAuthorID();
        testObject.delete(id);
        assertNull(entityManager.find(Author.class, id));
    }
}