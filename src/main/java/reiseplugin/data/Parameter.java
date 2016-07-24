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
import reiseplugin.gui.Controller;

/**
 * Contains all parameters used by the {@link reiseplugin.data.ReiseCalculator ReiseCalculator}.
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
 */
public class Parameter extends Observable implements Observer {
    private final Held[] helden;
    private int erschöpfungProStunde;
    private final List<Rast> erholung;
    
    /**
     * Creates a new {@link reiseplugin.data.Parameter Parameter} with the given values.
     * @param helden An array of {@link reiseplugin.data.Held Held}en.
     * @param erschöpfungProStunde The Erschöpfung the {@link reiseplugin.data.Held Held}en gain per hour.
     * @param erholung A Collection of {@link reiseplugin.data.Rast Rast}en. 
     */
    public Parameter(Held[] helden, int erschöpfungProStunde, Collection<Rast> erholung) {
        if(erschöpfungProStunde < 0) {
            throw new IllegalArgumentException("ErschöpfungProStunde cannot be negative");
        }
        
        if(helden == null) {
            this.helden = new Held[0];
        } else {
            this.helden = helden;
        }
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
     * Returns the i-th {@link reiseplugin.data.Held Held}.
     * @param i The index of the {@link reiseplugin.data.Held Held}.
     * @return The i-th {@link reiseplugin.data.Held Held}.
     */
    public Held getHeld(int i) {
        return this.helden[i];
    }
    
    /**
     * Returns the number of {@link reiseplugin.data.Held Held}en.
     * @return The number of {@link reiseplugin.data.Held Held}en.
     */
    public int getHeldenCount() {
        return this.helden.length;
    }
    
    /**
     * Returns a {@link java.util.List List} containing the {@link reiseplugin.data.Held Held}en.
     * @return A {@link java.util.List List} containing the {@link reiseplugin.data.Held Held}en.
     */
    public List<Held> getHelden() {
        return Arrays.asList(this.helden);
    }

    /**
     * Returns the Erschöpfung per hour.
     * @return The Erschöpfung per hour.
     */
    public int getErschöpfungProStunde() {
        return this.erschöpfungProStunde;
    }

    /**
     * Sets the Erschöpfung per hour.
     * @param erschöpfungProStunde The Erschöpfung per hour.
     */
    public void setErschöpfungProStunde(int erschöpfungProStunde) {
        if(erschöpfungProStunde < 0) {
            throw new IllegalArgumentException("ErschöpfungProStunde cannot be negative");
        }
        
        if(this.erschöpfungProStunde != erschöpfungProStunde) {
            this.erschöpfungProStunde = erschöpfungProStunde;
            this.setChanged();
            this.notifyObservers();
        }
    }
    
    /**
     * Returns a {@link java.util.List List} containing all {@link reiseplugin.data.Rast Rast}en.
     * @return A {@link java.util.List List} containing all {@link reiseplugin.data.Rast Rast}en.
     */
    public List<Rast> getErholung() {
        return this.erholung;
    }
    
    /**
     * Returns the i-th {@link reiseplugin.data.Rast Rast}.
     * @param i The index of the {@link reiseplugin.data.Rast Rast}.
     * @return The i-th {@link reiseplugin.data.Rast Rast}.
     */
    public Rast getRast(int i) {
        return this.erholung.get(i);
    }
    
    /**
     * Adds a {@link reiseplugin.data.Rast Rast}.
     * @param r A {@link reiseplugin.data.Rast Rast}.
     */
    public void addRast(Rast r) {
        if(!this.erholung.contains(r)) {
            this.erholung.add(r);
            r.addObserver(this);
            this.setChanged();
            this.notifyObservers();
        }
    }
    
    /**
     * Removes a {@link reiseplugin.data.Rast Rast}.
     * @param r The {@link reiseplugin.data.Rast Rast} to be removed.
     */
    public void removeRast(Rast r) {
        if(this.erholung.contains(r)) {
            this.erholung.remove(r);
            r.deleteObserver(this);
            this.setChanged();
            this.notifyObservers();
        }
    }

    /**
     * Called when any of the {@link java.util.Observable Observable}s are updated.
     * Passes the update on to it's {@link java.util.Observer Observer}.
     * @param o The {@link java.util.Observable Observable} that was updated.
     * @param arg An argument.
     */
    @Override
    public void update(Observable o, Object arg) {
        Controller.getInstance().appendDebugText(getClass().getCanonicalName() + "update("+(o == null ? "null" : o.getClass().getCanonicalName())+")\n");
        this.setChanged();
        this.notifyObservers(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Parameter{" + "helden=" + Arrays.toString(this.helden) + ", ersch\u00f6pfungProStunde=" + this.erschöpfungProStunde + ", erholung=" + this.erholung + '}';
    }
}
