package erleak;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import static erleak.Datuak.*;

public class Sozioak {
    private int id_sozioa;
    private int id_zuzendaria;
    private long erle_kantitatea;
    private long kolmena_kantitatea;
    private String sozio_izena;
    private String sozio_abizena;
    private String nan;
    private String telefonoa;
    private String jaiote_eguna;
    private String email;
    private String pasahitza;

    public Sozioak(int id_sozioa, int id_zuzendaria, long erle_kantitatea, long kolmena_kantitatea, String sozio_izena, String sozio_abizena, String nan, String telefonoa, String jaiote_eguna, String email, String pasahitza) {
        this.id_sozioa = id_sozioa;
        this.id_zuzendaria = id_zuzendaria;
        this.erle_kantitatea = erle_kantitatea;
        this.kolmena_kantitatea = kolmena_kantitatea;
        this.sozio_izena = sozio_izena;
        this.sozio_abizena = sozio_abizena;
        this.nan = nan;
        this.telefonoa = telefonoa;
        this.jaiote_eguna = jaiote_eguna;
        this.email = email;
        this.pasahitza = pasahitza;
    }

   public static ArrayList<Sozioak> sozio_ArrayList(){

        try {
            konexioa();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT id_sozioa, id_zuzendaria, erle_kantitatea, kolmena_kantitatea, sozio_izena, sozio_abizena, nan, telefonoa, jaiote_eguna, email, pasahitza FROM SOZIOAK");

            ArrayList<Sozioak> sozio_Arraya= new ArrayList<>();

            while (rs.next()){
                sozio_Arraya.add(new Sozioak(rs.getInt("id_sozioa"), rs.getInt("id_zuzendaria"), rs.getLong("erle_kantitatea"), rs.getLong("kolmena_kantitatea"), rs.getString("sozio_izena"), rs.getString("sozio_abizena"), rs.getString("nan"), rs.getString("telefonoa"), rs.getString("jaiote_eguna"), rs.getString("email"), rs.getString("pasahitza")));
            }
            con.close();
            return sozio_Arraya;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String[][] sozio_Array(){

        try {
            konexioa();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT id_sozioa, id_zuzendaria, erle_kantitatea, kolmena_kantitatea, sozio_izena, sozio_abizena, nan, telefonoa, jaiote_eguna, email, pasahitza FROM SOZIOAK");

            rs.last();
            String[][] sozio_Arraya= new String[rs.getRow()][11];
            rs.beforeFirst();

            int x=0;
            while (rs.next()){
                sozio_Arraya[x][0]=rs.getString("id_sozioa");
                sozio_Arraya[x][1]=rs.getString("id_zuzendaria");
                sozio_Arraya[x][2]=rs.getString("erle_kantitatea");
                sozio_Arraya[x][3]=rs.getString("kolmena_kantitatea");
                sozio_Arraya[x][4]=rs.getString("sozio_izena");
                sozio_Arraya[x][5]=rs.getString("sozio_abizena");
                sozio_Arraya[x][6]=rs.getString("nan");
                sozio_Arraya[x][7]=rs.getString("telefonoa");
                sozio_Arraya[x][8]=rs.getString("jaiote_eguna");
                sozio_Arraya[x][9]=rs.getString("email");
                sozio_Arraya[x][10]=rs.getString("pasahitza");
                x++;
            }
            con.close();
            return sozio_Arraya;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static HashSet<Integer> id_sozioak(){
        try {
            konexioa();
            HashSet<Integer> id_sozioak= new HashSet<>();
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs= stmt.executeQuery("SELECT id_sozioa FROM sozioak");

            while (rs.next()){
                id_sozioak.add(rs.getInt("id_sozioa"));
            }
            con.close();
            System.out.println(id_sozioak);
            return id_sozioak;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static HashSet<Integer> zuzendariak(){
        try {
            konexioa();
            HashSet<Integer> id_zuzendariak= new HashSet<>();
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs= stmt.executeQuery("SELECT id_zuzendaria FROM sozioak");

            while (rs.next()){
                id_zuzendariak.add(rs.getInt("id_zuzendaria"));
            }
            con.close();
            System.out.println(id_zuzendariak);
            return id_zuzendariak;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int id_atera(){
            ArrayList<Sozioak> sozio_lista= sozio_ArrayList();
            if(identifikatzaile_mota==1){
                for (Sozioak s: sozio_lista) {
                    if (izena_login.equals(s.getSozio_izena()) && abizena_login.equals(s.getSozio_abizena())){
                        return s.getId_sozioa();
                    }
                }
            } else if (identifikatzaile_mota==2){
                for (Sozioak s: sozio_lista) {
                    if(nan_login.equals(s.getNan())){
                        return s.getId_sozioa();
                    }
                }
            }else if (identifikatzaile_mota==3){
                for (Sozioak s: sozio_lista) {
                    if (telefonoa_login.equals(s.getTelefonoa())){
                        return s.getId_sozioa();
                    }
                }
            }else if (identifikatzaile_mota==4){
                for (Sozioak s: sozio_lista) {
                    if (email_login.equals(s.getEmail())){
                        return s.getId_sozioa();
                    }
                }
            }
            return 0;
    }

    public static int id_hadiena(){
        try {
            konexioa();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT MAX(id_sozioa) FROM SOZIOAK");
            return rs.getInt("MAX(id_sozioa)")+1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String nan_atera(int id){
        ArrayList<Sozioak> sozio_lista= sozio_ArrayList();
            for (Sozioak s: sozio_lista) {
                if (id == s.getId_sozioa()){
                    return s.getNan();
                }
            }
        return "ERROR";
    }
    @Override
    public String toString() {
        return id_sozioa +
                " || "+ id_zuzendaria +
                " || "+ erle_kantitatea +
                " || "+ kolmena_kantitatea +
                " || "+ sozio_izena + '\'' +
                " || "+ sozio_abizena + '\'' +
                " || "+ nan + '\'' +
                " || " + telefonoa + '\'' +
                " || "+ jaiote_eguna +
                " || "+ email + '\'' +
                " || "+ pasahitza + '\'' + "\n";
    }

    public int getId_sozioa() {
        return id_sozioa;
    }

    public String getSozio_izena() {
        return sozio_izena;
    }

    public String getSozio_abizena() {
        return sozio_abizena;
    }

    public String getNan() {
        return nan;
    }

    public String getTelefonoa() {
        return telefonoa;
    }

    public String getEmail() {
        return email;
    }

    public String getPasahitza() {
        return pasahitza;
    }

    public long getErle_kantitatea() {
        return erle_kantitatea;
    }

    public long getKolmena_kantitatea() {
        return kolmena_kantitatea;
    }

    public String getJaiote_eguna() {
        return jaiote_eguna;
    }
}
