/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reiseplugin.data;

import java.util.Objects;
import java.util.Observable;
import javax.swing.Action;

/**
 *
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class Held extends Observable {
    private String name;
    private int KO;
    private int mod;

    public Held(String name, int KO, int mod) {
        this.name = name;
        this.KO = KO;
        this.mod = mod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(this.name == null || this.name.equals(name)) {
            this.name = name;
            this.setChanged();
            this.notifyObservers();
        }
    }

    public int getKO() {
        return KO;
    }

    public void setKO(int KO) {
        if(this.KO != KO) {
            this.KO = KO;
            this.setChanged();
            this.notifyObservers();
        }
    }

    public int getMod() {
        return mod;
    }

    public void setMod(int mod) {
        if(this.mod != mod) {
            this.mod = mod;
            this.setChanged();
            this.notifyObservers();
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Held)) {
            return false;
        }
        Held h = (Held)o;
        return this.name.equals(h.name) && this.KO == h.KO && this.mod == h.mod;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + this.KO;
        hash = 53 * hash + this.mod;
        return hash;
    }
}