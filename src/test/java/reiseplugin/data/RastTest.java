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
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;


/**
 *
 * @author Luca Corbatto
 */
@RunWith(MockitoJUnitRunner.class)
public class RastTest {
    
    @Mock
    protected Observer observer;
    
    public RastTest() {
    }
    
    /**
     * Test of constructor, of class Rast.
     */
    @Test
    public void testConstructor_OkLow() {
        System.out.println("setStart");
        Rast instance = new Rast(0, 0, 0, 0);
    }
    
    /**
     * Test of constructor, of class Rast.
     */
    @Test
    public void testConstructor_OkHigh() {
        System.out.println("setStart");
        Rast instance = new Rast(23, 23, 0, 0);
    }
    
    /**
     * Test of constructor, of class Rast.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_FailStartNeg() {
        System.out.println("setStart");
        Rast instance = new Rast(-1, 0, 0, 0);
    }
    
    /**
     * Test of constructor, of class Rast.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_FailStartHigh() {
        System.out.println("setStart");
        Rast instance = new Rast(24, 0, 0, 0);
    }
    
    /**
     * Test of constructor, of class Rast.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_FailEndeNeg() {
        System.out.println("setStart");
        Rast instance = new Rast(0, -1, 0, 0);
    }
    
    /**
     * Test of constructor, of class Rast.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_FailEndeHigh() {
        System.out.println("setStart");
        Rast instance = new Rast(0, 24, 0, 0);
    }
    
    /**
     * Test of constructor, of class Rast.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_FailErschNeg() {
        System.out.println("setStart");
        Rast instance = new Rast(0, 0, -1, 0);
    }
    
    /**
     * Test of constructor, of class Rast.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_FailÜberanstNeg() {
        System.out.println("setStart");
        Rast instance = new Rast(0, 0, 0, -1);
    }
    
    /**
     * Test of setStart method, of class Rast.
     */
    @Test
    public void testSetStart_OkLow() {
        System.out.println("setStart");
        int start = 0;
        Rast instance = new Rast(0, 0, 0, 0);
        instance.setStart(start);
    }

    /**
     * Test of setStart method, of class Rast.
     */
    @Test
    public void testSetStart_OkHigh() {
        System.out.println("setStart");
        int start = 23;
        Rast instance = new Rast(0, 0, 0, 0);
        instance.setStart(start);
    }

    /**
     * Test of setStart method, of class Rast.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetStart_Negative() {
        System.out.println("setStart");
        int start = -1;
        Rast instance = new Rast(0, 0, 0, 0);
        instance.setStart(start);
    }

    /**
     * Test of setStart method, of class Rast.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetStart_TooLarge() {
        System.out.println("setStart");
        int start = 24;
        Rast instance = new Rast(0, 0, 0, 0);
        instance.setStart(start);
    }

    /**
     * Test of setEnde method, of class Rast.
     */
    @Test
    public void testSetEnde_OkLow() {
        System.out.println("setEnde");
        int ende = 0;
        Rast instance = new Rast(0, 0, 0, 0);
        instance.setEnde(ende);
    }

    /**
     * Test of setEnde method, of class Rast.
     */
    @Test
    public void testSetEnde_OkHigh() {
        System.out.println("setEnde");
        int ende = 23;
        Rast instance = new Rast(0, 0, 0, 0);
        instance.setEnde(ende);
    }

    /**
     * Test of setEnde method, of class Rast.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetEnde_Negative() {
        System.out.println("setEnde");
        int ende = -1;
        Rast instance = new Rast(0, 0, 0, 0);
        instance.setEnde(ende);
    }

    /**
     * Test of setEnde method, of class Rast.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetEnde_TooLarge() {
        System.out.println("setEnde");
        int ende = 24;
        Rast instance = new Rast(0, 0, 0, 0);
        instance.setEnde(ende);
    }

    /**
     * Test of setErschöpfungProStunde method, of class Rast.
     */
    @Test
    public void testSetErschöpfungProStunde_Ok() {
        System.out.println("setErsch\u00f6pfungProStunde");
        int erschöpfungProStunde = 0;
        Rast instance = new Rast(0, 0, 0, 0);
        instance.setErschöpfungProStunde(erschöpfungProStunde);
    }

    /**
     * Test of setErschöpfungProStunde method, of class Rast.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetErschöpfungProStunde_Negative() {
        System.out.println("setErsch\u00f6pfungProStunde");
        int erschöpfungProStunde = -1;
        Rast instance = new Rast(0, 0, 0, 0);
        instance.setErschöpfungProStunde(erschöpfungProStunde);
    }

    /**
     * Test of setÜberanstrengungProStunde method, of class Rast.
     */
    @Test
    public void testSetÜberanstrengungProStunde_Ok() {
        System.out.println("set\u00dcberanstrengungProStunde");
        int überanstrengungProStunde = 0;
        Rast instance = new Rast(0, 0, 0, 0);
        instance.setÜberanstrengungProStunde(überanstrengungProStunde);
    }

