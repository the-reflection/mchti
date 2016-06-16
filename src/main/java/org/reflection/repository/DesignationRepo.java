package org.reflection.repository;

import java.util.List;
import org.reflection.model.hcm.cr.Designation;
import java.math.BigInteger;

public interface DesignationRepo {

    public Designation findById(BigInteger id);

    public void save(Designation designation);

    public List<Designation> findAll();

}
