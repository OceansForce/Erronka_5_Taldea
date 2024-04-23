package erleak;

import java.sql.Date;
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
    private Date jaiote_eguna;
    private String email;
    private String pasahitza;

    public Sozioak(int id_sozioa, int id_zuzendaria, long erle_kantitatea, long kolmena_kantitatea, String sozio_izena, String sozio_abizena, String nan, String telefonoa, Date jaiote_eguna, String email, String pasahitza) {
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
                sozio_Arraya.add(new Sozioak(rs.getInt("id_sozioa"), rs.getInt("id_zuzendaria"), rs.getLong("erle_kantitatea"), rs.getLong("kolmena_kantitatea"), rs.getString("sozio_izena"), rs.getString("sozio_abizena"), rs.getString("nan"), rs.getString("telefonoa"), rs.getDate("jaiote_eguna"), rs.getString("email"), rs.getString("pasahitza")));
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

}
