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
public class ReiseCalculatorIT {
    
    @Mock
    protected Observer observer;
    
    public ReiseCalculatorIT() {
    }

    /**
     * Test of the Obsevable behavior, of class ReiseCalculator.
     */
    @Test
    public void testObservablePropagation_ModifyParameter() {
        Parameter parameter = new Parameter(null, 0, null);
        ReiseCalculator rc = new ReiseCalculator(parameter);
        rc.addObserver(this.observer);
        
        // Only test the shallow propagation because propagaiton from Rast/Held
        // -> Parameter is tested in ParameterIT.
        parameter.setErschöpfungProStunde(1);
        
        verify(this.observer, times(1)).update(rc, parameter);
        verifyNoMoreInteractions(this.observer);
    }
}
