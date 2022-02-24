package se.lexicon.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.model.AppUser;
import se.lexicon.model.Details;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Optional;

@Repository
@Transactional
public class DetailsDAOimpl implements DetailsDAO{

    private final EntityManager entityManager;

    @Autowired
    public DetailsDAOimpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Details create(Details details) {
        if(details == null) throw new IllegalArgumentException("Entity was null");
        if(details.getDetailsId() == 0) {
            entityManager.persist(details);
        }
        return details;
    }

    @Override
    @Transactional
    public Details update(Details details) {
        if(details.getDetailsId()>0) {
            entityManager.merge(details);
        }

        return details;

    }

    @Override
    @Transactional
    public Details findById(int id) {
       Details details = entityManager.find(Details.class,id);
        return details;
    }

    @Override
    @Transactional
    public Collection<Details> findAll() {

        return entityManager.createQuery("SELECT d FROM Details d", Details.class).getResultList();

    }

    @Override
    @Transactional
    public void delete(int id) {

        Details found = findById(id);
        if (found.getDetailsId() != 0){
            entityManager.remove(found);

        }
        }
}
