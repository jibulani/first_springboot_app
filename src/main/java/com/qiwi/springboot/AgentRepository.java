package com.qiwi.springboot;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by etrofimov on 18.07.17.
 */
public interface AgentRepository extends CrudRepository<Agent, String> {

}
