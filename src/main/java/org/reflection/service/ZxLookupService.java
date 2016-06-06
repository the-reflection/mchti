package org.reflection.service;

import org.reflection.model.sample.ZxLookup;
import org.reflection.exception.ZxLookupNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;
import java.util.List;

public interface ZxLookupService {

    public ZxLookup findById(BigInteger id);

    public ZxLookup create(ZxLookup zxLookup);

    public ZxLookup update(ZxLookup zxLookup) throws ZxLookupNotFoundException;

    public ZxLookup copy(ZxLookup zxLookup);

    public ZxLookup delete(BigInteger id) throws ZxLookupNotFoundException;

    public List<ZxLookup> search(_SearchDTO pageable);

    public List<ZxLookup> findAll(_SearchDTO pageable);

    public List<ZxLookup> findAll();
}
