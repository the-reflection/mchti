package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.hcm.tl.AssignmentTl;

@Repository("assignmentTlRepo")
public class AssignmentTlRepoImpl extends AbstractRepo<BigInteger, AssignmentTl> implements AssignmentTlRepo {

    public AssignmentTl findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<AssignmentTl> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<AssignmentTl>) crit.list();
    }

    public void save(AssignmentTl assignmentTl) {
        persist(assignmentTl);
    }
}
