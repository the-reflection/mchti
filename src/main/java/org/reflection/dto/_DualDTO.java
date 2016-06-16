package org.reflection.dto;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

public class _DualDTO implements Serializable {

    private String title;

    public _DualDTO() {
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
