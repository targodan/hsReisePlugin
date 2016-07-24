/*
 * Copyright (C) 2016 Luca Corbatto
 *
 * This file is part of the hsReisePlugin.
 *
 * The hsReisePlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The hsReisePlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package reiseplugin.data.helden.entities;

import helden.plugin.datenxmlplugin.DatenAustausch3Interface;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import reiseplugin.data.helden.entities.jaxb.Action;
import reiseplugin.data.helden.entities.jaxb.ActionType;
import reiseplugin.data.helden.entities.jaxb.Format;
import reiseplugin.data.helden.entities.jaxb.Result;

/**
 * The class which implements the communication with the HeldenSoftware.
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
 */
public class HeldenService extends Observable implements ChangeListener {
    private DatenAustausch3Interface dai;
    private DocumentBuilder documentBuilder;
    private Unmarshaller unmarshaller;
    private Marshaller marshaller;
    
    /**
     * Creates a new HeldenService using the given interface.
     * @param dai The interface.
     */
    public HeldenService(DatenAustausch3Interface dai) {
        this.dai = dai;
        this.dai.addChangeListener(this);
        
        try {
            this.documentBuilder = DocumentBuilderFactory
                    .newInstance().newDocumentBuilder();
        } catch(ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        
        try {
            JAXBContext context = JAXBContext.newInstance(reiseplugin.data.helden.entities.jaxb.ObjectFactory.class);
            this.unmarshaller = context.createUnmarshaller();
            this.marshaller = context.createMarshaller();
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
    
    /**
     * Converts a {@link org.w3c.dom.Document Document} to {@link java.lang.String String}.
     * Used only for testing purposes.
     * @param doc The {@link org.w3c.dom.Document Document} that is to be converted.
     * @return A {@link java.lang.String String} representation of the XML-Document. 
     */
    protected String documentToString(Document doc) {
        DOMSource ds = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            t.transform(ds, result);
        } catch(TransformerException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }
    
    /**
     * Unmarshalls a {@link org.w3c.dom.Document Document}.
     * @param doc The {@link org.w3c.dom.Document Document}.
     * @return The unmashalled {@link java.lang.Object Object}.
     */
    protected Object unmarshal(Document doc) {
        try {
            return this.unmarshaller.unmarshal(doc);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Marshalls an {@link java.lang.Object Object}.
     * @param obj The {@link java.lang.Object Object}.
     * @return The mashalled {@link org.w3c.dom.Document Document}.
     */
    protected Document marshal(Object obj) {
        StringWriter writer = new StringWriter();
        try {
            this.marshaller.marshal(obj, writer);
            return this.documentBuilder.parse(new ByteArrayInputStream(writer.toString().getBytes(StandardCharsets.UTF_8)));
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Sends a request {@link org.w3c.dom.Document Document} to the HeldenSoftware.
     * @param request A request {@link org.w3c.dom.Document Document}
     * @return The resulting {@link org.w3c.dom.Document Document}.
     */
    protected Document sendRequest(Document request) {
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
    
    /**
     * Sends a request to the HeldenSoftware.
     * @param request An {@code Action} representing the request
     * @return The result can either be an instance of {@code Result} or {@code Daten}.
     */
    protected Object sendRequest(Action request) {
        return this.unmarshal(this.sendRequest(this.marshal(request)));
    }
    
    /**
     * Comfortably build a new request {@link org.w3c.dom.Document Document}.
     * 
     * @deprecated Requests are now handled via JAXB generated classes and marshalling.
     * 
     * @param action The action.
     * @param parameters The parameters of the action. In the for of name followed by the value.
     * @return The resulting request {@link org.w3c.dom.Document Document}.
     */
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
    
    /**
     * Comfortably build a new request {@link org.w3c.dom.Document Document},
     * automatically adding the xml parameters.
     * 
     * @deprecated Requests are now handled via JAXB generated classes and marshalling.
     * 
     * @param action The action.
     * @param parameters The parameters of the action. In the for of name followed by the value.
     * @return The resulting request {@link org.w3c.dom.Document Document}.
     */
    private Document buildXMLRequest(String action, String... parameters) {
        List<String> tmp = new ArrayList<>(parameters.length + 2);
        tmp.addAll(Arrays.asList(parameters));
        tmp.add("format"); tmp.add("xml");
        tmp.add("version"); tmp.add("3");
        
        return this.buildRequest(action, tmp.toArray(new String[0]));
    }
    
    /**
     * Returns the selected hero in the native format ({@code Daten}).
     * @return The selected hero in the native format ({@code Daten}).
     */
    public Daten getSelectedHeld() {
        Action req = new Action();
        req.setAction(ActionType.HELD);
        req.setId("selected");
        req.setFormat(Format.XML);
        req.setVersion(3);
        Daten d = (Daten)this.sendRequest(req);
        return d;
    }
    
    /**
     * Returns the i-th {@link reiseplugin.data.Held Held} in the native format ({@code Daten}).
     * @param i The index of the requested {@link reiseplugin.data.Held Held}.
     * @return The i-th hero in the native format ({@code Daten}).
     */
    public Daten getHeld(int i) {
        Action req = new Action();
        req.setAction(ActionType.HELD);
        req.setId(String.valueOf(i));
        req.setFormat(Format.XML);
        req.setVersion(3);
        Daten d = (Daten)this.sendRequest(req);
        return d;
    }
    
    /**
     * Returns all {@link reiseplugin.data.Held Held}en in the native format ({@code Daten}).
     * @return All {@link reiseplugin.data.Held Held}en in the native format ({@code Daten}).
     */
    public List<Daten> getAllHelden() {
        Action req = new Action();
        req.setAction(ActionType.LIST_HELDEN);
        Result result = (Result)this.sendRequest(req);
        return result.getHeldAndProp().stream()
                .map(h -> {
                    Result.Held held = (Result.Held)h;
                    return (Daten)this.getHeld(held.getId());
                }).collect(Collectors.toList());
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.setChanged();
        this.notifyObservers();
    }
}
