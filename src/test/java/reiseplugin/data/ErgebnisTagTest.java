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
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
 */
public class ErgebnisTagTest {
    
    public ErgebnisTagTest() {
    }

    /**
     * Test of addHeld method, of class ErgebnisTag.
     */
    @Test
    public void testAddHeld_Add() {
        System.out.println("addHeld");
        Held h = new Held("Rimalde", 0, 0);
        ErgebnisTag instance = new ErgebnisTag();
        int expected = instance.helden.size() + 1;
        instance.addHeld(h);
        assertThat(instance.helden.size(), equalTo(expected));
    }

    /**
     * Test of addHeld method, of class ErgebnisTag.
     */
    @Test
    public void testAddHeld_DontAdd() {
        System.out.println("addHeld");
        Held h = new Held("Rimalde", 0, 0);
        ErgebnisTag instance = new ErgebnisTag();
        instance.addHeld(h);
        int expected = instance.helden.size();
        instance.addHeld(h);
        assertThat(instance.helden.size(), equalTo(expected));
    }

    /**
     * Test of addHeld method, of class ErgebnisTag.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddHeld_Throws() {
        System.out.println("addHeld");
        Held h = null;
        ErgebnisTag instance = new ErgebnisTag();
        instance.addHeld(h);
    }

    /**
     * Test of setZustand method, of class ErgebnisTag.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetZustand_3args_ThrowsHeld() {
        System.out.println("setZustand");
        Held held = new Held("Rimaldo", 0, 0);
        int stunde = 0;
        ErgebnisTag.Zustand z = null;
        ErgebnisTag instance = new ErgebnisTag();
        instance.setZustand(held, stunde, z);
    }

    /**
     * Test of setZustand method, of class ErgebnisTag.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetZustand_3args_ThrowsTimeBefore() {
        System.out.println("setZustand");
        Held held = new Held("Rimaldo", 0, 0);
        int stunde = -1;
        ErgebnisTag.Zustand z = null;
        ErgebnisTag instance = new ErgebnisTag();
        instance.addHeld(held);
        instance.setZustand(held, stunde, z);
    }

    /**
     * Test of setZustand method, of class ErgebnisTag.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetZustand_3args_ThrowsTimeAfter() {
        System.out.println("setZustand");
        Held held = new Held("Rimaldo", 0, 0);
        int stunde = 24;
        ErgebnisTag.Zustand z = null;
        ErgebnisTag instance = new ErgebnisTag();
        instance.addHeld(held);
        instance.setZustand(held, stunde, z);
    }

    /**
     * Test of setZustand method, of class ErgebnisTag.
     */
    @Test
    public void testSetZustand_3args_DontThrows() {
        System.out.println("setZustand");
        Held held = new Held("Rimaldo", 0, 0);
        int stunde = 0;
        ErgebnisTag.Zustand z = null;
        ErgebnisTag instance = new ErgebnisTag();
        instance.addHeld(held);
        instance.setZustand(held, stunde, z);
    }

    /**
     * Test of setZustand method, of class ErgebnisTag.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetZustand_4args_ThrowsHeld() {
        System.out.println("setZustand");
        Held held = new Held("Rimaldo", 0, 0);
        int stunde = 0;
        int ersch = 0;
        int überanst = 0;
        ErgebnisTag instance = new ErgebnisTag();
        instance.setZustand(held, stunde, ersch, überanst);
    }

    /**
     * Test of setZustand method, of class ErgebnisTag.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetZustand_4args_ThrowsTimeBefore() {
        System.out.println("setZustand");
        Held held = new Held("Rimaldo", 0, 0);
        int stunde = -1;
        int ersch = 0;
        int überanst = 0;
        ErgebnisTag instance = new ErgebnisTag();
        instance.addHeld(held);
        instance.setZustand(held, stunde, ersch, überanst);
    }

    /**
     * Test of setZustand method, of class ErgebnisTag.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetZustand_4args_ThrowsTimeAfter() {
        System.out.println("setZustand");
        Held held = new Held("Rimaldo", 0, 0);
        int stunde = 24;
        int ersch = 0;
        int überanst = 0;
        ErgebnisTag instance = new ErgebnisTag();
        instance.addHeld(held);
        instance.setZustand(held, stunde, ersch, überanst);
    }

    /**
     * Test of setZustand method, of class ErgebnisTag.
     */
    @Test
    public void testSetZustand_4args_DontThrows() {
        System.out.println("setZustand");
        Held held = new Held("Rimaldo", 0, 0);
        int stunde = 0;
        int ersch = 0;
        int überanst = 0;
        ErgebnisTag instance = new ErgebnisTag();
        instance.addHeld(held);
        instance.setZustand(held, stunde, ersch, überanst);
    }

