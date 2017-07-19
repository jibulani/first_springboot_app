package com.qiwi.springboot;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by etrofimov on 19.07.17.
 */

@Entity
@Table(name = "user_balance")
public class AgentBalance {

    @Id
    private String telephone;

    private BigDecimal balance;

    public AgentBalance() {

    }

    public AgentBalance(String telephone) {
        this.telephone = telephone;
        this.balance = new BigDecimal(0);
    }

    public AgentBalance(String telephone, BigDecimal balance) {
        this.telephone = telephone;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("AgentBalance[telephone='%s', balance=%d", telephone, balance);
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
