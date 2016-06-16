package org.reflection.repository;

import java.util.List;
import org.reflection.model.hcm.tl.GeneralHoliday;
import java.math.BigInteger;

public interface GeneralHolidayRepo {

    public GeneralHoliday findById(BigInteger id);

    public void save(GeneralHoliday generalHoliday);

    public List<GeneralHoliday> findAll();

}
