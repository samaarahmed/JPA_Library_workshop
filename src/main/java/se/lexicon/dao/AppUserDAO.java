package se.lexicon.dao;

import se.lexicon.model.AppUser;

import java.util.Collection;

public interface AppUserDAO {

    AppUser create(AppUser appUser);
    AppUser update(AppUser appUser);
    AppUser findById(int id);
    Collection<AppUser> findAll();
    void delete(int id);

}
