/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reiseplugin.calculator;

/**
 *
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class Held {
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
}