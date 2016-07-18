package reiseplugin.data.helden.entities;

/**
 *
 * @author Luca Corbatto
 */
public class Daten extends reiseplugin.data.helden.entities.jaxb.Daten {
    @Override
    public String toString() {
        return this.getAngaben().getName();
    }
}
