package org.reflection.repository;

import java.util.List;
import org.reflection.model.hcm.proc.ProcOutCalender;
import java.math.BigInteger;

public interface ProcOutCalenderRepo {

    public ProcOutCalender findById(BigInteger id);

    public void save(ProcOutCalender procOutCalender);

    public List<ProcOutCalender> findAll();

}
