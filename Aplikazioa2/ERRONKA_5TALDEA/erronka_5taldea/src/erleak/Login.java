package erleak;
import Objetuak.Encrypt;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Login extends JFrame {
    private JFrame f_login= new JFrame();

    private JButton erregistratu, login , itzuli1;
    private  JTextField txertatu_izen;
    private JPasswordField txertatupass;
    private JLabel mezua;
    static String identifikatzaile;

    static boolean logeatua_dago= false;
    static boolean zuzendaria_da= false;

    public void sortu_login(){// login display-a sortu.
        north();
        center1();
        south();
        login_orria();
    }
    public void login_orria(){ //login orria sortu.
        f_login.setTitle("Login");// Display-ari titulua jarri
        f_login.setSize(400, 250);// Display-aren tamaina ezarri
        f_login.setVisible(true);// display-a ikusgarria egin.
        f_login.setLocationRelativeTo(null);// display nagusia pantailaren erdialdean egertarazi.
        f_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// display-a ixterakoan.
        f_login.setLayout(new BorderLayout());// logina border layout ezarri.

    }

    public void north(){
        JPanel panel2 = new JPanel();// panel2 jpanela sortu.
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.X_AXIS));// panel2 box layout mota x ardatzean bihurtu.
        ImageIcon fondoa_irudia = new ImageIcon(".\\Irudiak\\kuadroa_erdia.jpg");// irdudia aldagaian kargatu.
        ImageIcon fondo_aldatuta= new ImageIcon(fondoa_irudia.getImage().getScaledInstance(400, 80, java.awt.Image.SCALE_SMOOTH));// irudiaren tamaina aldatu.
        JLabel irudia = new JLabel(fondo_aldatuta);// label bat sortu irudiarekin
        panel2.add(irudia); // sortutako label-a panel2-an gehitu.
        f_login.add(panel2, BorderLayout.NORTH);// panel2-a display-aren goian jarri.
    }


    public void center1(){
        SpringLayout layout= new SpringLayout();
        JPanel panel1 = new JPanel(layout);// panel bat sortu.

        txertatu_izen = new JTextField(18);// textfield bat sortu eta honen tamaina ezarri.
        txertatu_izen.setEnabled(false);// texfield-a ez editagarria egin.
        txertatu_izen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {// textfield-aren gainean entzule bat gehitu, click egitea kontrolatzeko.
                txertatu_izen.setEnabled(true);// textfield-a editagarria egin.
            }
        });

        txertatu_izen.setForeground(Color.GRAY);// textfield-eko letra kolorea gisxa jarri.
        txertatu_izen.setText("Izena, NAN, Telefonoa edo Emaila");// Texfield-ean testua jarri.
        txertatu_izen.addFocusListener(new FocusListener() {// Textfield_ean entzule bat sortu click eginda dagoela jakiteko.
            @Override
            public void focusGained(FocusEvent e) {// textfield-ean click egin eta beste edonon click egiten ez bada.
                SwingUtilities.invokeLater(() -> {
                    if (txertatu_izen.getText().equals("Izena, NAN, Telefonoa edo Emaila")) {// testua idatzita badago.
                        txertatu_izen.setText(" ");// textfield-a hutsa utzi.
                        txertatu_izen.setForeground(Color.BLACK);// letren kolorea beltzera aldatu.
                    }
                });
            }
            @Override
            public void focusLost(FocusEvent e) {// textfield-ean click egin eta beste edonon click egiten  bada.
                SwingUtilities.invokeLater(() -> {
                    if (txertatu_izen.getText().equals(" ")) {// textfield-ean ez badogo ezer idatzita.
                        txertatu_izen.setText("Izena, NAN, Telefonoa edo Emaila");// Testua idatzi.
                        txertatu_izen.setForeground(Color.GRAY);// testuaren kolorea grixera aldatu.
                    }
                });
            }
        });
        txertatu_izen.getDocument().addDocumentListener(new DocumentListener() {// Textfield_ean entzule bat sortu.
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (txertatu_izen.getText().isEmpty()) {// textfield-a hutsa badago.
                        txertatu_izen.setText("Izena, NAN, Telefonoa edo Emaila");// Testua idatzi.
                        txertatu_izen.setForeground(Color.GRAY);// testuaren kolorea grixera aldatu.
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


        txertatupass = new JPasswordField(18);// passwordfield bat sortu pasahitzarentzako.
        txertatupass.setEnabled(false);// passwordfield-a ez editagarria jarri.
        txertatupass.setEchoChar((char) 0);// passwordfield-ean sartzen diren karektereak eta zenbakiak normal ikusi.
        txertatupass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//passwordfield-ean entzule bat gehitu click-atzeko momenturako.
                txertatupass.setEnabled(true);// passwordfield-a editagarri a egin
            }
        });


        txertatupass.setForeground(Color.GRAY);//passwordfield-eko testu kolerea grixa.
        txertatupass.setText("Pasahitza");// // passwordfield-ean testua jarri.

        txertatupass.addFocusListener(new FocusListener() {// passwordfield-ean entzule bat gehitu clic egida dagoen unerako.
            @Override
            public void focusGained(FocusEvent e) {// passwordfield-ean click egin eta beste edonon click egiten ez bada.
                SwingUtilities.invokeLater(() -> {
                    String pasahitza= new String(txertatupass.getPassword());// sartutako pasahitza gorde.
                    if (pasahitza.equals("Pasahitza")) {// pasahitza testua agertzen bada.
                        txertatupass.setText("");// passwordfield-a hutsa jarri.
                        txertatupass.setForeground(Color.BLACK);//passwordfield-eko kolorea beltza jarri.
                        txertatupass.setEchoChar('*');//passwordfield-ean karaktereak agertu beharrean * agertu.
                    }
                });
            }
            @Override
            public void focusLost(FocusEvent e) {// passwordfield-ean click egin eta beste edonon click egiten bada.
                SwingUtilities.invokeLater(() -> {
                    String pasahitza= new String(txertatupass.getPassword());// pasahitza gorde.
                    if (pasahitza.equals("")) {//passwordfield-a utsa badago
                        txertatupass.setText("Pasahitza");// testua jarri.
                        txertatupass.setForeground(Color.GRAY);// testu kolorea aldatu gixera.
                        txertatupass.setEchoChar((char) 0);//passwordfield-an sartutako karaktere eta zenbakiak ikusi.
                    }
                });
            }
        });

        txertatupass.getDocument().addDocumentListener(new DocumentListener() {// passwordfield-ean egiten diren aldaketak kontrolatzen duen entzulea gehitu.
            @Override
            public void insertUpdate(DocumentEvent e) {// passwordfield-ean zerbai sartzen denean.
                SwingUtilities.invokeLater(() -> {
                    String pasahitza= new String(txertatupass.getPassword());// pasahitza gorde.
                    if (pasahitza.isEmpty()) {// passwordfield hutsa badago.
                        txertatupass.setText("Pasahitza");// testua jarri.
                        txertatupass.setForeground(Color.GRAY);//testu kolorea grixera aldatu.
                        txertatupass.setEchoChar((char) 0);// passwordfield-ean sartutako testua eta zenbakiak ikusi alko dira.
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

        mezua = new JLabel("");// mezua display bat sortu.

        panel1.add(txertatu_izen);// panel1-era texfield-a gehitu.
        panel1.add(txertatupass);
        panel1.add(mezua); // panel1-era mezua texfield-a gehitu.

        layout.putConstraint(SpringLayout.NORTH, txertatu_izen, 10, SpringLayout.NORTH, panel1);
        layout.putConstraint(SpringLayout.NORTH, txertatupass, 10, SpringLayout.SOUTH, txertatu_izen);
        layout.putConstraint(SpringLayout.NORTH, mezua, 10, SpringLayout.SOUTH, txertatupass);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, txertatu_izen, 0, SpringLayout.HORIZONTAL_CENTER, panel1);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, txertatupass, 0, SpringLayout.HORIZONTAL_CENTER, panel1);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mezua, 0, SpringLayout.HORIZONTAL_CENTER, panel1);

        f_login.add(panel1,  BorderLayout.CENTER);
    }


    public void south(){
        Datuak da = new Datuak();
        JPanel panel3 = new JPanel();// panel3 sortu.
        panel3.setLayout(new BoxLayout(panel3,BoxLayout.X_AXIS));// panel3-ri box layout mota ezarri x ardatzean.

        JLabel tartea1= new JLabel("                       ");// tartea egiteko label bat sortu.
        login = new JButton("Login");// login botoia sortu.
        erregistratu = new JButton("Erregistratu");//Botoia sortu.
        itzuli1 = new JButton("Atzera");//Botoia sortu.

        // Sortutako hiru botoiak eta label-a panel3-an gehitu.
        panel3.add(tartea1);
        panel3.add(itzuli1);
        panel3.add(erregistratu);
        panel3.add(login);

        itzuli1.addActionListener(new ActionListener() {// itzili botoiean entzule bat sortu click egiteko momenturako.
            @Override
            public void actionPerformed(ActionEvent e) {// click egiten bada.
                new Index().sortu();// index display-a sortu eta bistaratu.
                f_login.dispose();// login display-a itxi.
            }
        });


        login.addActionListener(new ActionListener() {// login botoiari entzule bat gehitu.
            @Override
            public void actionPerformed(ActionEvent e) {// login botoiean click egiterakoan.
                String pasahitza1= new String(txertatupass.getPassword());// sartutako pasahitza aldagaian gorde.
                if (da.identifikatzailea_ondo(txertatu_izen.getText().trim()) && da.pazaitza_jarri(txertatu_izen.getText().trim()).equals(da.hash(pasahitza1))){// datu baseko pasahitza sartutako pasahitz berbera bada.

                        da.izena_jarri(txertatu_izen.getText().trim());
                        JOptionPane.showMessageDialog(null, "Logeatu egin zara");// mezua agertu logeatu zara testuarekin.
                        logeatua_dago = true;// logetuta egon.
                        if (da.zuzendariak().contains(da.hash(String.valueOf(da.id_atera_login())))) {// logetutako sozioa zuzendariak taulan egon.
                            zuzendaria_da = true;// zuzendaria izan.
                        }
                        try {
                            BufferedWriter erabiltzaila = new BufferedWriter(new FileWriter(".\\sesio.txt"));// fitxategian idazlea sortu.
                            erabiltzaila.write(Encrypt.encript(da.hash(String.valueOf(da.id_atera_login()))));// sartutako sozioa
                            erabiltzaila.close();//idazlea itxi.
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        new Index().sortu();// index display-a sortu eta biztaratu
                        f_login.dispose();

                }else {
                    mezua.setText("Identifikatzailea edo pasahitza gaizki dago");
                    mezua.setForeground(Color.red);
                }
            }
        });

        f_login.add(panel3, BorderLayout.SOUTH);
    }
}
