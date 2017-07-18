package com.qiwi.springboot;

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
        String respMsg = "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><result-code>";
        int resultCode;

        if (agentRequest.getRequestType().equals("new-agt")) {
            resultCode = this.agentService.registerNewAgent(agentRequest).getStatusCode();
            respMsg += resultCode + "</result-code></response>";
        }

        else if (agentRequest.getRequestType().equals("agt-bal")) {
            Response response = this.agentService.getBalance(agentRequest);
            respMsg += response.getCode().getStatusCode() + "</result-code><bal>" + response.getBalance() + "</bal></response>";
        }
        else respMsg += "5</result-code></response>";
        return new ResponseEntity<String>(respMsg, HttpStatus.OK);
    }

}
