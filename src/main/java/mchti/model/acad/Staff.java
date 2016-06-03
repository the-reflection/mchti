package mchti.model.acad;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Staff")
@XmlRootElement
public class Staff extends Person {

    public Staff() {
    }
}
