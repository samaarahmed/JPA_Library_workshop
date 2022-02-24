package se.lexicon.dao;

import se.lexicon.model.BookLoan;

import java.util.Collection;

public interface BookLoanDAO {

    BookLoan create(BookLoan BookLoan);
    BookLoan update(BookLoan BookLoan);
    BookLoan findById(int id);
    Collection<BookLoan> findAll();
    void delete(int id);


}
