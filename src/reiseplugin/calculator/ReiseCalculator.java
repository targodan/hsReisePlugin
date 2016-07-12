/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reiseplugin.calculator;

import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class ReiseCalculator {
    private Parameter parameter;
    private List<ErgebnisTag> ergebnis;
    
    public ReiseCalculator(Parameter parameter) {
        this.parameter = parameter;
    }
    
    public void calculate(int tag) {
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
                IntStream.range(0, 23).forEach(st -> {
                    this.calculateStunde(h, lastZustand, newDay, st);
                });
            });
            
            this.ergebnis.add(newDay);
        }
    }
    
    public void calculateStunde(Held h, ErgebnisTag.Zustand lastZustand, ErgebnisTag newDay, int st) {
        int ersch = lastZustand.getErschöpfung();
        int überanst = lastZustand.getÜberanstregnung();

        Parameter.Rast rast = this.parameter.getErholung().stream()
                .filter(r -> r.getStart() <= st && st <= r.getEnde()) // TODO: Check limits
                .findFirst().orElse(null);
        if(rast == null) {
            ersch += this.parameter.getErschöpfungProStunde();
            if(ersch > h.getKO()) {
                überanst = ersch - h.getKO();
                ersch = h.getKO();
            }
        } else {
            if(überanst > 0) {
                überanst -= rast.getÜberanstrengungProStunde();
            } else {
                ersch -= rast.getErschöpfungProStunde();
            }
        }

        newDay.setZustand(h, st, new ErgebnisTag.Zustand(ersch, überanst));
    }
}
