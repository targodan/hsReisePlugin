package reiseplugin;

import reiseplugin.data.DummyService;
import reiseplugin.data.Service;

/**
 *
 * @author Luca Corbatto
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Object o = new reiseplugin.gui.Controller(new Service(null));
    }
}
