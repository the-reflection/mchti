package org.reflection.model.acad;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.reflection.model.com.Employee;

@Entity
@Table(name = "STAFF")
@XmlRootElement
public class Staff extends Employee {

    public Staff() {
    }
}
