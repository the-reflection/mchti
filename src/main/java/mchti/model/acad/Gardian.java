package mchti.model.acad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "Gardian")
@XmlRootElement
public class Gardian extends Person {

    @Column(precision = 10, scale = 2)
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
