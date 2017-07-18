package com.qiwi.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import java.io.StringReader;

/**
 * Created by etrofimov on 18.07.17.
 */

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class App {

    @RequestMapping(method = RequestMethod.GET)
    String home() {
        return "Hello World!";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addUser(@RequestBody String body) {
        String respMsg = "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><result-code>";
        try {
            AgentRequest agentRequest = (AgentRequest) JaxbParser.getObject(new StringReader(body), AgentRequest.class);
            int resultCode;

            if (agentRequest.getRequestType().equals("new-agt")) {
                respMsg += "new agent added</result-code></response>";
            }

            else if (agentRequest.getRequestType().equals("agt-bal")) {
                respMsg += "get agent balance</result-code></response>";
            }
        }
        catch (Exception e) {
            respMsg += "error</result-code></response>";
        }
        return respMsg;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
