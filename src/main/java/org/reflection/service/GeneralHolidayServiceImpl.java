package org.reflection.service;

import org.reflection.model.hcm.tl.GeneralHoliday;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.GeneralHolidayNotFoundException;
import org.reflection.repositories.GeneralHolidayRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("generalHolidayService")
@Transactional(readOnly = true)
public class GeneralHolidayServiceImpl implements GeneralHolidayService {

    @Autowired
    private GeneralHolidayRepository generalHolidayRepository;



    @Transactional
    @Override
    public GeneralHoliday create(GeneralHoliday lookup) {
        return generalHolidayRepository.save(lookup);
    }

    @Override
    @Transactional
    public GeneralHoliday findById(BigInteger id) {
        GeneralHoliday generalHoliday=generalHolidayRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return generalHoliday;
    }

    @Override
    @Transactional(rollbackFor = GeneralHolidayNotFoundException.class)
    public GeneralHoliday delete(BigInteger id) throws GeneralHolidayNotFoundException {

        GeneralHoliday generalHoliday = generalHolidayRepository.findOne(id);

        if (generalHoliday == null) {
            throw new GeneralHolidayNotFoundException();
        }
        generalHolidayRepository.delete(id);
        return generalHoliday;
    }

    @Override
    @Transactional
    public Iterable<GeneralHoliday> findAll() {
        Iterable<GeneralHoliday> generalHolidays=generalHolidayRepository.findAll();
        
        for (GeneralHoliday generalHoliday : generalHolidays) {

        //Hibernate.initialize(generalHoliday.getA());
        //Hibernate.initialize(generalHoliday.getZs());
        }
        
        return generalHolidays;
    }

    @Transactional(rollbackFor = GeneralHolidayNotFoundException.class)
    @Override
    public GeneralHoliday update(GeneralHoliday updated) throws GeneralHolidayNotFoundException {

        GeneralHoliday generalHoliday = generalHolidayRepository.findOne(updated.getId());

        if (generalHoliday == null) {
            throw new GeneralHolidayNotFoundException();
        }

        BeanUtils.copyProperties(updated, generalHoliday);
        return generalHolidayRepository.save(generalHoliday);
    }

    @Transactional(rollbackFor = GeneralHolidayNotFoundException.class)
    @Override
    public GeneralHoliday copy(final GeneralHoliday copied) {

        GeneralHoliday generalHoliday = new GeneralHoliday();
        BeanUtils.copyProperties(copied, generalHoliday);
        generalHoliday.setId(null);

        return generalHolidayRepository.save(generalHoliday);
    }

    @Transactional
    @Override
    public Iterable<GeneralHoliday> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<GeneralHoliday> search(_SearchDTO pageable) {
        return findAll();
    }
}