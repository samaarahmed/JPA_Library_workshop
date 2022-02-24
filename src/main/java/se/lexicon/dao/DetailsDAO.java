package se.lexicon.dao;

import se.lexicon.model.Details;

import java.util.Collection;

public interface DetailsDAO {

    Details create(Details details);
    Details update (Details details);
    Details findById(int id);
    Collection<Details> findAll();
    void delete(int id);

}
