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

import javax.xml.bind.annotation.XmlRegistry;

/**
 * An example extension of the HeldenSoftware-native JAXB Factory.
 * Not really used, just left in here as an example for future use.
 * @author Luca Corbatto<luca@corbatto.de>
 */
@XmlRegistry
public class ObjectFactory extends reiseplugin.data.helden.entities.jaxb.ObjectFactory {

    /**
     * Creates a new (extended) Daten instance.
     * @return A new (extended) Daten instance.
     */
    @Override
    public reiseplugin.data.helden.entities.jaxb.Daten createDaten() {
        return new reiseplugin.data.helden.entities.Daten();
    }
    
}
