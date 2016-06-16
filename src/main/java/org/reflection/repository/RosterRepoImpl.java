package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.hcm.tl.Roster;

@Repository("rosterRepo")
public class RosterRepoImpl extends AbstractRepo<BigInteger, Roster> implements RosterRepo {

    public Roster findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<Roster> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<Roster>) crit.list();
    }

    public void save(Roster roster) {
        persist(roster);
    }
}
