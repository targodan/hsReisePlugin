package reiseplugin.data.entities;

/**
 *
 * @author Luca Corbatto
 */
public class Daten extends reiseplugin.data.entities.jaxb.Daten {
    @Override
    public String toString() {
        return this.getAngaben().getName();
    }
}
