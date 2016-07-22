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
import java.util.Observer;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

/**
 *
 * @author Luca Corbatto
 */
@RunWith(MockitoJUnitRunner.class)
public class ParameterTest {
    
    @Mock
    protected Observer observer;
    
    public ParameterTest() {
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
    
    /**
     * Test of the Obsevable behavior, of class Parameter.
     */
    @Test
    public void testObservable_UpdateErschöpfung() {
        Parameter parameter = new Parameter(null, 0, null);
        parameter.addObserver(this.observer);
        parameter.setErschöpfungProStunde(1);
        
        verify(this.observer).update(parameter, null);
    }
    
    /**
     * Test of the Obsevable behavior, of class Parameter.
     */
    @Test
    public void testObservable_NoUpdateErschöpfung() {
        Parameter parameter = new Parameter(null, 0, null);
        parameter.addObserver(this.observer);
        parameter.setErschöpfungProStunde(0);
        
        verifyNoMoreInteractions(this.observer);
    }
    
    /**
     * Test of the Obsevable behavior, of class Parameter.
     */
    @Test
    public void testObservable_UpdateAddRast() {
        Parameter parameter = new Parameter(null, 0, null);
        parameter.addObserver(this.observer);
        
        Rast rast = new Rast(0, 0, 0, 0);
        parameter.addRast(rast);
        parameter.addRast(rast);
        
        verify(this.observer, times(1)).update(parameter, null);
        verifyNoMoreInteractions(this.observer);
    }
    
    /**
     * Test of the Obsevable behavior, of class Parameter.
     */
    @Test
    public void testObservable_NoUpdateAddRast() {
        Rast rast = new Rast(0, 0, 0, 0);
        
        Parameter parameter = new Parameter(null, 0, Arrays.asList(new Rast[]{rast}));
        parameter.addObserver(this.observer);
        
        parameter.addRast(rast);
        
        verifyNoMoreInteractions(this.observer);
    }
    
    /**
     * Test of the Obsevable behavior, of class Parameter.
     */
    @Test
    public void testObservable_UpdateRemoveRast() {
        Rast rast = new Rast(0, 0, 0, 0);
        
        Parameter parameter = new Parameter(null, 0, Arrays.asList(new Rast[]{rast}));
        parameter.addObserver(this.observer);
        
        parameter.removeRast(rast);
        parameter.removeRast(rast);
        
        verify(this.observer, times(1)).update(parameter, null);
        verifyNoMoreInteractions(this.observer);
    }
    
    /**
     * Test of the Obsevable behavior, of class Parameter.
     */
    @Test
    public void testObservable_NoUpdateRemoveRast() {
        Rast rast = new Rast(0, 0, 0, 0);
        Rast rast2 = new Rast(1, 2, 0, 0);
        
        Parameter parameter = new Parameter(null, 0, Arrays.asList(new Rast[]{rast}));
        parameter.addObserver(this.observer);
        
        parameter.removeRast(rast2);
        
        verifyNoMoreInteractions(this.observer);
    }
}
