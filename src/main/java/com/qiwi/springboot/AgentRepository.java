package com.qiwi.springboot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by etrofimov on 18.07.17.
 */
public interface AgentRepository extends JpaRepository<Agent, String> {

    @Query(value = "SELECT count(*) FROM users WHERE telephone = ? AND pwd = ?", nativeQuery = true)
    int getAgentCount(String telephone, String pwd);

}
