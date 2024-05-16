package erleak;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        String originalString = "Hola mundo";

        try {
            // Obtener una instancia de MessageDigest con el algoritmo SHA-1
            MessageDigest digest = MessageDigest.getInstance("SHA-1");

            // Convertir el string original a bytes
            byte[] hashBytes = digest.digest(originalString.getBytes());

            // Convertir los bytes del hash a formato hexadecimal
            BigInteger bigInt = new BigInteger(1, hashBytes);
            String hashString = bigInt.toString(16);

            // Truncar el hash a 50 caracteres si es necesario
            if (hashString.length() > 50) {
                hashString = hashString.substring(0, 50);
            }

            // Imprimir el hash resultante
            System.out.println("Hash SHA-1 truncado de '" + originalString + "':");
            System.out.println(hashString);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
