package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    Kassapaate paate;

    @Before
    public void setUp() {
        paate = new Kassapaate();
    }
    
    @Test
    public void luotuPaateOlemassa() {
        assertTrue(paate != null);      
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void myydytEdullisetLounaatAlussaOikein() {
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myydytMaukkaatLounaatAlussaOikein() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanKateisOstoToimii() {
        assertEquals(239, paate.syoEdullisesti(239));
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        
        assertEquals(0, paate.syoEdullisesti(240));
        assertEquals(1, paate.edullisiaLounaitaMyyty());
        
        assertEquals(60, paate.syoEdullisesti(300));
        assertEquals(2, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void MaukkaanLounaanKateisOstoToimii() {
        assertEquals(399, paate.syoMaukkaasti(399));
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        
        assertEquals(0, paate.syoMaukkaasti(400));
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
        
        assertEquals(200, paate.syoMaukkaasti(600));
        assertEquals(2, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanKorttiOstoToimii() {
        Maksukortti kortti = new Maksukortti(239);
        
        assertFalse(paate.syoEdullisesti(kortti));
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(239, kortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
        
        kortti = new Maksukortti(240);
        assertTrue(paate.syoEdullisesti(kortti));
        assertEquals(1, paate.edullisiaLounaitaMyyty());
        assertEquals(0, kortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
        
        kortti = new Maksukortti(300);
        assertTrue(paate.syoEdullisesti(kortti));
        assertEquals(2, paate.edullisiaLounaitaMyyty());
        assertEquals(60, kortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void maukkaanLounaanKorttiOstoToimii() {
        Maksukortti kortti = new Maksukortti(399);
        
        assertFalse(paate.syoMaukkaasti(kortti));
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        assertEquals(399, kortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
        
        kortti = new Maksukortti(400);
        assertTrue(paate.syoMaukkaasti(kortti));
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
        assertEquals(0, kortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
        
        kortti = new Maksukortti(600);
        assertTrue(paate.syoMaukkaasti(kortti));
        assertEquals(2, paate.maukkaitaLounaitaMyyty());
        assertEquals(200, kortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kortinLatausToimii() {
        Maksukortti kortti = new Maksukortti(100);
        
        paate.lataaRahaaKortille(kortti, 10);
        assertEquals(110, kortti.saldo());
        assertEquals(100010, paate.kassassaRahaa());
        
        paate.lataaRahaaKortille(kortti, 15);
        assertEquals(125, kortti.saldo());
        assertEquals(100025, paate.kassassaRahaa());
        
        paate.lataaRahaaKortille(kortti, -15);
        assertEquals(125, kortti.saldo());
        assertEquals(100025, paate.kassassaRahaa());
    }
}
