package mchti.model.hcm.cr;

import com.oith.annotation.MacSearchable;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigInteger; import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Dept")
@XmlRootElement
public class Dept implements Serializable {

    @Id
    // @SequenceGenerator(name = "HIBERNATE_SEQUENCE", sequenceName = "HIBERNATE_SEQUENCE")
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger id;
    @Version
    private Integer version;
    @Basic(optional = false)
    @Column(nullable = false, unique = true, length = 20)
    private String code;
    @MacSearchable
    @Basic(optional = false)
    @Column(name = "TITLE", nullable = false, length = 100)
    @NotNull
    @Size(min = 1, max = 100)
    @NotEmpty
    @Pattern(regexp = "[A-Za-z ]*", message = "Must contain only letters and spaces")
    private String title;
    @Column(name = "TITLE_BNG", length = 200)
    private String titleBng;
    @MacSearchable
    @Column(name = "REMARKS", length = 500)
    private String remarks;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    private Integer slNo;

    public Dept() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getSlNo() {
        return slNo;
    }

    public void setSlNo(Integer slNo) {
        this.slNo = slNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleBng() {
        return titleBng;
    }

    public void setTitleBng(String titleBng) {
        this.titleBng = titleBng;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return title;
    }
}
