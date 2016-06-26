package org.reflection.service;

import org.reflection.model.com.Department;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.DepartmentNotFoundException;
import org.reflection.repositories.DepartmentRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("departmentService")
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;



    @Transactional
    @Override
    public Department create(Department lookup) {
        return departmentRepository.save(lookup);
    }

    @Override
    @Transactional
    public Department findById(BigInteger id) {
        Department department=departmentRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return department;
    }

    @Override
    @Transactional(rollbackFor = DepartmentNotFoundException.class)
    public Department delete(BigInteger id) throws DepartmentNotFoundException {

        Department department = departmentRepository.findOne(id);

        if (department == null) {
            throw new DepartmentNotFoundException();
        }
        departmentRepository.delete(id);
        return department;
    }

    @Override
    @Transactional
    public Iterable<Department> findAll() {
        Iterable<Department> departments=departmentRepository.findAll();
        
        for (Department department : departments) {

        //Hibernate.initialize(department.getA());
        //Hibernate.initialize(department.getZs());
        }
        
        return departments;
    }

    @Transactional(rollbackFor = DepartmentNotFoundException.class)
    @Override
    public Department update(Department updated) throws DepartmentNotFoundException {

        Department department = departmentRepository.findOne(updated.getId());

        if (department == null) {
            throw new DepartmentNotFoundException();
        }

        BeanUtils.copyProperties(updated, department);
        return departmentRepository.save(department);
    }

    @Transactional(rollbackFor = DepartmentNotFoundException.class)
    @Override
    public Department copy(final Department copied) {

        Department department = new Department();
        BeanUtils.copyProperties(copied, department);
        department.setId(null);

        return departmentRepository.save(department);
    }

    @Transactional
    @Override
    public Iterable<Department> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<Department> search(_SearchDTO pageable) {
        return findAll();
    }
}