package org.reflection.model.hcm.proc;

import com.oith.annotation.MacCareless;
import org.reflection.model.com.Employee;
import org.reflection.model.hcm.enums.DtAttnType;
import org.reflection.model.hcm.tl.Shift;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.EmbeddedId;
import java.math.BigInteger;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@MacCareless
@Entity
@Table(catalog = "MCHTI", name = "PROC_OUT_ATTN_DT")
@XmlRootElement
public class ProcOutAttnDt implements Serializable {

    @EmbeddedId
    private ProcOutAttnDtPK procOutAttnDtPK;

    @Enumerated(EnumType.STRING)
    @Column(name = "DT_ATTN_TYPE", nullable = false, length = 30)
    private DtAttnType dtAttnType;
    @JoinColumn(name = "SHIFT_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shift shift;
    @Column(name = "IN_TIME")
    @Temporal(TemporalType.TIME)
    private Date inTime;
    @Column(name = "OUT_TIME")
    @Temporal(TemporalType.TIME)
    private Date outTime;
    private Double ot;
    @Column(name = "REMARKS", length = 500)
    private String remarks;

    public ProcOutAttnDt() {
    }

    public ProcOutAttnDt(Employee employee, Date attnDate) {
        procOutAttnDtPK = new ProcOutAttnDtPK(employee, attnDate);
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

    public Double getOt() {
        return ot;
    }

    public void setOt(Double ot) {
        this.ot = ot;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public DtAttnType getDtAttnType() {
        return dtAttnType;
    }

    public void setDtAttnType(DtAttnType dtAttnType) {
        this.dtAttnType = dtAttnType;
    }

    public ProcOutAttnDtPK getProcOutAttnDtPK() {
        return procOutAttnDtPK;
    }

    public void setProcOutAttnDtPK(ProcOutAttnDtPK procOutAttnDtPK) {
        this.procOutAttnDtPK = procOutAttnDtPK;
    }

    @Override
    public String toString() {
        return "ProcOutAttnDt{" + "procOutAttnDtPK=" + procOutAttnDtPK + ", dtAttnType=" + dtAttnType + ", shift=" + shift + ", inTime=" + inTime + ", outTime=" + outTime + ", ot=" + ot + ", remarks=" + remarks + '}';
    }

}
