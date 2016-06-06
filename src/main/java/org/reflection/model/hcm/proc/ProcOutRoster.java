package org.reflection.model.hcm.proc;

import org.reflection.model.com.Employee;
import org.reflection.model.hcm.tl.Period;
import org.reflection.model.hcm.tl.Roster;
import org.reflection.model.hcm.tl.Shift;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PROC_OUT_ROSTER")
@XmlRootElement
public class ProcOutRoster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName="HIBERNATE_SEQUENCE")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")
    @Basic(optional = false)
    private BigInteger id;
    @NotNull
    @JoinColumn(name = "EMPLOYEE_ID",  nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;
    @NotNull
    @JoinColumn(name = "PERIOD_ID",  nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Period period;

    @NotNull
    @JoinColumn(name = "ROSTER_ID",  nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Roster roster;

    @NotNull
    @JoinColumn(name = "SHIFT_ID",  nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shift shift;

    @NotNull
    @Column(name = "CALC_DATE")
    @Temporal(TemporalType.DATE)
    private Date calcDate;

    public Roster getRoster() {
        return roster;
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

  

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Date getCalcDate() {
        return calcDate;
    }

    public void setCalcDate(Date calcDate) {
        this.calcDate = calcDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
