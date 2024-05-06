package erleak;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;

import static erleak.Login.*;
import static erleak.Datuak.*;

public class Sesioa {
    private JFrame f_sesio = new JFrame();
    private JPanel panel1, panel2, panel3;
    private JButton itzuli1, itxi_sesioa, editatu, gorde;
    private JTextField email, izena, abizena, nan, telefonoa, jaio_eguna, erle_kantitatea, kolmena_kantitatea, pazaitza, pazaitza2;
    private JLabel email_textua, izena_textua, abizena_textua, nan_textua, telefonoa_textua, jaio_eguna_Textua, erle_kantitatea_textua, kolmena_kantitatea_textua,pazaitza_textua, pazaitza2_textua, pazaitz_mezua;
    public  void sortu_login(){//logina sortzeko funtzioei deitu.
        north();
        center();
        south();
        sesio_orria();
    }
    public void sesio_orria(){//sesio orria sortu.
        f_sesio.setTitle("Sesioa");// Display-ari titulua jarri.
        f_sesio.setSize(400, 600);// display-aren tamaina ezarri.
        f_sesio.setVisible(true);// ikusgai egin.
        f_sesio.setLocationRelativeTo(null);// display-a pantailaren erdian agertu.
        f_sesio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// lehioa ixterakoan programa amaitu.
        f_sesio.setLayout(new BorderLayout()); //display nagusia BorderLayout matakoa ezarri.
    }
    public void north(){// display-ko gaiko panela sortu.
        panel2 = new JPanel();//panel2 sortu.
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.X_AXIS)); //panel2-ri box layout x ardatzean mota ezarri.
        ImageIcon fondoa_irudia = new ImageIcon(".\\Irudiak\\kuadroa_erdia.jpg"); // irudia aldagaian kargatu.
        ImageIcon fondo_aldatuta= new ImageIcon(fondoa_irudia.getImage().getScaledInstance(400, 80, java.awt.Image.SCALE_SMOOTH));// irudiaren tamaina aldatu.
        JLabel irudia = new JLabel(fondo_aldatuta);// irudia label batean bihurtu.
        panel2.add(irudia);// irudiaren label-a panel2-an txertatu.
        f_sesio.add(panel2, BorderLayout.NORTH);// panel2-a display nagusian txertatu.
    }
    public void center(){// sesioa hasteko display-eko textfiel eta labelak sortu, sozioaren datu bakoitzarentzako bana.
        panel1= new JPanel(null);
        email_textua= new JLabel("Email:");
        email= new JTextField(17);
        izena_textua= new JLabel("Izena:");
        izena = new JTextField();
        abizena_textua= new JLabel("Abizena:");
        abizena = new JTextField();
        nan_textua= new JLabel("NAN:");
        nan= new JTextField();
        telefonoa_textua= new JLabel("Telefonoa:");
        telefonoa= new JTextField();
        erle_kantitatea_textua= new JLabel("Erle KANT");
        erle_kantitatea= new JTextField();
        kolmena_kantitatea_textua= new JLabel("Kolmena KANT");
        kolmena_kantitatea= new JTextField();
        jaio_eguna_Textua= new JLabel("Jaio Eguna:");
        jaio_eguna= new JTextField();
        pazaitza_textua= new JLabel("Pazaitza");
        pazaitza= new JTextField();
        pazaitza2_textua= new JLabel("Pazaitza Egiaztatua");
        pazaitza2= new JTextField();
        gorde= new JButton("Gorde Aldaketak");
        pazaitz_mezua= new JLabel("");

        // aurretik sortutako textfiel eta label guztie tamaina ezarri.
        email_textua.setBounds(90,58,100, 10);
        email.setBounds(90,70,210,20);
        izena_textua.setBounds(90,138,100, 10);
        izena.setBounds(90,150,100,20);
        abizena_textua.setBounds(200,138,100, 10);
        abizena.setBounds(200,150,100,20);
        nan_textua.setBounds(90,98, 100, 10);
        nan.setBounds(90,110,100,20);
        telefonoa_textua.setBounds(200, 98, 100, 10);
        telefonoa.setBounds(200, 110, 100, 20);
        erle_kantitatea_textua.setBounds(200, 176, 100, 15);
        erle_kantitatea.setBounds(200, 190, 100, 20);
        kolmena_kantitatea_textua.setBounds(90, 176, 100, 15);
        kolmena_kantitatea.setBounds(90, 190, 100, 20);
        jaio_eguna_Textua.setBounds(145, 216, 100, 15);
        jaio_eguna.setBounds(145, 230, 100, 20);
        pazaitza_textua.setBounds(145, 256, 200, 15);
        pazaitza.setBounds(145, 270, 100, 20);
        pazaitza2_textua.setBounds(145, 296, 200, 15);
        pazaitza2.setBounds(145, 310, 100, 20);
        gorde.setBounds(92, 350, 200, 25);
        pazaitz_mezua.setBounds(92, 390, 200, 25);

        //fiel bakoitzari dagokion testua ezarri datuak klasetik.
        email.setText(email_login);
        izena.setText(izena_login);
        abizena.setText(abizena_login);
        nan.setText(nan_login);
        telefonoa.setText(telefonoa_login);
        jaio_eguna.setText(jaio_eguna_login);
        erle_kantitatea.setText(erle_kantitatea_login);
        kolmena_kantitatea.setText(kolmena_kantitatea_login);
        pazaitza.setText(pazaitza_login);
        pazaitza2.setText(pazaitza_login);

        // eremu guztiak ez editagarriak egin.
        email.setEnabled(false);
        izena.setEnabled(false);
        abizena.setEnabled(false);
        nan.setEnabled(false);
        telefonoa.setEnabled(false);
        jaio_eguna.setEnabled(false);
        erle_kantitatea.setEnabled(false);
        kolmena_kantitatea.setEnabled(false);
        pazaitza.setEnabled(false);
        pazaitza2.setEnabled(false);
        gorde.setEnabled(false);

        // panel1-ean label eta textfiel guztian gehitu.
        panel1.add(email_textua);
        panel1.add(email);
        panel1.add(izena_textua);
        panel1.add(izena);
        panel1.add(abizena_textua);
        panel1.add(abizena);
        panel1.add(nan_textua);
        panel1.add(nan);
        panel1.add(telefonoa_textua);
        panel1.add(telefonoa);
        panel1.add(jaio_eguna_Textua);
        panel1.add(jaio_eguna);
        panel1.add(erle_kantitatea_textua);
        panel1.add(erle_kantitatea);
        panel1.add(kolmena_kantitatea_textua);
        panel1.add(kolmena_kantitatea);
        panel1.add(pazaitza_textua);
        panel1.add(pazaitza);
        panel1.add(pazaitza2_textua);
        panel1.add(pazaitza2);
        panel1.add(gorde);
        panel1.add(pazaitz_mezua);

        gorde.addActionListener(new ActionListener() { //gorde botoiaren gainean entzle bat sortu.
            @Override
            public void actionPerformed(ActionEvent e) {// aldaketak gordetzeko botoiaren gainean click egiterakoan.
                    if (pazaitza.getText().equals(pazaitza2.getText())) {// pasahitza bi textfield-etan berdina bada.
                        pazaitz_mezua.setText(" ");// errore mezurik ez.
// sartutako datu berriak jaso textfiel bakoitzetik eta datuak klaseko funtzioari deia eginez, datu basean update bat egin eta datuak eguneratu.
                        datuak_eguneratu(email.getText(), nan.getText(), Long.parseLong(telefonoa.getText()), izena.getText(), abizena.getText(), Long.parseLong(kolmena_kantitatea.getText()), Long.parseLong(erle_kantitatea.getText()), Date.valueOf(jaio_eguna.getText()), pazaitza2.getText());
// datuak ez editagarriak egin.
                        email.setEnabled(false);
                        izena.setEnabled(false);
                        abizena.setEnabled(false);
                        nan.setEnabled(false);
                        telefonoa.setEnabled(false);
                        jaio_eguna.setEnabled(false);
                        erle_kantitatea.setEnabled(false);
                        kolmena_kantitatea.setEnabled(false);
                        pazaitza.setEnabled(false);
                        pazaitza2.setEnabled(false);
                        gorde.setEnabled(false);
                    }else {// sartutako bi pasahitzak ezberdinak badira.
                        pazaitz_mezua.setText("Pasahitzak ez dira berdinak");// errore mezua pantailaratu da.
                        pazaitz_mezua.setForeground(Color.red);// errore mezuaren kolorea gorria ezarri.
                    }
            }
        });

        f_sesio.add(panel1, BorderLayout.CENTER);// display nagusiko erdian panel1 gehitu.

    }
    public void south(){

        panel3 = new JPanel();// panel3 sortu.
        panel3.setLayout(new BoxLayout(panel3,BoxLayout.X_AXIS));// panel3 box leyaut x ardatzean mota ezarri.

        JLabel tartea1= new JLabel("    ");// jlabel bat sortu textu huts batekin.
        tartea1.setBorder(new EmptyBorder(0, 0, 0, 70));// jlabel-aren kokalekua ezarri.

        itzuli1 = new JButton("Atzera");// itzuli botoia sortu.
        editatu= new JButton("Editatu");// editatu botoia sortu.
        itxi_sesioa= new JButton("Sesioa Itxi");// sesioa itxi botoia sortu.
// panel3an sortutako botaiak eta label-a gahitu.
        panel3.add(tartea1);
        panel3.add(itzuli1);
        panel3.add(editatu);
        panel3.add(itxi_sesioa);
        itzuli1.addActionListener(new ActionListener() {// izuli botoiaren gainean entzule bat sortu.
            @Override
            public void actionPerformed(ActionEvent e) {//itzuli botoiaren gainean click egiterakoan.
                new Index().sortu();// index display-a sortu eta pantailaratu.
                f_sesio.dispose();// sesio display-a itxi.
            }
        });

        editatu.addActionListener(new ActionListener() { // editatu botoiaren gainean entzule bat sortu.
            @Override
            public void actionPerformed(ActionEvent e) { // botoiaren gainean click egiterakoan.

                //  profileko textfield guztiak editagarriak egin.
                email.setEnabled(true);
                izena.setEnabled(true);
                abizena.setEnabled(true);
                nan.setEnabled(true);
                telefonoa.setEnabled(true);
                jaio_eguna.setEnabled(true);
                erle_kantitatea.setEnabled(true);
                kolmena_kantitatea.setEnabled(true);
                pazaitza.setEnabled(true);
                pazaitza2.setEnabled(true);
                gorde.setEnabled(true);
            }
        });

        itxi_sesioa.addActionListener(new ActionListener() {// itxi sesioaren botoiaren gainean entzule bat gehitu.
            @Override
            public void actionPerformed(ActionEvent e) { // botoiaren gainean click egitean.
                try {
                    // logeatuta ez egotean eta ezuzendaria ez izan.
                    logeatua_dago=false;
                    zuzendaria_da=false;
                    // langilearen datuak null gorde fitxategian.
                    id_sozioa_login= null;
                    email_login=null;
                    izena_login= null;
                    abizena_login= null;
                    nan_login= null;
                    telefonoa_login=null;
                    jaio_eguna_login= null;
                    erle_kantitatea_login= null;
                    kolmena_kantitatea_login= null;
                    pazaitza_login=null;


                    FileWriter fw= new FileWriter(".\\sesio.txt");// fitxategi gainean idazlea difinitu.
                    fw.write(Integer.toString(0));// fitxategian zenbakiak 0;
                    fw.close();//idazlea itxi.
                } catch (IOException ex) {//Errorea arrapatu
                    throw new RuntimeException(ex);//Errorea bota.
                }
                f_sesio.dispose();// sesio display-a itxi.
                new Index().sortu(); // index display-a sortu eta pantailaratu.
            }
        });

        f_sesio.add(panel3, BorderLayout.SOUTH);//panel3 sesio displayko behealdean txertau.

    }
    public static void main(String[] args){
        new Sesioa().sortu_login();// logis display sortu eta pantailaratu.
    }
}
