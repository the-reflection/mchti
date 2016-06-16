package org.reflection.repository;

import java.util.List;
import org.reflection.model.hcm.proc.ProcOutAttnPeriod;
import java.math.BigInteger;

public interface ProcOutAttnPeriodRepo {

    public ProcOutAttnPeriod findById(BigInteger id);

    public void save(ProcOutAttnPeriod procOutAttnPeriod);

    public List<ProcOutAttnPeriod> findAll();

}
