package se.lexicon.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.model.AppUser;
import se.lexicon.model.Book;
import se.lexicon.model.BookLoan;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@Transactional
class BookLoanDAOimplTest {

    @Autowired
    BookLoanDAOimpl testObject;
    @Autowired
    TestEntityManager entityManager;

    private List<BookLoan> persistedList;

    public List<BookLoan> bookLoanList(){
        return Arrays.asList(
                new BookLoan(LocalDate.now(), LocalDate.parse("2022-03-21"), false),
                new BookLoan(LocalDate.now(), LocalDate.parse("2022-03-24"), false),
                new BookLoan(LocalDate.now(), LocalDate.parse("2022-03-25"), false)
        );
    }

    @BeforeEach
    void setup(){
        persistedList = bookLoanList().stream()
                .map(entityManager::persist)
                .collect(Collectors.toList());
    }

    @Test
    void create() {
        BookLoan bookLoan = new BookLoan(LocalDate.now(), LocalDate.parse("2022-03-21"), false);
        BookLoan result = testObject.create(bookLoan);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void update() {
        BookLoan bookLoan2 = new BookLoan(LocalDate.now(),LocalDate.parse("2022-03-10"),false);
        BookLoan result = testObject.update(bookLoan2);
        assertNotNull(result);
        System.out.println(result);
    }


    @Test
    void findById() {
        int id = persistedList.get(2).getLoanId();
        System.out.println(id);
        BookLoan foundDetails = testObject.findById(id);
        System.out.println(foundDetails);
        assertEquals(foundDetails.getLoanId(), id);
    }

    @Test
    void findAll() {
        int expected = 3;
        Collection<BookLoan> userFound = testObject.findAll();
        System.out.println(userFound);
        assertEquals(expected, userFound.size());

    }

    @Test
    void delete() {
        int id = persistedList.get(2).getLoanId();
        testObject.delete(id);

        assertNull(entityManager.find(BookLoan.class, id));
    }
}