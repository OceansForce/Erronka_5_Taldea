package erleak;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static erleak.Datuak.con;
import static erleak.Datuak.konexioa;

public class Sozietateak {
    private int id_asoziazioa;
    private String izena;
    private String herrialdea;

    public Sozietateak(int id_asoziazioa, String izena, String herrialdea) {
        this.id_asoziazioa = id_asoziazioa;
        this.izena = izena;
        this.herrialdea = herrialdea;
    }

    public int getId_asoziazioa() {
        return id_asoziazioa;
    }

    public String getIzena() {
        return izena;
    }

    public String getHerrialdea() {
        return herrialdea;
    }
}
