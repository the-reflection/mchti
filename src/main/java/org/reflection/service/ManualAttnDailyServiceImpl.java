package org.reflection.service;

import org.reflection.model.hcm.tl.ManualAttnDaily;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ManualAttnDailyNotFoundException;
import org.reflection.repositories.ManualAttnDailyRepository;
import java.math.BigInteger;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("manualAttnDailyService")
@Transactional(readOnly = true)
public class ManualAttnDailyServiceImpl implements ManualAttnDailyService {

    @Autowired
    private ManualAttnDailyRepository manualAttnDailyRepository;

    @Transactional
    @Override
    public ManualAttnDaily create(ManualAttnDaily lookup) {
        return manualAttnDailyRepository.save(lookup);
    }

    @Override
    @Transactional
    public ManualAttnDaily findById(BigInteger id) {
        ManualAttnDaily manualAttnDaily = manualAttnDailyRepository.findOne(id);
        Hibernate.initialize(manualAttnDaily.getEntryBy());
        Hibernate.initialize(manualAttnDaily.getEditBy());
        Hibernate.initialize(manualAttnDaily.getEmployee());

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return manualAttnDaily;
    }

    @Override
    @Transactional(rollbackFor = ManualAttnDailyNotFoundException.class)
    public ManualAttnDaily delete(BigInteger id) throws ManualAttnDailyNotFoundException {

        ManualAttnDaily manualAttnDaily = manualAttnDailyRepository.findOne(id);

        if (manualAttnDaily == null) {
            throw new ManualAttnDailyNotFoundException();
        }
        manualAttnDailyRepository.delete(id);
        return manualAttnDaily;
    }

    @Override
    @Transactional
    public Iterable<ManualAttnDaily> findAll() {
        Iterable<ManualAttnDaily> manualAttnDailys = manualAttnDailyRepository.findAll();

        for (ManualAttnDaily manualAttnDaily : manualAttnDailys) {
            Hibernate.initialize(manualAttnDaily.getEntryBy());
            Hibernate.initialize(manualAttnDaily.getEditBy());
            Hibernate.initialize(manualAttnDaily.getEmployee());

            //Hibernate.initialize(manualAttnDaily.getA());
            //Hibernate.initialize(manualAttnDaily.getZs());
        }

        return manualAttnDailys;
    }

    @Transactional(rollbackFor = ManualAttnDailyNotFoundException.class)
    @Override
    public ManualAttnDaily update(ManualAttnDaily updated) throws ManualAttnDailyNotFoundException {

        ManualAttnDaily manualAttnDaily = manualAttnDailyRepository.findOne(updated.getId());

        if (manualAttnDaily == null) {
            throw new ManualAttnDailyNotFoundException();
        }
        updated.setEntryBy(manualAttnDaily.getEntryBy());
        updated.setEntryDate(manualAttnDaily.getEntryDate());
        BeanUtils.copyProperties(updated, manualAttnDaily);

        return manualAttnDailyRepository.save(manualAttnDaily);
    }

    @Transactional(rollbackFor = ManualAttnDailyNotFoundException.class)
    @Override
    public ManualAttnDaily copy(final ManualAttnDaily copied) {

        ManualAttnDaily manualAttnDaily = new ManualAttnDaily();
        BeanUtils.copyProperties(copied, manualAttnDaily);
        manualAttnDaily.setId(null);

        return manualAttnDailyRepository.save(manualAttnDaily);
    }

    @Transactional
    @Override
    public Iterable<ManualAttnDaily> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ManualAttnDaily> search(_SearchDTO pageable) {
        return findAll();
    }
}
