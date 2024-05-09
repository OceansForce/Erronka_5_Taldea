package erleak;

import erleak.*;

import java.sql.*;
import java.util.ArrayList;

import erleak.Sozioak.*;
import static erleak.Sozioak.sozio_ArrayList;

public class Datuak {// datu basearekin konexioa ahalbidetzeko datuak definitu.
    static String ip_eta_portua="10.14.4.124";
    static String erabiltzailea="T5_2";
    static String pazaitza="123";
    static String datu_base_IZENA="ORCLCDB";
    static final String url= "jdbc:oracle:thin:@"+ip_eta_portua+":1521:"+datu_base_IZENA;
    static final String driver= "oracle.jdbc.driver.OracleDriver";
    public static Connection con;

//Aldagaiak definitu.
    public static int identifikatzaile_mota;
    public static String izena_login, abizena_login, nan_login, telefonoa_login, email_login, jaio_eguna_login, erle_kantitatea_login, kolmena_kantitatea_login, pazaitza_login, id_sozioa_login;

    public static void konexioa(){// datu baseareki konektatu.
        try {
            Class.forName(driver);// driverra
            con = DriverManager.getConnection(url, erabiltzailea, pazaitza);// erabiltzailea pasahitza eta elbidea sartuta datu basera konektatu.
        }catch (ClassNotFoundException | SQLException e) { // errorea kudeatu.
            throw new RuntimeException(e);
        }
    }
    public static String[][] center_4_txertatu(){// beste batzuk taula buelatuko duen funtzioa.
        try {
            konexioa();// konexioa egin datu basearekin.
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// datu baseko kontsulta sortu.

            ResultSet rs = stmt.executeQuery("SELECT Produktu_mota, prezioa, kantitatea, deskribapena FROM Produktuak where Produktu_mota not like 'eztia'");// kontsulta definitu eta balioak gorde.
            rs.last();// azken balioaren gainean kokatu lerro jakiteko.
            String[][] besteak_datuak=new String[rs.getRow()][4];//array bat sortu jasotako lerroen arabera eta datuen zutabeen arabera(4).
            rs.beforeFirst();// lehenengoko posizioa itzuli.
            int x= 0;// x aldagaia 0 hasieratu.
            while (rs.next()){// jasotako balioak hurrengoko bat duten bitartean.
                String izena= rs.getString("Produktu_mota");//produktu mota zutabeko balioa izena aldagaian gorde.
                String prezioa= rs.getString("prezioa");//prezioa mota zutabeko balioa prezioa aldagaian gorde.
                String kantitatea= rs.getString("kantitatea");//kantitatea mota zutabeko balioa kantitatea aldagaian gorde.
                String deskribapena= rs.getString("deskribapena");//deskribapena mota zutabeko balioa deskribapena aldagaian gorde.
                //Datuak array-ean gorde.
                besteak_datuak[x][0]=izena;
                besteak_datuak[x][1]=prezioa;
                besteak_datuak[x][2]=kantitatea;
                besteak_datuak[x][3]=deskribapena;
                x++;// x+1 egin ondorengo bueltan lerroa aldatzeko.
            }
            con.close();//kontsulta bukatu.
            return besteak_datuak;// datuak bueltatu.
        } catch (SQLException e) {// errorea kudeatu.
            throw new RuntimeException(e);
        }
    }
    public static String[][] center_3_txertatu(){
        try {
            konexioa();// datu basearekin konexioa egin.
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// kontsulta1 sortu.
            Statement stmt2= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);//Kontsult2sortu.

            ResultSet rs = stmt.executeQuery("SELECT id_produktua, prezioa, kantitatea, deskribapena FROM Produktuak where Produktu_mota='eztia'");// kotsulta1 egin eta balioak gorde.
            ResultSet rs2 = stmt2.executeQuery("SELECT EZTIA_MOTA FROM Eztia ORDER BY id_produktua");//Kontsulta2 egin eta balioak gorde.
            rs.last();// kontsulta1-eko azken balioan kokatu.
            rs2.last();//kontsulta2-ko azken balioan kokatu.
            String[] id_produktuak= new String[rs2.getRow()];// kontsulta1-eko azken lerroaren arabera tamaina horretako array a sortu.
            String[][] eztiak=new String[rs.getRow()][4];// kontsulta2-ko azken lerroaren arabera tamaina horretako array a sortu.
            rs.beforeFirst();// kontsulta1-eko lehen balioaren gainean kokatu.
            rs2.beforeFirst();// kontsulta2-ko lehen balioaren gainean kokatu.
            int x= 0;
            while (rs.next()){// kontsulta1-eko balioak hurrengoa duten bitartean.
                String prezioa= rs.getString("prezioa");//koltsulta1-eko prezioa zutabeko balioa gorde.
                String kantitatea= rs.getString("kantitatea");//koltsulta1-eko kantitatea zutabeko balioa gorde.
                String deskribapena= rs.getString("deskribapena");//koltsulta1-eko deskribapena zutabeko balioa gorde.
                String id_produktua= rs.getString("id_produktua");//koltsulta1-eko id_produktua zutabeko balioa gorde.
                id_produktuak[x]=id_produktua; // id_produktua gorde.
                eztiak[x][1]=prezioa;// beste_datuak array-ean prezioa gorde.
                eztiak[x][2]=kantitatea;// beste_datuak array-ean kantitatea gorde.
                eztiak[x][3]=deskribapena;// beste_datuak array-ean deskribapena gorde.
                x++;
            }
            x=0;
            while (rs2.next()){// kontsulta2-k hurrengo balioa duen bitartean
                String izena= rs2.getString("EZTIA_MOTA");// kontsulta2-ko ezti mota zutabeko balioa gorde.
                eztiak[x][0]=izena;// gordetako izena beste_datuak array-ean gorde.
                x++;
            }
            con.close();// kontsulta amaitu.
            return eztiak; // beste_datuak array-a itzuli.
        } catch (SQLException e) {//errorea kudeatu.
            throw new RuntimeException(e);
        }
    }
    public static String[][] center_5_txertatu(){// materiak taula.
        try {
            konexioa();// konexioa sortu datu basearekin.
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// kontsulta sortu.

            ResultSet rs = stmt.executeQuery("SELECT materiala_izena, prezioa, kantitatea, deskribapena FROM Materiala");// kontsulta egin eta balioak gorde.
            rs.last();// jasotako balioen azkenean kokatu.
            String[][] materialak=new String[rs.getRow()][4];//lortutako taularen lerro kopurua hartuta aray bat sortu materialak gorderzeko.
            rs.beforeFirst();// lortutako taularen lehen balioan kokatu.
            int x= 0;
            while (rs.next()){// lortutako balioak hurrengoko bat badu.
                String izena= rs.getString("materiala_izena");// lortutako taulatik materiala_izena zutabeko balioa gorde.
                String prezioa= rs.getString("prezioa");// lortutako taulatik prezioa zutabeko balioa gorde.
                String kantitatea= rs.getString("kantitatea");// lortutako taulatik kantitatea zutabeko balioa gorde.
                String deskribapena= rs.getString("deskribapena");// lortutako taulatik deskribapena zutabeko balioa gorde.
                materialak[x][0]=izena;// sortutako array-ean izena gorde.
                materialak[x][1]=prezioa;// sortutako array-ean prezioa gorde.
                materialak[x][2]=kantitatea;// sortutako array-ean kantitatea gorde.
                materialak[x][3]=deskribapena;// sortutako array-ean deskribapena gorde.
                x++;
            }
            con.close();// kontsultak itxi.
            return materialak;// materialak array-a itzuli.
        } catch (SQLException e) {// errorea kudeatu.
            throw new RuntimeException(e);
        }
    }
    public static boolean izena_jarri(String identifikatzailea){// identifikatzaile bakoitzarekin sozioaren datu guztiak emnago ditu.
        ArrayList<Sozioak> sozio_lista= sozio_ArrayList();// sozioen arraylista.
        for (Sozioak s: sozio_lista) {// sozio bakoitzeko.
            String izen_abizen = s.getSozio_izena()+" "+s.getSozio_abizena();// ArrayList-etik izena eta abizen artu eta batu, hau identifikatzaile bat da eta honekin konparatzeko.
            if ((" "+izen_abizen).equals(identifikatzailea) || izen_abizen.equals(identifikatzailea)){// sortukako testuak datu baseko izen eta abizenekin koinziditzen badu.

                // sozioaren datu guztiak gorde.
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
//identifikatzilea mot a1 izango da.
                identifikatzaile_mota=1;
                return true;
            } else if ((" " + s.getNan()).equals(identifikatzailea) || s.getNan().equals(identifikatzailea)) {// sartutako identifikatzailea datu baseko nan zenbakiarekin bat egiten badu.
                // sozioaren datu guztiak gorde.
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
// identifikatzaile mota 2 izango da.
                identifikatzaile_mota=2;
                return true;
            } else if ((" " + s.getTelefonoa()).equals(identifikatzailea) || s.getTelefonoa().equals(identifikatzailea)) {// sartutako identifikatzailea datu baseko telefono zenbakiarekin bat egiten badu.
                // sozioaren balio guztiak gorde.
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
// identifikatzaile mota 3 izango da.
                identifikatzaile_mota=3;
                return true;
            }else if ((" " + s.getEmail()).equals(identifikatzailea) || s.getEmail().equals(identifikatzailea)) {// sartutako identifikatzailea datu baseko email-arekin bat egiten badu.
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
// identifikatzaile mota 4 izango da.
                identifikatzaile_mota=4;
                return true;
            }
        }
        return false;
    }
    public static String pazaitza_jarri(){// identifikatzaile bakoitzeko pasahitza lortu.
        ArrayList<Sozioak> sozio_lista= sozio_ArrayList();// sozio gustien arraylist bat sortu.
        if(identifikatzaile_mota==1){// identifikatzaile mota 1 bada.
            for (Sozioak s: sozio_lista) {// arraylisteko sozio bakoitzeko.
                if (izena_login.equals(s.getSozio_izena()) && abizena_login.equals(s.getSozio_abizena())){// sozioaren izena eta abizena arraylisteko balioekin bat egiten badute.
                    return s.getPasahitza(); // sozio horren pasahitza itzuli.
                }
            }
        } else if (identifikatzaile_mota==2){// identifikatzaile mota 2 bada.
            for (Sozioak s: sozio_lista) {// arraylist-eko sozio bakoitzeko.
                if(nan_login.equals(s.getNan())){// nan zenbakiak bat egiten badute.
                    return s.getPasahitza();// pasahitza itzuli
                }
            }
        }else if (identifikatzaile_mota==3){// identifikatzaile mota 3 bada.
            for (Sozioak s: sozio_lista) {// arraylist-eko sozio bakoitzeko.
                if (telefonoa_login.equals(s.getTelefonoa())){// telefono zenbakiak bat egiten badute.
                    return s.getPasahitza();// pasahitza itzuli.
                }
            }
        }else if (identifikatzaile_mota==4){//identifikatzaile mota 4 bada.
            for (Sozioak s: sozio_lista) {// arraylist-eko balio bakoitzeko
                if (email_login.equals(s.getEmail())){// email-ak bat egiten badute.
                    return s.getPasahitza();// pasahitza itzuli.
                }
            }
        }
        return "ERROREA pazaitza_jarri";// identifikatzail mota okerrabaldin bada mezu itzuli.
    }
    public static void datuak_eguneratu(String email, String nan, long telefonoa, String izena, String abizena, long kolmena_kantitatea, long erle_kantitatea, Date jaio_eguna, String pazaitza){// balioak emanda datu basean update bat egingo duen prozedura.
        try {
            konexioa();// datu basearekin konexioa egin.
            // kontsulta definitu.
            PreparedStatement update = con.prepareStatement("UPDATE sozioak SET email=?, nan=?, Telefonoa=?, sozio_izena=?, sozio_abizena=?, kolmena_kantitatea=?, erle_kantitatea=?, jaiote_eguna=?, pasahitza=? WHERE id_sozioa=?");
            // kontsultako updatean sartuko diren balioak jarri.
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

            update.executeUpdate();// updatea exekutatu.
        } catch (SQLException e) {// Errorea kudeatu.
            throw new RuntimeException("Error al ejecutar la actualización", e);
        }
    }

}
