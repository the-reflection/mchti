package org.reflection.model.acad.om;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigInteger; import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Relationship")
@XmlRootElement
public class Relationship implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Basic(optional = false)
    private BigInteger id;
    @Version
    private Integer version;

    @JoinColumn(name = "ALPHA_ID",  nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ObjectType alpha;
    @JoinColumn(name = "BETA_ID",  nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ObjectType beta;
    @Enumerated(EnumType.STRING)
    @Column(name = "Association", length = 30)
    private Association association;

    public Relationship() {
    }

    public Relationship(ObjectType alpha, ObjectType beta) {

        validation(alpha, beta);

        this.alpha = alpha;
        this.beta = beta;
    }

    public void validation(ObjectType alpha, ObjectType beta) {

        if (alpha == null || beta == null) {
            throw new RuntimeException("can not be null.");
        }

        if (alpha instanceof OrgUnit && beta instanceof OrgUnit) {
            association = Association.BELONGS_TO;
        } else if ((alpha instanceof Position && beta instanceof OrgUnit) || (alpha instanceof OrgUnit && beta instanceof Position)) {
            association = Association.BELONGS_TO;
        } else if ((alpha instanceof CostCenter && beta instanceof OrgUnit) || (alpha instanceof OrgUnit && beta instanceof CostCenter)) {
            association = Association.ALLOCATION;
        } else if ((alpha instanceof Position && beta instanceof CostCenter) || (alpha instanceof CostCenter && beta instanceof Position)) {
            association = Association.ALLOCATION;
        } else if ((alpha instanceof Position && beta instanceof Job) || (alpha instanceof Job && beta instanceof Position)) {
            association = Association.IS_DESCRIBED_BY;
        } else if ((alpha instanceof Position && beta instanceof WorkCenter) || (alpha instanceof WorkCenter && beta instanceof Position)) {
            association = Association.BELONGS_TO;
        } else if ((alpha instanceof Task && beta instanceof Job) || (alpha instanceof Job && beta instanceof Task)) {
            association = Association.IS_DESCRIBED_BY;
        } else if ((alpha instanceof Task && beta instanceof Position) || (alpha instanceof Position && beta instanceof Task)) {
            association = Association.IS_DESCRIBED_BY;
        } else {
            throw new RuntimeException("wrong org combination.");
        }
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public ObjectType getAlpha() {
        return alpha;
    }

    public void setAlpha(ObjectType alpha) {

        if (beta != null) {
            validation(alpha, beta);
        }
        this.alpha = alpha;
    }

    public ObjectType getBeta() {
        return beta;
    }

    public void setBeta(ObjectType beta) {

        if (alpha != null) {
            validation(alpha, beta);
        }
        this.beta = beta;
    }

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }

}
