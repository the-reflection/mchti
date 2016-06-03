package mchti.model.acad;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Owner")
@XmlRootElement
public class Owner extends Person {

    private Double pct;

    public Owner() {
    }

}
