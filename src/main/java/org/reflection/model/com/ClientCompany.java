package org.reflection.model.com;

import com.oith.annotation.MacCodable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Client_Company")
@XmlRootElement
@MacCodable(id = "id", code = "code", caption = "fullName")
public class ClientCompany extends Company {

    public ClientCompany() {
    }

}
