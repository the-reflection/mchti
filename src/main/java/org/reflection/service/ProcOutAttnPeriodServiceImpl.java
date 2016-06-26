package org.reflection.service;

import org.reflection.model.hcm.proc.ProcOutAttnPeriod;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ProcOutAttnPeriodNotFoundException;
import org.reflection.repositories.ProcOutAttnPeriodRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("procOutAttnPeriodService")
@Transactional(readOnly = true)
public class ProcOutAttnPeriodServiceImpl implements ProcOutAttnPeriodService {

    @Autowired
    private ProcOutAttnPeriodRepository procOutAttnPeriodRepository;



    @Transactional
    @Override
    public ProcOutAttnPeriod create(ProcOutAttnPeriod lookup) {
        return procOutAttnPeriodRepository.save(lookup);
    }

    @Override
    @Transactional
    public ProcOutAttnPeriod findById(BigInteger id) {
        ProcOutAttnPeriod procOutAttnPeriod=procOutAttnPeriodRepository.findOne(id);
            Hibernate.initialize(procOutAttnPeriod.getPeriod());
            Hibernate.initialize(procOutAttnPeriod.getEmployee());

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return procOutAttnPeriod;
    }

    @Override
    @Transactional(rollbackFor = ProcOutAttnPeriodNotFoundException.class)
    public ProcOutAttnPeriod delete(BigInteger id) throws ProcOutAttnPeriodNotFoundException {

        ProcOutAttnPeriod procOutAttnPeriod = procOutAttnPeriodRepository.findOne(id);

        if (procOutAttnPeriod == null) {
            throw new ProcOutAttnPeriodNotFoundException();
        }
        procOutAttnPeriodRepository.delete(id);
        return procOutAttnPeriod;
    }

    @Override
    @Transactional
    public Iterable<ProcOutAttnPeriod> findAll() {
        Iterable<ProcOutAttnPeriod> procOutAttnPeriods=procOutAttnPeriodRepository.findAll();
        
        for (ProcOutAttnPeriod procOutAttnPeriod : procOutAttnPeriods) {
            Hibernate.initialize(procOutAttnPeriod.getPeriod());
            Hibernate.initialize(procOutAttnPeriod.getEmployee());

        //Hibernate.initialize(procOutAttnPeriod.getA());
        //Hibernate.initialize(procOutAttnPeriod.getZs());
        }
        
        return procOutAttnPeriods;
    }

    @Transactional(rollbackFor = ProcOutAttnPeriodNotFoundException.class)
    @Override
    public ProcOutAttnPeriod update(ProcOutAttnPeriod updated) throws ProcOutAttnPeriodNotFoundException {

        ProcOutAttnPeriod procOutAttnPeriod = procOutAttnPeriodRepository.findOne(updated.getId());

        if (procOutAttnPeriod == null) {
            throw new ProcOutAttnPeriodNotFoundException();
        }

        BeanUtils.copyProperties(updated, procOutAttnPeriod);
        return procOutAttnPeriodRepository.save(procOutAttnPeriod);
    }

    @Transactional(rollbackFor = ProcOutAttnPeriodNotFoundException.class)
    @Override
    public ProcOutAttnPeriod copy(final ProcOutAttnPeriod copied) {

        ProcOutAttnPeriod procOutAttnPeriod = new ProcOutAttnPeriod();
        BeanUtils.copyProperties(copied, procOutAttnPeriod);
        procOutAttnPeriod.setId(null);

        return procOutAttnPeriodRepository.save(procOutAttnPeriod);
    }

    @Transactional
    @Override
    public Iterable<ProcOutAttnPeriod> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ProcOutAttnPeriod> search(_SearchDTO pageable) {
        return findAll();
    }
}