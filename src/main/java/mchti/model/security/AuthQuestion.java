package mchti.model.security;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigInteger;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "AUTH_QUESTION")
@XmlRootElement
public class AuthQuestion implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Basic(optional = false)
    private BigInteger id;
    @Version
    private Integer version;

    private String question;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.authQuestion")
    private Set<AuthUserAuthQuestion> authUserAuthQuestions;

//    @ManyToMany(mappedBy = "authQuestions")
//    private Set<AuthUser> authUsers = new HashSet<>();
    public AuthQuestion() {
    }

    public AuthQuestion(String question) {
        this.question = question;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<AuthUserAuthQuestion> getAuthUserAuthQuestions() {
        return authUserAuthQuestions;
    }

    public void setAuthUserAuthQuestions(Set<AuthUserAuthQuestion> authUserAuthQuestions) {
        this.authUserAuthQuestions = authUserAuthQuestions;
    }

}
