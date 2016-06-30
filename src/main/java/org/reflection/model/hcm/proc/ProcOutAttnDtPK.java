package org.reflection.model.hcm.proc;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.reflection.model.com.Employee;

@Embeddable
public class ProcOutAttnDtPK implements Serializable {

    @NotNull
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;
    @Column(name = "ATTN_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date attnDate;

    public ProcOutAttnDtPK() {
    }

    public ProcOutAttnDtPK(Employee employee, Date attnDate) {
        this.attnDate = attnDate;
        this.employee = employee;
    }

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

    @Override
    public String toString() {
        return "ProcOutAttnDtPK{" + "employee=" + employee + ", attnDate=" + attnDate + '}';
    }

}
