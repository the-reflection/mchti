package org.reflection.service;

import org.reflection.model.hcm.tl.Shift;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ShiftNotFoundException;
import org.reflection.repositories.ShiftRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("shiftService")
@Transactional(readOnly = true)
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;



    @Transactional
    @Override
    public Shift create(Shift lookup) {
        return shiftRepository.save(lookup);
    }

    @Override
    @Transactional
    public Shift findById(BigInteger id) {
        Shift shift=shiftRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return shift;
    }

    @Override
    @Transactional(rollbackFor = ShiftNotFoundException.class)
    public Shift delete(BigInteger id) throws ShiftNotFoundException {

        Shift shift = shiftRepository.findOne(id);

        if (shift == null) {
            throw new ShiftNotFoundException();
        }
        shiftRepository.delete(id);
        return shift;
    }

    @Override
    @Transactional
    public Iterable<Shift> findAll() {
        Iterable<Shift> shifts=shiftRepository.findAll();
        
        for (Shift shift : shifts) {

        //Hibernate.initialize(shift.getA());
        //Hibernate.initialize(shift.getZs());
        }
        
        return shifts;
    }

    @Transactional(rollbackFor = ShiftNotFoundException.class)
    @Override
    public Shift update(Shift updated) throws ShiftNotFoundException {

        Shift shift = shiftRepository.findOne(updated.getId());

        if (shift == null) {
            throw new ShiftNotFoundException();
        }

        BeanUtils.copyProperties(updated, shift);
        return shiftRepository.save(shift);
    }

    @Transactional(rollbackFor = ShiftNotFoundException.class)
    @Override
    public Shift copy(final Shift copied) {

        Shift shift = new Shift();
        BeanUtils.copyProperties(copied, shift);
        shift.setId(null);

        return shiftRepository.save(shift);
    }

    @Transactional
    @Override
    public Iterable<Shift> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<Shift> search(_SearchDTO pageable) {
        return findAll();
    }
}