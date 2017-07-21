package com.qiwi.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * Created by etrofimov on 18.07.17.
 */
public class HashCodeGenerator {

    private static final Logger log = LoggerFactory.getLogger(AgentController.class);

    public static String getHashCode(String password) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (Exception e) {
//            e.printStackTrace();
            log.error("error occured", e);
        }
        return generatedPassword;
    }
}
