package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.com.Lookup;

@Repository("lookupRepo")
public class LookupRepoImpl extends AbstractRepo<BigInteger, Lookup> implements LookupRepo {

    public Lookup findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<Lookup> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<Lookup>) crit.list();
    }

    public void save(Lookup lookup) {
        persist(lookup);
    }
}
