package erleak;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.xml.bind.DatatypeConverter;


import static erleak.Login.logeatua_dago;
import static erleak.Login.zuzendaria_da;


public class Datuak {// datu basearekin konexioa ahalbidetzeko datuak definitu.

   // static final String ip_eta_portua="10.14.4.124";
    static final String ip_eta_portua="192.168.8.110";
    static final String erabiltzailea="T5_2";
    static final String pazaitza="123";
    static final String datu_base_IZENA="ORCLCDB";
    static final String url= "jdbc:oracle:thin:@"+ip_eta_portua+":1521:"+datu_base_IZENA;
    static final String driver= "oracle.jdbc.driver.OracleDriver";
    public static Connection con;

//Aldagaiak definitu.
    public static int identifikatzaile_mota;
    public static String izena_login, abizena_login, nan_login, telefonoa_login, email_login, jaio_eguna_login, erle_kantitatea_login, kolmena_kantitatea_login, pazaitza_login, id_sozioa_login;
    public Datuak() {}

    public void konexioa(){// datu baseareki konektatu.
        try {
            Class.forName(driver);// driverra
            con = DriverManager.getConnection(url, erabiltzailea, pazaitza);// erabiltzailea pasahitza eta elbidea sartuta datu basera konektatu.
        }catch (ClassNotFoundException | SQLException e) { // errorea kudeatu.
            throw new RuntimeException(e);
        }
    }
    public String[][] center_4_txertatu(){// beste batzuk taula buelatuko duen funtzioa.
        try {
            Datuak da = new Datuak();
            da.konexioa();// konexioa egin datu basearekin.
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
    public String[][] center_3_txertatu(){
        try {
            Datuak da = new Datuak();
            da.konexioa();// datu basearekin konexioa egin.
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// kontsulta1 sortu.
            Statement stmt2= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);//Kontsult2sortu.

            ResultSet rs = stmt.executeQuery("SELECT id_produktua, prezioa, kantitatea, deskribapena FROM Produktuak where Produktu_mota='eztia' order by id_produktua asc");// kotsulta1 egin eta balioak gorde.
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
    public String[][] center_5_txertatu(){// materiak taula.
        try {
            Datuak da = new Datuak();
            da.konexioa();// konexioa sortu datu basearekin.
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

    public void izena_jarri(String identifikatzailea){// identifikatzaile bakoitzarekin sozioaren datu guztiak emnago ditu.
        try {
            konexioa();
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs= stmt.executeQuery("Select ID_SOZIOA, EMAIL, JAIOTE_EGUNA, ERLE_KANTITATEA, KOLMENA_KANTITATEA, SOZIO_IZENA, SOZIO_ABIZENA, NAN, TELEFONOA, PASAHITZA From sozioak");
            while (rs.next()) {

                String izen_abizen = rs.getString("SOZIO_IZENA") + " " +rs.getString("SOZIO_ABIZENA");// ArrayList-etik izena eta abizen artu eta batu, hau identifikatzaile bat da eta honekin konparatzeko.
                if ((izen_abizen).equals(identifikatzailea)) {// sortukako testuak datu baseko izen eta abizenekin koinziditzen badu.
                    // sozioaren datu guztiak gorde.
                    id_sozioa_login = rs.getString("ID_SOZIOA");
                    email_login = rs.getString("EMAIL");
                    izena_login = rs.getString("SOZIO_IZENA");
                    abizena_login = rs.getString("SOZIO_ABIZENA");
                    nan_login = rs.getString("NAN");
                    telefonoa_login = rs.getString("TELEFONOA");
                    String[] ju = rs.getString("JAIOTE_EGUNA").split(" ");
                    jaio_eguna_login = ju[0];
                    erle_kantitatea_login = rs.getString("ERLE_KANTITATEA");
                    kolmena_kantitatea_login = rs.getString("KOLMENA_KANTITATEA");
                    pazaitza_login = rs.getString("PASAHITZA");

                } else if (rs.getString("NAN").equals(identifikatzailea)){// sartutako identifikatzailea datu baseko nan zenbakiarekin bat egiten badu.
                    // sozioaren datu guztiak gorde.
                    id_sozioa_login = rs.getString("ID_SOZIOA");
                    email_login = rs.getString("EMAIL");
                    izena_login = rs.getString("SOZIO_IZENA");
                    abizena_login = rs.getString("SOZIO_ABIZENA");
                    nan_login = rs.getString("NAN");
                    telefonoa_login = rs.getString("TELEFONOA");
                    String[] ju = rs.getString("JAIOTE_EGUNA").split(" ");
                    jaio_eguna_login = ju[0];
                    erle_kantitatea_login = rs.getString("ERLE_KANTITATEA");
                    kolmena_kantitatea_login = rs.getString("KOLMENA_KANTITATEA");
                    pazaitza_login = rs.getString("PASAHITZA");

                } else if (rs.getString("TELEFONOA").equals(identifikatzailea)){// sartutako identifikatzailea datu baseko telefono zenbakiarekin bat egiten badu.
                    // sozioaren balio guztiak gorde.
                    id_sozioa_login = rs.getString("ID_SOZIOA");
                    email_login = rs.getString("EMAIL");
                    izena_login = rs.getString("SOZIO_IZENA");
                    abizena_login = rs.getString("SOZIO_ABIZENA");
                    nan_login = rs.getString("NAN");
                    telefonoa_login = rs.getString("TELEFONOA");
                    String[] ju = rs.getString("JAIOTE_EGUNA").split(" ");
                    jaio_eguna_login = ju[0];
                    erle_kantitatea_login = rs.getString("ERLE_KANTITATEA");
                    kolmena_kantitatea_login = rs.getString("KOLMENA_KANTITATEA");
                    pazaitza_login = rs.getString("PASAHITZA");
                } else if (rs.getString("EMAIL").equals(identifikatzailea)){// sartutako identifikatzailea datu baseko email-arekin bat egiten badu.
                    id_sozioa_login = rs.getString("ID_SOZIOA");
                    email_login = rs.getString("EMAIL");
                    izena_login = rs.getString("SOZIO_IZENA");
                    abizena_login = rs.getString("SOZIO_ABIZENA");
                    nan_login = rs.getString("NAN");
                    telefonoa_login = rs.getString("TELEFONOA");
                    String[] ju = rs.getString("JAIOTE_EGUNA").split(" ");
                    jaio_eguna_login = ju[0];
                    erle_kantitatea_login = rs.getString("ERLE_KANTITATEA");
                    kolmena_kantitatea_login = rs.getString("KOLMENA_KANTITATEA");
                    pazaitza_login = rs.getString("PASAHITZA");
                }
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean identifikatzailea_ondo(String identifikatzailea){// Sartu den identifikatzailea baimentzen du
        try {
            konexioa();
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs= stmt.executeQuery("Select EMAIL, SOZIO_IZENA, SOZIO_ABIZENA, NAN, TELEFONOA From sozioak");
            while (rs.next()) {
                String izen_abizen = rs.getString("SOZIO_IZENA") + " " +rs.getString("SOZIO_ABIZENA");// Select-etik izena eta abizen artu eta batu, hau identifikatzaile bat da eta honekin konparatzeko.
                if ((izen_abizen).equals(identifikatzailea)) {// sortukako testuak datu baseko izen eta abizenekin koinziditzen badu.
//identifikatzilea mot a1 izango da.
                    identifikatzaile_mota=1;
                    return true;
                } else if (rs.getString("NAN").equals(identifikatzailea)){// sartutako identifikatzailea datu baseko nan zenbakiarekin bat egiten badu.
// identifikatzaile mota 2 izango da.
                    identifikatzaile_mota=2;
                    return true;
                } else if (rs.getString("TELEFONOA").equals(identifikatzailea)){// sartutako identifikatzailea datu baseko telefono zenbakiarekin bat egiten badu.
// identifikatzaile mota 3 izango da.
                    identifikatzaile_mota=3;
                    return true;
                } else if (rs.getString("EMAIL").equals(identifikatzailea)){// sartutako identifikatzailea datu baseko email-arekin bat egiten badu.
// identifikatzaile mota 4 izango da.
                    identifikatzaile_mota=4;
                    return true;
                }
            }
            con.close();
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String pazaitza_jarri(String identifikatzaile){// identifikatzaile bakoitzeko pasahitza lortu.
        ArrayList<Sozioak> sozio_lista= sozio_ArrayList();// sozio gustien arraylist bat sortu.
        if(identifikatzaile_mota==1){// identifikatzaile mota 1 bada.
            for (Sozioak s: sozio_lista) {// arraylisteko sozio bakoitzeko.
                String izen_abizen = s.getSozio_izena()+" "+s.getSozio_abizena();
                if (identifikatzaile.equals(izen_abizen)){// sozioaren izena eta abizena arraylisteko balioekin bat egiten badute.
                    return s.getPasahitza(); // sozio horren pasahitza itzuli.
                }
            }
        } else if (identifikatzaile_mota==2){// identifikatzaile mota 2 bada.
            for (Sozioak s: sozio_lista) {// arraylist-eko sozio bakoitzeko.
                if(identifikatzaile.equals(s.getNan())){// nan zenbakiak bat egiten badute.
                    return s.getPasahitza();// pasahitza itzuli
                }
            }
        }else if (identifikatzaile_mota==3){// identifikatzaile mota 3 bada.
            for (Sozioak s: sozio_lista) {// arraylist-eko sozio bakoitzeko.
                if (identifikatzaile.equals(s.getTelefonoa())){// telefono zenbakiak bat egiten badute.
                    return s.getPasahitza();// pasahitza itzuli.
                }
            }
        }else if (identifikatzaile_mota==4){//identifikatzaile mota 4 bada.
            for (Sozioak s: sozio_lista) {// arraylist-eko balio bakoitzeko
                if (identifikatzaile.equals(s.getEmail())){// email-ak bat egiten badute.
                    return s.getPasahitza();// pasahitza itzuli.
                }
            }
        }
        return "ERROREA pazaitza_jarri";// identifikatzail mota okerrabaldin bada mezu itzuli.
    }

    public ArrayList<Sozioak> sozio_ArrayList(){// sozioen arraylist bat bueltatuko duen funtzioa.

        try {
            Datuak da = new Datuak();
            da.konexioa();// datu basera konektatu.
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// datu baserako kontsulta sortu.
            //Eskaera egin.
            ResultSet rs = stmt.executeQuery("SELECT id_sozioa, id_zuzendaria, erle_kantitatea, kolmena_kantitatea, sozio_izena, sozio_abizena, nan, telefonoa, jaiote_eguna, email, pasahitza FROM SOZIOAK order by id_sozioa asc");
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
    public String[][] sozio_Array(){//Array bat bueltatuko duen funtzioa.

        try {
            Datuak da = new Datuak();
            da.konexioa();// datu basearekin konexioa egin.
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// datu baseko kontsulta sortu.
            // eskaera egin.
            ResultSet rs = stmt.executeQuery("SELECT id_sozioa, id_zuzendaria, erle_kantitatea, kolmena_kantitatea, sozio_izena, sozio_abizena, nan, telefonoa, jaiote_eguna, email, pasahitza " +
                    "FROM SOZIOAK order by id_sozioa asc");

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
    public String[][] ezti_bilatzailea(String ezti_izena){
        try {
            ArrayList<String> id= new ArrayList<>();
            Datuak da = new Datuak();
            da.konexioa();// datu basearekin konexioa egin.
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// kontsulta1 sortu.
            Statement stmt2= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);//Kontsult2sortu.

            ResultSet rs2 = stmt2.executeQuery("SELECT EZTIA_MOTA, id_produktua FROM Eztia WHERE EZTIA_MOTA like '"+ezti_izena.trim()+"%' ORDER BY id_produktua");//Kontsulta2 egin eta balioak gorde.
            while (rs2.next()) {
                id.add(rs2.getString("id_produktua"));
            }
            rs2.beforeFirst();
            ResultSet rs = stmt.executeQuery("SELECT id_produktua, prezioa, kantitatea, deskribapena FROM Produktuak where Produktu_mota='eztia'");// kotsulta1 egin eta balioak gorde.

            rs.last();// kontsulta1-eko azken balioan kokatu.
            rs2.last();//kontsulta2-ko azken balioan kokatu.
            String[] id_produktuak= new String[rs2.getRow()];// kontsulta1-eko azken lerroaren arabera tamaina horretako array a sortu.
            String[][] eztiak=new String[rs.getRow()][4];// kontsulta2-ko azken lerroaren arabera tamaina horretako array a sortu.
            rs.beforeFirst();// kontsulta1-eko lehen balioaren gainean kokatu.
            rs2.beforeFirst();// kontsulta2-ko lehen balioaren gainean kokatu.
            int x= 0;
            while (rs.next()){// kontsulta1-eko balioak hurrengoa duten bitartean.
                for (String a: id){
                    if (rs.getString("id_produktua").equals(a)) {
                        String prezioa = rs.getString("prezioa");//koltsulta1-eko prezioa zutabeko balioa gorde.
                        String kantitatea = rs.getString("kantitatea");//koltsulta1-eko kantitatea zutabeko balioa gorde.
                        String deskribapena = rs.getString("deskribapena");//koltsulta1-eko deskribapena zutabeko balioa gorde.
                        String id_produktua = rs.getString("id_produktua");//koltsulta1-eko id_produktua zutabeko balioa gorde.
                        id_produktuak[x] = id_produktua; // id_produktua gorde.
                        eztiak[x][1] = prezioa;// beste_datuak array-ean prezioa gorde.
                        eztiak[x][2] = kantitatea;// beste_datuak array-ean kantitatea gorde.
                        eztiak[x][3] = deskribapena;// beste_datuak array-ean deskribapena gorde.
                        x++;
                    }
                }
            }
            x=0;
            rs2.beforeFirst();
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
    public String[][] sozio_Array_bilatzailea(String izena){//Array bat bueltatuko duen funtzioa.

        try {
            Datuak da = new Datuak();
            da.konexioa();// datu basearekin konexioa egin.
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// datu baseko kontsulta sortu.
            // eskaera egin.
            ResultSet rs = stmt.executeQuery("SELECT id_sozioa, id_zuzendaria, erle_kantitatea, kolmena_kantitatea, sozio_izena, sozio_abizena, nan, telefonoa, jaiote_eguna, email, pasahitza FROM SOZIOAK WHERE sozio_izena like '"+izena.trim()+"%' order by id_sozioa asc");

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
    public HashSet<String> id_sozioak(){// HashSet bat bueltatzen duen funztioa.
        try {
            konexioa();// datu basearekin konexioa sortu.
            HashSet<String> id_sozioak= new HashSet<>();// HashSet bat sortu id_sozioarentzako.
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);// datu basean egiteko kontsulta sortu.
            ResultSet rs= stmt.executeQuery("SELECT id_sozioa FROM sozioak");// eskaera definitu eta erantzuna gorde.

            while (rs.next()){// erantzunak hurrengo balioa duen bitartean.
                id_sozioak.add(hash(String.valueOf(rs.getInt("id_sozioa"))));// HashSet-era bueltatutako id_sozioak gehitu.
            }
            con.close();// kontsulta desegin.
            return id_sozioak;// HashSet-a bueltatu.

        } catch (SQLException e) {//Errorea kudeatu.
            throw new RuntimeException(e);
        }
    }
    public HashSet<String> zuzendariak(){// zuzendarien HashSet bat bueltatzen duen funtzioa.
        try {
            Datuak da = new Datuak();
            da.konexioa();// datu basearekin konexioa egin
            HashSet<String> id_zuzendariak= new HashSet<>();// HashSet-a sortu.
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);// kontsulta sortu.
            ResultSet rs= stmt.executeQuery("SELECT id_zuzendaria FROM sozioak");// Eskaera definitu eta balioak jaso.

            while (rs.next()){// jasotako balioak hurrengo duen bitartean.
                id_zuzendariak.add(hash(String.valueOf(rs.getInt("id_zuzendaria"))));// id_zuzendariak HashSet-ean gorde.
            }
            con.close();// kontsulta desegin.
            return id_zuzendariak;// zuzendarien HashSet-a bueltatu.

        } catch (SQLException e) {//Errorea kuedeatu
            throw new RuntimeException(e);
        }
    }

    public int id_atera_login(){//logeatutako sozioaren id_sozioa bultatzen duen funtzioa.
        Datuak da = new Datuak();
        ArrayList<Sozioak> sozio_lista= da.sozio_ArrayList();//Sozioaen arraylist bat sortu, haurreko funtzioan sortutako sozioen arraylist-arekin.
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
    public int id_atera(String nan){// nan zebakia jasota sozioaren id-a bueltatuko duen funtzioa.
        Datuak da = new Datuak();
        ArrayList<Sozioak> sozio_lista= da.sozio_ArrayList();// Arraylist-a sortu, haurreko funtzioan sortutako arraylist-arekin.
        for (Sozioak s: sozio_lista) {// ArrayList-eko sozio bakoitzeko.
            if (nan.equals(s.getNan())){//jasotako nan zenbakia datu basean badago.
                return s.getId_sozioa();// sozioaren id-a itzuli.
            }
        }
        return 0;
    }
    public int id_hadiena(){// azken id_sozioa jakiteko funtzioa.
        try {
            Datuak da = new Datuak();
            da.konexioa();// datu basearekin konexio egin.
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);//kontsulta sortu.
            ResultSet rs = stmt.executeQuery("SELECT MAX(id_sozioa) FROM SOZIOAK");//Eskaera egin eta balioak jaso.

            rs.next();// jasotako balioaren gainean kokatu.
            return rs.getInt(1)+1;// jasotako balioaren hurrengo zenbakia bueltatu.

        } catch (SQLException e) {// errorea kudeatu.
            throw new RuntimeException(e);
        }
    }

    public String nan_atera(String id){// soizoaren id-a jasota honen nan zenbakia bultatuko duen funtzioa.
        Datuak da = new Datuak();
        ArrayList<Sozioak> sozio_lista= da.sozio_ArrayList();// Arraylist-a sortu, haurreko funtzioan sortutako arraylist-arekin.
        for (Sozioak s: sozio_lista) {// Arraylist-eko
            if (id.equals(hash(String.valueOf(s.getId_sozioa())))){// jasotako id_sozioa
                return s.getNan();// nan zenbakia itzuli.
            }
        }
        return "ERROR";// ez bada betzen "error" itzuli.
    }
    public String[][] sozietate_Arraya(){
        Datuak da = new Datuak();
        da.konexioa();
        try {
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs= stmt.executeQuery("SELECT id_asoziazioa, asoziazio_izena, herrialdea FROM asoziazioak order by id_asoziazioa asc");
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
    public ArrayList<Sozietateak> sozietate_Arrayalist(){
        Datuak da = new Datuak();
        da.konexioa();
        try {
            ArrayList<Sozietateak> sozietateak= new ArrayList<>();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs= stmt.executeQuery("Select id_asoziazioa, asoziazio_izena, herrialdea From asoziazioak order by id_asoziazioa asc");
            while (rs.next()){
                Sozietateak s= new Sozietateak(rs.getInt("id_asoziazioa"), rs.getString("asoziazio_izena"), rs.getString("herrialdea"));
                sozietateak.add(s);
            }
            con.close();
            return sozietateak;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String[][] sozietate_Arraya_bilatzailea(String izena){
        Datuak da = new Datuak();
        da.konexioa();
        try {
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs= stmt.executeQuery("SELECT id_asoziazioa, asoziazio_izena, herrialdea FROM asoziazioak WHERE asoziazio_izena like '"+izena.trim()+"%' order by id_asoziazioa asc");
            rs.last();
            String[][] sozietateak=  new String[rs.getRow()][3];
            rs.beforeFirst();
            int x=0;
            while (rs.next()) {

                sozietateak[x][0] = rs.getString("id_asoziazioa");
                sozietateak[x][1] = rs.getString("asoziazio_izena");
                sozietateak[x][2] = rs.getString("herrialdea");
                x++;
            }
            con.close();
            return sozietateak;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String[][] sozietate_Arraya_herria(String izena){
        konexioa();
        try {
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs= stmt.executeQuery("SELECT id_asoziazioa, asoziazio_izena, herrialdea FROM asoziazioak WHERE herrialdea like '"+izena+"'");
            rs.last();
            String[][] sozietateak=  new String[rs.getRow()][3];
            rs.beforeFirst();
            int x=0;
            while (rs.next()) {

                sozietateak[x][0] = rs.getString("id_asoziazioa");
                sozietateak[x][1] = rs.getString("asoziazio_izena");
                sozietateak[x][2] = rs.getString("herrialdea");
                x++;
            }
            con.close();
            return sozietateak;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void datuak_eguneratu(String email, String nan, long telefonoa, String izena, String abizena, long kolmena_kantitatea, long erle_kantitatea, Date jaio_eguna, String pazaitza){// balioak emanda datu basean update bat egingo duen prozedura.
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
            update.setString(9, hash(pazaitza));
            update.setString(10, id_sozioa_login);

            update.executeUpdate();// updatea exekutatu.
        } catch (SQLException e) {// Errorea kudeatu.
            throw new RuntimeException("Error al ejecutar la actualización", e);
        }
    }

    public void erregistratu(Date jaio_eguna_date){
        Datuak da = new Datuak();
        try {
            da.konexioa();// datu basearekin konexioa egin.
            //eremu bakoitzean sartutako textua jasota  insert into bat sortu.
            PreparedStatement insert = con.prepareStatement("INSERT INTO sozioak(id_sozioa, id_zuzendaria, erle_kantitatea, kolmena_kantitatea, sozio_izena, sozio_abizena, nan, telefonoa, jaiote_eguna, email, pasahitza) \n" +
                "VALUES ("+id_hadiena()+",3,NULL,NULL,'"+izena_login+"', '"+abizena_login+"', '"+nan_login+"', "+(Long.parseLong(telefonoa_login))+", " +
                    "TO_DATE('"+jaio_eguna_date+"', 'YYYY-MM-DD'), '"+email_login+"', '"+hash(pazaitza_login)+"')");
            insert.executeUpdate(); // aurreko insert-a datu basean exekutatu.
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logeatua_dago=true;// logeatuta egon.
        if (da.zuzendariak().contains(id_atera(nan_login))){// logeatutako sozioa zuzendaria den jakin.
            zuzendaria_da=true;// zuzendaria izan.
        }
    }
    public String[][] besteak_bilatu(String produktu_izena){// beste batzuk taula buelatuko duen funtzioa.
        try {
            Datuak da = new Datuak();
            da.konexioa();// konexioa egin datu basearekin.
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// datu baseko kontsulta sortu.

            ResultSet rs = stmt.executeQuery("SELECT Produktu_mota, prezioa, kantitatea, deskribapena FROM Produktuak where Produktu_mota not like 'eztia' and Produktu_mota like '"+produktu_izena.trim()+"%'");// kontsulta definitu eta balioak gorde.
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
    public String[][] materiala_bilatu(String material_izena){// materiak taula.
        try {
            Datuak da = new Datuak();
            da.konexioa();// konexioa sortu datu basearekin.
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// kontsulta sortu.

            ResultSet rs = stmt.executeQuery("SELECT materiala_izena, prezioa, kantitatea, deskribapena FROM Materiala WHERE materiala_izena like '"+material_izena.trim()+"%'");// kontsulta egin eta balioak gorde.
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

    public String hash(String originalString){
        String sha1 = null;
        try {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(originalString.getBytes("UTF-8"), 0, originalString.length());
            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());

            return sha1;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            System.err.println("Hash errorea");
        }
        return sha1;
    }

    public void textuGrixa(JTextField kuadroa, String textua, Color kolorea){
        kuadroa.setEnabled(false);// text field bateko atzeko testua
        kuadroa.addMouseListener(new MouseAdapter() { // text field en gainean entzule bat sortu click-ateko momenturako.
            @Override
            public void mouseClicked(MouseEvent e) {// click egitean.
                kuadroa.setEnabled(true);// editagarria izango dean text field-eko tstu grixa.
            }

        });

        kuadroa.setForeground(kolorea);// text field-eko atzeko testuaren kolorea ezarri.
        kuadroa.setText(textua);// text field-eko atzean agertuko den testua.
        kuadroa.addFocusListener(new FocusListener() {// entzule bat sortu momentu guztian eremuak kontrolatzeko.
            @Override
            public void focusGained(FocusEvent e) {// entzulearen funtzio bat da, hau eremu batean click egiterakoan aktibaruko da.
                SwingUtilities.invokeLater(() -> {
                    if (kuadroa.getText().equals(textua)) { // eremuko testua eta eremua berdinak badira.
                        kuadroa.setText(" ");// Testu grixa ezabatu egingo da.
                        kuadroa.setForeground(Color.BLACK); // idazten den testuaren kolorea beltza ezango da.
                    }
                });
            }
            @Override
            public void focusLost(FocusEvent e) {// eremutik kampo click egiten bada.
                SwingUtilities.invokeLater(() -> {
                    if (kuadroa.getText().equals(" ")) {// eremuan ezer idatzita ez badago.
                        kuadroa.setText(textua); // Eremuko izena txertatuko du eremuan.
                        kuadroa.setForeground(kolorea);// eremuko testuaren kolorea grixa izango da.
                    }
                });
            }
        });

        kuadroa.getDocument().addDocumentListener(new DocumentListener() {// eremuan egiten diren aldaketak kudeatuko dituen entzulea sortu.
            @Override
            public void insertUpdate(DocumentEvent e) {// txertatzerakoan.
                SwingUtilities.invokeLater(() -> {
                    if (kuadroa.getText().isEmpty()) {// eremua utsa badago.
                        kuadroa.setText(textua);// eremuaren arabera testua agertu.
                        kuadroa.setForeground(kolorea);// testuaren kolorea grixa bihurtu.
                    }
                });
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

    }

    //sozioen csv bat sortzen du.
    public void sozioak_csv(){
        try {
            konexioa();
            BufferedWriter taula_sortu = new BufferedWriter(new FileWriter(".\\Deskargak\\Sozioak.csv"));
            taula_sortu.write("ID,Zuzendaria,Erle KANT,Erlauntz,Izena,Abizena,NAN,Telefonoa,Jaiote Data,Email");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs= stmt.executeQuery("SELECT id_sozioa, id_zuzendaria, erle_kantitatea, kolmena_kantitatea, sozio_izena, sozio_abizena, nan, telefonoa, jaiote_eguna, email FROM SOZIOAK order by id_sozioa asc");
            while (rs.next()){
                taula_sortu.newLine();
                taula_sortu.write(rs.getString("id_sozioa")+","+rs.getString("id_zuzendaria")+","+rs.getString("erle_kantitatea")+","+rs.getString("kolmena_kantitatea")+","+rs.getString("sozio_izena")+","+rs.getString("sozio_abizena")+","+rs.getString("nan")+","+rs.getString("telefonoa")+","+rs.getString("jaiote_eguna")+","+rs.getString("email"));
            }
            taula_sortu.close();
            con.close();
        } catch (IOException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    //sozietateen csv bat sortzen du
    public void sozietateak_csv(){
        try {
            konexioa();
            BufferedWriter taula_sortu = new BufferedWriter(new FileWriter(".\\Deskargak\\Asoziazioak.csv"));
            taula_sortu.write("ID,Izena,Herrialdea");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs= stmt.executeQuery("SELECT id_asoziazioa, asoziazio_izena, herrialdea FROM asoziazioak order by id_asoziazioa asc");
            while (rs.next()){
                taula_sortu.newLine();
                taula_sortu.write(rs.getString("id_asoziazioa")+","+rs.getString("asoziazio_izena")+","+rs.getString("herrialdea"));
            }
            taula_sortu.close();
            con.close();
        } catch (IOException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
