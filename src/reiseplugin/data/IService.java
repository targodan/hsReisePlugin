package reiseplugin.data;

/**
 * Interface for the locally used service.
 * @author Luca Corbatto<luca@corbatto.de>
 */
public interface IService {
    /**
     * Returns an array of all Helden contained in the HeldenSoftware.
     * @return Array of all Helden contained in the HeldenSoftware.
     */
    public Held[] getAllHelden();
}
