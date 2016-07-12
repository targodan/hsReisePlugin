package reiseplugin.data.entities;

import javax.xml.bind.annotation.XmlRegistry;

/**
 *
 * @author Luca Corbatto
 */
@XmlRegistry
public class ObjectFactory extends reiseplugin.data.entities.jaxb.ObjectFactory {

    @Override
    public reiseplugin.data.entities.jaxb.Daten createDaten() {
        return new reiseplugin.data.entities.Daten();
    }
    
}
