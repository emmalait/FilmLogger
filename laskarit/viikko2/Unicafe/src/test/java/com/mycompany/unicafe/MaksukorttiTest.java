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
    public void tarkistaSaldo() {
        assertEquals(10, kortti.saldo());      
    }
    
    @Test
    public void tarkistaSaldoAlussa() {
        assertEquals("saldo: 0.10", kortti.toString()); 
    }
    
    @Test
    public void rahanLatausToimiiOikein() {
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 10.10", kortti.toString()); 
    }
    
    @Test
    public void saldoMuuttuuOikeinKunTarpeeksiRahaa() {
        kortti.lataaRahaa(1000);
        kortti.otaRahaa(750);
        assertEquals("saldo: 2.60", kortti.toString()); 
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi() {
        kortti.otaRahaa(1000);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void saldoTrueJosRahaaTarpeeksi() {
        kortti.lataaRahaa(1000);
        assertTrue(kortti.otaRahaa(500)); 
    }
    
    @Test
    public void saldoFalseJosRahaaTarpeeksi() {
        assertFalse(kortti.otaRahaa(500)); 
    }
    
}
