/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    Kassapaate paate;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
    }
    
    // Kassan saldo ja lounaiden määrä alussa
    
    @Test
    public void rahamaaraOikeaAlussa() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void lounaidenMaaraOikeaAlussa() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty() + paate.edullisiaLounaitaMyyty());
    }
    
    // Käteismaksu + edullinen lounas
    
    @Test
    public void edullisenLounaanOstoLounaidenMaarassaOikeinKunRiittavaMaksu() {
        paate.syoEdullisesti(300);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanOstoKassanSaldossaOikeinKunRiittavaMaksu() {
        paate.syoEdullisesti(240);
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    @Test
    public void vaihtorahaOikeinEdullisenLounaanOstossaKunLiikamaksu() {
        assertEquals(60, paate.syoEdullisesti(300));
    }
    
    @Test
    public void edullisenLounaanOstoLounaidenMaarassaOikeinKunRiittamatonMaksu() {
        paate.syoEdullisesti(200);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanOstoKassanSaldossaOikeinKunRiittamatonMaksu() {
        paate.syoEdullisesti(200);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void edullisenLounaanOstoVaihtorahaOikeinKunRiittamatonMaksu() {
        assertEquals(200, paate.syoEdullisesti(200));
    }
    
    // Käteismaksu + maukas lounas
   
    @Test
    public void maukkaanLounaanOstoLounaidenMaarassaOikeinKunRiittavaMaksu() {
        paate.syoMaukkaasti(400);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanOstoKassanSaldossaOikeinKunRiittavaMaksu() {
        paate.syoMaukkaasti(400);
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void maukkaanOikeinEdullisenLounaanOstossaKunLiikamaksu() {
        assertEquals(100, paate.syoMaukkaasti(500));
    }
    
    @Test
    public void maukkaanLounaanOstoLounaidenMaarassaOikeinKunRiittamatonMaksu() {
        paate.syoMaukkaasti(300);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanOstoKassanSaldossaOikeinKunRiittamatonMaksu() {
        paate.syoMaukkaasti(300);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void maukkaanLounaanOstoVaihtorahaOikeinKunRiittamatonMaksu() {
        assertEquals(300, paate.syoMaukkaasti(300));
    }
    
    // Maksukortti + edullinen lounas
    
    @Test
    public void edullisenLounaanOstoLounaidenMaarassaOikeinKunKortillaRiittavastiRahaa() {
        Maksukortti kortti = new Maksukortti(10000);
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanOstoKassanSaldossaOikeinKunKortillaRiittavastiRahaa() {
        Maksukortti kortti = new Maksukortti(10000);
        paate.syoEdullisesti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void edullisenLounaanOstoPalautaTrueKunKortillaRiittavastiRahaa() {
        Maksukortti kortti = new Maksukortti(10000);
        assertTrue(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void edullisenLounaanOstoKortinSaldoOikeinKunKortillaRiittavastiRahaa() {
        Maksukortti kortti = new Maksukortti(10000);
        paate.syoEdullisesti(kortti);
        assertEquals(9760, kortti.saldo());
    }
    
    @Test
    public void edullisenLounaanOstoLounaidenMaarassaOikeinKunKortillaEiRiittavastiRahaa() {
        Maksukortti kortti = new Maksukortti(100);
        paate.syoEdullisesti(kortti);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanOstoKassanSaldossaOikeinKunKortillaEiRiittavastiRahaa() {
        Maksukortti kortti = new Maksukortti(100);
        paate.syoEdullisesti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void edullisenLounaanOstoPalautaFalseKunKortillaEiRiittavastiRahaa() {
        Maksukortti kortti = new Maksukortti(100);
        assertFalse(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void edullisenLounaanOstoKortinSaldoOikeinKunKortillaEiRiittavastiRahaa() {
        Maksukortti kortti = new Maksukortti(100);
        paate.syoEdullisesti(kortti);
        assertEquals(100, kortti.saldo());
    }
    
    // Maksukortti + maukas lounas
    
    @Test
    public void maukkaanLounaanOstoLounaidenMaarassaOikeinKunKortillaRiittavastiRahaa() {
        Maksukortti kortti = new Maksukortti(10000);
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanOstoKassanSaldossaOikeinKunKortillaRiittavastiRahaa() {
        Maksukortti kortti = new Maksukortti(10000);
        paate.syoMaukkaasti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void maukkaanLounaanOstoPalautaTrueKunKortillaRiittavastiRahaa() {
        Maksukortti kortti = new Maksukortti(10000);
        assertTrue(paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void maukkaanLounaanOstoKortinSaldoOikeinKunKortillaRiittavastiRahaa() {
        Maksukortti kortti = new Maksukortti(10000);
        paate.syoMaukkaasti(kortti);
        assertEquals(9600, kortti.saldo());
    }
    
    @Test
    public void maukkaanLounaanOstoLounaidenMaarassaOikeinKunKortillaEiRiittavastiRahaa() {
        Maksukortti kortti = new Maksukortti(100);
        paate.syoMaukkaasti(kortti);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanOstoKassanSaldossaOikeinKunKortillaEiRiittavastiRahaa() {
        Maksukortti kortti = new Maksukortti(300);
        paate.syoMaukkaasti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void maukkaanLounaanOstoPalautaFalseKunKortillaEiRiittavastiRahaa() {
        Maksukortti kortti = new Maksukortti(300);
        assertFalse(paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void maukkaanLounaanOstoKortinSaldoOikeinKunKortillaEiRiittavastiRahaa() {
        Maksukortti kortti = new Maksukortti(300);
        paate.syoMaukkaasti(kortti);
        assertEquals(300, kortti.saldo());
    }
    
    // Kortin lataus ja kassan saldo
    
    @Test
    public void kortinSaldoMuuttuuKorttiaLadattaessaKunMaaraSuurempiKuinNolla() {
        Maksukortti kortti = new Maksukortti(300);
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(400, kortti.saldo());
    }
    
    @Test
    public void kassanSaldoMuuttuuKorttiaLadattaessaKunMaaraSuurempiKuinNolla() {
        Maksukortti kortti = new Maksukortti(300);
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, paate.kassassaRahaa()); 
    }
    
    @Test
    public void kortinSaldoMuuttuuKorttiaLadattaessaKunMaaraPienempiKuinNolla() {
        Maksukortti kortti = new Maksukortti(300);
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(300, kortti.saldo());
    }
    
    @Test
    public void kassanSaldoMuuttuuKorttiaLadattaessaKunMaaraPienempiKuinNolla() {
        Maksukortti kortti = new Maksukortti(300);
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, paate.kassassaRahaa()); 
    }

}
