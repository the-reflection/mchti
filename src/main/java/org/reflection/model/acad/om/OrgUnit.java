package org.reflection.model.acad.om;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ORG_UNIT")
@XmlRootElement
public class OrgUnit extends ObjectType {

    public OrgUnit() {
    }

}
