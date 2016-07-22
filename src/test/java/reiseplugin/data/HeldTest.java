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
}
