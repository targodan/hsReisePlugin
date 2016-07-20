/*
 * Copyright (C) 2016 Luca Corbatto
 *
 * This file is part of the hsReisePlugin.
 *
 * The hsReisePlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The hsReisePlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package reiseplugin.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * The class that does all the math.
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
 */
public class ReiseCalculator extends Observable implements Observer {
    private final Parameter parameter;
    private final List<ErgebnisTag> ergebnis;
    
    /**
     * Creates a new ReiseCalculator with the given Parameter.
     * @param parameter The Parameter for the calculations.
     */
    public ReiseCalculator(Parameter parameter) {
        this.parameter = parameter;
        this.parameter.addObserver(this);
        this.ergebnis = new ArrayList<>();
    }
    
    /**
     * Returns the result for the given day.
     * @param tag The day.
     * @return The result for the given day.
     */
    public ErgebnisTag getTag(int tag) {
        if(this.ergebnis.size() <= tag) {
            this.calculate(tag);
        }
        return this.ergebnis.get(tag);
    }

    /**
     * Returns the Parameter.
     * @return The Parameter.
     */
    public Parameter getParameter() {
        return parameter;
    }
    
    /**
     * Calculates the result for the given day.
     * @param tag The day to be calculated.
     */
    protected void calculate(int tag) {
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
    
    /**
     * Calculates an hour for a Held.
     * @param h The Held.
     * @param lastZustand The previous Zustand of the Held.
     * @param newDay The day.
     * @param st The hour.
     * @return The result for the given hour.
     */
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
    
    /**
     * Caculates a followup Zustand.
     * @param h The Held.
     * @param lastZustand The previous Zustand.
     * @param rast The Rast affecting this hour.
     * @return The next Zustand.
     */
    protected ErgebnisTag.Zustand nextZustand(Held h, ErgebnisTag.Zustand lastZustand, Rast rast) {
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

    /**
     * Called when any of the Ovservables are updated.
     * Cleares all calculated results, forcing a recalculation.
     * @param o The Observable that was updated.
     * @param arg An argument.
     */
    @Override
    public void update(Observable o, Object arg) {
        this.ergebnis.clear();
        this.setChanged();
        this.notifyObservers();
    }
}
