package mchti.service;

import mchti.model.hcm.cr.Emp;
import mchti.exception.EmpNotFoundException;
import mchti.dto._SearchDTO;
import java.util.List;

public interface EmpService {

    public Emp findByCode(String code);

    public Emp findById(Long id);
    
    public Emp create(Emp emp);
    
    public Emp update(Emp emp) throws EmpNotFoundException;
    
    public Emp delete(Long id) throws EmpNotFoundException;
   
    public List<Emp> search(_SearchDTO pageable);
    
    public List<Emp> findAll(_SearchDTO pageable);
    
    public List<Emp> findAll();
}