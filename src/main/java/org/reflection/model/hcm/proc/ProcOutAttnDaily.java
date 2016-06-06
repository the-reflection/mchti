package org.reflection.model.hcm.proc;

import org.reflection.model.hcm.enums.AttnMode;
import org.reflection.model.com.Employee;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigInteger;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PROC_OUT_ATTN_DAILY", uniqueConstraints = {
    @UniqueConstraint(name = "emp_transaction_time_uq", columnNames = {"EMPLOYEE_ID", "TRANSACTION_TIME"})})
@XmlRootElement
public class ProcOutAttnDaily implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    //    @SequenceGenerator(name = "HIBERNATE_SEQUENCE", sequenceName = "HIBERNATE_SEQUENCE")
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")
    //    @GeneratedValue(strategy = GenerationType.TABLE)
    //    @GeneratedValue(strategy = GenerationType.AUTO)
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger id;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TRANSACTION_TIME")
    private Date transactionTime;
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;
    @Enumerated(EnumType.STRING)
    @Column(name = "ATTN_MODE", length = 5, nullable = false)
    private AttnMode attnMode;

    @Column(name = "REMARKS", length = 500)
    private String remarks;

    public ProcOutAttnDaily() {
    }

    public ProcOutAttnDaily(Employee employee, Date transactionTime, AttnMode attnMode) {
        this.transactionTime = transactionTime;
        this.employee = employee;
        this.attnMode = attnMode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public AttnMode getAttnMode() {
        return attnMode;
    }

    public void setAttnMode(AttnMode attnMode) {
        this.attnMode = attnMode;
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

    @Override
    public String toString() {
        return "ProcOutAttnDaily{" + "id=" + id + ", transactionTime=" + transactionTime + ", employee=" + employee + ", attnMode=" + attnMode + ", remarks=" + remarks + '}';
    }

}
