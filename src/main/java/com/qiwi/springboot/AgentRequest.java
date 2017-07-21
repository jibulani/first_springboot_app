package com.qiwi.springboot;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by etrofimov on 18.07.17.
 */

@XmlRootElement(name = "request")
@XmlType(propOrder = {"requestType", "login", "password"})
public class AgentRequest {

    @JsonProperty("request-type")
    private String requestType;
    @JsonProperty("login")
    private String login;
    @JsonProperty("password")
    private String password;

    String getLogin() { return this.login; }

    String getPassword() { return this.password; }

    String getRequestType() { return this.requestType; }

    @XmlElement
    void setLogin(String login) { this.login = login; }

    @XmlElement
    void setPassword(String password) { this.password = password; }

    @XmlElement(name = "request-type")
    void setRequestType(String requestType) { this.requestType = requestType; }

}
