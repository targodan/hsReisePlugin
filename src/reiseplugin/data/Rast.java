/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reiseplugin.data;

import java.util.Observable;

/**
 *
 * @author Luca Corbatto
 */

public class Rast extends Observable {
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
        if(this.start != start) {
            if(start < 0 || start > 23) {
                throw new IllegalArgumentException("The time must be between 0 and 23.");
            }

            this.start = start;
            this.setChanged();
            this.notifyObservers();
        }
    }

    public int getEnde() {
        return ende;
    }

    public void setEnde(int ende) {
        if(this.ende != ende) {
            if(start < 0 || start > 23) {
                throw new IllegalArgumentException("The time must be between 0 and 23.");
            }
            this.ende = ende;
            this.setChanged();
            this.notifyObservers();
        }
    }

    public int getErschöpfungProStunde() {
        return erschöpfungProStunde;
    }

    public void setErschöpfungProStunde(int erschöpfungProStunde) {
        if(this.erschöpfungProStunde != erschöpfungProStunde) {
            this.erschöpfungProStunde = erschöpfungProStunde;
            this.setChanged();
            this.notifyObservers();
        }
    }

    public int getÜberanstrengungProStunde() {
        return überanstrengungProStunde;
    }

    public void setÜberanstrengungProStunde(int überanstrengungProStunde) {
        if(this.überanstrengungProStunde != überanstrengungProStunde) {
            this.überanstrengungProStunde = überanstrengungProStunde;
            this.setChanged();
            this.notifyObservers();
        }
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