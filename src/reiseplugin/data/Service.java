package reiseplugin.data;

import helden.plugin.datenxmlplugin.DatenAustausch3Interface;
import java.util.ArrayList;
import java.util.List;

import reiseplugin.data.helden.entities.Daten;
import reiseplugin.data.helden.entities.HeldenService;

/**
 *
 * @author Luca Corbatto
 */
public class Service implements IService {
    private final HeldenService service;
    
    public Service(DatenAustausch3Interface dai) {
        this.service = new HeldenService(dai);
    }
    
    private Held nativeToHeld(Daten d) {
        return new Held(d.getAngaben().getName(), d.getEigenschaften().getKonstitution().getAkt().intValue(), 0);
    }

    @Override
    public Held[] getAllHelden() {
        // Highly unclean but the best I can do until I find the documentation of the API
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
