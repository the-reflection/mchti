package org.reflection.model.hcm.tl;

import com.oith.annotation.MacSearchable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import org.reflection.model.com.AbstractAuditableEntity;
import org.reflection.model.com.Employee;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "MANUAL_ATTN_DAILY", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"EMPLOYEE_ID", "ATTN_DATE"})})
@XmlRootElement
public class ManualAttnDaily extends AbstractAuditableEntity { //AbstractEntity implements IAuditable{

    @NotNull
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;
    @NotNull
    @Column(name = "ATTN_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date attnDate;

    @Column(name = "IN_TIME")
    //([01]?[0-9]|2[0-3]):[0-5][0-9]
    @DateTimeFormat(pattern = "HH:mm")
    //@Pattern(regexp = "(([01]?[0-9]|2[0-3]):[0-5][0-9])|", message = "Use HH:MM format, i.e. 23:59")
    @Temporal(TemporalType.TIME)
    private Date inTime;
    @Column(name = "OUT_TIME")
    //@Pattern(regexp = "(?:[01]\\d|2[0123]):(?:[012345]\\d)|", message = "Use HH:MM format, i.e. 23:59")
    @DateTimeFormat(pattern = "HH:mm")
    @Temporal(TemporalType.TIME)
    private Date outTime;

    @MacSearchable
    @Column(name = "REMARKS", length = 500)
    private String remarks;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getAttnDate() {
        return attnDate;
    }

    public void setAttnDate(Date attnDate) {
        this.attnDate = attnDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    @Override
    public String toString() {
        return "ManualAttnDaily{" + "employee=" + employee + ", attnDate=" + attnDate + ", inTime=" + inTime + ", outTime=" + outTime + ", remarks=" + remarks + '}';
    }
}
