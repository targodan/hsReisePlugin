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
package reiseplugin.data.helden.entities;

/**
 * An example extension of the HeldenSoftware-native {@code Daten} class.
 * Not really used, just left in here as an example for future use.
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
 */
public class Daten extends reiseplugin.data.helden.entities.jaxb.Daten {
    @Override
    public String toString() {
        return this.getAngaben().getName();
    }
}
