package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.com.Employee;

@Repository("employeeRepo")
public class EmployeeRepoImpl extends AbstractRepo<BigInteger, Employee> implements EmployeeRepo {

    public Employee findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<Employee> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<Employee>) crit.list();
    }

    public void save(Employee employee) {
        persist(employee);
    }
}
