package org.reflection.model.acad;

import org.reflection.model.com.Person;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "OWNER")
@XmlRootElement
public class Owner extends Person {

    private Double pct;

    public Owner() {
    }

    public Double getPct() {
        return pct;
    }

    public void setPct(Double pct) {
        this.pct = pct;
    }

}
