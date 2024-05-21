package erleak;

public class Sozietateak {
    private int id_asoziazioa;
    private String izena;
    private String herrialdea;

    public Sozietateak(int id_asoziazioa, String izena, String herrialdea) {
        this.id_asoziazioa = id_asoziazioa;
        this.izena = izena;
        this.herrialdea = herrialdea;
    }
    public String getHerrialdea() {
        return herrialdea;
    }
}
