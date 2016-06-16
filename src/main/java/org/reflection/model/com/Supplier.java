package org.reflection.model.com;

import com.oith.annotation.MacCodable;
import com.oith.annotation.MacSearchable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "SUPPLIER")
@XmlRootElement
@MacCodable(id = "id", code = "code", caption = "fullName")
public class Supplier extends Person {

    public Supplier() {
    }

}
