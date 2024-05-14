package erleak;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;


public class Login extends JFrame {
    private JFrame f_login= new JFrame();
    private JPanel panel_1_4;
    private CardLayout card1, card2;
    private JButton erregistratu, login ,urrengoa, itzuli1,itzuli2;
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
        card1= new CardLayout();// card bat sortu.
        panel_1_4 = new JPanel();// panel bat sortu.
        panel_1_4.setLayout(card1); // panela cardlayout bihurtu.

        JPanel panel1 = new JPanel(null);// panel bat sortu.

        txertatu_izen = new JTextField(17);// textfield bat sortu eta honen tamaina ezarri.
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

        mezua = new JLabel("");// mezua display bat sortu.

        txertatu_izen.setBounds(95,10,200, 25);//textfield-ari tamaina ezarri.
        mezua.setBounds(105, 30, 200, 50);// mezua display-ari tamaina ezarri.

        panel1.add(txertatu_izen);// panel1-era texfield-a gehitu.
        panel1.add(mezua); // panel1-era mezua texfield-a gehitu.

        JPanel panel4 = new JPanel();// display bat sortu.

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

        panel4.add(txertatupass);

        panel_1_4.add(panel1, "panel1");
        panel_1_4.add(panel4, "panel4");

        f_login.add(panel_1_4,  BorderLayout.CENTER);
    }


    public void south(){
        card2= new CardLayout();// card2 sortu.
        JPanel panel_3_5= new JPanel();// panel bat sortu.
        panel_3_5.setLayout(card2);// panelari card mota ezarri.

        JPanel panel3 = new JPanel();// panel3 sortu.
        panel3.setLayout(new BoxLayout(panel3,BoxLayout.X_AXIS));// panel3-ri box layout mota ezarri x ardatzean.

        JLabel tartea1= new JLabel("                    ");// tartea egiteko label bat sortu.
        urrengoa = new JButton("Hurrengoa");//Botoia sortu.
        erregistratu = new JButton("Erregistratu");//Botoia sortu.
        itzuli1 = new JButton("Atzera");//Botoia sortu.

        // Sortutako hiru botoiak eta label-a panel3-an gehitu.
        panel3.add(tartea1);
        panel3.add(itzuli1);
        panel3.add(erregistratu);
        panel3.add(urrengoa);

        itzuli1.addActionListener(new ActionListener() {// itzili botoiean entzule bat sortu click egiteko momenturako.
            @Override
            public void actionPerformed(ActionEvent e) {// click egiten bada.
                new Index().sortu();// index display-a sortu eta bistaratu.
                f_login.dispose();// login display-a itxi.
            }
        });

        erregistratu.addActionListener(new ActionListener() {// erregistratu botoiean entzule bat sortu click egiteko unerako.
            @Override
            public void actionPerformed(ActionEvent e) { // click egiterakoan.
                new Erregistratu().sortu_Erregistratu();// erregistratu display a sortu eta pantailaratu.
            }
        });
        urrengoa.addActionListener(new ActionListener() {// urrengoa botoiean entzule bat gehitu.
            // Pasahitza jarri al izateko, aldatzen du.
            @Override
            public void actionPerformed(ActionEvent e) {// urrengoa botoian click egiterakoan.
                if (!txertatu_izen.getText().isEmpty() && !txertatu_izen.getText().equals("Izena, NAN, Telefonoa edo Emaila") && Datuak.izena_jarri(txertatu_izen.getText())) {// sartutako sozioa zuzena baldin bada edo ez badago hutsa textfield-a.
                    identifikatzaile = txertatu_izen.getText();// txertatutako sozioaren identifikatzailea gorde.
                    card2.show(panel_3_5, "panel5");//
                    card1.show(panel_1_4, "panel4");
                    mezua.setText("");
                }else {// sartutako langilea okerra bada edo textfield-a utsa badago.
                    mezua.setForeground(Color.red);// mezua agertiko da textfield azpian.
                    mezua.setText("Identifikatzailea sartu behar da");// mezuko esaldia.
                }
            }
        });

        JPanel panel5 = new JPanel();// display bat sortu.
        panel5.setLayout(new BoxLayout(panel5,BoxLayout.X_AXIS));// panelari mota ezarri, boxlayout x ardatzean.

        JLabel tartea2= new JLabel("                          ");// tartea uzteko label-a sortu.
        login = new JButton("Login");// login botoia sortu.
        erregistratu = new JButton("Erregistratu");// erregistratu botoia sortu.
        itzuli2 = new JButton("Atzera"); //itzuli botoia sortu.

        // sortutako label-a eta botoiak display-ean gehitu.
        panel5.add(tartea2);
        panel5.add(itzuli2);
        panel5.add(erregistratu);
        panel5.add(login);

        itzuli2.addActionListener(new ActionListener() { //itzuli botoian entzule bat gehitu.
            @Override
            public void actionPerformed(ActionEvent e) {// itzuli botoian click egiterakoan.
                //Pasahitzatik atzera bueltatzeko
                card2.show(panel_3_5, "panel3");//
                card1.show(panel_1_4, "panel1");
                txertatu_izen.setText(identifikatzaile);//
            }
        });

       
        login.addActionListener(new ActionListener() {// login botoiari entzule bat gehitu.
            @Override
            public void actionPerformed(ActionEvent e) {// login botoiean click egiterakoan.
                String pasahitza1= new String(txertatupass.getPassword());// sartutako pasahitza aldagaian gorde.
                if (Datuak.pazaitza_jarri().equals(pasahitza1)){// datu baseko pasahitza sartutako pasahitz berbera bada.
                    JOptionPane.showMessageDialog(null, "Logeatu egin zara");// mezua agertu logeatu zara testuarekin.
                    logeatua_dago= true;// logetuta egon.
                    if (Datuak.zuzendariak().contains(Datuak.id_atera_login())){// logetutako sozioa zuzendariak taulan egon.
                        zuzendaria_da=true;// zuzendaria izan.
                    }
                    try {
                        FileWriter erabiltzaila = new FileWriter(".\\sesio.txt");// fitxategian idazlea sortu.
                        erabiltzaila.write(Integer.toString(Datuak.id_atera_login()));// sartutako sozioa
                        erabiltzaila.close();//idazlea itxi.
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    new Index().sortu();// index display-a sortu eta biztaratu
                    f_login.dispose();
                }
            }
        });

// panel bakoitza bere posizioan gehitu.
        panel_3_5.add(panel3, "panel3");
        panel_3_5.add(panel5, "panel5");
        f_login.add(panel_3_5, BorderLayout.SOUTH);

    }

    public static void main(String[] args){//logina sortu eta biztaratu.
        new Login().sortu_login();
    }


}
