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
 * Contains all information of a {@link reiseplugin.data.Rast Rast}.
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
 */
public class Rast extends Observable {
    private int start;
    private int ende;
    private int erschöpfungProStunde;
    private int überanstrengungProStunde;

    /**
     * Creates a new {@link reiseplugin.data.Rast Rast} with the given parameters.
     * If you give start = 12 and ende = 14 that results in a {@link reiseplugin.data.Rast Rast} from
     * 12:00 - 14:00.
     * @param start The start hour.
     * @param ende The end hour.
     * @param erschöpfungProStunde The Erschöpfung the {@link reiseplugin.data.Held Held}en regenerate per hour.
     * @param überanstrengungProStunde The Überanstrengung the {@link reiseplugin.data.Held Held}en regenerate per hour.
     */
    public Rast(int start, int ende, int erschöpfungProStunde, int überanstrengungProStunde) {
        if(start < 0 || start > 23) {
            throw new IllegalArgumentException("The time must be between 0 and 23.");
        }
        if(ende < 0 || ende > 23) {
            throw new IllegalArgumentException("The time must be between 0 and 23.");
        }
        if(erschöpfungProStunde < 0) {
            throw new IllegalArgumentException("ErschöpfungProStunde may not be less than 0.");
        }
        if(überanstrengungProStunde < 0) {
            throw new IllegalArgumentException("ErschöpfungProStunde may not be less than 0.");
        }
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
        if(start < 0 || start > 23) {
            throw new IllegalArgumentException("The time must be between 0 and 23.");
        }
        if(this.start != start) {
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
        if(ende < 0 || ende > 23) {
            throw new IllegalArgumentException("The time must be between 0 and 23.");
        }
        if(this.ende != ende) {
            this.ende = ende;
            this.setChanged();
            this.notifyObservers();
        }
    }

    /**
     * Returns the Erschöpfung the {@link reiseplugin.data.Held Held}en regenerate per hour.
     * @return The Erschöpfung the {@link reiseplugin.data.Held Held}en regenerate per hour.
     */
    public int getErschöpfungProStunde() {
        return erschöpfungProStunde;
    }

    /**
     * Sets the Erschöpfung the {@link reiseplugin.data.Held Held}en regenerate per hour.
     * @param erschöpfungProStunde The Erschöpfung the {@link reiseplugin.data.Held Held}en regenerate per hour.
     */
    public void setErschöpfungProStunde(int erschöpfungProStunde) {
        if(erschöpfungProStunde < 0) {
            throw new IllegalArgumentException("ErschöpfungProStunde may not be less than 0.");
        }
        if(this.erschöpfungProStunde != erschöpfungProStunde) {
            this.erschöpfungProStunde = erschöpfungProStunde;
            this.setChanged();
            this.notifyObservers();
        }
    }

    /**
     * Returns the Überanstrengung the {@link reiseplugin.data.Held Held}en regenerate per hour.
     * @return The Überanstrengung the {@link reiseplugin.data.Held Held}en regenerate per hour.
     */
    public int getÜberanstrengungProStunde() {
        return überanstrengungProStunde;
    }

    /**
     * Sets the Überanstrengung the {@link reiseplugin.data.Held Held}en regenerate per hour.
     * @param überanstrengungProStunde The Überanstrengung the {@link reiseplugin.data.Held Held}en regenerate per hour.
     */
    public void setÜberanstrengungProStunde(int überanstrengungProStunde) {
        if(überanstrengungProStunde < 0) {
            throw new IllegalArgumentException("ErschöpfungProStunde may not be less than 0.");
        }
        if(this.überanstrengungProStunde != überanstrengungProStunde) {
            this.überanstrengungProStunde = überanstrengungProStunde;
            this.setChanged();
            this.notifyObservers();
        }
    }

    /**
     * Returns true if the given hour is within this {@link reiseplugin.data.Rast Rast}en start and end.
     * @param st The hour to test.
     * @return true if the given hour is within this {@link reiseplugin.data.Rast Rast}en start and end.
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Rast{" + "start=" + this.start + ", ende=" + this.ende + ", ersch\u00f6pfungProStunde=" + this.erschöpfungProStunde + ", \u00fcberanstrengungProStunde=" + this.überanstrengungProStunde + '}';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.start;
        hash = 37 * hash + this.ende;
        hash = 37 * hash + this.erschöpfungProStunde;
        hash = 37 * hash + this.überanstrengungProStunde;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rast other = (Rast) obj;
        if (this.start != other.start) {
            return false;
        }
        if (this.ende != other.ende) {
            return false;
        }
        if (this.erschöpfungProStunde != other.erschöpfungProStunde) {
            return false;
        }
        if (this.überanstrengungProStunde != other.überanstrengungProStunde) {
            return false;
        }
        return true;
    }
}