package se.lexicon.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.model.Author;
import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class AuthorDAOimpl implements AuthorDAO{

    private final EntityManager entityManager;


    @Autowired
    public AuthorDAOimpl(EntityManager entityManager) {
        this.entityManager=entityManager;
    }


    @Override
    @Transactional
    public Author create(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("appUser is null.");
        }else {
            entityManager.persist(author);
        }
        return author;
    }

    @Override
    @Transactional
    public Author update(Author author) {
        if (author.getAuthorID() > 0) {
            entityManager.merge(author);
        }
        return author;
    }

    @Override
    @Transactional
    public Author findById(int id) {
        Author author = entityManager.find(Author.class, id);
        return author;
    }

    @Override
    @Transactional
    public Collection<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a ", Author.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(int id) {
        Author found = findById(id);
        if (found.getAuthorID() != 0){
            entityManager.remove(found);
        }
    }
}
