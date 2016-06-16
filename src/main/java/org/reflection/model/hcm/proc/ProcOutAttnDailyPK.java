
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
public class ProcOutAttnDailyPK implements Serializable {

    @JoinColumn(name = "EMPLOYEE_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TRANSACTION_TIME")
    private Date transactionTime;

    public ProcOutAttnDailyPK() {
    }

    public ProcOutAttnDailyPK(Employee employee, Date transactionTime) {
        this.transactionTime = transactionTime;
        this.employee = employee;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
