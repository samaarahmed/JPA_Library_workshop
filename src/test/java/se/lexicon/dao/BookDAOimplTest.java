package se.lexicon.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.model.AppUser;
import se.lexicon.model.Book;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookDAOTest {
    @Autowired
    BookDAO testObject;

    @Autowired
    TestEntityManager entityManager;

    public List<Book> bookList(){
        return Arrays.asList(
                new Book("123456","React", 14),
                new Book("654321","Java",14),
                new Book("658721","Spring",14)
        );
    }
    private List<Book> addedBooks;

    @BeforeEach
    void setUp() {
        addedBooks = bookList().stream()
                .map(entityManager::persist)
                .collect(Collectors.toList());
    }



    @Test
    void create() {
        Book book1 = new Book("123456","React", 14);
        Book result =  testObject.save(book1);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void update() {
        Book book2 = new Book("654321","Java",14);

        Book result = testObject.save(book2);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void findById() {

        int id = addedBooks.get(2).getBookId();
        System.out.println(id);
       // Book foundDetails = testObject.findById(id);
       // System.out.println(foundDetails);
       // assertEquals(foundDetails.getBookId(), id);
    }

    @Test
    void findAll() {

        int expected = 3;
        Collection<Book> userFound = testObject.findAll();
        System.out.println(userFound);
        assertEquals(expected, userFound.size());

    }


    @Test
    void delete() {
        int id = addedBooks.get(2).getBookId();
       // testObject.delete(id);

        assertNull(entityManager.find(Book.class, id));
    }
}