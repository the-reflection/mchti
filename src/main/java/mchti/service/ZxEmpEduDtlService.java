package mchti.service;

import mchti.model.sample.ZxEmpEduDtl;
import mchti.exception.ZxEmpEduDtlNotFoundException;
import mchti.dto._SearchDTO;
import java.math.BigInteger;
import java.util.List;

public interface ZxEmpEduDtlService {

    public ZxEmpEduDtl findById(BigInteger id);

    public ZxEmpEduDtl create(ZxEmpEduDtl zxEmpEduDtl);

    public ZxEmpEduDtl update(ZxEmpEduDtl zxEmpEduDtl) throws ZxEmpEduDtlNotFoundException;

    public ZxEmpEduDtl copy(ZxEmpEduDtl zxEmpEduDtl);

    public ZxEmpEduDtl delete(BigInteger id) throws ZxEmpEduDtlNotFoundException;

    public List<ZxEmpEduDtl> search(_SearchDTO pageable);

    public List<ZxEmpEduDtl> findAll(_SearchDTO pageable);

    public List<ZxEmpEduDtl> findAll();
}
