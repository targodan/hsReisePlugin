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
    
    public Parameter(Held[] helden, int erschöpfungProStunde) {
        this.helden = helden;
        this.erschöpfungProStunde = erschöpfungProStunde;
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
    
    
    public static class Rast {
        private int start;
        private int ende;
        private int erholungProStunde;

        public Rast(int start, int ende, int erholungProStunde) {
            this.start = start;
            this.ende = ende;
            this.erholungProStunde = erholungProStunde;
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

        public int getErholungProStunde() {
            return erholungProStunde;
        }

        public void setErholungProStunde(int erholungProStunde) {
            this.erholungProStunde = erholungProStunde;
        }
    }
}
