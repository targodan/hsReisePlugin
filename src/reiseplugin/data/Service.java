package reiseplugin.data;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import helden.plugin.datenxmlplugin.DatenAustausch3Interface;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.w3c.dom.*;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import reiseplugin.data.helden.entities.Daten;
import reiseplugin.data.helden.entities.HeldenService;
import reiseplugin.data.helden.entities.ObjectFactory;

/**
 *
 * @author Luca Corbatto
 */
public class Service implements IService {
    private DatenAustausch3Interface dai;
    private DocumentBuilder documentBuilder;
    private Unmarshaller unmarshaller;
    private HeldenService service;
    
    public Service(DatenAustausch3Interface dai) {
        this.service = new HeldenService(dai);
    }
    
    private Held nativeToHeld(Daten d) {
        return new Held(d.getAngaben().getName(), d.getEigenschaften().getKonstitution().getAkt().intValue(), 0);
    }

    @Override
    public Held[] getAllHelden() {
        List<Held> ret = new ArrayList<>();
        try {
            for(int i = 0; true; ++i) {
                ret.add(this.nativeToHeld(this.service.getHeld(i)));
            }
        } catch(Exception e) {
            // last Held was read already
        }
        return ret.toArray(new Held[0]);
    }
}
