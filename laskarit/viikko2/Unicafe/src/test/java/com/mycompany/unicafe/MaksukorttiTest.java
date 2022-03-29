package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals(10, kortti.saldo());
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenToimii() {
        kortti.lataaRahaa(100);
        assertEquals(110, kortti.saldo());
        assertEquals("saldo: 1.10", kortti.toString());
        kortti.lataaRahaa(1);
        assertEquals(111, kortti.saldo());
        assertEquals("saldo: 1.11", kortti.toString());
        kortti.lataaRahaa(2000000);
        assertEquals(2000111, kortti.saldo());
        assertEquals("saldo: 20001.11", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenToimii() {
        kortti.otaRahaa(7);
        assertEquals(3, kortti.saldo());
        assertEquals("saldo: 0.03", kortti.toString());
        kortti.otaRahaa(4);
        assertEquals(3, kortti.saldo());
        assertEquals("saldo: 0.03", kortti.toString());
        kortti.otaRahaa(3);
        assertEquals(0, kortti.saldo());
        assertEquals("saldo: 0.00", kortti.toString());
    }
}
