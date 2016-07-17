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
        reiseplugin.gui.Controller c = new reiseplugin.gui.Controller(new DummyService());
    }
}
