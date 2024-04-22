package erleak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

   /* public ArrayList<Sozioak> sozio_denak(){
        konexioa();
        try {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT id_sozioa, id_zuzendaria, erle_kantitatea, kolmena_kantitatea, sozio_izena, sozio_abizena, nan, telefonoa, jaiote_eguna, email, pasahitza");

            ArrayList<Sozioak> sozio_Arraya;

            while (rs.next()){
                //sozio_Arraya.add(new Sozioak(rs.getInt("id_sozioa"), rs.getInt("id_zuzendaria"), rs.getLong("erle_kantitatea"), rs.getLong("kolmena_kantitatea"), rs.getString("sozio_izena"), rs.getString("sozio_abizena"), rs.getString("nan"), rs.getString("telefonoa"), rs.getDate("jaiote_eguna"), rs.getString("email"), rs.getString("pasahitza")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
}
