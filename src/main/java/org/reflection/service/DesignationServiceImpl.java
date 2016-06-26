package org.reflection.service;

import org.reflection.model.hcm.cr.Designation;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.DesignationNotFoundException;
import org.reflection.repositories.DesignationRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("designationService")
@Transactional(readOnly = true)
public class DesignationServiceImpl implements DesignationService {

    @Autowired
    private DesignationRepository designationRepository;



    @Transactional
    @Override
    public Designation create(Designation lookup) {
        return designationRepository.save(lookup);
    }

    @Override
    @Transactional
    public Designation findById(BigInteger id) {
        Designation designation=designationRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return designation;
    }

    @Override
    @Transactional(rollbackFor = DesignationNotFoundException.class)
    public Designation delete(BigInteger id) throws DesignationNotFoundException {

        Designation designation = designationRepository.findOne(id);

        if (designation == null) {
            throw new DesignationNotFoundException();
        }
        designationRepository.delete(id);
        return designation;
    }

    @Override
    @Transactional
    public Iterable<Designation> findAll() {
        Iterable<Designation> designations=designationRepository.findAll();
        
        for (Designation designation : designations) {

        //Hibernate.initialize(designation.getA());
        //Hibernate.initialize(designation.getZs());
        }
        
        return designations;
    }

    @Transactional(rollbackFor = DesignationNotFoundException.class)
    @Override
    public Designation update(Designation updated) throws DesignationNotFoundException {

        Designation designation = designationRepository.findOne(updated.getId());

        if (designation == null) {
            throw new DesignationNotFoundException();
        }

        BeanUtils.copyProperties(updated, designation);
        return designationRepository.save(designation);
    }

    @Transactional(rollbackFor = DesignationNotFoundException.class)
    @Override
    public Designation copy(final Designation copied) {

        Designation designation = new Designation();
        BeanUtils.copyProperties(copied, designation);
        designation.setId(null);

        return designationRepository.save(designation);
    }

    @Transactional
    @Override
    public Iterable<Designation> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<Designation> search(_SearchDTO pageable) {
        return findAll();
    }
}