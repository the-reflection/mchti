package org.reflection.service;

import org.reflection.exception.LookupNotFoundException;
import org.reflection.dto._SearchDTO;
import org.reflection.model.hcm.tl.Lookup;
import java.util.List;

public interface LookupService {

    public Lookup findById(Long id);

    public Lookup create(Lookup lookup);

    public Lookup update(Lookup lookup) throws LookupNotFoundException;

    public Lookup copy(Lookup lookup);

    public Lookup delete(Long id) throws LookupNotFoundException;

    public List<Lookup> search(_SearchDTO pageable);

    public List<Lookup> findAll(_SearchDTO pageable);

    public List<Lookup> findAll();
}
