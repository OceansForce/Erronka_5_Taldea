package Testak;

import erleak.Datuak;
import erleak.Login;
import erleak.Sozioak;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SozioakTest {

    @Test
    void getId_sozioa() {
      Sozioak sozioa = new Sozioak(1,1,43,54, "Julen", "Garcia", "62259722J", "34231232132", "2004/04/23", "Julen@gmail.com", "32432");
      Assertions.assertEquals(1,sozioa.getId_sozioa());
    }

    @Test
    void getSozio_izena() {
        Sozioak sozioa = new Sozioak(1,1,43,54, "Julen", "Garcia", "62259722J", "34231232132", "2004/04/23", "Julen@gmail.com", "32432");
        Assertions.assertEquals("Julen",sozioa.getSozio_izena());
    }
}