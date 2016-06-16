package org.reflection.service;

import org.reflection.model.com.Employee;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.EmployeeNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeService")
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional(readOnly = true)
    @Override
    public Employee findByCode(String code) {
        return (Employee) getCurrentSession().createQuery("FROM Employee m WHERE m.code='"+code+"'").uniqueResult();
    }

    @Transactional
    @Override
    public Employee create(Employee employee) {
        getCurrentSession().save(employee);
        return employee;
    }
    
    @Override
    @Transactional
    public Employee findById(BigInteger id) {
        Employee employee = (Employee) getCurrentSession().get(Employee.class, id);
        

        //Hibernate.initialize(employee.getHrTlShftDtlId());
        //Hibernate.initialize(employee.getPersonEduDtlList());
        return employee;
    }
    
    @Override
    @Transactional(rollbackFor = EmployeeNotFoundException.class)
    public Employee delete(BigInteger employeeId) throws EmployeeNotFoundException {
           
        Employee employee = (Employee) getCurrentSession().get(Employee.class, employeeId);
            
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
    
        getCurrentSession().delete(employee);
        return employee;
    }
    
    @Override
    @Transactional
    public List<Employee> findAll() {
        List<Employee> employees =getCurrentSession().createQuery("FROM " + Employee.class.getName()).list();
            
        for (Employee employee : employees) {

            //Hibernate.initialize(employee.getHrTlShftDtlId());
            //Hibernate.initialize(employee.getPersonEduDtlList());
        }
        return employees;
    }
      
    @Transactional(rollbackFor = EmployeeNotFoundException.class)
    @Override
    public Employee update(Employee updated) throws EmployeeNotFoundException {

        Employee employee = (Employee) getCurrentSession().get(Employee.class, updated.getId());

        if (employee == null) {
            throw new EmployeeNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(employee, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 Employee update: "+ e);
        }

        getCurrentSession().save(employee);
        return updated;
    }
    
    @Transactional(rollbackFor = EmployeeNotFoundException.class)
    @Override
    public Employee copy(final Employee copied) {
    
        Employee employee = new Employee();//(Employee) getCurrentSession().get(Employee.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(employee, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 Employee copy: "+ e);
        }
    
        getCurrentSession().save(employee);
        return employee;
    }
    
    @Transactional
    @Override
    public List<Employee> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM Employee m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Employee> employees = (List<Employee>) query.list();
        for (Employee employee : employees) {

        //Hibernate.initialize(employee.getHrTlShftDtlId());
        //Hibernate.initialize(employee.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT count(m) FROM Employee m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return employees;

    //Criteria criteria = getCurrentSession().createCriteria(Employee.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<Employee> employees = (List<Employee>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<Employee> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM Employee m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM Employee m WHERE 1=1  AND UPPER(CONCAT(m.code, m.fullName)) LIKE CONCAT('%',:search,'%')";

        Query queryCount = session.createQuery("SELECT count(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Employee> employees = (List<Employee>) query.list();

        for (Employee employee : employees) {

            //Hibernate.initialize(employee.getHrTlShftDtlId());
            //Hibernate.initialize(employee.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return employees;
    }
}
