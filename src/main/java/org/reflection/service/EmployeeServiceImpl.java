package org.reflection.service;

import org.reflection.model.com.Employee;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.EmployeeNotFoundException;
import org.reflection.repositories.EmployeeRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeService")
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    @Override
    public Employee findByCode(String code) {
        return employeeRepository.findByCode(code);
    }

    @Transactional
    @Override
    public Employee create(Employee lookup) {
        return employeeRepository.save(lookup);
    }

    @Override
    @Transactional
    public Employee findById(BigInteger id) {
        Employee employee=employeeRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return employee;
    }

    @Override
    @Transactional(rollbackFor = EmployeeNotFoundException.class)
    public Employee delete(BigInteger id) throws EmployeeNotFoundException {

        Employee employee = employeeRepository.findOne(id);

        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        employeeRepository.delete(id);
        return employee;
    }

    @Override
    @Transactional
    public Iterable<Employee> findAll() {
        Iterable<Employee> employees=employeeRepository.findAll();
        
        for (Employee employee : employees) {

        //Hibernate.initialize(employee.getA());
        //Hibernate.initialize(employee.getZs());
        }
        
        return employees;
    }

    @Transactional(rollbackFor = EmployeeNotFoundException.class)
    @Override
    public Employee update(Employee updated) throws EmployeeNotFoundException {

        Employee employee = employeeRepository.findOne(updated.getId());

        if (employee == null) {
            throw new EmployeeNotFoundException();
        }

        BeanUtils.copyProperties(updated, employee);
        return employeeRepository.save(employee);
    }

    @Transactional(rollbackFor = EmployeeNotFoundException.class)
    @Override
    public Employee copy(final Employee copied) {

        Employee employee = new Employee();
        BeanUtils.copyProperties(copied, employee);
        employee.setId(null);

        return employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public Iterable<Employee> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<Employee> search(_SearchDTO pageable) {
        return findAll();
    }
}