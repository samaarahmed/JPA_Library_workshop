package se.lexicon.dao;

import se.lexicon.model.Book;
import java.util.Collection;

public interface BookDAO {

    Book create(Book book);
    Book update(Book book);
    Book findById(int id);
    Collection<Book> findAll();
    void delete(int id);

}
