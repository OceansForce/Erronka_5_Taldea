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
    void sozio_ArrayList() {
        Assertions.assertNotNull(Sozioak.sozio_ArrayList());
    }

    @Test
    void sozio_Array() {
        Assertions.assertNotNull(Sozioak.sozio_Array());
    }

    @Test
    void id_sozioak() {
        Assertions.assertNotNull(Sozioak.id_sozioak());
    }

    @Test
    void zuzendariak() {
        Assertions.assertNotNull(Sozioak.zuzendariak());
    }

    @Test
    void id_atera_login() {
        Datuak.identifikatzaile_mota=2;
        Datuak.nan_login="58339691R";
        Assertions.assertEquals(8,Sozioak.id_atera_login());
    }

    @Test
    void id_atera() {
        Assertions.assertEquals(5,Sozioak.id_atera("16880065H"));
    }

    @Test
    void id_hadiena() {
        Assertions.assertEquals(14, Sozioak.id_hadiena());
    }

    @Test
    void nan_atera() {
        Assertions.assertEquals("53243243T", Sozioak.nan_atera(11));
    }

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