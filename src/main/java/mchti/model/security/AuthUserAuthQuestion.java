package mchti.model.security;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "AUTH_USER_AUTH_QUESTION")
@AssociationOverrides({
    @AssociationOverride(name = "id.authUser", joinColumns = @JoinColumn(name = "AUTH_USER_ID")),
    @AssociationOverride(name = "id.authQuestion", joinColumns = @JoinColumn(name = "AUTH_QUESTION_ID"))})
public class AuthUserAuthQuestion implements Serializable {

    @EmbeddedId
    private AuthUserAuthQuestionPK id = new AuthUserAuthQuestionPK();

    @Column(name = "answer", length = 30, nullable = false)
    private String answer;

    public AuthUserAuthQuestionPK getId() {
        return id;
    }

    public void setId(AuthUserAuthQuestionPK id) {
        this.id = id;
    }

    public AuthUser getAuthUser() {
        return getId().getAuthUser();
    }

    public void setAuthUser(AuthUser authUser) {
        getId().setAuthUser(authUser);
    }

    public AuthQuestion getAuthQuestion() {
        return getId().getAuthQuestion();
    }

    public void setAuthQuestion(AuthQuestion authQuestion) {
        getId().setAuthQuestion(authQuestion);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
