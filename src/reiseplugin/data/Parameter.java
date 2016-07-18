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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Contains all parameters used by the ReiseCalculator.
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class Parameter extends Observable implements Observer {
    private final Held[] helden;
    private int erschöpfungProStunde;
    private final List<Rast> erholung;
    
    /**
     * Creates a new Parameter with the given values.
     * @param helden An array of Helden.
     * @param erschöpfungProStunde The Erschöpfung the Helden gain per hour.
     * @param erholung A Collection of Rasten. 
     */
    public Parameter(Held[] helden, int erschöpfungProStunde, Collection<Rast> erholung) {
        this.helden = helden;
        for(Held h : this.helden) {
            h.addObserver(this);
        }
        this.erschöpfungProStunde = erschöpfungProStunde;
        if(erholung == null) {
            this.erholung = new ArrayList<>();
        } else {
            this.erholung = new ArrayList<>(erholung);
        }
        this.erholung.stream().forEach((r) -> {
            r.addObserver(this);
        });
    }

    /**
     * Returns the i-th Held.
     * @param i The index of the Held.
     * @return The i-th Held.
     */
    public Held getHeld(int i) {
        return helden[i];
    }
    
    /**
     * Returns the number of Helden.
     * @return The number of Helden.
     */
    public int getHeldenCount() {
        return helden.length;
    }
    
    /**
     * Returns a List containing the Helden.
     * @return A List containing the Helden.
     */
    public List<Held> getHelden() {
        return Arrays.asList(helden);
    }

    /**
     * Returns the Erschöpfung per hour.
     * @return The Erschöpfung per hour.
     */
    public int getErschöpfungProStunde() {
        return erschöpfungProStunde;
    }

    /**
     * Sets the Erschöpfung per hour.
     * @param erschöpfungProStunde The Erschöpfung per hour.
     */
    public void setErschöpfungProStunde(int erschöpfungProStunde) {
        if(this.erschöpfungProStunde != erschöpfungProStunde) {
            this.erschöpfungProStunde = erschöpfungProStunde;
            this.setChanged();
            this.notifyObservers();
        }
    }
    
    /**
     * Returns a List containing all Rasten.
     * @return A List containing all Rasten.
     */
    public List<Rast> getErholung() {
        return this.erholung;
    }
    
    /**
     * Returns the i-th Rast.
     * @param i The index of the Rast.
     * @return The i-th Rast.
     */
    public Rast getRast(int i) {
        return this.erholung.get(i);
    }
    
    /**
     * Adds a Rast.
     * @param r A Rast.
     */
    public void addRast(Rast r) {
        this.erholung.add(r);
        r.addObserver(this);
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Removes a Rast.
     * @param r The Rast to be removed.
     */
    public void removeRast(Rast r) {
        this.erholung.remove(r);
        r.deleteObserver(this);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Called when any of the Ovservables are updated.
     * Passes the update on to it's Observers.
     * @param o The Observable that was updated.
     * @param arg An argument.
     */
    @Override
    public void update(Observable o, Object arg) {
        this.setChanged();
        this.notifyObservers(o);
    }
}
