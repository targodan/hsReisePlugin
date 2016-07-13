/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reiseplugin.calculator;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class Parameter {
    private final Held[] helden;
    private int erschöpfungProStunde;
    private List<Rast> erholung;
    
    public Parameter(Held[] helden, int erschöpfungProStunde, List<Rast> erholung) {
        this.helden = helden;
        this.erschöpfungProStunde = erschöpfungProStunde;
        this.erholung = erholung;
    }

    public Held getHeld(int i) {
        return helden[i];
    }
    
    public int getHeldenCount() {
        return helden.length;
    }
    
    public List<Held> getHelden() {
        return Arrays.asList(helden);
    }

    public int getErschöpfungProStunde() {
        return erschöpfungProStunde;
    }

    public void setErschöpfungProStunde(int erschöpfungProStunde) {
        this.erschöpfungProStunde = erschöpfungProStunde;
    }
    
    public List<Rast> getErholung() {
        return this.erholung;
    }
    
    public void addRast(Rast r) {
        this.erholung.add(r);
    }
    
    
    public static class Rast {
        private int start;
        private int ende;
        private int erschöpfungProStunde;
        private int überanstrengungProStunde;

        public Rast(int start, int ende, int erschöpfungProStunde, int überanstrengungProStunde) {
            this.start = start;
            this.ende = ende;
            this.erschöpfungProStunde = erschöpfungProStunde;
            this.überanstrengungProStunde = überanstrengungProStunde;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnde() {
            return ende;
        }

        public void setEnde(int ende) {
            this.ende = ende;
        }

        public int getErschöpfungProStunde() {
            return erschöpfungProStunde;
        }

        public void setErschöpfungProStunde(int erschöpfungProStunde) {
            this.erschöpfungProStunde = erschöpfungProStunde;
        }

        public int getÜberanstrengungProStunde() {
            return überanstrengungProStunde;
        }

        public void setÜberanstrengungProStunde(int überanstrengungProStunde) {
            this.überanstrengungProStunde = überanstrengungProStunde;
        }
        
        public boolean matchStunde(int st) {
            if(this.start > this.ende) {
                // über nacht
                return this.start <= st || st < this.ende;
            } else {
                // über tag
                return this.start <= st && st < this.ende;
            }
        }
    }
}
