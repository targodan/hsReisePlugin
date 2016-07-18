/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reiseplugin.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class ReiseCalculator extends Observable implements Observer {
    private final Parameter parameter;
    private final List<ErgebnisTag> ergebnis;
    
    public ReiseCalculator(Parameter parameter) {
        this.parameter = parameter;
        this.parameter.addObserver(this);
        this.ergebnis = new ArrayList<>();
    }
    
    public ErgebnisTag getTag(int tag) {
        if(this.ergebnis.size() <= tag) {
            this.calculate(tag);
        }
        return this.ergebnis.get(tag);
    }

    public Parameter getParameter() {
        return parameter;
    }
    
    private void calculate(int tag) {
        while(ergebnis.size() <= tag) {
            ErgebnisTag lastDay;
            if(ergebnis.size() > 0) {
                lastDay = ergebnis.get(ergebnis.size()-1);
            } else {
                lastDay = null;
            }
            ErgebnisTag newDay = new ErgebnisTag();
            
            parameter.getHelden().stream().forEach(h -> {
                ErgebnisTag.Zustand lastZustand;
                if(lastDay != null) {
                    lastZustand = lastDay.getZustand(h, 23);
                } else {
                    lastZustand = new ErgebnisTag.Zustand(0, 0);
                }
                        
                newDay.addHeld(h);
                for(int st = 0; st < 24; ++st) {
                    lastZustand = this.calculateStunde(h, lastZustand, newDay, st);
                }
            });
            
            this.ergebnis.add(newDay);
        }
    }
    
    public ErgebnisTag.Zustand calculateStunde(Held h, ErgebnisTag.Zustand lastZustand, ErgebnisTag newDay, int st) {
        Rast rast = this.parameter.getErholung().stream()
                .filter(r -> r.matchStunde(st))
                .reduce(null, (r1, r2) -> {
                    if(r1 == null) {
                        r1 = new Rast(0, 0, 0, 0);
                    }
                    return new Rast(0, 0,
                            r1.getErschöpfungProStunde() + r2.getErschöpfungProStunde(),
                            r1.getÜberanstrengungProStunde() + r2.getÜberanstrengungProStunde());
                });
        
        ErgebnisTag.Zustand z = this.nextZustand(h, lastZustand, rast);
        newDay.setZustand(h, st, z);
        return z;
    }
    
    private ErgebnisTag.Zustand nextZustand(Held h, ErgebnisTag.Zustand lastZustand, Rast rast) {
        int ersch = lastZustand.getErschöpfung();
        int überanst = lastZustand.getÜberanstregnung();
        
        if(rast == null) {
            ersch += this.parameter.getErschöpfungProStunde() + h.getMod();
            if(ersch > h.getKO()) {
                überanst += ersch - h.getKO();
                ersch = h.getKO();
            }
        } else {
            if(überanst > 0) {
                überanst -= rast.getÜberanstrengungProStunde();
            } else {
                ersch -= rast.getErschöpfungProStunde();
            }
            überanst = Math.max(0, überanst);
            ersch = Math.max(0, ersch);
        }
        return new ErgebnisTag.Zustand(ersch, überanst);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.ergebnis.clear();
        this.setChanged();
        this.notifyObservers();
    }
}
