package Testak;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;

import static erleak.Datuak.*;
import static erleak.Sozioak.*;

public class Testak {

    @BeforeAll
    static void beforeall(){
        System.out.println("Test hasiera");
    }
    @AfterAll
    static void afterAll(){
        System.out.println("Test bukaera");
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    @DisplayName("sozio_Array metodoa ez du itzuliko NULL")
    public void test1(){
        Assertions.assertNotNull(sozio_Array());

    }
    @Test
    @DisplayName("Izena_jarri true itzuliko du.")
    public void test2(){
        Assertions.assertTrue(izena_jarri("76390986Y"));

    }
    @Test
    @DisplayName("id_atera metodoa 3 ida bueltatuko du.")
    public void test3(){
        Assertions.assertEquals(3,id_atera("85844584D"));

    }
    @Nested
    public void habiaratutakoa(){

    }
}

