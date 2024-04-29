package Testak;

import erleak.Sozietateak;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SozietateakTest {

    @Test
    void sozietate_Arraya() {
        Assertions.assertNotNull(Sozietateak.sozietate_Arraya());
    }
}