    /**
     * Test of setÜberanstrengungProStunde method, of class Rast.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetÜberanstrengungProStunde_Negative() {
        System.out.println("set\u00dcberanstrengungProStunde");
        int überanstrengungProStunde = -1;
        Rast instance = new Rast(0, 0, 0, 0);
        instance.setÜberanstrengungProStunde(überanstrengungProStunde);
    }

    /**
     * Test of matchStunde method, of class Rast.
     */
    @Test
    public void testMatchStunde_MatchEasy() {
        System.out.println("matchStunde");
        int st = 12;
        Rast instance = new Rast(12, 13, 0, 0);
        boolean expResult = true;
        boolean result = instance.matchStunde(st);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of matchStunde method, of class Rast.
     */
    @Test
    public void testMatchStunde_NoMatchEasyBefore() {
        System.out.println("matchStunde");
        int st = 11;
        Rast instance = new Rast(12, 13, 0, 0);
        boolean expResult = false;
        boolean result = instance.matchStunde(st);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of matchStunde method, of class Rast.
     */
    @Test
    public void testMatchStunde_NoMatchEasyAfter() {
        System.out.println("matchStunde");
        int st = 13;
        Rast instance = new Rast(12, 13, 0, 0);
        boolean expResult = false;
        boolean result = instance.matchStunde(st);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of matchStunde method, of class Rast.
     */
    @Test
    public void testMatchStunde_MatchStartComplex() {
        System.out.println("matchStunde");
        int st = 13;
        Rast instance = new Rast(13, 12, 0, 0);
        boolean expResult = true;
        boolean result = instance.matchStunde(st);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of matchStunde method, of class Rast.
     */
    @Test
    public void testMatchStunde_MatchEndComplex() {
        System.out.println("matchStunde");
        int st = 11;
        Rast instance = new Rast(13, 12, 0, 0);
        boolean expResult = true;
        boolean result = instance.matchStunde(st);
        assertThat(result, equalTo(expResult));
    }

    /**
     * Test of matchStunde method, of class Rast.
     */
    @Test
    public void testMatchStunde_NoMatchComplex() {
        System.out.println("matchStunde");
        int st = 12;
        Rast instance = new Rast(13, 12, 0, 0);
        boolean expResult = false;
        boolean result = instance.matchStunde(st);
        assertThat(result, equalTo(expResult));
    }
    
    /**
     * Test of the Obsevable behavior, of class Rast.
     */
    @Test
    public void testObservable_UpdateStart() {
        Rast rast = new Rast(0, 0, 0, 0);
        rast.addObserver(this.observer);
        rast.setStart(1);
        
        verify(this.observer).update(rast, null);
    }
    
    /**
     * Test of the Obsevable behavior, of class Rast.
     */
    @Test
    public void testObservable_NoUpdateStart() {
        Rast rast = new Rast(0, 0, 0, 0);
        rast.addObserver(this.observer);
        rast.setStart(0);
        
        verifyNoMoreInteractions(this.observer);
    }
    
    /**
     * Test of the Obsevable behavior, of class Rast.
     */
    @Test
    public void testObservable_UpdateEnde() {
        Rast rast = new Rast(0, 0, 0, 0);
        rast.addObserver(this.observer);
        rast.setEnde(1);
        
        verify(this.observer).update(rast, null);
    }
    
    /**
     * Test of the Obsevable behavior, of class Rast.
     */
    @Test
    public void testObservable_NoUpdateEnde() {
        Rast rast = new Rast(0, 0, 0, 0);
        rast.addObserver(this.observer);
        rast.setEnde(0);
        
        verifyNoMoreInteractions(this.observer);
    }
    
    /**
     * Test of the Obsevable behavior, of class Rast.
     */
    @Test
    public void testObservable_UpdateErschöpfung() {
        Rast rast = new Rast(0, 0, 0, 0);
        rast.addObserver(this.observer);
        rast.setErschöpfungProStunde(1);
        
        verify(this.observer).update(rast, null);
    }
    
    /**
     * Test of the Obsevable behavior, of class Rast.
     */
    @Test
    public void testObservable_NoUpdateErschöpfung() {
        Rast rast = new Rast(0, 0, 0, 0);
        rast.addObserver(this.observer);
        rast.setErschöpfungProStunde(0);
        
        verifyNoMoreInteractions(this.observer);
    }
    
    /**
     * Test of the Obsevable behavior, of class Rast.
     */
    @Test
    public void testObservable_UpdateÜberanstrengung() {
        Rast rast = new Rast(0, 0, 0, 0);
        rast.addObserver(this.observer);
        rast.setÜberanstrengungProStunde(1);
        
        verify(this.observer).update(rast, null);
    }
    
    /**
     * Test of the Obsevable behavior, of class Rast.
     */
    @Test
    public void testObservable_NoUpdateÜberanstrengung() {
        Rast rast = new Rast(0, 0, 0, 0);
        rast.addObserver(this.observer);
        rast.setÜberanstrengungProStunde(0);
        
        verifyNoMoreInteractions(this.observer);
    }
}
