package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.hcm.proc.ProcOutCalender;

@Repository("procOutCalenderRepo")
public class ProcOutCalenderRepoImpl extends AbstractRepo<BigInteger, ProcOutCalender> implements ProcOutCalenderRepo {

    public ProcOutCalender findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<ProcOutCalender> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<ProcOutCalender>) crit.list();
    }

    public void save(ProcOutCalender procOutCalender) {
        persist(procOutCalender);
    }
}
