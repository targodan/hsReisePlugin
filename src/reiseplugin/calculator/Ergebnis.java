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

/**
 *
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class Ergebnis {
    private Map<Held, Zustand[]> ergebnis;

    public Ergebnis() {
        this.ergebnis = new HashMap<>();
    }
    
    public void addHeld(Held h) {
        this.ergebnis.put(h, new Zustand[24]);
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
