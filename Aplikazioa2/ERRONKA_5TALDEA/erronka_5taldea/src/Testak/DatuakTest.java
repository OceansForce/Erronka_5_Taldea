package Testak;

import erleak.Datuak;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static erleak.Datuak.izena_jarri;
import static org.junit.jupiter.api.Assertions.*;

class DatuakTest {

    @Test
    public void izena_jarri() {
        Assertions.assertTrue(Datuak.izena_jarri("85844584D"));
    }

    @Test
    void center_4_txertatu() {
        Assertions.assertNotNull(Datuak.center_4_txertatu());
    }

    @Test
    void center_3_txertatu() {
        Assertions.assertNotNull(Datuak.center_3_txertatu());
    }

    @Test
    void center_5_txertatu() {
        Assertions.assertNotNull(Datuak.center_5_txertatu());
    }
}