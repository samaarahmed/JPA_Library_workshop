package se.lexicon.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.model.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@Repository
public class AppUserDAOimpl implements AppUserDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public AppUser create(AppUser appUser) {
        if (appUser == null) {
            throw new IllegalArgumentException("appUser is null.");
        }else {
            entityManager.persist(appUser);
        }
        return appUser;
    }

    @Override
    @Transactional
    public AppUser update(AppUser appUser) {
        if (appUser.getAppUserId() > 0) {
            entityManager.merge(appUser);
        }

        return appUser;
    }

    @Override
    @Transactional
    public AppUser findById(int id) {
        AppUser appUser = entityManager.find(AppUser.class, id);
        return appUser;
    }

    @Override
    @Transactional
    public Collection<AppUser> findAll() {
        return entityManager.createQuery("SELECT a FROM AppUser a ", AppUser.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(int id) {
        AppUser found = findById(id);
        if (found.getAppUserId() != 0){
            entityManager.remove(found);
        }
    }

}
