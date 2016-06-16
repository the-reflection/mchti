package org.reflection.repository;

import java.util.List;
import org.reflection.model.hcm.tl.Period;
import java.math.BigInteger;

public interface PeriodRepo {

    public Period findById(BigInteger id);

    public void save(Period period);

    public List<Period> findAll();

}
