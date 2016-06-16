package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.hcm.proc.ProcOutAttnDaily;

@Repository("procOutAttnDailyRepo")
public class ProcOutAttnDailyRepoImpl extends AbstractRepo<BigInteger, ProcOutAttnDaily> implements ProcOutAttnDailyRepo {

    public ProcOutAttnDaily findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<ProcOutAttnDaily> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<ProcOutAttnDaily>) crit.list();
    }

    public void save(ProcOutAttnDaily procOutAttnDaily) {
        persist(procOutAttnDaily);
    }
}
