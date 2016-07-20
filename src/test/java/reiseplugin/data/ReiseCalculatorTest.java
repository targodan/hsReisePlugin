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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luca Corbatto
 */
public class ReiseCalculatorTest {
    
    public ReiseCalculatorTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTag method, of class ReiseCalculator.
     */
    @Test
    public void testGetTag() {
        System.out.println("getTag");
        int tag = 0;
        ReiseCalculator instance = null;
        ErgebnisTag expResult = null;
        ErgebnisTag result = instance.getTag(tag);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParameter method, of class ReiseCalculator.
     */
    @Test
    public void testGetParameter() {
        System.out.println("getParameter");
        ReiseCalculator instance = null;
        Parameter expResult = null;
        Parameter result = instance.getParameter();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateStunde method, of class ReiseCalculator.
     */
    @Test
    public void testCalculateStunde() {
        System.out.println("calculateStunde");
        Held h = null;
        ErgebnisTag.Zustand lastZustand = null;
        ErgebnisTag newDay = null;
        int st = 0;
        ReiseCalculator instance = null;
        ErgebnisTag.Zustand expResult = null;
        ErgebnisTag.Zustand result = instance.calculateStunde(h, lastZustand, newDay, st);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class ReiseCalculator.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Observable o = null;
        Object arg = null;
        ReiseCalculator instance = null;
        instance.update(o, arg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculate method, of class ReiseCalculator.
     */
    @Test
    public void testCalculate() {
        System.out.println("calculate");
        int tag = 0;
        ReiseCalculator instance = null;
        instance.calculate(tag);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextZustand method, of class ReiseCalculator.
     */
    @Test
    public void testNextZustand() {
        System.out.println("nextZustand");
        Held h = null;
        ErgebnisTag.Zustand lastZustand = null;
        Rast rast = null;
        ReiseCalculator instance = null;
        ErgebnisTag.Zustand expResult = null;
        ErgebnisTag.Zustand result = instance.nextZustand(h, lastZustand, rast);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
