package se.lexicon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.lexicon.dao.BookDAOimpl;
import se.lexicon.dao.BookLoanDAO;
import se.lexicon.dao.BookLoanDAOimpl;
import se.lexicon.model.Book;
import se.lexicon.model.BookLoan;

import java.util.HashSet;

@SpringBootApplication
public class JpaLibraryWorkshopApplication implements CommandLineRunner {

	@Autowired
	BookDAOimpl bookDAOimpl;


	public static void main(String[] args) {
		SpringApplication.run(JpaLibraryWorkshopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		bookDAOimpl.create(new Book(0,"1abc123","Java fundamentals",14,true,new HashSet<>()));
		bookDAOimpl.create(new Book(0,"2abc125","Java advance",14,true,new HashSet<>()));
		bookDAOimpl.create(new Book(0,"3abc189","Java master",14,true,new HashSet<>()));
		bookDAOimpl.delete(2);
		bookDAOimpl.update(new Book(3,"12456bnda","Java advance",14,true,new HashSet<>()));

	}
}
