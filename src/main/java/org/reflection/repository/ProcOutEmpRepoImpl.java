package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.hcm.proc.ProcOutEmp;

@Repository("procOutEmpRepo")
public class ProcOutEmpRepoImpl extends AbstractRepo<BigInteger, ProcOutEmp> implements ProcOutEmpRepo {

    public ProcOutEmp findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<ProcOutEmp> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<ProcOutEmp>) crit.list();
    }

    public void save(ProcOutEmp procOutEmp) {
        persist(procOutEmp);
    }
}
