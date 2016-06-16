package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.hcm.cr.AssignmentHr;

@Repository("assignmentHrRepo")
public class AssignmentHrRepoImpl extends AbstractRepo<BigInteger, AssignmentHr> implements AssignmentHrRepo {

    @Override
    public AssignmentHr findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<AssignmentHr> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<AssignmentHr>) crit.list();
    }

    public void save(AssignmentHr assignmentHr) {
        persist(assignmentHr);
    }
}
