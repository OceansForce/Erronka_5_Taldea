package Testak;

import erleak.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static erleak.Datuak.*;
import static erleak.Sozioak.*;

public class Testak {
    private static Datuak datu;
    private static Erregistratu erre;
    private static Index hasiera;
    private static Login logeatu;
    private static Sesioa sesio;
    private static Sozietateak sozietatea;
    private static Sozioak sozioa;
    @BeforeAll
    static void beforeall(){
        System.out.println("Test hasiera");
    }
    @AfterAll
    static void afterAll(){
        System.out.println("Test bukaera");
    }


    @Tag("Sozioak")
    @Test
    @EnabledOnOs(OS.WINDOWS)
    @DisplayName("sozio_Array metodoa ez du itzuliko NULL")
    public void test1(){
        Datuak da= new Datuak();
        Assertions.assertNotNull(da.sozio_Array());

    }
    @Test
    @DisplayName("id_atera metodoa 3 ida bueltatuko du.")
    public void test3(){
        Datuak da= new Datuak();
        Assertions.assertEquals(3,da.id_atera("85844584D"));
    }
    @Nested
    class habiaratutakoa{
        @Tag("4.Panelan")
        @Test
        @DisplayName("CRUD testa")
        void test4(){
            Datuak da= new Datuak();
            Assertions.assertNotNull(da.center_4_txertatu());
        }
    }
    @Tag("3.Panelan")
    @Test
    @RepeatedTest(value = 3)
    public void test5(){
        Datuak da= new Datuak();
        Assertions.assertNotNull(da.center_3_txertatu());
    }

    @DisplayName("CSV")
    @CsvFileSource(resources = ".././CSV.csv")
    @ParameterizedTest
    void test6(String IDa){
        Datuak da= new Datuak();
        Assertions.assertEquals("85844584D",da.nan_atera(IDa), "NANa aurkitu dut");
    }


}
