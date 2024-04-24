package erleak;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import static erleak.Login.*;
import static erleak.Datuak.*;

public class Sesioa {
    private JFrame f_sesio = new JFrame();
    private JPanel panel1, panel2, panel3;
    private JButton itzuli1, itxi_sesioa, editatu, gorde;
    private JTextField email, izena, abizena, nan, telefonoa, jaio_eguna, erle_kantitatea, kolmena_kantitatea, pazaitza, pazaitza2;
    private JLabel email_textua, izena_textua, abizena_textua, nan_textua, telefonoa_textua, jaio_eguna_Textua, erle_kantitatea_textua, kolmena_kantitatea_textua,pazaitza_textua, pazaitza2_textua;
    public  void sortu_login(){
        nothr();
        center();
        south();
        sesio_orria();
    }
    public void sesio_orria(){
        f_sesio.setTitle("Sesioa");
        f_sesio.setSize(400, 600);
        f_sesio.setVisible(true);
        f_sesio.setLocationRelativeTo(null);
        f_sesio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f_sesio.setLayout(new BorderLayout());
    }
    public void nothr(){
        panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.X_AXIS));
        ImageIcon fondoa_irudia = new ImageIcon(".\\Irudiak\\kuadroa_erdia.jpg");
        ImageIcon fondo_aldatuta= new ImageIcon(fondoa_irudia.getImage().getScaledInstance(400, 80, java.awt.Image.SCALE_SMOOTH));
        JLabel irudia = new JLabel(fondo_aldatuta);
        panel2.add(irudia);
        f_sesio.add(panel2, BorderLayout.NORTH);
    }
    public void center(){
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

        gorde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    konexioa();
                    PreparedStatement update= con.prepareStatement("UPDATE sozioak SET email=?, nan=?, Telefonoa=?, sozio_izena=?, sozio_abizena=?, kolmena_kantitatea=?, erle_kantitatea=?, jaiote_eguna=?, pasahitza=? WHERE id_sozioa=?");
                    update.setString(1, email.getText());
                    update.setString(2, nan.getText());
                    update.setString(3, telefonoa.getText());
                    update.setString(4, izena.getText());
                    update.setString(5, abizena.getText());
                    update.setString(6, kolmena_kantitatea.getText());
                    update.setString(7, erle_kantitatea.getText());
                    update.setString(8, jaio_eguna.getText());
                    update.setString(9, pazaitza2.getText());
                    update.setString(10, id_sozioa_login);

                    int exekutatu = update.executeUpdate();
                    System.out.println("Filas actualizadas: " + exekutatu);

                    // Confirmar la transacción
                    con.commit();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    throw new RuntimeException("Error al ejecutar la actualización", ex);
                }
            }
        });

        f_sesio.add(panel1, BorderLayout.CENTER);

    }
    public void south(){

        panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3,BoxLayout.X_AXIS));

        JLabel tartea1= new JLabel("    ");
        tartea1.setBorder(new EmptyBorder(0, 0, 0, 70));

        itzuli1 = new JButton("Atzera");
        editatu= new JButton("Editatu");
        itxi_sesioa= new JButton("Sesio Itxi");

        panel3.add(tartea1);
        panel3.add(itzuli1);
        panel3.add(editatu);
        panel3.add(itxi_sesioa);
        itzuli1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Index().sortu();
                f_sesio.dispose();
            }
        });

        editatu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        itxi_sesioa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logeatua_dago=false;
                zuzendaria_da=false;

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

                f_sesio.dispose();
                new Index().sortu();
            }
        });

        f_sesio.add(panel3, BorderLayout.SOUTH);

    }
    public static void main(String[] args){
        new Sesioa().sortu_login();
    }
}
