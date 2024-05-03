package erleak;

import erleak.*;

import java.sql.*;
import java.util.ArrayList;

import erleak.Sozioak.*;
import static erleak.Sozioak.sozio_ArrayList;

public class Datuak {
    static String ip_eta_portua="10.14.4.124";
    static String erabiltzailea="T5_2";
    static String pazaitza="123";
    static String datu_base_IZENA="ORCLCDB";
    static final String url= "jdbc:oracle:thin:@"+ip_eta_portua+":1521:"+datu_base_IZENA;
    static final String driver= "oracle.jdbc.driver.OracleDriver";
    public static Connection con;


    public static int identifikatzaile_mota;
    public static String izena_login, abizena_login, nan_login, telefonoa_login, email_login, jaio_eguna_login, erle_kantitatea_login, kolmena_kantitatea_login, pazaitza_login, id_sozioa_login;

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
        ArrayList<Sozioak> sozio_lista= sozio_ArrayList();
        for (Sozioak s: sozio_lista) {
            String izen_abizen = s.getSozio_izena()+" "+s.getSozio_abizena();
            if ((" "+izen_abizen).equals(identifikatzailea) || izen_abizen.equals(identifikatzailea)){
                id_sozioa_login= String.valueOf(s.getId_sozioa());
                email_login=s.getEmail();
                izena_login= s.getSozio_izena();
                abizena_login= s.getSozio_abizena();
                nan_login= s.getNan();
                telefonoa_login=s.getTelefonoa();
                String[] ju=s.getJaiote_eguna().split(" ");
                jaio_eguna_login=ju[0];
                erle_kantitatea_login= String.valueOf(s.getErle_kantitatea());
                kolmena_kantitatea_login= String.valueOf(s.getKolmena_kantitatea());
                pazaitza_login=s.getPasahitza();

                identifikatzaile_mota=1;
                return true;
            } else if ((" " + s.getNan()).equals(identifikatzailea) || s.getNan().equals(identifikatzailea)) {
                id_sozioa_login= String.valueOf(s.getId_sozioa());
                email_login=s.getEmail();
                izena_login= s.getSozio_izena();
                abizena_login= s.getSozio_abizena();
                nan_login= s.getNan();
                telefonoa_login=s.getTelefonoa();
                String[] ju=s.getJaiote_eguna().split(" ");
                jaio_eguna_login=ju[0];
                erle_kantitatea_login= String.valueOf(s.getErle_kantitatea());
                kolmena_kantitatea_login= String.valueOf(s.getKolmena_kantitatea());
                pazaitza_login=s.getPasahitza();

                identifikatzaile_mota=2;
                return true;
            } else if ((" " + s.getTelefonoa()).equals(identifikatzailea) || s.getTelefonoa().equals(identifikatzailea)) {
                id_sozioa_login= String.valueOf(s.getId_sozioa());
                email_login=s.getEmail();
                izena_login= s.getSozio_izena();
                abizena_login= s.getSozio_abizena();
                nan_login= s.getNan();
                telefonoa_login=s.getTelefonoa();
                String[] ju=s.getJaiote_eguna().split(" ");
                jaio_eguna_login=ju[0];
                erle_kantitatea_login= String.valueOf(s.getErle_kantitatea());
                kolmena_kantitatea_login= String.valueOf(s.getKolmena_kantitatea());
                pazaitza_login=s.getPasahitza();

                identifikatzaile_mota=3;
                return true;
            }else if ((" " + s.getEmail()).equals(identifikatzailea) || s.getEmail().equals(identifikatzailea)) {
                id_sozioa_login= String.valueOf(s.getId_sozioa());
                email_login=s.getEmail();
                izena_login= s.getSozio_izena();
                abizena_login= s.getSozio_abizena();
                nan_login= s.getNan();
                telefonoa_login=s.getTelefonoa();
                String[] ju=s.getJaiote_eguna().split(" ");
                jaio_eguna_login=ju[0];
                erle_kantitatea_login= String.valueOf(s.getErle_kantitatea());
                kolmena_kantitatea_login= String.valueOf(s.getKolmena_kantitatea());
                pazaitza_login=s.getPasahitza();

                identifikatzaile_mota=4;
                return true;
            }
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
    public static void datuak_eguneratu(String email, String nan, long telefonoa, String izena, String abizena, long kolmena_kantitatea, long erle_kantitatea, Date jaio_eguna, String pazaitza){
        try {
            konexioa();
            PreparedStatement update = con.prepareStatement("UPDATE sozioak SET email=?, nan=?, Telefonoa=?, sozio_izena=?, sozio_abizena=?, kolmena_kantitatea=?, erle_kantitatea=?, jaiote_eguna=?, pasahitza=? WHERE id_sozioa=?");
            update.setString(1, email);
            update.setString(2, nan);
            update.setLong(3, telefonoa);
            update.setString(4, izena);
            update.setString(5, abizena);
            update.setLong(6, kolmena_kantitatea);
            update.setLong(7, erle_kantitatea);
            update.setDate(8, jaio_eguna);
            update.setString(9, pazaitza);
            update.setString(10, id_sozioa_login);

            update.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la actualización", e);
        }
    }

}
