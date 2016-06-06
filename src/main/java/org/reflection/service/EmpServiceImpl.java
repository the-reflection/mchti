package org.reflection.service;

import org.reflection.model.com.Employee;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.EmpNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("empService")
@Transactional(readOnly = true)
public class EmpServiceImpl implements EmpService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmpServiceImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional(readOnly = true)
    @Override
    public Employee findByCode(String code) {
        LOGGER.debug("Finding object by code: " + code);
        return (Employee) getCurrentSession().createQuery("FROM Emp m WHERE m.code='"+code+"'").uniqueResult();
    }

    @Transactional
    @Override
    public Employee create(Employee emp) {
        LOGGER.debug("Creating a new emp with information: " + emp);
        getCurrentSession().save(emp);
        return emp;
    }
    
    @Override
    @Transactional
    public Employee findById(Long id) {
        Employee emp = (Employee) getCurrentSession().get(Employee.class, id);
        
        //Hibernate.initialize(emp.getLookupAnyTypeId());
        //Hibernate.initialize(emp.getEmpEduDtlList());

        //Hibernate.initialize(emp.getHrTlShftDtlId());
        //Hibernate.initialize(emp.getPersonEduDtlList());
        return emp;
    }
    
    @Override
    @Transactional(rollbackFor = EmpNotFoundException.class)
    public Employee delete(Long empId) throws EmpNotFoundException {
           
        Employee emp = (Employee) getCurrentSession().get(Employee.class, empId);
            
        if (emp == null) {
            throw new EmpNotFoundException();
        }
    
        getCurrentSession().delete(emp);
        return emp;
    }
    
    @Override
    @Transactional
    public List<Employee> findAll() {
        List<Employee> emps =getCurrentSession().createQuery("FROM " + Employee.class.getName()).list();
            
        for (Employee emp : emps) {
            //Hibernate.initialize(emp.getLookupAnyTypeId());
            //Hibernate.initialize(emp.getEmpEduDtlList());

            //Hibernate.initialize(emp.getHrTlShftDtlId());
            //Hibernate.initialize(emp.getPersonEduDtlList());
        }
        return emps;
    }
      
    @Transactional(rollbackFor = EmpNotFoundException.class)
    @Override
    public Employee update(Employee updated) throws EmpNotFoundException {

        Employee emp = (Employee) getCurrentSession().get(Employee.class, updated.getId());

        if (emp == null) {
            throw new EmpNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(emp, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.info("No emp found with err: " + e);
        }

        getCurrentSession().save(emp);
        return updated;
    }
    
    @Transactional
    @Override
    public List<Employee> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM Emp m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Employee> emps = (List<Employee>) query.list();
        for (Employee emp : emps) {
            //Hibernate.initialize(emp.getLookupAnyTypeId());
            //Hibernate.initialize(emp.getEmpEduDtlList());

        //Hibernate.initialize(emp.getHrTlShftDtlId());
        //Hibernate.initialize(emp.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT COUNT(m) FROM Emp m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return emps;

    //Criteria criteria = getCurrentSession().createCriteria(Employee.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<Emp> emps = (List<Emp>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<Employee> search(_SearchDTO pageable) {
        LOGGER.debug("Searching accBnks with search criteria: " + pageable);

        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM Employee m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM Emp m WHERE 1=1  AND UPPER(CONCAT(m.fullName, m.code)) LIKE CONCAT('%',:search,'%')";

        Query queryCount = session.createQuery("SELECT COUNT(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Employee> emps = (List<Employee>) query.list();

        for (Employee emp : emps) {
            //Hibernate.initialize(emp.getLookupAnyTypeId());
            //Hibernate.initialize(emp.getEmpEduDtlList());

            //Hibernate.initialize(emp.getHrTlShftDtlId());
            //Hibernate.initialize(emp.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return emps;
    }
}
