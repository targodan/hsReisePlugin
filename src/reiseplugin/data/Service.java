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

import helden.plugin.datenxmlplugin.DatenAustausch3Interface;
import java.util.ArrayList;
import java.util.List;

import reiseplugin.data.helden.entities.Daten;
import reiseplugin.data.helden.entities.HeldenService;

/**
 * The service providing the necessary methods to access the HeldenSoftware data.
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
 */
public class Service implements IService {
    private final HeldenService service;
    
    /**
     * Creates a new Service using the given interface.
     * @param dai The interface to the HeldenSoftware.
     */
    public Service(DatenAustausch3Interface dai) {
        this.service = new HeldenService(dai);
    }
    
    /**
     * Converts a HeldenSoftware-native format into the local Held format.
     * @param d The native Daten.
     * @return Return the local representation of the given Daten.
     */
    private Held nativeToHeld(Daten d) {
        return new Held(d.getAngaben().getName(), d.getEigenschaften().getKonstitution().getAkt().intValue(), 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Held[] getAllHelden() {
        // Highly unclean but the best I can do until I find the documentation of the API
        List<Held> ret = new ArrayList<>();
        try {
            for(int i = 0; true; ++i) {
                ret.add(this.nativeToHeld(this.service.getHeld(i)));
            }
        } catch(Exception e) {
            // last Held was read already
        }
        return ret.toArray(new Held[0]);
    }
}
