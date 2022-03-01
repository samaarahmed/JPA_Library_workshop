package se.lexicon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.lexicon.dao.AppUserDAO;
import se.lexicon.dao.BookDAO;
import se.lexicon.model.Book;

import java.util.HashSet;

@SpringBootApplication
public class JpaLibraryWorkshopApplication implements CommandLineRunner {

	@Autowired
	BookDAO bookDAO;


	public static void main(String[] args) {
		SpringApplication.run(JpaLibraryWorkshopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		bookDAO.save(new Book(0,"1abc123","Java fundamentals",14,true,new HashSet<>()));
		bookDAO.save(new Book(0,"2abc125","Java advance",14,true,new HashSet<>()));
		bookDAO.save(new Book(0,"3abc189","Java master",14,true,new HashSet<>()));

		//bookDAO.update(new Book(3,"12456bnda","Java advance",14,true,new HashSet<>()));
		//bookDAO.deleteById(2);
		//System.out.println(bookDAO.findAll());
		//System.out.println(bookDAO.findBookByTitle("Java master"));
		System.out.println(bookDAO.findbookbyname("java advance"));

	}
}
