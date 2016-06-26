package org.reflection.model.security;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class AuthUserAuthQuestionPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    private AuthUser authUser;
    @ManyToOne
    private AuthQuestion authQuestion;

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    public AuthQuestion getAuthQuestion() {
        return authQuestion;
    }

    public void setAuthQuestion(AuthQuestion authQuestion) {
        this.authQuestion = authQuestion;
    }

}
