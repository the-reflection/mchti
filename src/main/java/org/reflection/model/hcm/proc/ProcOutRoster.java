package org.reflection.model.hcm.proc;

import com.oith.annotation.MacCareless;
import org.reflection.model.com.Employee;
import org.reflection.model.hcm.tl.Period;
import org.reflection.model.hcm.tl.Roster;
import org.reflection.model.hcm.tl.Shift;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@MacCareless
@Entity
@Table(name = "PROC_OUT_ROSTER")
@XmlRootElement
public class ProcOutRoster implements Serializable {

    @EmbeddedId
    private ProcOutRosterPK procOutRosterPK;

    @NotNull
    @JoinColumn(name = "PERIOD_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Period period;

    @NotNull
    @JoinColumn(name = "ROSTER_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Roster roster;

    @NotNull
    @JoinColumn(name = "SHIFT_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shift shift;

    public ProcOutRoster() {

    }

    public ProcOutRoster(Employee employee, Date calcDate) {
        this.procOutRosterPK = new ProcOutRosterPK(employee, calcDate);
    }

    public Roster getRoster() {
        return roster;
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
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

    public ProcOutRosterPK getProcOutRosterPK() {
        return procOutRosterPK;
    }

    public void setProcOutRosterPK(ProcOutRosterPK procOutRosterPK) {
        this.procOutRosterPK = procOutRosterPK;
    }

}
