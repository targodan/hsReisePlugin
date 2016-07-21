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
import static org.hamcrest.Matchers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
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
        ReiseCalculator instance = new ReiseCalculator(new Parameter(null, 0, null));
        ErgebnisTag expResult = new ErgebnisTag();
        ErgebnisTag result = instance.getTag(tag);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of calculateStunde method, of class ReiseCalculator.
     */
    @Test
    public void testCalculateStunde() {
        System.out.println("calculateStunde");
        Held h = new Held("Rimaldo", 0, 0);
        ErgebnisTag.Zustand lastZustand = new ErgebnisTag.Zustand(0, 0);
        ErgebnisTag newDay = new ErgebnisTag();
        int st = 0;
        ReiseCalculator instance = new ReiseCalculator(new Parameter(new Held[]{h}, 0, null));
        ErgebnisTag.Zustand expResult = new ErgebnisTag.Zustand(0, 0);
        ErgebnisTag.Zustand result = instance.calculateStunde(h, lastZustand, newDay, st);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of update method, of class ReiseCalculator.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Observable o = null;
        Object arg = null;
        ReiseCalculator instance = new ReiseCalculator(new Parameter(null, 0, null));
        instance.update(o, arg);
        assertThat(instance.ergebnis.size(), equalTo(0));
    }

    /**
     * Test of calculate method, of class ReiseCalculator.
     */
    @Test
    public void testCalculate() {
        System.out.println("calculate");
        int tag = 0;
        Held h = new Held("Rimaldo", 0, 0);
        ErgebnisTag expResult = new ErgebnisTag();
        expResult.addHeld(h);
        for(int i = 0; i < 24; ++i) {
            expResult.setZustand(h, i, 0, 0);
        }
        ReiseCalculator instance = new ReiseCalculator(new Parameter(new Held[]{h}, 0, null));
        instance.calculate(tag);
        ErgebnisTag result = instance.getTag(tag);
        assertThat(result.helden.toArray(), arrayContainingInAnyOrder(expResult.helden.toArray()));
        assertThat(result.ergebnis.get(h), arrayContainingInAnyOrder(expResult.ergebnis.get(h)));
        assertThat(result.ergebnis.get(h).length, equalTo(expResult.ergebnis.get(h).length));
    }

    /**
     * Test of nextZustand method, of class ReiseCalculator.
     */
    @Test
    public void testNextZustand() {
        System.out.println("nextZustand");
        Held h = new Held("Rimaldo", 0, 0);
        ErgebnisTag.Zustand lastZustand = new ErgebnisTag.Zustand(0, 0);
        Rast rast = new Rast(0, 0, 0, 0);
        ReiseCalculator instance = new ReiseCalculator(new Parameter(new Held[]{h}, 0, null));
        ErgebnisTag.Zustand expResult = new ErgebnisTag.Zustand(0, 0);
        ErgebnisTag.Zustand result = instance.nextZustand(h, lastZustand, rast);
        assertThat(result, equalTo(expResult));
    }
    
}
