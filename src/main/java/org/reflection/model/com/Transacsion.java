package org.reflection.model.com;

import com.oith.annotation.MacSearchable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Transacsion")
@XmlRootElement
public class Transacsion extends AbstractEntity {

    @MacSearchable
    @Temporal(TemporalType.DATE)
    @Past
    @Column(name = "trans_Date")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date transDate;

    private Double amount;

    @Column(name = "remarks", length = 30)
    private String remarks;

    public Transacsion() {
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transactionDate) {
        this.transDate = transactionDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
