package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.hcm.proc.ProcOutAttnPeriod;

@Repository("procOutAttnPeriodRepo")
public class ProcOutAttnPeriodRepoImpl extends AbstractRepo<BigInteger, ProcOutAttnPeriod> implements ProcOutAttnPeriodRepo {

    public ProcOutAttnPeriod findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<ProcOutAttnPeriod> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<ProcOutAttnPeriod>) crit.list();
    }

    public void save(ProcOutAttnPeriod procOutAttnPeriod) {
        persist(procOutAttnPeriod);
    }
}
