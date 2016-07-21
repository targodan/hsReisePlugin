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

import java.util.List;
import java.util.Observable;
import jdk.nashorn.internal.runtime.arrays.ArrayIndex;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luca Corbatto
 */
public class ParameterTest {
    
    public ParameterTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getHeld method, of class Parameter.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetHeld() {
        System.out.println("getHeld");
        int i = 0;
        Parameter instance = new Parameter(null, 0, null);
        instance.getHeld(i);
    }

    /**
     * Test of constructor, of class Parameter.
     */
    @Test
    public void testConstructor_ThrowsNot() {
        System.out.println("getHeld");
        int i = 0;
        Parameter instance = new Parameter(null, i, null);
    }
    
    /**
     * Test of constructor, of class Parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_Throws() {
        System.out.println("getHeld");
        int i = -1;
        Parameter instance = new Parameter(null, i, null);
    }

    /**
     * Test of setErschöpfungProStunde method, of class Parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetErschöpfungProStunde_Throws() {
        System.out.println("setErsch\u00f6pfungProStunde");
        int erschöpfungProStunde = -1;
        Parameter instance = new Parameter(null, 0, null);
        instance.setErschöpfungProStunde(erschöpfungProStunde);
    }

    /**
     * Test of setErschöpfungProStunde method, of class Parameter.
     */
    @Test
    public void testSetErschöpfungProStunde_NotThrows() {
        System.out.println("setErsch\u00f6pfungProStunde");
        int erschöpfungProStunde = 0;
        Parameter instance = new Parameter(null, 0, null);
        instance.setErschöpfungProStunde(erschöpfungProStunde);
    }
}
