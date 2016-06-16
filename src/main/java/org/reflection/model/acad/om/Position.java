package org.reflection.model.acad.om;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "POSITION")
@XmlRootElement
public class Position extends ObjectType {
    
}