    /**
     * Test of getZustand method, of class ErgebnisTag.
     */
    @Test
    public void testGetZustand_DontThrows() {
        System.out.println("getZustand");
        Held h = new Held("Rimaldo", 0, 0);
        int stunde = 0;
        ErgebnisTag instance = new ErgebnisTag();
        instance.addHeld(h);
        ErgebnisTag.Zustand expResult = new ErgebnisTag.Zustand(0, 0);
        instance.setZustand(h, stunde, expResult);
        ErgebnisTag.Zustand result = instance.getZustand(h, stunde);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of getZustand method, of class ErgebnisTag.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetZustand_ThrowsHeld() {
        System.out.println("getZustand");
        Held h = new Held("Rimaldo", 0, 0);
        int stunde = 0;
        ErgebnisTag instance = new ErgebnisTag();
        ErgebnisTag.Zustand result = instance.getZustand(h, stunde);
    }

    /**
     * Test of getZustand method, of class ErgebnisTag.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetZustand_ThrowsTimeBefore() {
        System.out.println("getZustand");
        Held h = new Held("Rimaldo", 0, 0);
        int stunde = -1;
        ErgebnisTag instance = new ErgebnisTag();
        instance.addHeld(h);
        ErgebnisTag.Zustand result = instance.getZustand(h, stunde);
    }

    /**
     * Test of getZustand method, of class ErgebnisTag.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetZustand_ThrowsTimeAfter() {
        System.out.println("getZustand");
        Held h = new Held("Rimaldo", 0, 0);
        int stunde = 24;
        ErgebnisTag instance = new ErgebnisTag();
        instance.addHeld(h);
        ErgebnisTag.Zustand result = instance.getZustand(h, stunde);
    }

    /**
     * Test of getHelden method, of class ErgebnisTag.
     */
    @Test
    public void testGetHelden() {
        System.out.println("getHelden");
        ErgebnisTag instance = new ErgebnisTag();
        List<Held> expResult = new ArrayList<>();
        List<Held> result = instance.getHelden();
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of equals method, of class ErgebnisTag.
     */
    @Test
    public void testEquals_True() {
        System.out.println("equals");
        
        Held h1 = new Held("Rimaldo", 0, 0);
        Held h2 = new Held("Rimaldo", 0, 0);
        
        ErgebnisTag other = new ErgebnisTag();
        other.addHeld(h1);
        other.setZustand(h1, 0, 1, 2);
        other.setZustand(h1, 1, 2, 3);
        
        ErgebnisTag instance = new ErgebnisTag();
        instance.addHeld(h2);
        instance.setZustand(h2, 0, 1, 2);
        instance.setZustand(h2, 1, 2, 3);
        
        Object obj = other;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of equals method, of class ErgebnisTag.
     */
    @Test
    public void testEquals_TrueSame() {
        System.out.println("equals");
        
        Held h1 = new Held("Rimaldo", 0, 0);
        ErgebnisTag instance = new ErgebnisTag();
        instance.addHeld(h1);
        instance.setZustand(h1, 0, 1, 2);
        instance.setZustand(h1, 1, 1, 3);
        
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of equals method, of class ErgebnisTag.
     */
    @Test
    public void testEquals_FalseClass() {
        System.out.println("equals");
        
        Held h1 = new Held("Rimaldo", 0, 0);
        ErgebnisTag instance = new ErgebnisTag();
        instance.addHeld(h1);
        instance.setZustand(h1, 0, 1, 2);
        instance.setZustand(h1, 1, 1, 3);
        
        boolean expResult = false;
        boolean result = instance.equals(new Object());
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of equals method, of class ErgebnisTag.
     */
    @Test
    public void testEquals_FalseNull() {
        System.out.println("equals");
        
        Held h1 = new Held("Rimaldo", 0, 0);
        ErgebnisTag instance = new ErgebnisTag();
        instance.addHeld(h1);
        instance.setZustand(h1, 0, 1, 2);
        instance.setZustand(h1, 1, 1, 3);
        
        boolean expResult = false;
        boolean result = instance.equals(null);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of equals method, of class ErgebnisTag.
     */
    @Test
    public void testEquals_FalseZustand() {
        System.out.println("equals");
        
        Held h1 = new Held("Rimaldo", 0, 0);
        Held h2 = new Held("Rimaldo", 0, 0);
        
        ErgebnisTag other = new ErgebnisTag();
        other.addHeld(h1);
        other.setZustand(h1, 0, 2, 2);
        other.setZustand(h1, 1, 2, 3);
        
        ErgebnisTag instance = new ErgebnisTag();
        instance.addHeld(h2);
        instance.setZustand(h2, 0, 1, 2);
        instance.setZustand(h2, 1, 2, 3);
        
        Object obj = other;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of equals method, of class ErgebnisTag.
     */
    @Test
    public void testEquals_FalseHeld() {
        System.out.println("equals");
        
        Held h1 = new Held("Rimaldo", 0, 0);
        Held h2 = new Held("RimaldO", 0, 0);
        
        ErgebnisTag other = new ErgebnisTag();
        other.addHeld(h1);
        other.setZustand(h1, 0, 2, 2);
        other.setZustand(h1, 1, 2, 3);
        
        ErgebnisTag instance = new ErgebnisTag();
        instance.addHeld(h2);
        instance.setZustand(h2, 0, 1, 2);
        instance.setZustand(h2, 1, 2, 3);
        
        Object obj = other;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of equals method, of class ErgebnisTag.
     */
    @Test
    public void testEquals_FalseHeld2() {
        System.out.println("equals");
        
        Held h1 = new Held("Rimaldo", 0, 0);
        Held h2 = new Held("Rimaldo", 0, 0);
        Held h3 = new Held("RimaldO", 0, 0);
        
        ErgebnisTag other = new ErgebnisTag();
        other.addHeld(h1);
        
        ErgebnisTag instance = new ErgebnisTag();
        instance.addHeld(h2);
        instance.addHeld(h3);
        
        Object obj = other;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of equals method, of class ErgebnisTag.
     */
    @Test
    public void dummyForCoverage() {
        System.out.println("dummy");
        
        ErgebnisTag instance = new ErgebnisTag();
        instance.hashCode();
        instance.toString();
    }
}
