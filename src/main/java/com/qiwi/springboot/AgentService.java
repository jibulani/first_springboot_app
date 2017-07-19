package com.qiwi.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.qiwi.springboot.Agent;
import com.qiwi.springboot.AgentRepository;

import java.math.BigDecimal;

/**
 * Created by etrofimov on 18.07.17.
 */

@Component
public class AgentService {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private AgentRepository agentRepository;

    @Autowired
    private AgentBalanceRepository agentBalanceRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;


    static boolean isRightLogin(String login) {
        if (login.length() > 11) {
            return false;
        }
        for (int i = 0; i < login.length(); i++) {
            if (!Character.isDigit(login.charAt(i))) return false;
        }
        return true;
    }

    static boolean isLoginExists(String login) {
        if ((login != null) && (login.length() > 0)) return true;
        return false;
    }

    static boolean isPasswordExists(String password) {
        if ((password != null) && (password.length() > 0)) return true;
        return false;
    }

    Status registerNewAgent(AgentRequest agentRequest) {
        String login = agentRequest.getLogin();
        if (!isLoginExists(login) || !isRightLogin(login)) {
            return Status.WRONG_FORMAT;
        }

        String password = agentRequest.getPassword();
        if (!isPasswordExists(password) || (password.length() < 8) || (password.length() > 20)) {
            return Status.BAD_PASSWORD;
        }

        Agent agent = new Agent();
        agent.setTelephone(agentRequest.getLogin());
        agent.setPwd(HashCodeGenerator.getHashCode(agentRequest.getPassword()));
        if (agentRepository.exists(agentRequest.getLogin())) return Status.EXISTS;
        agentRepository.save(agent);

        AgentBalance agentBalance = new AgentBalance();
        agentBalance.setTelephone(agentRequest.getLogin());
        agentBalance.setBalance(new BigDecimal(0));
        agentBalanceRepository.save(agentBalance);

        return Status.OK;

    }

    Response getBalance(AgentRequest agentRequest) {
        String login = agentRequest.getLogin();
        if (!isLoginExists(login) || !isRightLogin(login)) {
            return new Response(Status.WRONG_FORMAT);
        }

        String password = agentRequest.getPassword();
        if (!isPasswordExists(password)) {
            return new Response(Status.BAD_PASSWORD);
        }

        int countAgent = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM users WHERE telephone = ? AND pwd = ?", Integer.class,
                agentRequest.getLogin(), HashCodeGenerator.getHashCode(agentRequest.getPassword())
        );

        if (countAgent == 0) return new Response(Status.NOT_EXISTS);

        BigDecimal balance = jdbcTemplate.queryForObject(
                "SELECT balance FROM user_balance WHERE telephone = ?", BigDecimal.class,
                agentRequest.getLogin()
        );

        return new Response(Status.OK, balance);

//        return ClientDaoSingleton.getInstance().getUserBalance(agentRequest);
    }
}
