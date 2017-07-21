package com.qiwi.springboot;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created by etrofimov on 18.07.17.
 */


@RestController
@RequestMapping("/")
public class AgentController {

    private final AgentService agentService;

    private static final Logger log = LoggerFactory.getLogger(AgentController.class);

    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> home() {
        return new ResponseEntity<String>("Home page", HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE}, consumes={"application/xml"})
    public @ResponseBody BalanceResponse addUserXml(@RequestBody AgentRequest agentRequest) {
        log.info(agentRequest.getRequestType() + " POST-request with login=" + agentRequest.getLogin() + " and password=" + agentRequest.getPassword());
        if (agentRequest.getRequestType().equals("new-agt")) {
            Status status = this.agentService.registerNewAgent(agentRequest);
            log.info("Returned response with result-code=" + status.getStatusCode());
            return new BalanceResponse(status);
        }

        else if (agentRequest.getRequestType().equals("agt-bal")) {
            BalanceResponse balanceResponse = this.agentService.getBalance(agentRequest);
            log.info("Returned balanceResponse with result-code=" + balanceResponse.getCode());
            return balanceResponse;
        }
        else {
            log.info("Returned response with result-code=5");
            return new BalanceResponse(Status.OTHER);
        }
    }

    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE},  consumes={"application/json"})
    public @ResponseBody BalanceResponse addUserJson(@RequestBody AgentRequest agentRequest) {
        log.info(agentRequest.getRequestType() + " POST-request with login=" + agentRequest.getLogin() + " and password=" + agentRequest.getPassword());
        if (agentRequest.getRequestType().equals("new-agt")) {
            Status status = this.agentService.registerNewAgent(agentRequest);
            log.info("Returned response with result-code=" + status.getStatusCode());
            return new BalanceResponse(status);
        }

        else if (agentRequest.getRequestType().equals("agt-bal")) {
            BalanceResponse balanceResponse = this.agentService.getBalance(agentRequest);
            log.info("Returned balanceResponse with result-code=" + balanceResponse.getCode());
            return balanceResponse;
        }
        else {
            log.info("Returned response with result-code=5");
            return new BalanceResponse(Status.OTHER);
        }
    }

}
