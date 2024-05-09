package erleak;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import static erleak.Datuak.*;

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

   public static ArrayList<Sozioak> sozio_ArrayList(){// sozioen arraylist bat bueltatuko duen funtzioa.

        try {
            konexioa();// datu basera konektatu.
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// datu baserako kontsulta sortu.
            //Eskaera egin.
            ResultSet rs = stmt.executeQuery("SELECT id_sozioa, id_zuzendaria, erle_kantitatea, kolmena_kantitatea, sozio_izena, sozio_abizena, nan, telefonoa, jaiote_eguna, email, pasahitza FROM SOZIOAK");
            ArrayList<Sozioak> sozio_Arraya= new ArrayList<>();//Eskaeran jasotako balioak gordetzeko arraylist-a sortu.

            while (rs.next()){// balio guztiak jasotzeko
                //datubaseko datuak jaso eta array-an sartu.
                sozio_Arraya.add(new Sozioak(rs.getInt("id_sozioa"), rs.getInt("id_zuzendaria"), rs.getLong("erle_kantitatea"), rs.getLong("kolmena_kantitatea"), rs.getString("sozio_izena"), rs.getString("sozio_abizena"), rs.getString("nan"), rs.getString("telefonoa"), rs.getString("jaiote_eguna"), rs.getString("email"), rs.getString("pasahitza")));
            }
            con.close();// kontsulta itxi.
            return sozio_Arraya;// array-a datuekin itzuli.
        } catch (SQLException e) {//errorea kudeatu.
            throw new RuntimeException(e);
        }
    }
    public static String[][] sozio_Array(){//Array bat bueltatuko duen funtzioa.

        try {
            konexioa();// datu basearekin konexioa egin.
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// datu baseko kontsulta sortu.
            // eskaera egin.
            ResultSet rs = stmt.executeQuery("SELECT id_sozioa, id_zuzendaria, erle_kantitatea, kolmena_kantitatea, sozio_izena, sozio_abizena, nan, telefonoa, jaiote_eguna, email, pasahitza FROM SOZIOAK");

            rs.last();//azken elementura joan.
            String[][] sozio_Arraya= new String[rs.getRow()][11];//arrat bat sortu
            rs.beforeFirst();// eskaeran bueltatu den balioen haurrean jarri.

            int x=0;
            while (rs.next()){//bueltatutako balioak hurrengo bat duen bitartean.
                // balioak array-ean gorde.
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
            con.close();//kontsulta desegin
            return sozio_Arraya;// array-a itzuli.
        } catch (SQLException e) {//errorea kudeatu.
            throw new RuntimeException(e);
        }
    }
    public static HashSet<Integer> id_sozioak(){// HashSet bat bueltatzen duen funztioa.
        try {
            konexioa();// datu basearekin konexioa sortu.
            HashSet<Integer> id_sozioak= new HashSet<>();// HashSet bat sortu id_sozioarentzako.
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);// datu basean egiteko kontsulta sortu.
            ResultSet rs= stmt.executeQuery("SELECT id_sozioa FROM sozioak");// eskaera definitu eta erantzuna gorde.

            while (rs.next()){// erantzunak hurrengo balioa duen bitartean.
                id_sozioak.add(rs.getInt("id_sozioa"));// HashSet-era bueltatutako id_sozioak gehitu.
            }
            con.close();// kontsulta desegin.
            return id_sozioak;// HashSet-a bueltatu.

        } catch (SQLException e) {//Errorea kudeatu.
            throw new RuntimeException(e);
        }
    }
    public static HashSet<Integer> zuzendariak(){// zuzendarien HashSet bat bueltatzen duen funtzioa.
        try {
            konexioa();// datu basearekin konexioa egin
            HashSet<Integer> id_zuzendariak= new HashSet<>();// HashSet-a sortu.
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);// kontsulta sortu.
            ResultSet rs= stmt.executeQuery("SELECT id_zuzendaria FROM sozioak");// Eskaera definitu eta balioak jaso.

            while (rs.next()){// jasotako balioak hurrengo duen bitartean.
                id_zuzendariak.add(rs.getInt("id_zuzendaria"));// id_zuzendariak HashSet-ean gorde.
            }
            con.close();// kontsulta desegin.
            System.out.println(id_zuzendariak);// HashSet-eko id_zuzendariak pantailaratu.
            return id_zuzendariak;// zuzendarien HashSet-a bueltatu.

        } catch (SQLException e) {//Errorea kuedeatu
            throw new RuntimeException(e);
        }
    }

    public static int id_atera_login(){//logeatutako sozioaren id_sozioa bultatzen duen funtzioa.
            ArrayList<Sozioak> sozio_lista= sozio_ArrayList();//Sozioaen arraylist bat sortu, haurreko funtzioan sortutako sozioen arraylist-arekin.
            if(identifikatzaile_mota==1){// sozioa ligeatzeko izena sartzen bada.
                for (Sozioak s: sozio_lista) {////Arraylisteko sozio bakoitzeko
                    if (izena_login.equals(s.getSozio_izena()) && abizena_login.equals(s.getSozio_abizena())){// sartutako izena eta abizena datu baseko sozioarekin bat badatoz.
                        return s.getId_sozioa();// id_sozioa itzuli.
                    }
                }
            } else if (identifikatzaile_mota==2){// sartutako identifikatzailea nan zenbakia bada.
                for (Sozioak s: sozio_lista) {//ArrayList-eko sozio bakoitzeko.
                    if(nan_login.equals(s.getNan())){// sartutako nan zenabakiak datu basean dagoen nan zenbakiarekin bat egiten badu.
                        return s.getId_sozioa();//Sozioaren id_sozioa bueltatu.
                    }
                }
            }else if (identifikatzaile_mota==3){// Sartutako identifikatzailea telefonoa baldin bada.
                for (Sozioak s: sozio_lista) {//ArrayList-eko sozio bakoitzeko.
                    if (telefonoa_login.equals(s.getTelefonoa())){//Sartutako telefonoa datu basean badago.
                        return s.getId_sozioa();// id sozioa bueltatu.
                    }
                }
            }else if (identifikatzaile_mota==4){// Sartutako identifikatzailea email-a bada.
                for (Sozioak s: sozio_lista) {// arrayListeko sozio bakoitzeko.
                    if (email_login.equals(s.getEmail())){// Sartutako email-a datu basean badago.
                        return s.getId_sozioa();// id sozioa bueltatu.
                    }
                }
            }
            return 0;
    }
    public static int id_atera(String nan){// nan zebakia jasota sozioaren id-a bueltatuko duen funtzioa.
        ArrayList<Sozioak> sozio_lista= sozio_ArrayList();// Arraylist-a sortu, haurreko funtzioan sortutako arraylist-arekin.
            for (Sozioak s: sozio_lista) {// ArrayList-eko sozio bakoitzeko.
                if (nan.equals(s.getNan())){//jasotako nan zenbakia datu basean badago.
                    return s.getId_sozioa();// sozioaren id-a itzuli.
                }
            }
        return 0;
    }
    public static int id_hadiena(){// azken id_sozioa jakiteko funtzioa.
        try {
            konexioa();// datu basearekin konexio egin.
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);//kontsulta sortu.
            ResultSet rs = stmt.executeQuery("SELECT MAX(id_sozioa) FROM SOZIOAK");//Eskaera egin eta balioak jaso.

            rs.next();// jasotako balioaren gainean kokatu.
            return rs.getInt(1)+1;// jasotako balioaren hurrengo zenbakia bueltatu.

        } catch (SQLException e) {// errorea kudeatu.
            throw new RuntimeException(e);
        }
    }

    public static String nan_atera(int id){// soizoaren id-a jasota honen nan zenbakia bultatuko duen funtzioa.
        ArrayList<Sozioak> sozio_lista= sozio_ArrayList();// Arraylist-a sortu, haurreko funtzioan sortutako arraylist-arekin.
            for (Sozioak s: sozio_lista) {// Arraylist-eko
                if (id == s.getId_sozioa()){// jasotako id_sozioa
                    return s.getNan();// nan zenbakia itzuli.
                }
            }
        return "ERROR";// ez bada betzen "error" itzuli.
    }
    @Override
    public String toString() {// Sozioaren balioak string motara pasatu.
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
