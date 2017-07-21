package com.qiwi.springboot;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by etrofimov on 18.07.17.
 */

@XmlRootElement
public class BalanceResponse {
//    private Status code;


    private int code;

    private BigDecimal balance;

    public BalanceResponse() {

    }

    public BalanceResponse(Status code) {
        this.code = code.getStatusCode();
//        this.balance = null;

        this.balance = new BigDecimal(0);
    }

    public BalanceResponse(Status code, BigDecimal balance) {
        this.code = code.getStatusCode();
        this.balance = balance.setScale(2, RoundingMode.CEILING);
    }

    public int getCode() {
        return this.code;
    }

    @XmlElement
    public void setCode(int code) {
        this.code = code;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    @XmlElement
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
