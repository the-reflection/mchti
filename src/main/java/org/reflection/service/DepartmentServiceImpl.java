package org.reflection.service;

import org.reflection.model.com.Department;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.DepartmentNotFoundException;
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

@Service("departmentService")
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }



    @Transactional
    @Override
    public Department create(Department department) {
        getCurrentSession().save(department);
        return department;
    }
    
    @Override
    @Transactional
    public Department findById(BigInteger id) {
        Department department = (Department) getCurrentSession().get(Department.class, id);
        

        //Hibernate.initialize(department.getHrTlShftDtlId());
        //Hibernate.initialize(department.getPersonEduDtlList());
        return department;
    }
    
    @Override
    @Transactional(rollbackFor = DepartmentNotFoundException.class)
    public Department delete(BigInteger departmentId) throws DepartmentNotFoundException {
           
        Department department = (Department) getCurrentSession().get(Department.class, departmentId);
            
        if (department == null) {
            throw new DepartmentNotFoundException();
        }
    
        getCurrentSession().delete(department);
        return department;
    }
    
    @Override
    @Transactional
    public List<Department> findAll() {
        List<Department> departments =getCurrentSession().createQuery("FROM " + Department.class.getName()).list();
            
        for (Department department : departments) {

            //Hibernate.initialize(department.getHrTlShftDtlId());
            //Hibernate.initialize(department.getPersonEduDtlList());
        }
        return departments;
    }
      
    @Transactional(rollbackFor = DepartmentNotFoundException.class)
    @Override
    public Department update(Department updated) throws DepartmentNotFoundException {

        Department department = (Department) getCurrentSession().get(Department.class, updated.getId());

        if (department == null) {
            throw new DepartmentNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(department, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 Department update: "+ e);
        }

        getCurrentSession().save(department);
        return updated;
    }
    
    @Transactional(rollbackFor = DepartmentNotFoundException.class)
    @Override
    public Department copy(final Department copied) {
    
        Department department = new Department();//(Department) getCurrentSession().get(Department.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(department, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 Department copy: "+ e);
        }
    
        getCurrentSession().save(department);
        return department;
    }
    
    @Transactional
    @Override
    public List<Department> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM Department m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Department> departments = (List<Department>) query.list();
        for (Department department : departments) {

        //Hibernate.initialize(department.getHrTlShftDtlId());
        //Hibernate.initialize(department.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT count(m) FROM Department m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return departments;

    //Criteria criteria = getCurrentSession().createCriteria(Department.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<Department> departments = (List<Department>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<Department> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM Department m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM Department m WHERE 1=1  AND UPPER(CONCAT(m.remarks, m.title)) LIKE CONCAT('%',:search,'%')";

        Query queryCount = session.createQuery("SELECT count(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Department> departments = (List<Department>) query.list();

        for (Department department : departments) {

            //Hibernate.initialize(department.getHrTlShftDtlId());
            //Hibernate.initialize(department.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return departments;
    }
}
