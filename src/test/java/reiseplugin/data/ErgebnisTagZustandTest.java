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
public class ErgebnisTagZustandTest {
    
    public ErgebnisTagZustandTest() {
    }
    
    /**
     * Test of constructor, of class ErgebnisTag.Zustand.
     */
    @Test
    public void testContructor_Ok() {
        System.out.println("constructor");
        ErgebnisTag.Zustand z = new ErgebnisTag.Zustand(0, 0);
    }
    
    /**
     * Test of constructor, of class ErgebnisTag.Zustand.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testContructor_ThrowsFirst() {
        System.out.println("constructor");
        ErgebnisTag.Zustand z = new ErgebnisTag.Zustand(-1, 0);
    }
    
    /**
     * Test of constructor, of class ErgebnisTag.Zustand.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testContructor_ThrowsSecond() {
        System.out.println("constructor");
        ErgebnisTag.Zustand z = new ErgebnisTag.Zustand(0, -1);
    }
    
    /**
     * Test of equals method, of class ErgebnisTag.
     */
    @Test
    public void testEquals_True() {
        System.out.println("equals");
        
        ErgebnisTag.Zustand z1 = new ErgebnisTag.Zustand(1, 5);
        ErgebnisTag.Zustand z2 = new ErgebnisTag.Zustand(1, 5);
        
        boolean expResult = true;
        boolean result = z1.equals(z2);
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of equals method, of class ErgebnisTag.
     */
    @Test
    public void testEquals_FalseFirst() {
        System.out.println("equals");
        
        ErgebnisTag.Zustand z1 = new ErgebnisTag.Zustand(1, 5);
        ErgebnisTag.Zustand z2 = new ErgebnisTag.Zustand(2, 5);
        
        boolean expResult = false;
        boolean result = z1.equals(z2);
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of equals method, of class ErgebnisTag.
     */
    @Test
    public void testEquals_FalseSecond() {
        System.out.println("equals");
        
        ErgebnisTag.Zustand z1 = new ErgebnisTag.Zustand(1, 5);
        ErgebnisTag.Zustand z2 = new ErgebnisTag.Zustand(1, 6);
        
        boolean expResult = false;
        boolean result = z1.equals(z2);
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of equals method, of class ErgebnisTag.
     */
    @Test
    public void testEquals_FalseNull() {
        System.out.println("equals");
        
        ErgebnisTag.Zustand z = new ErgebnisTag.Zustand(1, 5);
        
        boolean expResult = false;
        boolean result = z.equals(null);
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of equals method, of class ErgebnisTag.
     */
    @Test
    public void testEquals_FalseClass() {
        System.out.println("equals");
        
        ErgebnisTag.Zustand z = new ErgebnisTag.Zustand(1, 5);
        
        boolean expResult = false;
        boolean result = z.equals(new Object());
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of equals method, of class ErgebnisTag.
     */
    @Test
    public void testEquals_TrueSame() {
        System.out.println("equals");
        
        ErgebnisTag.Zustand z = new ErgebnisTag.Zustand(0, 0);
        
        boolean expResult = true;
        boolean result = z.equals(z);
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of equals method, of class ErgebnisTag.
     */
    @Test
    public void dummyForCoverage() {
        System.out.println("dummy");
        
        ErgebnisTag.Zustand z = new ErgebnisTag.Zustand(0, 0);
        z.hashCode();
        z.toString();
    }
}
