package com.qiwi.springboot;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;

/**
 * Created by etrofimov on 18.07.17.
 */
public class JaxbParser {

    public static Object getObject(Reader body, Class c) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(body);

        return object;
    }
}
