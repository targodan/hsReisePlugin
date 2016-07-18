/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reiseplugin.data;

import reiseplugin.data.helden.entities.Daten;

/**
 * A dummy service for testing purposes.
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class DummyService implements IService {

    /**
     * Returns the currently selected Held.
     * @return The currently selected Held.
     */
    public Daten getSelectedHeld() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Held[] getAllHelden() {
        Held h = new Held("Rimaldo", 12, 0);
        Held h2 = new Held("Janon", 13, 0);
        return new Held[]{h, h2};
    }
}
