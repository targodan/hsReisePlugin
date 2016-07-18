package reiseplugin.data.helden.entities;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * An example extension of the HeldenSoftware-native JAXB Factory.
 * Not really used, just left in here as an example for future use.
 * @author Luca Corbatto<luca@corbatto.de>
 */
@XmlRegistry
public class ObjectFactory extends reiseplugin.data.helden.entities.jaxb.ObjectFactory {

    /**
     * Creates a new (extended) Daten instance.
     * @return A new (extended) Daten instance.
     */
    @Override
    public reiseplugin.data.helden.entities.jaxb.Daten createDaten() {
        return new reiseplugin.data.helden.entities.Daten();
    }
    
}
