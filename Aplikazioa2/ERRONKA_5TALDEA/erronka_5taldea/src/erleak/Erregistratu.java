package erleak;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import static erleak.Datuak.*;
import static erleak.Sozioak.*;
import static erleak.Login.*;
public class Erregistratu {
    private JFrame f_Erregistratu= new JFrame();
    private JPanel panel1, panel2, panel3;
    private JTextField email, izena, abizena, pasahitza, pasahitza2;
    private JFormattedTextField nan, telefonoa,jaio_eguna;
    private JLabel nan_textua, jaio_eguna_Textua, pasahitza_textua, pasahitza2_textua;
    private JButton login, erregistratu, atzera;

    public static void main(String[] args){
        new Erregistratu().sortu_Erregistratu();
    }


    public void sortu_Erregistratu() {// eregistratu display-a sortzeko funtzioei deitu.
        north();
        south();
        center();
        erregistratu_orria();
    }
    public void erregistratu_orria(){
        f_Erregistratu.setTitle("Erregistratu");//Erregistartu orriari taitulua jarri.
        f_Erregistratu.setSize(400, 400);// Erregistratu horriari tamaina ezarri.
        f_Erregistratu.setVisible(true);// orria ikusgai jarri.
        f_Erregistratu.setLocationRelativeTo(null);// display-a erdian agetzea.
        f_Erregistratu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// display-a ixterakoak funtzioa guztia bukatu.
        f_Erregistratu.setLayout(new BorderLayout());// border layout mota ezarri diplay-ari.
    }

    public void north(){
        panel1 = new JPanel();// panel bat sortu.
        panel1.setLayout(new BoxLayout(panel1,BoxLayout.X_AXIS));// panela box layout batean bihurtu medu horizontalean.
        ImageIcon fondoa_irudia = new ImageIcon(".\\Irudiak\\kuadroa_erdia.jpg");// irudia aldagaian kargatu,
        ImageIcon fondo_aldatuta= new ImageIcon(fondoa_irudia.getImage().getScaledInstance(400, 80, java.awt.Image.SCALE_SMOOTH));// irudiaren tamaina ezarri.
        JLabel irudia = new JLabel(fondo_aldatuta); // irudia jlabel batean bihurtu.
        panel1.add(irudia); // irudiaren jlabel-a panelean gehitu.
        f_Erregistratu.add(panel1, BorderLayout.NORTH);// panela erregistratu diplay-erko goikaldean txertatu.
    }

    public void center(){
        panel2= new JPanel(null);// panel bat sortu.
        email= new JTextField(17); // emailarentzako text field bat sortu eta honen tamaina definitu.
        textuGrixa(email, "Emaila"); // textugrixa funtzioari deituz, emailaren textfiel-ean agertuko den testua ezarri

        String emailT= email.getText();// emailtextfield-etik sartutako textua jaso.

        if (!emailT.matches(".+@.+..+")){// sartutako email-ak formatu egokia ez badu.
            System.out.println("MAL"); // "MAl" mezua atera.
        }

        izena = new JTextField(); //izenarentzako text field bat sortu.
        textuGrixa(izena, "Izena");// textugrixa funtzioari deituz, izenaren textfiel-ean agertuko den testua ezarri

        abizena = new JTextField();//abizenarentzako text field bat sortu.
        textuGrixa(abizena, "Abizena");// textugrixa funtzioari deituz, abizenaren textfiel-ean agertuko den testua ezarri

        pasahitza_textua = new JLabel("Pasahitza");// pasahitzarentzako label bat sortu.
        pasahitza = new JTextField();//pasahitzarentzako text field bat sortu.
        enable_false_true(pasahitza);// funtzioari deitu text field-eko testu grixa aktibatu, ondoren hau borratzeko.

        pasahitza2_textua = new JLabel("Pasahitza Egiaztatua");// pasahitza egiaztatzeko label-a sortu.
        pasahitza2 = new JTextField();//pasahitza egiaztatzeko txet field-a sortu.
        enable_false_true(pasahitza2);// funtzioari deitu text field-eko testu grixa aktibatu, ondoren hau borratzeko.
        try {// erroreak kudeatzeko try catch bat ireki.
            nan_textua= new JLabel("NAN:");// nan-arentzat j label bat sortu.
            MaskFormatter nan_formatua= new MaskFormatter("########?");// nan-aren formatua sortu (# = zenbakiak, ? = letra)
            nan = new JFormattedTextField(nan_formatua); // nan-ari formatua ezarri.
            enable_false_true(nan);// funtzioari deitu text field-eko testu grixa aktibatu, ondoren hau borratzeko.

            MaskFormatter telefono_formatua = new MaskFormatter("34#########"); //telefonoaren formatua sortu.
            telefonoa = new JFormattedTextField(telefono_formatua);// terefonoari formatu ezarri.
            enable_false_true(telefonoa);// funtzioari deitu text field-eko testu grixa aktibatu, ondoren hau borratzeko.

            jaio_eguna_Textua= new JLabel("Jaio Eguna:"); // jaiotze dataren label bat sortu.
            MaskFormatter jaio_eguna_Formatua = new MaskFormatter("####-##-##"); // jaiotze dataren formatua sortu.
            jaio_eguna = new JFormattedTextField(jaio_eguna_Formatua);// formatua jaiotze datari ezarri.
            enable_false_true(jaio_eguna);// funtzioari deitu text field-eko testu grixa aktibatu, ondoren hau borratzeko.

        }catch (ParseException e){// Aurreko eremuetan sartutako daturen bat formatu dezegokia izango balu.
            System.err.println("MaskFormatter-ekin errorea. Erregistratu.cente()");// Errore mezua pantailaratu.
        }

        // Sortutako textfield guztiei tamaina eta kokalekua ezarri.
        email.setBounds(90,10,210,20);
        nan_textua.setBounds(90,38, 100, 10);
        nan.setBounds(90,50,100,20);
        telefonoa.setBounds(200, 50, 100, 20);
        izena.setBounds(90,90,100,20);
        abizena.setBounds(200,90,100,20);
        jaio_eguna_Textua.setBounds(140, 116, 100, 15);
        jaio_eguna.setBounds(140, 130, 100, 20);
        pasahitza_textua.setBounds(135, 156, 200, 15);
        pasahitza.setBounds(135, 170, 110, 20);
        pasahitza2_textua.setBounds(135, 196, 200, 15);
        pasahitza2.setBounds(135, 210, 110, 20);

        // text field guztiak panelean txertatu.
        panel2.add(email);
        panel2.add(nan_textua);
        panel2.add(nan);
        panel2.add(telefonoa);
        panel2.add(izena);
        panel2.add(abizena);
        panel2.add(jaio_eguna_Textua);
        panel2.add(jaio_eguna);
        panel2.add(pasahitza_textua);
        panel2.add(pasahitza);
        panel2.add(pasahitza2_textua);
        panel2.add(pasahitza2);
// panela display nagusiaren erdiak txertatu.
        f_Erregistratu.add(panel2, BorderLayout.CENTER);
    }

