package Testak;

import erleak.Datuak;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class DatuakTest {

    @Test
    public void izena_jarri() {
        Datuak da= new Datuak();
        Assertions.assertEquals(da.nan_atera("1"), "76390986Y");
    }

    @Test
    void center_4_txertatu() {
        Datuak da= new Datuak();
        Assertions.assertNotNull(da.center_4_txertatu());
    }

    @Test
    void center_3_txertatu() {
        Datuak da= new Datuak();
        Assertions.assertNotNull(da.center_3_txertatu());
    }

    @Test
    void center_5_txertatu() {
        Datuak da= new Datuak();
        Assertions.assertNotNull(da.center_5_txertatu());
    }
}