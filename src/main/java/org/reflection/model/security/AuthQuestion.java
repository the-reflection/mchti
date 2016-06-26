package org.reflection.model.security;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.reflection.model.com.AbstractEntity;

@Entity
@Table(name = "AUTH_QUESTION")
@XmlRootElement
public class AuthQuestion extends AbstractEntity {

    @Column(name = "QUESTION", length = 100, nullable = false)
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
