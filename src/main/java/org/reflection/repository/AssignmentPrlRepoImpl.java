package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.hcm.prl.AssignmentPrl;

@Repository("assignmentPrlRepo")
public class AssignmentPrlRepoImpl extends AbstractRepo<BigInteger, AssignmentPrl> implements AssignmentPrlRepo {

    public AssignmentPrl findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<AssignmentPrl> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<AssignmentPrl>) crit.list();
    }

    public void save(AssignmentPrl assignmentPrl) {
        persist(assignmentPrl);
    }
}
