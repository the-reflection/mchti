package org.reflection.model.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.reflection.model.com.AbstractEntity;
import org.springframework.http.HttpMethod;

@Entity
@Table(catalog = "MCHTI", name = "AUTH_REQUEST_MAP")
@XmlRootElement
public class AuthRequestMap extends AbstractEntity {

    @Column(name = "CONFIG_ATTRIBUTE", nullable = false)
    private String configAttribute;
    @Column(name = "HTTP_METHOD")
    private HttpMethod httpMethod;
    @Column(name = "URL", unique = true, nullable = false)
    private String url;

    public AuthRequestMap() {
    }

    public AuthRequestMap(String url, String configAttribute, HttpMethod httpMethod) {

        this.configAttribute = configAttribute;
        this.httpMethod = httpMethod;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getConfigAttribute() {
        return configAttribute;
    }

    public void setConfigAttribute(String configAttribute) {
        this.configAttribute = configAttribute;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

}
