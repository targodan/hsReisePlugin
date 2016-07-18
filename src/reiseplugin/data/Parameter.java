/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reiseplugin.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class Parameter extends Observable implements Observer {
    private final Held[] helden;
    private int erschöpfungProStunde;
    private final List<Rast> erholung;
    
    public Parameter(Held[] helden, int erschöpfungProStunde, Collection<Rast> erholung) {
        this.helden = helden;
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
        if(this.erschöpfungProStunde != erschöpfungProStunde) {
            this.erschöpfungProStunde = erschöpfungProStunde;
            this.setChanged();
            this.notifyObservers();
        }
    }
    
    public List<Rast> getErholung() {
        return this.erholung;
    }
    
    public Rast getRast(int i) {
        return this.erholung.get(i);
    }
    
    public void addRast(Rast r) {
        this.erholung.add(r);
        r.addObserver(this);
        this.setChanged();
        this.notifyObservers();
    }
    
    public void removeRast(Rast r) {
        this.erholung.remove(r);
        r.deleteObserver(this);
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setChanged();
        this.notifyObservers(o);
    }
}
