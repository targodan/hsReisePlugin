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

import java.util.Arrays;
import java.util.Observable;
import static org.hamcrest.Matchers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
     * Test of constructor, of class ReiseCalculator.
     */
    @Test
    public void testConstructor_Ok() {
        System.out.println("constructor");
        ReiseCalculator instance = new ReiseCalculator(new Parameter(null, 0, null));
    }
    
    /**
     * Test of constructor, of class ReiseCalculator.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_Null() {
        System.out.println("constructor");
        ReiseCalculator instance = new ReiseCalculator(null);
    }

    /**
     * Test of findRast method, of class ReiseCalculator.
     */
    @Test
    public void testFindRast_Single() {
        System.out.println("findRast");
        Rast[] rasten = new Rast[]{
            new Rast(11, 12, 100, 200),
            new Rast(12, 13, 2, 4),
            new Rast(13, 14, 100, 200)
        };
        ReiseCalculator instance = new ReiseCalculator(new Parameter(null, 0, Arrays.asList(rasten)));
        Rast expResult = new Rast(0, 0, 2, 4);
        Rast result = instance.findRast(12);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of findRast method, of class ReiseCalculator.
     */
    @Test
    public void testFindRast_Multiple() {
        System.out.println("findRast");
        Rast[] rasten = new Rast[]{
            new Rast(11, 12, 100, 200),
            new Rast(12, 13, 2, 4),
            new Rast(11, 14, 3, 5),
            new Rast(13, 14, 100, 200)
        };
        ReiseCalculator instance = new ReiseCalculator(new Parameter(null, 0, Arrays.asList(rasten)));
        Rast expResult = new Rast(0, 0, 5, 9);
        Rast result = instance.findRast(12);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of findRast method, of class ReiseCalculator.
     */
    @Test
    public void testFindRast_None() {
        System.out.println("findRast");
        Rast[] rasten = new Rast[]{
            new Rast(11, 12, 100, 200),
            new Rast(12, 13, 2, 4),
            new Rast(11, 14, 3, 5),
            new Rast(13, 14, 100, 200)
        };
        ReiseCalculator instance = new ReiseCalculator(new Parameter(null, 0, Arrays.asList(rasten)));
        Rast expResult = null;
        Rast result = instance.findRast(18);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of update method, of class ReiseCalculator.
     */
    @Test
    public void testUpdate_ResetCalculatedDays() {
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
    public void testCalculate_Zero() {
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
     * Test of calculate method, of class ReiseCalculator.
     * @deprecated 
     */
    @Test
    public void testCalculate_DeprecatedZero() {
        System.out.println("calculate");
        Held h = new Held("Rimaldo", 0, 0);
        ErgebnisTag.Zustand z = new ErgebnisTag.Zustand(0, 0);
        ReiseCalculator instance = new ReiseCalculator(new Parameter(new Held[]{h}, 0, null));
        instance.calculateStunde(h, z, new ErgebnisTag(), 0);
    }
    
    /**
     * Test of calculate method, of class ReiseCalculator.
     */
    @Test
    public void testCalculate_OneWithRast() {
        System.out.println("calculate");
        int tag = 0;
        Held h = new Held("Rimaldo", 12, 0);
        Rast[] rasten = new Rast[] {
            new Rast(12, 13, 2, 1)
        };
        ErgebnisTag expResult = new ErgebnisTag();
        expResult.addHeld(h);
        
        int hour = 0;
        expResult.setZustand(h, hour++, 1, 0);
        expResult.setZustand(h, hour++, 2, 0);
        expResult.setZustand(h, hour++, 3, 0);
        expResult.setZustand(h, hour++, 4, 0);
        expResult.setZustand(h, hour++, 5, 0);
        expResult.setZustand(h, hour++, 6, 0);
        expResult.setZustand(h, hour++, 7, 0);
        expResult.setZustand(h, hour++, 8, 0);
        expResult.setZustand(h, hour++, 9, 0);
        expResult.setZustand(h, hour++, 10, 0);
        expResult.setZustand(h, hour++, 11, 0);
        expResult.setZustand(h, hour++, 12, 0);
        expResult.setZustand(h, hour++, 10, 0);
        expResult.setZustand(h, hour++, 11, 0);
        expResult.setZustand(h, hour++, 12, 0);
        expResult.setZustand(h, hour++, 12, 1);
        expResult.setZustand(h, hour++, 12, 2);
        expResult.setZustand(h, hour++, 12, 3);
        expResult.setZustand(h, hour++, 12, 4);
        expResult.setZustand(h, hour++, 12, 5);
        expResult.setZustand(h, hour++, 12, 6);
        expResult.setZustand(h, hour++, 12, 7);
        expResult.setZustand(h, hour++, 12, 8);
        expResult.setZustand(h, hour++, 12, 9);
        
        
        ReiseCalculator instance = new ReiseCalculator(new Parameter(new Held[]{h}, 1, Arrays.asList(rasten)));
        instance.calculate(tag);
        ErgebnisTag result = instance.getTag(tag);
        assertThat(result.helden.toArray(), arrayContainingInAnyOrder(expResult.helden.toArray()));
        assertThat(result.ergebnis.get(h), arrayContaining(expResult.ergebnis.get(h)));
        assertThat(result.ergebnis.get(h).length, equalTo(expResult.ergebnis.get(h).length));
    }
    
    /**
     * Test of calculate method, of class ReiseCalculator.
     */
    @Test
    public void testCalculate_ContinuousCalculation() {
        System.out.println("calculate");
        int tag = 42;
        Held h = new Held("Rimaldo", 12, 0);
        ReiseCalculator instance = new ReiseCalculator(new Parameter(new Held[]{h}, 1, null));
        instance.calculate(tag);
        assertThat(instance.ergebnis.size(), equalTo(tag+1));
    }

    /**
     * Test of nextZustand method, of class ReiseCalculator.
     */
    @Test
    public void testNextZustand_Zero() {
        System.out.println("nextZustand");
        Held h = new Held("Rimaldo", 0, 0);
        ErgebnisTag.Zustand lastZustand = new ErgebnisTag.Zustand(0, 0);
        Rast rast = new Rast(0, 0, 0, 0);
        ReiseCalculator instance = new ReiseCalculator(new Parameter(new Held[]{h}, 0, null));
        ErgebnisTag.Zustand expResult = new ErgebnisTag.Zustand(0, 0);
        ErgebnisTag.Zustand result = instance.nextZustand(h, lastZustand, rast);
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of nextZustand method, of class ReiseCalculator.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextZustand_ThrowsFirstNull() {
        System.out.println("nextZustand");
        ReiseCalculator instance = new ReiseCalculator(new Parameter(new Held[0], 1, null));
        instance.nextZustand(null, new ErgebnisTag.Zustand(0, 0), null);
    }
    
    /**
     * Test of nextZustand method, of class ReiseCalculator.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextZustand_ThrowsSecondNull() {
        System.out.println("nextZustand");
        ReiseCalculator instance = new ReiseCalculator(new Parameter(new Held[0], 1, null));
        instance.nextZustand(new Held("Rimaldo", 0, 0), null, null);
    }

    /**
     * Test of nextZustand method, of class ReiseCalculator.
     */
    @Test
    public void testNextZustand_OneNoModNoRast() {
        System.out.println("nextZustand");
        Held h = new Held("Rimaldo", 11, 0);
        ErgebnisTag.Zustand lastZustand = new ErgebnisTag.Zustand(0, 0);
        Rast rast = null;
        ReiseCalculator instance = new ReiseCalculator(new Parameter(new Held[]{h}, 1, null));
        ErgebnisTag.Zustand expResult = new ErgebnisTag.Zustand(1, 0);
        ErgebnisTag.Zustand result = instance.nextZustand(h, lastZustand, rast);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of nextZustand method, of class ReiseCalculator.
     */
    @Test
    public void testNextZustand_TwoNoModNoRast() {
        System.out.println("nextZustand");
        Held h = new Held("Rimaldo", 11, 0);
        ErgebnisTag.Zustand lastZustand = new ErgebnisTag.Zustand(0, 0);
        Rast rast = null;
        ReiseCalculator instance = new ReiseCalculator(new Parameter(new Held[]{h}, 2, null));
        ErgebnisTag.Zustand expResult = new ErgebnisTag.Zustand(2, 0);
        ErgebnisTag.Zustand result = instance.nextZustand(h, lastZustand, rast);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of nextZustand method, of class ReiseCalculator.
     */
    @Test
    public void testNextZustand_TwoAndÜberanstrNoModNoRast() {
        System.out.println("nextZustand");
        Held h = new Held("Rimaldo", 1, 0);
        ErgebnisTag.Zustand lastZustand = new ErgebnisTag.Zustand(0, 0);
        Rast rast = null;
        ReiseCalculator instance = new ReiseCalculator(new Parameter(new Held[]{h}, 2, null));
        ErgebnisTag.Zustand expResult = new ErgebnisTag.Zustand(1, 1);
        ErgebnisTag.Zustand result = instance.nextZustand(h, lastZustand, rast);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of nextZustand method, of class ReiseCalculator.
     */
    @Test
    public void testNextZustand_TwoAndÜberanstrWithModNoRast() {
        System.out.println("nextZustand");
        Held h = new Held("Rimaldo", 1, 2);
        ErgebnisTag.Zustand lastZustand = new ErgebnisTag.Zustand(0, 0);
        Rast rast = null;
        ReiseCalculator instance = new ReiseCalculator(new Parameter(new Held[]{h}, 2, null));
        ErgebnisTag.Zustand expResult = new ErgebnisTag.Zustand(1, 3);
        ErgebnisTag.Zustand result = instance.nextZustand(h, lastZustand, rast);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of nextZustand method, of class ReiseCalculator.
     */
    @Test
    public void testNextZustand_WithZeroRast() {
        System.out.println("nextZustand");
        Held h = new Held("Rimaldo", 1, 0);
        ErgebnisTag.Zustand lastZustand = new ErgebnisTag.Zustand(0, 0);
        Rast rast = new Rast(0, 0, 0, 0);
        ReiseCalculator instance = new ReiseCalculator(new Parameter(new Held[]{h}, 2, null));
        ErgebnisTag.Zustand expResult = new ErgebnisTag.Zustand(0, 0);
        ErgebnisTag.Zustand result = instance.nextZustand(h, lastZustand, rast);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of nextZustand method, of class ReiseCalculator.
     */
    @Test
    public void testNextZustand_WithRastÜberanst() {
        System.out.println("nextZustand");
        Held h = new Held("Rimaldo", 1, 0);
        ErgebnisTag.Zustand lastZustand = new ErgebnisTag.Zustand(1, 1);
        Rast rast = new Rast(0, 0, 1, 1);
        ReiseCalculator instance = new ReiseCalculator(new Parameter(new Held[]{h}, 2, null));
        ErgebnisTag.Zustand expResult = new ErgebnisTag.Zustand(1, 0);
        ErgebnisTag.Zustand result = instance.nextZustand(h, lastZustand, rast);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of nextZustand method, of class ReiseCalculator.
     */
    @Test
    public void testNextZustand_WithRastNoÜberanst() {
        System.out.println("nextZustand");
        Held h = new Held("Rimaldo", 10, 0);
        ErgebnisTag.Zustand lastZustand = new ErgebnisTag.Zustand(1, 0);
        Rast rast = new Rast(0, 0, 1, 1);
        ReiseCalculator instance = new ReiseCalculator(new Parameter(new Held[]{h}, 2, null));
        ErgebnisTag.Zustand expResult = new ErgebnisTag.Zustand(0, 0);
        ErgebnisTag.Zustand result = instance.nextZustand(h, lastZustand, rast);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of nextZustand method, of class ReiseCalculator.
     */
    @Test
    public void testNextZustand_WithRastNegResult() {
        System.out.println("nextZustand");
        Held h = new Held("Rimaldo", 10, 0);
        ErgebnisTag.Zustand lastZustand = new ErgebnisTag.Zustand(0, 0);
        Rast rast = new Rast(0, 0, 2, 2);
        ReiseCalculator instance = new ReiseCalculator(new Parameter(new Held[]{h}, 2, null));
        ErgebnisTag.Zustand expResult = new ErgebnisTag.Zustand(0, 0);
        ErgebnisTag.Zustand result = instance.nextZustand(h, lastZustand, rast);
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of equals method, of class Held.
     */
    @Test
    public void testGetTag() {
        System.out.println("getTag");
        
        Held h = new Held("Rimaldo", 10, 0);
        ReiseCalculator instance = new ReiseCalculator(new Parameter(new Held[]{h}, 0, null));
        instance.getTag(2);
        assertThat(instance.ergebnis.size(), equalTo(3));
    }
    
    /**
     * Test of equals method, of class Held.
     */
    @Test
    public void dummyForCoverage() {
        System.out.println("dummy");
        
        ReiseCalculator instance = new ReiseCalculator(new Parameter(null, 0, null));
        instance.getParameter();
    }
}
