package org.reflection.model.hcm.cr;

import org.reflection.model.com.Employee;
import org.reflection.model.hcm.enums.EmpGroup;
import com.oith.annotation.MacSearchable;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigInteger;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(catalog = "MCHTI", name = "ASSIGNMENT_HR")
@XmlRootElement
public class AssignmentHr implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    private BigInteger id;
    @Version
    private Integer version;
    @MacSearchable
    @Basic(optional = false)
    @Column(name = "CODE", length = 20, nullable = false)
    private String code;
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;

    @MacSearchable
    @Column(name = "START_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @MacSearchable
    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate;
    @JoinColumn(name = "DEPARTMENT_ID", nullable = true)
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private Department department;
    @JoinColumn(name = "DESIGNATION_ID", nullable = true)
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private Designation designation;

    @MacSearchable
    @Enumerated(EnumType.STRING)
    @Column(name = "EMP_GROUP", length = 20)
    private EmpGroup empGroup;

    public AssignmentHr() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public EmpGroup getEmpGroup() {
        return empGroup;
    }

    public void setEmpGroup(EmpGroup empGroup) {
        this.empGroup = empGroup;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "AssignmentHr{" + "id=" + id + ", version=" + version + ", code=" + code + ", employee=" + employee + ", startDate=" + startDate + ", endDate=" + endDate + ", department=" + department + ", designation=" + designation + ", empGroup=" + empGroup + '}';
    }

}
