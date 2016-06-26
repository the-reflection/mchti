package org.reflection.service;

import org.reflection.model.hcm.tl.Roster;
import org.reflection.exception.RosterNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface RosterService {



    public Roster findById(BigInteger id);
    
    public Roster create(Roster roster);
    
    public Roster update(Roster roster) throws RosterNotFoundException;
    
    public Roster copy(Roster roster);
    
    public Roster delete(BigInteger id) throws RosterNotFoundException;
   
    public Iterable<Roster> search(_SearchDTO pageable);
    
    public Iterable<Roster> findAll(_SearchDTO pageable);
    
    public Iterable<Roster> findAll();
}