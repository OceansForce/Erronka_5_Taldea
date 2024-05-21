package erleak;

public class Sozioak {// adagaiak definitu.
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


    // eraikitzailea
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
    public int getId_sozioa() {// getterra datuk jasotzeko.
        return id_sozioa;
    }

    public String getSozio_izena() {// getterra datuk jasotzeko.
        return sozio_izena;
    }

    public String getSozio_abizena() {// getterra datuk jasotzeko.
        return sozio_abizena;
    }

    public String getNan() {// getterra datuk jasotzeko.
        return nan;
    }

    public String getTelefonoa() {// getterra datuk jasotzeko.
        return telefonoa;
    }

    public String getEmail() {// getterra datuk jasotzeko.
        return email;
    }

    public String getPasahitza() {// getterra datuk jasotzeko.
        return pasahitza;
    }

    public long getErle_kantitatea() {// getterra datuk jasotzeko.
        return erle_kantitatea;
    }

    public long getKolmena_kantitatea() {// getterra datuk jasotzeko.
        return kolmena_kantitatea;
    }

    public String getJaiote_eguna() {// getterra datuk jasotzeko.
        return jaiote_eguna;
    }
}
