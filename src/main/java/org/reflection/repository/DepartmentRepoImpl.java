package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.com.Department;

@Repository("departmentRepo")
public class DepartmentRepoImpl extends AbstractRepo<BigInteger, Department> implements DepartmentRepo {

    public Department findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<Department> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<Department>) crit.list();
    }

    public void save(Department department) {
        persist(department);
    }
}
