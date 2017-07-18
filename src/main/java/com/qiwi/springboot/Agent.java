package com.qiwi.springboot;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by etrofimov on 18.07.17.
 */

@Entity
@Table(name = "users")
public class Agent {

    @Id
    private String telephone;

    private String pwd;

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
