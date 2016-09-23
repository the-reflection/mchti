package org.reflection.model.hcm.prl;

import org.reflection.model.com.Employee;
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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(catalog = "MCHTI", name = "ASSIGNMENT_PRL")
@XmlRootElement
public class AssignmentPrl implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    private BigInteger id;
    @Version
    private Integer version;
    @MacSearchable
    @Basic(optional = false)
    @Column(name = "CODE", length = 20, nullable = false)
    private String code;
    @NotNull
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;

    @MacSearchable
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @MacSearchable
    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate;
    @Column(precision = 10, scale = 2)
    @Range(min = 1, max = 100000)
    private Double basic;
    @Column(name = "HOUSE_RENT", precision = 10, scale = 2)
    @Range(min = 1, max = 100000)
    private Double houseRent;
    @Column(precision = 10, scale = 2)
    @Range(min = 1, max = 100000)
    private Double medical;
    @Column(precision = 10, scale = 2)
    @Range(min = 1, max = 100000)
    private Double convance;
    @Column(name = "OTHER_ALLOWANCE", precision = 10, scale = 2)
    @Range(min = 1, max = 100000)
    private Double otherAllowance;
    @Column(precision = 10, scale = 2)
    @Range(min = 1, max = 100000)
    private Double gross;

    public AssignmentPrl() {
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

    public Double getBasic() {
        return basic;
    }

    public void setBasic(Double basic) {
        this.basic = basic;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Double getHouseRent() {
        return houseRent;
    }

    public void setHouseRent(Double houseRent) {
        this.houseRent = houseRent;
    }

    public Double getMedical() {
        return medical;
    }

    public void setMedical(Double medical) {
        this.medical = medical;
    }

    public Double getConvance() {
        return convance;
    }

    public void setConvance(Double convance) {
        this.convance = convance;
    }

    public Double getGross() {
        return gross;
    }

    public void setGross(Double gross) {
        this.gross = gross;
    }

    public Double getOtherAllowance() {
        return otherAllowance;
    }

    public void setOtherAllowance(Double otherAllowance) {
        this.otherAllowance = otherAllowance;
    }

}
