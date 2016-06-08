package org.reflection.model.hcm.proc;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class ProcOutCalenderPK implements Serializable {

    @Column(name = "CAL_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date calDate;

    public ProcOutCalenderPK() {
    }

    public ProcOutCalenderPK(Date calDate) {
        this.calDate = calDate;
    }

    public Date getCalDate() {
        return calDate;
    }

    public void setCalDate(Date calDate) {
        this.calDate = calDate;
    }

}
