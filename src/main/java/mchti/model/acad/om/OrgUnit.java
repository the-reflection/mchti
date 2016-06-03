package mchti.model.acad.om;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Org_Unit")
@XmlRootElement
public class OrgUnit extends ObjectType {

    public OrgUnit() {
    }

}
