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

import java.util.Observable;

/**
 * Contains all information of a Rast.
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class Rast extends Observable {
    private int start;
    private int ende;
    private int erschöpfungProStunde;
    private int überanstrengungProStunde;

    /**
     * Creates a new Rast with the given parameters.
     * If you give start = 12 and ende = 14 that results in a Rast from
     * 12:00 - 14:00.
     * @param start The start hour.
     * @param ende The end hour.
     * @param erschöpfungProStunde The Erschöpfung the Helden regenerate per hour.
     * @param überanstrengungProStunde The Überanstrengung the Helden regenerate per hour.
     */
    public Rast(int start, int ende, int erschöpfungProStunde, int überanstrengungProStunde) {
        this.start = start;
        this.ende = ende;
        this.erschöpfungProStunde = erschöpfungProStunde;
        this.überanstrengungProStunde = überanstrengungProStunde;
    }

    /**
     * Returns the start hour.
     * @return The start hour.
     */
    public int getStart() {
        return start;
    }

    /**
     * Sets the start hour.
     * @param start The start hour.
     */
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

    /**
     * Returns the end hour.
     * @return The end hour.
     */
    public int getEnde() {
        return ende;
    }

    /**
     * Sets the end hour.
     * @param ende The end hour.
     */
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

    /**
     * Returns the Erschöpfung the Helden regenerate per hour.
     * @return The Erschöpfung the Helden regenerate per hour.
     */
    public int getErschöpfungProStunde() {
        return erschöpfungProStunde;
    }

    /**
     * Sets the Erschöpfung the Helden regenerate per hour.
     * @param erschöpfungProStunde The Erschöpfung the Helden regenerate per hour.
     */
    public void setErschöpfungProStunde(int erschöpfungProStunde) {
        if(this.erschöpfungProStunde != erschöpfungProStunde) {
            this.erschöpfungProStunde = erschöpfungProStunde;
            this.setChanged();
            this.notifyObservers();
        }
    }

    /**
     * Returns the Überanstrengung the Helden regenerate per hour.
     * @return The Überanstrengung the Helden regenerate per hour.
     */
    public int getÜberanstrengungProStunde() {
        return überanstrengungProStunde;
    }

    /**
     * Sets the Überanstrengung the Helden regenerate per hour.
     * @param überanstrengungProStunde The Überanstrengung the Helden regenerate per hour.
     */
    public void setÜberanstrengungProStunde(int überanstrengungProStunde) {
        if(this.überanstrengungProStunde != überanstrengungProStunde) {
            this.überanstrengungProStunde = überanstrengungProStunde;
            this.setChanged();
            this.notifyObservers();
        }
    }

    /**
     * Returns true if the given hour is within this Rasts start and end.
     * @param st The hour to test.
     * @return true if the given hour is within this Rasts start and end.
     */
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