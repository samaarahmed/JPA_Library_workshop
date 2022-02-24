package se.lexicon.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.model.AppUser;

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
class AppUserDAOimplTest {

    @Autowired
    AppUserDAOimpl testObject;

    @Autowired
    TestEntityManager entityManager;

        public List<AppUser> appUserList(){
            return Arrays.asList(
                    new AppUser("sa1","samaar",LocalDate.parse("1900-01-01")),
                    new AppUser("su1","Susane",LocalDate.parse("1900-02-02")),
                    new AppUser("tob1","Tobias",LocalDate.parse("1900-03-03"))
            );
        }
        private List<AppUser> persistedList;

        @BeforeEach
        void setUp() {
            persistedList = appUserList().stream()
                    .map(entityManager::persist)
                    .collect(Collectors.toList());
        }


    @Test
    void create() {

            AppUser appUser = new AppUser("sa1","1234", LocalDate.parse("1900-01-01"));

        AppUser result =  testObject.create(appUser);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void update() {
        AppUser appUser = new AppUser("su1","1004", LocalDate.parse("1900-01-01"));

        AppUser result = testObject.update(appUser);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void findById() {
        int id = persistedList.get(2).getAppUserId();
        System.out.println(id);
        AppUser foundDetails = testObject.findById(id);
        System.out.println(foundDetails);
        assertEquals(foundDetails.getAppUserId(), id);
    }

    @Test
    void findAll() {
        int expected = 3;
        Collection<AppUser> userFound = testObject.findAll();
        System.out.println(userFound);
        assertEquals(expected, userFound.size());
    }

    @Test
    void delete() {

        int id = persistedList.get(2).getAppUserId();

        testObject.delete(id);

        assertNull(entityManager.find(AppUser.class, id));
    }
}