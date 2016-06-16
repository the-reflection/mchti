package org.reflection.repository;

import java.util.List;
import org.reflection.model.hcm.proc.ProcOutEmp;
import java.math.BigInteger;

public interface ProcOutEmpRepo {

    public ProcOutEmp findById(BigInteger id);

    public void save(ProcOutEmp procOutEmp);

    public List<ProcOutEmp> findAll();

}
