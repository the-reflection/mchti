package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.hcm.tl.Shift;

@Repository("shiftRepo")
public class ShiftRepoImpl extends AbstractRepo<BigInteger, Shift> implements ShiftRepo {

    public Shift findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<Shift> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<Shift>) crit.list();
    }

    public void save(Shift shift) {
        persist(shift);
    }
}
