package org.reflection.model.hcm.proc;

import com.oith.annotation.MacCareless;
import org.reflection.model.com.Employee;
import org.reflection.model.hcm.cr.Department;
import org.reflection.model.hcm.enums.Day;
import org.reflection.model.hcm.enums.EmpGroup;
import org.reflection.model.com.Gender;
import org.reflection.model.hcm.tl.Roster;
import org.reflection.model.hcm.tl.Shift;
import com.oith.annotation.MacImagable;
import com.oith.annotation.MacSearchable;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.math.BigInteger;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.reflection.model.com.Address;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.reflection.model.hcm.cr.Designation;

@MacCareless
@Entity
@Table(catalog = "MCHTI", name = "PROC_OUT_EMP")
@XmlRootElement
public class ProcOutEmp implements Serializable {

    //@Id
    //private BigInteger id;
    @Id
    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Version
    private Integer version;
    @MacSearchable
    @Basic(optional = false)
    @Column(name = "CODE", length = 20, nullable = false, unique = true)
    private String code;
//    @NotNull
//    @JoinColumn(name = "EMPLOYEE_ID", nullable = false, unique = true)
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    private Employee employee;

    @MacImagable
    @Column(name = "PIC_FILE", length = 255)
    private String picFile;
    @MacSearchable
    @Basic(optional = false)
    @Column(name = "FULL_NAME", length = 100, nullable = false)
    @NotEmpty
    @Size(min = 2, max = 100)
    private String fullName;
    @MacSearchable
    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 6)
    private Gender gender;
    @Temporal(TemporalType.DATE)
    @Past
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date doj;
    @MacSearchable
    @Temporal(TemporalType.DATE)
    @Past
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dob;
    @Column(name = "EMAIL", length = 30)
    @Email
    private String email;
    @Embedded
    private Address address;

    //cr
    @JoinColumn(name = "DEPARTMENT_ID", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Department department;
    @JoinColumn(name = "DESIGNATION_ID", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Designation designation;
    @MacSearchable
    @Enumerated(EnumType.STRING)
    @Column(name = "EMP_GROUP", length = 20)
    private EmpGroup empGroup;

    //tl
    @Enumerated(EnumType.STRING)
    @Column(name = "WEEKEND_SHIFT_OFF_DAY", length = 9)
    private Day weekendShiftOffDay;
    @JoinColumn(name = "SHIFT_ID", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Shift shift;
    @JoinColumn(name = "ROSTER_ID", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Roster roster;
    @Column(name = "IS_OVERTIME")
    private Boolean isOvertime;

    public ProcOutEmp() {
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

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Roster getRoster() {
        return roster;
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }

    public String getPicFile() {
        return picFile;
    }

    public void setPicFile(String picFile) {
        this.picFile = picFile;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public EmpGroup getEmpGroup() {
        return empGroup;
    }

    public void setEmpGroup(EmpGroup empGroup) {
        this.empGroup = empGroup;
    }

    public Boolean getIsOvertime() {
        return isOvertime;
    }

    public void setIsOvertime(Boolean isOvertime) {
        this.isOvertime = isOvertime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Day getWeekendShiftOffDay() {
        return weekendShiftOffDay;
    }

    public void setWeekendShiftOffDay(Day weekendShiftOffDay) {
        this.weekendShiftOffDay = weekendShiftOffDay;
    }

}
