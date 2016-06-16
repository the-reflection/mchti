package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.hcm.tl.GeneralHoliday;

@Repository("generalHolidayRepo")
public class GeneralHolidayRepoImpl extends AbstractRepo<BigInteger, GeneralHoliday> implements GeneralHolidayRepo {

    public GeneralHoliday findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<GeneralHoliday> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<GeneralHoliday>) crit.list();
    }

    public void save(GeneralHoliday generalHoliday) {
        persist(generalHoliday);
    }
}
