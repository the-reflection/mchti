package org.reflection.service;

import org.reflection.model.hcm.tl.Period;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.PeriodNotFoundException;
import org.reflection.repositories.PeriodRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("periodService")
@Transactional(readOnly = true)
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    private PeriodRepository periodRepository;



    @Transactional
    @Override
    public Period create(Period lookup) {
        return periodRepository.save(lookup);
    }

    @Override
    @Transactional
    public Period findById(BigInteger id) {
        Period period=periodRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return period;
    }

    @Override
    @Transactional(rollbackFor = PeriodNotFoundException.class)
    public Period delete(BigInteger id) throws PeriodNotFoundException {

        Period period = periodRepository.findOne(id);

        if (period == null) {
            throw new PeriodNotFoundException();
        }
        periodRepository.delete(id);
        return period;
    }

    @Override
    @Transactional
    public Iterable<Period> findAll() {
        Iterable<Period> periods=periodRepository.findAll();
        
        for (Period period : periods) {

        //Hibernate.initialize(period.getA());
        //Hibernate.initialize(period.getZs());
        }
        
        return periods;
    }

    @Transactional(rollbackFor = PeriodNotFoundException.class)
    @Override
    public Period update(Period updated) throws PeriodNotFoundException {

        Period period = periodRepository.findOne(updated.getId());

        if (period == null) {
            throw new PeriodNotFoundException();
        }

        BeanUtils.copyProperties(updated, period);
        return periodRepository.save(period);
    }

    @Transactional(rollbackFor = PeriodNotFoundException.class)
    @Override
    public Period copy(final Period copied) {

        Period period = new Period();
        BeanUtils.copyProperties(copied, period);
        period.setId(null);

        return periodRepository.save(period);
    }

    @Transactional
    @Override
    public Iterable<Period> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<Period> search(_SearchDTO pageable) {
        return findAll();
    }
}