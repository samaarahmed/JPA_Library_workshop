package se.lexicon.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.model.AppUser;
import se.lexicon.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
@Repository
@Transactional
public class BookDAOimpl implements BookDAO{

    private final EntityManager entityManager;
    @Autowired
    public BookDAOimpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Book create(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("appUser is null.");
        }else {
            entityManager.persist(book);
        }
        return book;

    }

    @Override
    @Transactional
    public Book update(Book book) {
            if (book.getBookId() > 0) {
                entityManager.merge(book);
            }

            return book;

    }

    @Override
    @Transactional
    public Book findById(int id) {
        Book book = entityManager.find(Book.class, id);
        return book;
    }

    @Override
    @Transactional
    public Collection<Book> findAll() {

        return entityManager.createQuery("SELECT a FROM Book a ", Book.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(int id) {
        Book found = findById(id);
        if (found.getBookId() != 0){
            entityManager.remove(found);
        }

    }
}
