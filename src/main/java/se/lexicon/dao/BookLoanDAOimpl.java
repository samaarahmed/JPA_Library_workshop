package se.lexicon.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.model.BookLoan;

import javax.persistence.EntityManager;
import java.util.Collection;
@Repository
@Transactional
public class BookLoanDAOimpl implements BookLoanDAO{

    private final EntityManager entityManager;
    @Autowired
    public BookLoanDAOimpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public BookLoan create(BookLoan bookLoan) {
        if (bookLoan == null){
            throw new IllegalArgumentException("Bookloan is null.");
        }else{
            entityManager.persist(bookLoan);
        }
        return bookLoan;
    }

    @Override
    @Transactional
    public BookLoan update(BookLoan bookLoan) {
        if (bookLoan.getLoanId() > 0)
            entityManager.merge(bookLoan);
        return bookLoan;
    }

    @Override
    @Transactional
    public BookLoan findById(int id) {
        BookLoan bookLoan  = entityManager.find(BookLoan.class, id);
        return bookLoan;
    }

    @Override
    @Transactional
    public Collection<BookLoan> findAll() {
        return entityManager.createQuery("SELECT b FROM BookLoan b", BookLoan.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(int id) {
        BookLoan found = findById(id);
        if (found.getLoanId() != 0)
            entityManager.remove(found);
    }
}
