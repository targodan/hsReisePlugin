package reiseplugin;

import reiseplugin.data.DummyService;

/**
 * Main class for testing purposes.
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        reiseplugin.gui.Controller c = new reiseplugin.gui.Controller(new DummyService());
    }
}
