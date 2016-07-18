package reiseplugin.data.helden.entities;

import javax.xml.bind.annotation.XmlRegistry;

/**
 *
 * @author Luca Corbatto
 */
@XmlRegistry
public class ObjectFactory extends reiseplugin.data.helden.entities.jaxb.ObjectFactory {

    @Override
    public reiseplugin.data.helden.entities.jaxb.Daten createDaten() {
        return new reiseplugin.data.helden.entities.Daten();
    }
    
}
