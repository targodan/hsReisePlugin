/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reiseplugin.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the calculator results for one day.
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class ErgebnisTag {
    public static final int STUNDEN = 24;
    
    private final Map<Held, Zustand[]> ergebnis;
    private final List<Held> helden;

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
        this.ergebnis.get(held)[stunde] = new Zustand(ersch, überanst);
    }
    
    /**
     * Returns the Zustand of a Held in a particular hour.
     * @param h The Held.
     * @param stunde The hour.
     * @return The Zustand of a Held.
     */
    public Zustand getZustand(Held h, int stunde) {
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
            if (this.überanstregnung != other.überanstregnung) {
                return false;
            }
            return true;
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
        return this.ergebnis.equals(et.ergebnis) && this.helden.equals(et.helden);
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
}
