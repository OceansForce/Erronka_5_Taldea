package erleak;

import erleak.*;

import java.sql.*;
import java.util.ArrayList;

import static erleak.Sozioak.*;

public class Datuak {
    static String ip_eta_portua="10.14.4.124";
    static String erabiltzailea="T5_2";
    static String pazaitza="123";
    static String datu_base_IZENA="ORCLCDB";
    static final String url= "jdbc:oracle:thin:@"+ip_eta_portua+":1521:"+datu_base_IZENA;
    static final String driver= "oracle.jdbc.driver.OracleDriver";
    public static Connection con;


    public static int identifikatzaile_mota;
    public static String izena_login, abizena_login, nan_login, telefonoa_login, email_login;

    public static void konexioa(){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, erabiltzailea, pazaitza);
        }catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String[][] center_4_txertatu(){
        try {
            konexioa();
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery("SELECT Produktu_mota, prezioa, kantitatea, deskribapena FROM Produktuak where Produktu_mota not like 'eztia'");
            rs.last();
            String[][] besteak_datuak=new String[rs.getRow()][4];
            rs.beforeFirst();
            int x= 0;
            while (rs.next()){
                String izena= rs.getString("Produktu_mota");
                String prezioa= rs.getString("prezioa");
                String kantitatea= rs.getString("kantitatea");
                String deskribapena= rs.getString("deskribapena");
                besteak_datuak[x][0]=izena;
                besteak_datuak[x][1]=prezioa;
                besteak_datuak[x][2]=kantitatea;
                besteak_datuak[x][3]=deskribapena;
                x++;
            }
            con.close();
            return besteak_datuak;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String[][] center_3_txertatu(){
        try {
            konexioa();
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Statement stmt2= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery("SELECT id_produktua, prezioa, kantitatea, deskribapena FROM Produktuak where Produktu_mota='eztia'");
            ResultSet rs2 = stmt2.executeQuery("SELECT EZTIA_MOTA FROM Eztia ORDER BY id_produktua");
            rs.last();
            rs2.last();
            String[] id_produktuak= new String[rs2.getRow()];
            String[][] besteak_datuak=new String[rs.getRow()][4];
            rs.beforeFirst();
            rs2.beforeFirst();
            int x= 0;
            while (rs.next()){
                String prezioa= rs.getString("prezioa");
                String kantitatea= rs.getString("kantitatea");
                String deskribapena= rs.getString("deskribapena");
                String id_produktua= rs.getString("id_produktua");
                id_produktuak[x]=id_produktua;
                besteak_datuak[x][1]=prezioa;
                besteak_datuak[x][2]=kantitatea;
                besteak_datuak[x][3]=deskribapena;
                x++;
            }
            x=0;
            while (rs2.next()){
                String izena= rs2.getString("EZTIA_MOTA");
                besteak_datuak[x][0]=izena;
                x++;
            }
            con.close();
            return besteak_datuak;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String[][] center_5_txertatu(){
        try {
            konexioa();
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery("SELECT materiala_izena, prezioa, kantitatea, deskribapena FROM Materiala");
            rs.last();
            String[][] besteak_datuak=new String[rs.getRow()][4];
            rs.beforeFirst();
            int x= 0;
            while (rs.next()){
                String izena= rs.getString("materiala_izena");
                String prezioa= rs.getString("prezioa");
                String kantitatea= rs.getString("kantitatea");
                String deskribapena= rs.getString("deskribapena");
                besteak_datuak[x][0]=izena;
                besteak_datuak[x][1]=prezioa;
                besteak_datuak[x][2]=kantitatea;
                besteak_datuak[x][3]=deskribapena;
                x++;
            }
            con.close();
            return besteak_datuak;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean izena_jarri(String identifikatzailea){
        try {
            konexioa();
            Statement stmt= Datuak.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs= stmt.executeQuery("SELECT sozio_izena, sozio_abizena, nan, telefonoa,email FROM sozioak");
            while (rs.next()) {
                String izen_abizen= " "+rs.getString("sozio_izena")+" "+rs.getString("sozio_abizena");
                if (izen_abizen.equals(identifikatzailea) || (" "+rs.getString("nan")).equals(identifikatzailea) || (" "+rs.getString("telefonoa")).equals(identifikatzailea) || (" "+rs.getString("email")).equals(identifikatzailea)) {
                    if (izen_abizen.equals(identifikatzailea)){

                        izena_login= rs.getString("sozio_izena");
                        abizena_login= rs.getString("sozio_abizena");

                        identifikatzaile_mota=1;
                    }else if ((" "+rs.getString("nan")).equals(identifikatzailea)){

                        nan_login=rs.getString("nan");

                        identifikatzaile_mota=2;
                    }else if ((" "+rs.getString("telefonoa")).equals(identifikatzailea)) {

                        telefonoa_login= rs.getString("telefonoa");

                        identifikatzaile_mota=3;
                    } else if ((" "+rs.getString("email")).equals(identifikatzailea)) {

                        email_login= rs.getString("email");

                        identifikatzaile_mota=4;
                    }
                    con.close();
                    return true;
                }else {
                    con.close();
                    return false;
                }
            }
        } catch (SQLException e ) {
            return false;
        }
        return false;
    }
    public static String pazaitza_jarri(){
        ArrayList<Sozioak> sozio_lista= sozio_ArrayList();
        if(identifikatzaile_mota==1){
            for (Sozioak s: sozio_lista) {
                if (izena_login.equals(s.getSozio_izena()) && abizena_login.equals(s.getSozio_abizena())){
                    return s.getPasahitza();
                }
            }
        } else if (identifikatzaile_mota==2){
            for (Sozioak s: sozio_lista) {
                if(nan_login.equals(s.getNan())){
                    return s.getPasahitza();
                }
            }
        }else if (identifikatzaile_mota==3){
            for (Sozioak s: sozio_lista) {
                if (telefonoa_login.equals(s.getTelefonoa())){
                    return s.getPasahitza();
                }
            }
        }else if (identifikatzaile_mota==4){
            for (Sozioak s: sozio_lista) {
                if (email_login.equals(s.getEmail())){
                    return s.getPasahitza();
                }
            }
        }
        return "ERROREA pazaitza_jarri";
    }

}
