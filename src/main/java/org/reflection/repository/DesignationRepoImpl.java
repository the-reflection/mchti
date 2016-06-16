package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.hcm.cr.Designation;

@Repository("designationRepo")
public class DesignationRepoImpl extends AbstractRepo<BigInteger, Designation> implements DesignationRepo {

    public Designation findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<Designation> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<Designation>) crit.list();
    }

    public void save(Designation designation) {
        persist(designation);
    }
}
