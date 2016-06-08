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
public class ProcOutRosterPK implements Serializable {

    @NotNull
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;
    @Column(name = "CALC_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date calcDate;

    public ProcOutRosterPK() {
    }

    public ProcOutRosterPK(Employee employee, Date calcDate) {
        this.calcDate = calcDate;
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getCalcDate() {
        return calcDate;
    }

    public void setCalcDate(Date calcDate) {
        this.calcDate = calcDate;
    }

}
