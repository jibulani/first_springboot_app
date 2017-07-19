package com.qiwi.springboot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by etrofimov on 19.07.17.
 */
public interface AgentBalanceRepository extends JpaRepository<AgentBalance, String> {

    @Query(value = "SELECT balance FROM user_balance WHERE telephone = ?", nativeQuery = true)
    BigDecimal getBalance(String telephone);

    AgentBalance findByTelephone(String telephone);


}
