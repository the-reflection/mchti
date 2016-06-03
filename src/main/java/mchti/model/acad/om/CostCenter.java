package mchti.model.acad.om;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Cost_Center")
@XmlRootElement
public class CostCenter extends ObjectType {

}
