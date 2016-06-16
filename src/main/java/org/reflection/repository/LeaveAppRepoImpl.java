package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.hcm.tl.LeaveApp;

@Repository("leaveAppRepo")
public class LeaveAppRepoImpl extends AbstractRepo<BigInteger, LeaveApp> implements LeaveAppRepo {

    public LeaveApp findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<LeaveApp> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<LeaveApp>) crit.list();
    }

    public void save(LeaveApp leaveApp) {
        persist(leaveApp);
    }
}
