package org.reflection.model.hcm.proc;

import com.oith.annotation.MacCareless;
import org.reflection.model.hcm.enums.AttnMode;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.reflection.model.com.Employee;

@MacCareless
@Entity
@Table(catalog = "MCHTI", name = "PROC_OUT_ATTN_DAILY")
//@Table(catalog = "MCHTI", name = "PROC_OUT_ATTN_DAILY", uniqueConstraints = {
//    @UniqueConstraint(name = "emp_transaction_time_uq", columnNames = {"EMPLOYEE_ID", "TRANSACTION_TIME"})})
@XmlRootElement
public class ProcOutAttnDaily implements Serializable {

    @EmbeddedId
    private ProcOutAttnDailyPK procOutAttnDailyPK;

    @Enumerated(EnumType.STRING)
    @Column(name = "ATTN_MODE", length = 5, nullable = false)
    private AttnMode attnMode;

    @Column(name = "REMARKS", length = 500)
    private String remarks;

    public ProcOutAttnDaily() {
    }

    public ProcOutAttnDaily(Employee employee, Date transactionTime, AttnMode attnMode) {
        this.procOutAttnDailyPK = new ProcOutAttnDailyPK(employee, transactionTime);
        this.attnMode = attnMode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public AttnMode getAttnMode() {
        return attnMode;
    }

    public void setAttnMode(AttnMode attnMode) {
        this.attnMode = attnMode;
    }

    public ProcOutAttnDailyPK getProcOutAttnDailyPK() {
        return procOutAttnDailyPK;
    }

    public void setProcOutAttnDailyPK(ProcOutAttnDailyPK procOutAttnDailyPK) {
        this.procOutAttnDailyPK = procOutAttnDailyPK;
    }

    @Override
    public String toString() {
        return "ProcOutAttnDaily{" + "procOutAttnDailyPK=" + procOutAttnDailyPK + ", attnMode=" + attnMode + ", remarks=" + remarks + '}';
    }

}
