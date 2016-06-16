package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.hcm.tl.Period;

@Repository("periodRepo")
public class PeriodRepoImpl extends AbstractRepo<BigInteger, Period> implements PeriodRepo {

    public Period findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<Period> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<Period>) crit.list();
    }

    public void save(Period period) {
        persist(period);
    }
}
