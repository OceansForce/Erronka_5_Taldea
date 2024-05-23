package erleak;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;
import java.text.ParseException;
import static erleak.Datuak.*;
public class Erregistratu {
    private JFrame f_Erregistratu= new JFrame();
    private JPanel panel1, panel2, panel3;
    private JTextField email, izena, abizena, pasahitza, pasahitza2;
    private JFormattedTextField nan, telefonoa,jaio_eguna;
    private JLabel nan_textua, jaio_eguna_Textua, pasahitza_textua, pasahitza2_textua;
    private JButton login, erregistratu, atzera;


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
        ImageIcon fondoa_irudia = new ImageIcon("./Irudiak/kuadroa_erdia.jpg");// irudia aldagaian kargatu,
        ImageIcon fondo_aldatuta= new ImageIcon(fondoa_irudia.getImage().getScaledInstance(400, 80, java.awt.Image.SCALE_SMOOTH));// irudiaren tamaina ezarri.
        JLabel irudia = new JLabel(fondo_aldatuta); // irudia jlabel batean bihurtu.
        panel1.add(irudia); // irudiaren jlabel-a panelean gehitu.
        f_Erregistratu.add(panel1, BorderLayout.NORTH);// panela erregistratu diplay-erko goikaldean txertatu.
    }

    public void center(){
        Datuak da = new Datuak();
        panel2= new JPanel();// panel bat sortu.
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        email= new JTextField(18); // emailarentzako text field bat sortu eta honen tamaina definitu.
        da.textuGrixa(email, "Emaila", Color.GRAY); // textugrixa funtzioari deituz, emailaren textfiel-ean agertuko den testua ezarri

        izena = new JTextField(); //izenarentzako text field bat sortu.
        da.textuGrixa(izena, "Izena",Color.GRAY);// textugrixa funtzioari deituz, izenaren textfiel-ean agertuko den testua ezarri

        abizena = new JTextField();//abizenarentzako text field bat sortu.
        da.textuGrixa(abizena, "Abizena",Color.GRAY);// textugrixa funtzioari deituz, abizenaren textfiel-ean agertuko den testua ezarri

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

            jaio_eguna_Textua= new JLabel("Jaiotze Data:"); // jaiotze dataren label bat sortu.
            MaskFormatter jaio_eguna_Formatua = new MaskFormatter("####-##-##"); // jaiotze dataren formatua sortu.
            jaio_eguna = new JFormattedTextField(jaio_eguna_Formatua);// formatua jaiotze datari ezarri.
            enable_false_true(jaio_eguna);// funtzioari deitu text field-eko testu grixa aktibatu, ondoren hau borratzeko.

        }catch (ParseException e){// Aurreko eremuetan sartutako daturen bat formatu dezegokia izango balu.
            System.err.println("MaskFormatter-ekin errorea. Erregistratu.cente()");// Errore mezua pantailaratu.
        }
        JLabel telefonoa_textua= new JLabel("Telefonoa:");


        // Sortutako textfield eta Jlabel guztien kokalekua ezartzen da, panelen bidez.

        JLabel hutsunea1= new JLabel(" ");
        JLabel hutsunea2= new JLabel(" ");

        JPanel dena = new JPanel();
        JPanel panel4= new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));

        panel4.add(email);

        JPanel panel6= new JPanel(new GridLayout(1,2));
        JPanel panel7= new JPanel();
        panel7.setLayout(new BoxLayout(panel7, BoxLayout.Y_AXIS));
        JPanel panel8= new JPanel();
        panel8.setLayout(new BoxLayout(panel8, BoxLayout.Y_AXIS));
        JPanel panel9= new JPanel(new GridLayout(6,1));


        panel7.add(nan_textua);
        panel7.add(nan);
        panel7.add(hutsunea1);
        panel7.add(izena);

        panel8.add(telefonoa_textua);
        panel8.add(telefonoa);
        panel8.add(hutsunea2);
        panel8.add(abizena);

        panel9.add(jaio_eguna_Textua);
        panel9.add(jaio_eguna);
        panel9.add(pasahitza_textua);
        panel9.add(pasahitza);
        panel9.add(pasahitza2_textua);
        panel9.add(pasahitza2);

        panel4.add(panel6);

        panel6.add(panel7);
        panel6.add(panel8);

        panel4.add(panel9);

        dena.add(panel4);
        panel2.add(dena, BorderLayout.CENTER);

// panela display nagusiaren erdiak txertatu.
        f_Erregistratu.add(panel2, BorderLayout.CENTER);
    }


    public void south(){
        Datuak da = new Datuak();
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
                String emailT= email.getText();// emailtextfield-etik sartutako textua jaso.
                if (!emailT.matches(".+@.+..+")){// sartutako email-ak formatu egokia ez badu, textua ezabatuko du eta "Email" gorris jarriko da.
                    da.textuGrixa(email, "Email",Color.RED);
                    email.setEnabled(true);
                }else {
                    email_login = email.getText();// sartutako emaila itzuli.
                    izena_login = izena.getText();// sartutako izena itzuli.
                    abizena_login = abizena.getText();// sartutako abizena itzuli.
                    nan_login = nan.getText();// sartutako nan-a itzuli.
                    telefonoa_login = telefonoa.getText();// sartutako telefonoa itzuli.
                    jaio_eguna_login = jaio_eguna.getText();// sartutako jaiotze eguna itzuli.
                    Date jaio_eguna_date = Date.valueOf(jaio_eguna.getText());// jaitze eguna testutik date motara pasatu.
                    pazaitza_login = pasahitza2.getText();// sartutako pasahitza itzuli.

                    da.erregistratu(jaio_eguna_date);
                    try {
                        BufferedWriter erabiltzaila = new BufferedWriter(new FileWriter("./sesio.txt"));// fitxategian idazlea sortu.
                        erabiltzaila.write(Encrypt.encript(da.hash(String.valueOf(da.id_atera_login()))));// sartutako sozioa
                        erabiltzaila.close();//idazlea itxi.
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                    f_Erregistratu.dispose();// erregistratu display-a itxi.
                    new Index().sortu();// index display-a sortu eta ireki.
                }
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
