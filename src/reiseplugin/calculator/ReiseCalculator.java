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
    private List<Ergebnis> ergebnis;
    
    public ReiseCalculator(Parameter parameter) {
        this.parameter = parameter;
    }
    
    public void calculate(int tag) {
        while(ergebnis.size() <= tag) {
            Ergebnis lastDay;
            if(ergebnis.size() > 0) {
                lastDay = ergebnis.get(ergebnis.size()-1);
            } else {
                lastDay = null;
            }
            Ergebnis newDay = new Ergebnis();
            
            parameter.getHelden().stream().forEach(h -> {
                Ergebnis.Zustand lastZustand;
                if(lastDay != null) {
                    lastZustand = lastDay.getZustand(h, 23);
                } else {
                    lastZustand = new Ergebnis.Zustand(0, 0);
                }
                        
                newDay.addHeld(h);
                IntStream.range(0, 23).forEach(st -> {
                    int ersch = lastZustand.getErschöpfung();
                    int überanst = lastZustand.getÜberanstregnung();
                    if(ersch < h.getKO()) {
                        ++ersch;
                    } else {
                        ++überanst;
                    }
                    newDay.setZustand(h, st, new Ergebnis.Zustand(ersch, überanst));
                });
            });
            
            this.ergebnis.add(newDay);
        }
    }
}
