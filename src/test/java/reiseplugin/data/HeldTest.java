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

import java.util.Observer;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

/**
 *
 * @author Luca Corbatto
 */
@RunWith(MockitoJUnitRunner.class)
public class HeldTest {
    
    @Mock
    protected Observer observer;
    
    public HeldTest() {
    }

    /**
     * Test of constructor, of class Held.
     */
    @Test
    public void testConstructor_Ok() {
        System.out.println("testConstructor");
        Held held = new Held("", 0, 0);
    }

    /**
     * Test of constructor, of class Held.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_ThrowsNegative() {
        System.out.println("testConstructor");
        Held held = new Held("", -1, 0);
    }
    
    /**
     * Test of the Obsevable behavior, of class Rast.
     */
    @Test
    public void testObservable_UpdateMod() {
        Held held = new Held("", 0, 0);
        held.addObserver(this.observer);
        held.setMod(1);
        
        verify(this.observer).update(held, null);
    }
    
    /**
     * Test of the Obsevable behavior, of class Rast.
     */
    @Test
    public void testObservable_NoUpdateMod() {
        Held held = new Held("", 0, 0);
        held.addObserver(this.observer);
        held.setMod(0);
        
        verifyNoMoreInteractions(this.observer);
    }
    
    /**
     * Test of equals method, of class Held.
     */
    @Test
    public void testEquals_True() {
        System.out.println("equals");
        
        Held h1 = new Held("Rimaldo", 0, 0);
        Held h2 = new Held("Rimaldo", 0, 0);
        
        boolean expResult = true;
        boolean result = h1.equals(h2);
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of equals method, of class Held.
     */
    @Test
    public void testEquals_FalseFirst() {
        System.out.println("equals");
        
        Held h1 = new Held("Rimaldo", 0, 0);
        Held h2 = new Held("RimaldO", 0, 0);
        
        boolean expResult = false;
        boolean result = h1.equals(h2);
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of equals method, of class Held.
     */
    @Test
    public void testEquals_FalseSecond() {
        System.out.println("equals");
        
        Held h1 = new Held("Rimaldo", 0, 0);
        Held h2 = new Held("Rimaldo", 1, 0);
        
        boolean expResult = false;
        boolean result = h1.equals(h2);
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of equals method, of class Held.
     */
    @Test
    public void testEquals_FalseThird() {
        System.out.println("equals");
        
        Held h1 = new Held("Rimaldo", 0, 0);
        Held h2 = new Held("Rimaldo", 0, 1);
        
        boolean expResult = false;
        boolean result = h1.equals(h2);
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of equals method, of class Held.
     */
    @Test
    public void testEquals_FalseNull() {
        System.out.println("equals");
        
        Held h1 = new Held("Rimaldo", 0, 0);
        
        boolean expResult = false;
        boolean result = h1.equals(null);
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of equals method, of class Held.
     */
    @Test
    public void testEquals_FalseClass() {
        System.out.println("equals");
        
        Held h1 = new Held("Rimaldo", 0, 0);
        
        boolean expResult = false;
        boolean result = h1.equals(new Object());
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of equals method, of class Held.
     */
    @Test
    public void dummyForCoverage() {
        System.out.println("dummy");
        
        Held held = new Held("", 0, 0);
        held.getName();
        held.getKO();
        held.getMod();
        held.hashCode();
        held.toString();
    }
}
