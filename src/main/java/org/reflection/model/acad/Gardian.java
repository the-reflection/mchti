package org.reflection.model.acad;

import org.reflection.model.com.Person;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "GARDIAN")
@XmlRootElement
public class Gardian extends Person {

    @Column(name = "CREDIT_AMOUNT", precision = 10, scale = 2)
    @Range(min = 1, max = 100000)
    private Double creditAmount;

    public Gardian() {
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

}
