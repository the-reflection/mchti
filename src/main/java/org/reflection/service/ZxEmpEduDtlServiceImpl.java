package org.reflection.service;

import org.reflection.model.sample.ZxEmpEduDtl;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxEmpEduDtlNotFoundException;
import org.reflection.repositories.ZxEmpEduDtlRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("zxEmpEduDtlService")
@Transactional(readOnly = true)
public class ZxEmpEduDtlServiceImpl implements ZxEmpEduDtlService {

    @Autowired
    private ZxEmpEduDtlRepository zxEmpEduDtlRepository;



    @Transactional
    @Override
    public ZxEmpEduDtl create(ZxEmpEduDtl lookup) {
        return zxEmpEduDtlRepository.save(lookup);
    }

    @Override
    @Transactional
    public ZxEmpEduDtl findById(BigInteger id) {
        ZxEmpEduDtl zxEmpEduDtl=zxEmpEduDtlRepository.findOne(id);
            Hibernate.initialize(zxEmpEduDtl.getZxEmp());

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return zxEmpEduDtl;
    }

    @Override
    @Transactional(rollbackFor = ZxEmpEduDtlNotFoundException.class)
    public ZxEmpEduDtl delete(BigInteger id) throws ZxEmpEduDtlNotFoundException {

        ZxEmpEduDtl zxEmpEduDtl = zxEmpEduDtlRepository.findOne(id);

        if (zxEmpEduDtl == null) {
            throw new ZxEmpEduDtlNotFoundException();
        }
        zxEmpEduDtlRepository.delete(id);
        return zxEmpEduDtl;
    }

    @Override
    @Transactional
    public Iterable<ZxEmpEduDtl> findAll() {
        Iterable<ZxEmpEduDtl> zxEmpEduDtls=zxEmpEduDtlRepository.findAll();
        
        for (ZxEmpEduDtl zxEmpEduDtl : zxEmpEduDtls) {
            Hibernate.initialize(zxEmpEduDtl.getZxEmp());

        //Hibernate.initialize(zxEmpEduDtl.getA());
        //Hibernate.initialize(zxEmpEduDtl.getZs());
        }
        
        return zxEmpEduDtls;
    }

    @Transactional(rollbackFor = ZxEmpEduDtlNotFoundException.class)
    @Override
    public ZxEmpEduDtl update(ZxEmpEduDtl updated) throws ZxEmpEduDtlNotFoundException {

        ZxEmpEduDtl zxEmpEduDtl = zxEmpEduDtlRepository.findOne(updated.getId());

        if (zxEmpEduDtl == null) {
            throw new ZxEmpEduDtlNotFoundException();
        }

        BeanUtils.copyProperties(updated, zxEmpEduDtl);
        return zxEmpEduDtlRepository.save(zxEmpEduDtl);
    }

    @Transactional(rollbackFor = ZxEmpEduDtlNotFoundException.class)
    @Override
    public ZxEmpEduDtl copy(final ZxEmpEduDtl copied) {

        ZxEmpEduDtl zxEmpEduDtl = new ZxEmpEduDtl();
        BeanUtils.copyProperties(copied, zxEmpEduDtl);
        zxEmpEduDtl.setId(null);

        return zxEmpEduDtlRepository.save(zxEmpEduDtl);
    }

    @Transactional
    @Override
    public Iterable<ZxEmpEduDtl> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ZxEmpEduDtl> search(_SearchDTO pageable) {
        return findAll();
    }
}