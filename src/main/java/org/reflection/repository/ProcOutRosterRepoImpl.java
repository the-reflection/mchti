package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.hcm.proc.ProcOutRoster;

@Repository("procOutRosterRepo")
public class ProcOutRosterRepoImpl extends AbstractRepo<BigInteger, ProcOutRoster> implements ProcOutRosterRepo {

    public ProcOutRoster findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<ProcOutRoster> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<ProcOutRoster>) crit.list();
    }

    public void save(ProcOutRoster procOutRoster) {
        persist(procOutRoster);
    }
}
