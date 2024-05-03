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

    public static String[][] sozietate_Arraya(){
        konexioa();
        try {
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs= stmt.executeQuery("SELECT id_asoziazioa, asoziazio_izena, herrialdea FROM asoziazioak");
            rs.last();
            String[][] sozietateak=  new String[rs.getRow()][3];
            rs.beforeFirst();
            int x=0;
            while (rs.next()){
                sozietateak[x][0]= rs.getString("id_asoziazioa");
                sozietateak[x][1]= rs.getString("asoziazio_izena");
                sozietateak[x][2]= rs.getString("herrialdea");
                x++;
            }
            con.close();
            return sozietateak;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
