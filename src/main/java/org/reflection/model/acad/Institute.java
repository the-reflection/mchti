package org.reflection.model.acad;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.reflection.model.com.Company;

@Entity
@Table(name = "INSTITUTE")
@XmlRootElement
public class Institute extends Company{
    public Institute() {
    }
}
