package mchti.service;

import mchti.model.sample.ZxLookup;
import mchti.exception.ZxLookupNotFoundException;
import mchti.dto._SearchDTO;
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
