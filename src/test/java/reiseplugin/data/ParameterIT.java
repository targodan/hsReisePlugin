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
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
 */
@RunWith(MockitoJUnitRunner.class)
public class ParameterIT {
    
    @Mock
    protected Observer observer;
    
    public ParameterIT() {
    }
    
    /**
     * Test of the Obsevable behavior, of class Parameter.
     */
    @Test
    public void testObservablePropagation_ModifyHeld() {
        Held held = new Held("", 0, 0);
        
        Parameter parameter = new Parameter(new Held[]{held}, 0, null);
        parameter.addObserver(this.observer);
        
        held.setMod(1);
        
        verify(this.observer, times(1)).update(parameter, held);
        verifyNoMoreInteractions(this.observer);
    }
    
    /**
     * Test of the Obsevable behavior, of class Parameter.
     */
    @Test
    public void testObservablePropagation_ModifyRast() {
        Rast rast = new Rast(0, 0, 0, 0);
        
        Parameter parameter = new Parameter(null, 0, Arrays.asList(rast));
        parameter.addObserver(this.observer);
        
        // Only test with setStart as a representative because the RastTest
        // already tests, that all setters invoke the notification.
        rast.setStart(1);
        
        verify(this.observer, times(1)).update(parameter, rast);
        verifyNoMoreInteractions(this.observer);
    }
}
