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

import helden.plugin.datenxmlplugin.DatenAustausch3Interface;
import java.math.BigInteger;
import java.util.Arrays;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import reiseplugin.data.helden.entities.Daten;
import reiseplugin.data.helden.entities.HeldenService;
import reiseplugin.data.helden.entities.jaxb.Angaben;
import reiseplugin.data.helden.entities.jaxb.Eigenschaften;
import reiseplugin.data.helden.entities.jaxb.Eigenschaftswerte;

/**
 *
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {
    
    @Mock
    protected HeldenService heldenService;
    
    public ServiceTest() {
    }
    
    protected void prepareHeldenService() {
        Daten d1 = new Daten();
        d1.setAngaben(new Angaben());
        d1.setEigenschaften(new Eigenschaften());
        d1.getEigenschaften().setKonstitution(new Eigenschaftswerte());
        Daten d2 = new Daten();
        d2.setAngaben(new Angaben());
        d2.setEigenschaften(new Eigenschaften());
        d2.getEigenschaften().setKonstitution(new Eigenschaftswerte());
        Daten d3 = new Daten();
        d3.setAngaben(new Angaben());
        d3.setEigenschaften(new Eigenschaften());
        d3.getEigenschaften().setKonstitution(new Eigenschaftswerte());
        
        d1.getAngaben().setName("Rimaldo");
        d1.getEigenschaften().getKonstitution().setAkt(new BigInteger("11"));
        d2.getAngaben().setName("Targodan");
        d2.getEigenschaften().getKonstitution().setAkt(new BigInteger("14"));
        d3.getAngaben().setName("Verdurius");
        d3.getEigenschaften().getKonstitution().setAkt(new BigInteger("8"));
        
        when(this.heldenService.getAllHelden()).thenReturn(Arrays.asList(
                d1,
                d2,
                d3
        ));
    }
    
    protected Held[] getPreparedEquivalent() {
        return new Held[]{
            new Held("Rimaldo", 11, 0),
            new Held("Targodan", 14, 0),
            new Held("Verdurius", 8, 0),
        };
    }

    /**
     * Test of constructor, of class Service.
     */
    @Test
    public void testConstructor_Ok() {
        System.out.println("constructor");
        Service instance = new Service(this.heldenService);
    }

    /**
     * Test of constructor, of class Service.
     * @deprecated 
     */
    @Test
    public void testConstructor_DeprecatedOk() {
        System.out.println("constructor");
        Service instance = new Service(mock(DatenAustausch3Interface.class));
    }

    /**
     * Test of constructor, of class Service.
     * @deprecated 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_DeprecatedThrows() {
        System.out.println("constructor");
        Service instance = new Service((DatenAustausch3Interface)null);
    }

    /**
     * Test of constructor, of class Service.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_ThrowsNull() {
        System.out.println("constructor");
        Service instance = new Service((HeldenService)null);
    }
    
    /**
     * Test of nativeToHeld method, of class Service.
     */
    @Test
    public void testNativeToHeld() {
        System.out.println("nativeToHeld");
        
        Daten d = new Daten();
        d.setAngaben(new Angaben());
        d.setEigenschaften(new Eigenschaften());
        d.getEigenschaften().setKonstitution(new Eigenschaftswerte());
        d.getAngaben().setName("Rimaldo");
        d.getEigenschaften().getKonstitution().setAkt(new BigInteger("11"));
        
        Service instance = new Service(this.heldenService);
        Held expResult = new Held("Rimaldo", 11, 0);
        Held result = instance.nativeToHeld(d);
        assertThat(expResult, equalTo(result));
    }

    /**
     * Test of getAllHelden method, of class Service.
     */
    @Test
    public void testGetAllHelden() {
        System.out.println("getAllHelden");
        
        this.prepareHeldenService();
        Held[] expResult = this.getPreparedEquivalent();
        
        Service instance = new Service(this.heldenService);
        Held[] result = instance.getAllHelden();
        assertThat(result, arrayContaining(expResult));
        assertThat(result.length, equalTo(expResult.length));
    }
}
