package se.lexicon.dao;

import se.lexicon.model.Author;

import java.util.Collection;

public interface AuthorDAO {

    Author create(Author author);
    Author update(Author author);
    Author findById(int id);
    Collection<Author> findAll();
    void delete(int id);

}
