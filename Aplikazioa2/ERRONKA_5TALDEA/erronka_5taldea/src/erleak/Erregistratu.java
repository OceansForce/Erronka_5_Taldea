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
    private JTextField email, izena, abizena, pazaitza, pazaitza2;
    private JFormattedTextField nan, telefonoa,jaio_eguna;
    private JLabel nan_textua, jaio_eguna_Textua,pazaitza_textua,pazaitza2_textua;
    private JButton login, erregistratu, atzera;

    public static void main(String[] args){
        new Erregistratu().sortu_Erregistratu();
    }
    public void sortu_Erregistratu() {
        nothr();
        south();
        center();
        erregistratu_orria();
    }
    public void erregistratu_orria(){
        f_Erregistratu.setTitle("Erregistratu");
        f_Erregistratu.setSize(400, 400);
        f_Erregistratu.setVisible(true);
        f_Erregistratu.setLocationRelativeTo(null);
        f_Erregistratu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f_Erregistratu.setLayout(new BorderLayout());
    }

    public void nothr(){
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1,BoxLayout.X_AXIS));
        ImageIcon fondoa_irudia = new ImageIcon(".\\Irudiak\\kuadroa_erdia.jpg");
        ImageIcon fondo_aldatuta= new ImageIcon(fondoa_irudia.getImage().getScaledInstance(400, 80, java.awt.Image.SCALE_SMOOTH));
        JLabel irudia = new JLabel(fondo_aldatuta);
        panel1.add(irudia);
        f_Erregistratu.add(panel1, BorderLayout.NORTH);
    }

    public void center(){
        panel2= new JPanel(null);
        email= new JTextField(17);
        textuGrixa(email, "Emaila");

        String emailT= email.getText();

        if (!emailT.matches(".+@.+..+")){
            System.out.println("MAL");
        }

        izena = new JTextField();
        textuGrixa(izena, "Izena");

        abizena = new JTextField();
        textuGrixa(abizena, "Abizena");

        pazaitza_textua= new JLabel("Pasahitza");
        pazaitza= new JTextField();
        enable_false_true(pazaitza);

        pazaitza2_textua= new JLabel("Pasahitza Egiaztatua");
        pazaitza2= new JTextField();
        enable_false_true(pazaitza2);
        try {
            nan_textua= new JLabel("NAN:");
            MaskFormatter nan_formatua= new MaskFormatter("########?");
            nan = new JFormattedTextField(nan_formatua);
            enable_false_true(nan);

            MaskFormatter telefono_formatua = new MaskFormatter("34#########");
            telefonoa = new JFormattedTextField(telefono_formatua);
            enable_false_true(telefonoa);

            jaio_eguna_Textua= new JLabel("Jaio Eguna:");
            MaskFormatter jaio_eguna_Formatua = new MaskFormatter("####-##-##");
            jaio_eguna = new JFormattedTextField(jaio_eguna_Formatua);
            enable_false_true(jaio_eguna);

        }catch (ParseException e){
            System.err.println("MaskFormatter-ekin errorea. Erregistratu.cente()");
        }
        email.setBounds(90,10,210,20);
        nan_textua.setBounds(90,38, 100, 10);
        nan.setBounds(90,50,100,20);
        telefonoa.setBounds(200, 50, 100, 20);
        izena.setBounds(90,90,100,20);
        abizena.setBounds(200,90,100,20);
        jaio_eguna_Textua.setBounds(140, 116, 100, 15);
        jaio_eguna.setBounds(140, 130, 100, 20);
        pazaitza_textua.setBounds(135, 156, 200, 15);
        pazaitza.setBounds(135, 170, 110, 20);
        pazaitza2_textua.setBounds(135, 196, 200, 15);
        pazaitza2.setBounds(135, 210, 110, 20);

        panel2.add(email);
        panel2.add(nan_textua);
        panel2.add(nan);
        panel2.add(telefonoa);
        panel2.add(izena);
        panel2.add(abizena);
        panel2.add(jaio_eguna_Textua);
        panel2.add(jaio_eguna);
        panel2.add(pazaitza_textua);
        panel2.add(pazaitza);
        panel2.add(pazaitza2_textua);
        panel2.add(pazaitza2);

        f_Erregistratu.add(panel2, BorderLayout.CENTER);
    }

    public void textuGrixa(JTextField kuadroa , String textua){
        kuadroa.setEnabled(false);
        kuadroa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                kuadroa.setEnabled(true);
            }

        });

        kuadroa.setForeground(Color.GRAY);
        kuadroa.setText(textua);
        kuadroa.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (kuadroa.getText().equals(textua)) {
                        kuadroa.setText(" ");
                        kuadroa.setForeground(Color.BLACK);
                    }
                });
            }
            @Override
            public void focusLost(FocusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (kuadroa.getText().equals(" ")) {
                        kuadroa.setText(textua);
                        kuadroa.setForeground(Color.GRAY);
                    }
                });
            }
        });

        kuadroa.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (kuadroa.getText().isEmpty()) {
                        kuadroa.setText(textua);
                        kuadroa.setForeground(Color.GRAY);
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
        panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3,BoxLayout.X_AXIS));

        JLabel tartea1= new JLabel("                        ");
        atzera = new JButton("Atzera");
        erregistratu = new JButton("Erregistratu");
        login = new JButton("Login");

        panel3.add(tartea1);
        panel3.add(atzera);
        panel3.add(erregistratu);
        panel3.add(login);

        f_Erregistratu.add(panel3, BorderLayout.SOUTH);
        atzera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Index().sortu();
                f_Erregistratu.dispose();
            }
        });

        erregistratu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                email_login= email.getText();
                izena_login= izena.getText();
                abizena_login= abizena.getText();
                nan_login= nan.getText();
                telefonoa_login=  telefonoa.getText();
                jaio_eguna_login= jaio_eguna.getText();
                Date jaio_eguna_date= Date.valueOf(jaio_eguna.getText());
                pazaitza_login= pazaitza2.getText();
                try {
                    konexioa();
                    PreparedStatement insert = con.prepareStatement("INSERT INTO sozioak(id_sozioa, id_zuzendaria, erle_kantitatea, kolmena_kantitatea, sozio_izena, sozio_abizena, nan, telefonoa, jaiote_eguna, email, pasahitza) \n" +
                            "VALUES ("+id_hadiena()+",3,NULL,NULL,'"+izena_login+"', '"+abizena_login+"', '"+nan_login+"', "+(Long.parseLong(telefonoa_login))+", TO_DATE('"+jaio_eguna_date+"', 'YYYY-MM-DD'), '"+email_login+"', '"+pazaitza_login+"')");
                    insert.executeUpdate();
                    logeatua_dago=true;
                    if (Sozioak.zuzendariak().contains(id_atera(nan_login))){
                        zuzendaria_da=true;
                    }
                    f_Erregistratu.dispose();
                    new Index().sortu();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login().sortu_login();
                f_Erregistratu.dispose();
            }
        });

    }

    public void  enable_false_true(JTextField a){
        a.setEnabled(false);
        a.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                a.setEnabled(true);
            }
        });

    }

}
