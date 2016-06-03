package mchti.service;

import mchti.exception.LookupNotFoundException;
import mchti.dto._SearchDTO;
import mchti.model.hcm.tl.Lookup;
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
