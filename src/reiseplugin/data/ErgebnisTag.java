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
 *
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class ErgebnisTag {
    public static final int STUNDEN = 24;
    
    private final Map<Held, Zustand[]> ergebnis;
    private final List<Held> helden;

    public ErgebnisTag() {
        this.ergebnis = new HashMap<>();
        this.helden = new ArrayList<>();
    }
    
    public void addHeld(Held h) {
        if(this.ergebnis.put(h, new Zustand[24]) == null) {
            // neuer Held
            this.helden.add(h);
        }
    }
    
    public void setZustand(Held held, int stunde, Zustand z) {
        this.ergebnis.get(held)[stunde] = z;
    }
    
    public void setZustand(Held held, int stunde, int ersch, int überanst) {
        this.ergebnis.get(held)[stunde] = new Zustand(ersch, überanst);
    }
    
    public Zustand getZustand(Held h, int stunde) {
        return this.ergebnis.get(h)[stunde];
    }
    
    public List<Held> getHelden() {
        return this.helden;
    }
    
    public static class Zustand {
        private final int erschöpfung;
        private final int überanstregnung;

        public Zustand(int erschöpfung, int überanstregnung) {
            this.erschöpfung = erschöpfung;
            this.überanstregnung = überanstregnung;
        }

        public int getErschöpfung() {
            return erschöpfung;
        }

        public int getÜberanstregnung() {
            return überanstregnung;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + this.erschöpfung;
            hash = 97 * hash + this.überanstregnung;
            return hash;
        }

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.ergebnis);
        hash = 53 * hash + Objects.hashCode(this.helden);
        return hash;
    }
}
