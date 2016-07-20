/*
 * Copyright (C) 2016 Luca Corbatto
 *
 * This file is part of the hsReisePlugin.
 *
 * The hsReisePlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The hsReisePlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package reiseplugin.data;

import reiseplugin.data.helden.entities.Daten;

/**
 * A dummy service for testing purposes.
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
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
