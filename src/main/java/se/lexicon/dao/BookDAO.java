package se.lexicon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.model.Book;


public interface BookDAO extends JpaRepository<Book,Integer> {

    //Book findBookByTitle (String title);
    @Query("SELECT t from Book t where t.title = :title ")
    Book findbookbyname (@Param("title") String title);
}
