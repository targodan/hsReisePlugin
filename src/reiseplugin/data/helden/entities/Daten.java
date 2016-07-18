package reiseplugin.data.helden.entities;

/**
 * An example extension of the HeldenSoftware-native Daten class.
 * Not really used, just left in here as an example for future use.
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class Daten extends reiseplugin.data.helden.entities.jaxb.Daten {
    @Override
    public String toString() {
        return this.getAngaben().getName();
    }
}
