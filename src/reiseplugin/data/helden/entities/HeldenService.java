/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reiseplugin.data.helden.entities;

import helden.plugin.datenxmlplugin.DatenAustausch3Interface;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import reiseplugin.data.Held;
import reiseplugin.data.IService;

/**
 *
 * @author Luca Corbatto
 */
public class HeldenService {
    private DatenAustausch3Interface dai;
    private DocumentBuilder documentBuilder;
    private Unmarshaller unmarshaller;
    
    public HeldenService(DatenAustausch3Interface dai) {
        this.dai = dai;
        
        try {
            documentBuilder = DocumentBuilderFactory
                    .newInstance().newDocumentBuilder();
        } catch(ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        
        try {
            JAXBContext context = JAXBContext.newInstance(reiseplugin.data.helden.entities.jaxb.ObjectFactory.class);
            this.unmarshaller = context.createUnmarshaller();
        } catch(JAXBException e) {
            throw new RuntimeException(e.getMessage());
        }
        
        try {
            this.unmarshaller.setProperty("com.sun.xml.bind.ObjectFactory", new ObjectFactory());
        } catch(PropertyException e) {
            try {
                this.unmarshaller.setProperty("com.sun.xml.internal.bind.ObjectFactory", new ObjectFactory());
            } catch(PropertyException e2) {
                throw new RuntimeException(e2);
            }
        }
    }
    
    private String documentToString(Document doc) {
        DOMSource ds = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        try {
            TransformerFactory.newInstance().newTransformer().transform(ds, result);
        } catch(TransformerException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }
    
    private Object unmarshal(Document doc) {
        try {
            return this.unmarshaller.unmarshal(doc);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private Document sendRequest(Document request) {
        Object result = dai.exec(request);
		if (result == null) {
			throw new RuntimeException("Unbekannter Rückgabewert auf Request: null");
		}
		if (!(result instanceof Document)) {
			throw new RuntimeException("Unbekannter Rückgabewert auf Request: "
					+ result.getClass().getCanonicalName());
		}
        return (Document)result;
    }
    
    private Document buildRequest(String action, String... parameters) {
        assert(parameters.length % 2 == 0);
        
        Document request = documentBuilder.newDocument();
        
        Element requestElement = request.createElement("action");
		requestElement.setAttribute("action", action);
        for(int i = 0; i < parameters.length; i += 2) {
            requestElement.setAttribute(parameters[i], parameters[i+1]);
        }
        
        request.appendChild(requestElement);
        
        return request;
    }
    
    private Document buildXMLRequest(String action, String... parameters) {
        List<String> tmp = new ArrayList<>(parameters.length + 2);
        tmp.addAll(Arrays.asList(parameters));
        tmp.add("format"); tmp.add("xml");
        tmp.add("version"); tmp.add("3");
        
        return this.buildRequest(action, tmp.toArray(new String[0]));
    }
    
    public Daten getSelectedHeld() {
        Daten d = (Daten)this.unmarshal(
                this.sendRequest(
                this.buildXMLRequest("held", 
                        "id", "selected"
                )));
        return d;
    }
    
    public Daten getHeld(int i) {
        Daten d = (Daten)this.unmarshal(
                this.sendRequest(
                this.buildXMLRequest("held", 
                        "id", String.valueOf(i)
                )));
        return d;
    }
}
