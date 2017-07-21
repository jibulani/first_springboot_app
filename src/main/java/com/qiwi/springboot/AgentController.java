package com.qiwi.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody AgentRequest agentRequest) {
        log.info(agentRequest.getRequestType() + " POST-request with login=" + agentRequest.getLogin() + " and password=" + agentRequest.getPassword());
        String respMsg = "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><result-code>";
        int resultCode;

        if (agentRequest.getRequestType().equals("new-agt")) {
            resultCode = this.agentService.registerNewAgent(agentRequest).getStatusCode();
            respMsg += resultCode + "</result-code></response>";
            log.info("Returned response with result-code=" + resultCode);
        }

        else if (agentRequest.getRequestType().equals("agt-bal")) {
            Response response = this.agentService.getBalance(agentRequest);
            respMsg += response.getCode().getStatusCode() + "</result-code><bal>" + response.getBalance() + "</bal></response>";
            log.info("Returned response with result-code=" + response.getCode().getStatusCode());
        }
        else {
            respMsg += "5</result-code></response>";
            log.info("Returned response with result-code=5");
        }
        return new ResponseEntity<String>(respMsg, HttpStatus.OK);
    }

}
