/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reiseplugin.calculator;

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
        this.name = name;
    }

    public int getKO() {
        return KO;
    }

    public void setKO(int KO) {
        this.KO = KO;
    }

    public int getMod() {
        return mod;
    }

    public void setMod(int mod) {
        this.mod = mod;
    }
    
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Held)) {
            return false;
        }
        Held h = (Held)o;
        return this.name.equals(h.name) && this.KO == h.KO && this.mod == h.mod;
    }
}