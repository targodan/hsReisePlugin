package reiseplugin.data;

import helden.plugin.datenxmlplugin.DatenAustausch3Interface;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;

import reiseplugin.data.helden.entities.Daten;
import reiseplugin.data.helden.entities.HeldenService;

/**
 *
 * @author Luca Corbatto
 */
public class Service implements IService {
    private DatenAustausch3Interface dai;
    private DocumentBuilder documentBuilder;
    private Unmarshaller unmarshaller;
    private final HeldenService service;
    
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
