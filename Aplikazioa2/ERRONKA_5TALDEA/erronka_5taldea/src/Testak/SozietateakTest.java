package Testak;

import erleak.Datuak;
import erleak.Sozietateak;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SozietateakTest {

    @Test
    void getHerrialdeaTest() {
        Sozietateak so= new Sozietateak(1, "Proba", "Proba_Herria");
        Assertions.assertNotNull(so.getHerrialdea());
    }
}