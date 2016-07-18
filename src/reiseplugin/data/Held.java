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

import java.util.Objects;
import java.util.Observable;

/**
 * Contains the locally necessary informations about a Held.
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
 */
public class Held extends Observable {
    private String name;
    private int KO;
    private int mod;

    /**
     * Creates a new Held with the given properties.
     * @param name The name of the Held.
     * @param KO The Konstitution of the Held.
     * @param mod The modificator for the Erschöpfung of this Held.
     */
    public Held(String name, int KO, int mod) {
        this.name = name;
        this.KO = KO;
        this.mod = mod;
    }

    /**
     * Returns the name of the Held.
     * @return The name of the Held.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the Konstitution of this Held.
     * @return The Konstitution of this Held.
     */
    public int getKO() {
        return KO;
    }

    /**
     * Returns the modificator for the Erschöpfung of this Held.
     * @return The modificator for the Erschöpfung of this Held.
     */
    public int getMod() {
        return mod;
    }

    /**
     * Sets the modificator for the Erschöpfung of this Held.
     * @param mod The modificator for the Erschöpfung of this Held.
     */
    public void setMod(int mod) {
        if(this.mod != mod) {
            this.mod = mod;
            this.setChanged();
            this.notifyObservers();
        }
    }
    
    /**
     * Compares a Held to this Held.
     * @param obj The other Held.
     * @return true if the other Held is equals to this.
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Held)) {
            return false;
        }
        Held h = (Held)obj;
        return this.name.equals(h.name) && this.KO == h.KO && this.mod == h.mod;
    }

    /**
     * Returns the hash code.
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + this.KO;
        hash = 53 * hash + this.mod;
        return hash;
    }
}