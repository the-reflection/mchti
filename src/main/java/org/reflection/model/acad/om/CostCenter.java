package org.reflection.model.acad.om;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "COST_CENTER")
@XmlRootElement
public class CostCenter extends ObjectType {

}
