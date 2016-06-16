package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.hcm.proc.ProcOutAttnDt;

@Repository("procOutAttnDtRepo")
public class ProcOutAttnDtRepoImpl extends AbstractRepo<BigInteger, ProcOutAttnDt> implements ProcOutAttnDtRepo {

    public ProcOutAttnDt findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<ProcOutAttnDt> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<ProcOutAttnDt>) crit.list();
    }

    public void save(ProcOutAttnDt procOutAttnDt) {
        persist(procOutAttnDt);
    }
}
