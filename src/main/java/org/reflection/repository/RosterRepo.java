package org.reflection.repository;

import java.util.List;
import org.reflection.model.hcm.tl.Roster;
import java.math.BigInteger;

public interface RosterRepo {

    public Roster findById(BigInteger id);

    public void save(Roster roster);

    public List<Roster> findAll();

}
