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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the calculator results for one day.
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
 */
public class ErgebnisTag {
    public static final int STUNDEN = 24;
    
    protected final Map<Held, Zustand[]> ergebnis;
    protected final List<Held> helden;

    /**
     * Creates a new ErgebnisTag.
     */
    public ErgebnisTag() {
        this.ergebnis = new HashMap<>();
        this.helden = new ArrayList<>();
    }
    
    /**
     * Adds a Held to the result.
     * @param h Held to be added.
     */
    public void addHeld(Held h) {
        if(h == null) {
            throw new IllegalArgumentException("Held cannot be null!");
        }
        if(this.ergebnis.put(h, new Zustand[24]) == null) {
            // neuer Held
            this.helden.add(h);
        }
    }
    
    /**
     * Sets the Zustand for a Held on a particular hour.
     * @param held Held
     * @param stunde The hour.
     * @param z The Zustand.
     */
    public void setZustand(Held held, int stunde, Zustand z) {
        if(!this.ergebnis.containsKey(held)) {
            throw new IllegalArgumentException("Add Held before setting Zustand!");
        }
        if(stunde < 0 || stunde > 23) {
            throw new IllegalArgumentException("The time must be between 0 and 23.");
        }
        this.ergebnis.get(held)[stunde] = z;
    }
    
    
    /**
     * Sets the Zustand for a Held on a particular hour.
     * @param held Held
     * @param stunde The hour.
     * @param ersch The Erschöpfung.
     * @param überanst The Überanstrengung.
     */
    public void setZustand(Held held, int stunde, int ersch, int überanst) {
        this.setZustand(held, stunde, new Zustand(ersch, überanst));
    }
    
    /**
     * Returns the Zustand of a Held in a particular hour.
     * @param h The Held.
     * @param stunde The hour.
     * @return The Zustand of a Held.
     */
    public Zustand getZustand(Held h, int stunde) {
        if(!this.ergebnis.containsKey(h)) {
            throw new IllegalArgumentException("Add Held before getting Zustand!");
        }
        if(stunde < 0 || stunde > 23) {
            throw new IllegalArgumentException("The time must be between 0 and 23.");
        }
        return this.ergebnis.get(h)[stunde];
    }
    
    /**
     * Returns all contained Helden.
     * @return All contained Helden.
     */
    public List<Held> getHelden() {
        return this.helden;
    }
    
    /**
     * Container for the Erschöpfung and Überanstrengung.
     */
    public static class Zustand {
        private final int erschöpfung;
        private final int überanstregnung;

        /**
         * Creates a new Zustand with the given Erschöpfung and Überanstrengung.
         * @param erschöpfung The Erschöpfung.
         * @param überanstregnung The Überanstrengung.
         */
        public Zustand(int erschöpfung, int überanstregnung) {
            if(erschöpfung < 0 || überanstregnung < 0) {
                throw new IllegalArgumentException("Neither Erschöpfung nor Überanstrengung can be less than 0.");
            }
            this.erschöpfung = erschöpfung;
            this.überanstregnung = überanstregnung;
        }

        /**
         * Returns the Erschöpfung.
         * @return The Erschöpfung.
         */
        public int getErschöpfung() {
            return erschöpfung;
        }

        /**
         * Returns the Überanstrengung.
         * @return The Überanstrengung.
         */
        public int getÜberanstregnung() {
            return überanstregnung;
        }

        /**
         * Returns the hash code.
         * @return The hash code.
         */
        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + this.erschöpfung;
            hash = 97 * hash + this.überanstregnung;
            return hash;
        }

        /**
         * Compares a Zustand to this Zustand.
         * @param obj The other Zustand.
         * @return true if the other Zustand is equals to this.
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Zustand other = (Zustand) obj;
            if (this.erschöpfung != other.erschöpfung) {
                return false;
            }
            return this.überanstregnung == other.überanstregnung;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "Zustand{" + "ersch\u00f6pfung=" + this.erschöpfung + ", \u00fcberanstregnung=" + this.überanstregnung + '}';
        }
    }
    
    /**
     * Compares an ErgebnisTag to this ErgebnisTag.
     * @param obj The other ErgebnisTag.
     * @return true if the other ErgebnisTag is equals to this.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ErgebnisTag et = (ErgebnisTag)obj;
        if(!(this.helden.containsAll(et.helden)
                && this.helden.size() == et.helden.size())) {
            return false;
        }
        return this.ergebnis.keySet().stream()
                .allMatch(k -> Arrays.equals(this.ergebnis.get(k), et.ergebnis.get(k)));
        // No need to check size.
    }

    /**
     * Returns the hash code.
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.ergebnis);
        hash = 53 * hash + Objects.hashCode(this.helden);
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ErgebnisTag{" + "ergebnis=" + this.ergebnis.toString() + ", helden=" + this.helden.toString() + '}';
    }
}
