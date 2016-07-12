/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reiseplugin.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class ErgebnisTag {
    public static final int STUNDEN = 24;
    
    private Map<Held, Zustand[]> ergebnis;
    private List<Held> helden;

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
    }
}