    public void textuGrixa(JTextField kuadroa , String textua){
        kuadroa.setEnabled(false);// text field bateko atzeko testua
        kuadroa.addMouseListener(new MouseAdapter() { // text field en gainean entzule bat sortu click-ateko momenturako.
            @Override
            public void mouseClicked(MouseEvent e) {// click egitean.
                kuadroa.setEnabled(true);// editagarria izango dean text field-eko tstu grixa.
            }

        });

        kuadroa.setForeground(Color.GRAY);// text field-eko atzeko testuaren kolorea ezarri.
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
                        kuadroa.setForeground(Color.GRAY);// eremuko testuaren kolorea grixa izango da.
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
                        kuadroa.setForeground(Color.GRAY);// testuaren kolorea grixa bihurtu.
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
    public void south(){
        panel3 = new JPanel();//panel3 sortu.
        panel3.setLayout(new BoxLayout(panel3,BoxLayout.X_AXIS));// panelari boxlayout mota ezarri x ardatzean.

        JLabel tartea1= new JLabel("                        ");// jlabel bat sortu bertan testu huts batekin.
        atzera = new JButton("Atzera");// atzera testuarekin botoi bat sortu.
        erregistratu = new JButton("Erregistratu");// erregistratu testuarekin botoi bat sortu.
        login = new JButton("Login");// login testuarekin botoi bat sortu.
// sortutako botoiak eta label-a panel3-an gehitu.
        panel3.add(tartea1);
        panel3.add(atzera);
        panel3.add(erregistratu);
        panel3.add(login);
//panel3-a display nagusian gehitu.
        f_Erregistratu.add(panel3, BorderLayout.SOUTH);
        atzera.addActionListener(new ActionListener() {// atzera botoiaren gainean entzuea sortu.
            @Override
            public void actionPerformed(ActionEvent e) {// botoian click egiterakoan.
                new Index().sortu(); // index display-a sortuko da.
                f_Erregistratu.dispose(); // erregistartu display a itxi.
            }
        });

        erregistratu.addActionListener(new ActionListener() {// erregistratu botoiaren gainean entzulea jarri.
            @Override
            public void actionPerformed(ActionEvent e) {// erregistratu botoian click egiterakoan.
                email_login= email.getText();// sartutako emaila itzuli.
                izena_login= izena.getText();// sartutako izena itzuli.
                abizena_login= abizena.getText();// sartutako abizena itzuli.
                nan_login= nan.getText();// sartutako nan-a itzuli.
                telefonoa_login=  telefonoa.getText();// sartutako telefonoa itzuli.
                jaio_eguna_login= jaio_eguna.getText();// sartutako jaiotze eguna itzuli.
                Date jaio_eguna_date= Date.valueOf(jaio_eguna.getText());// jaitze eguna testutik date motara pasatu.
                pazaitza_login= pasahitza2.getText();// sartutako pasahitza itzuli.

                erregistratu(jaio_eguna_date);
                f_Erregistratu.dispose();// erregistratu display-a itxi.
                new Index().sortu();// index display-a sortu eta ireki.
            }
        });

        login.addActionListener(new ActionListener() {// login botoiaren gainean entzulea jarri.
            @Override
            public void actionPerformed(ActionEvent e) { // click egiterakoan
                new Login().sortu_login();// login dispaly-a sortu eta ireki.
                f_Erregistratu.dispose();// erregistratu display-a itxi.
            }
        });

    }

    public void  enable_false_true(JTextField a){// textfield-a entzulearen bitartez editagarria edo ez egin.
        a.setEnabled(false);
        a.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                a.setEnabled(true);
            }
        });

    }

}
