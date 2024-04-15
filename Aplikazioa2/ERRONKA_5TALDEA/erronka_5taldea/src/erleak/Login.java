package erleak;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class Login extends JFrame {
    private JFrame f_login= new JFrame();
    private JPanel panel1, panel2, panel3, panel4, panel5, panel_1_4, panel_3_5;
    private CardLayout card1, card2;
    private JButton erregistratu, login ,urrengoa, itzuli1,itzuli2;
    private  JTextField txertatu_izen;
    private JPasswordField txertatupass;
    private JLabel mezua;
    private String identifikatzaile;

    public void sortu_login(){

        nothr();
        center1();
        south();
        login_orria();
    }
    public void login_orria(){
        f_login.setTitle("Login");
        f_login.setSize(400, 250);
        f_login.setVisible(true);
        f_login.setLocationRelativeTo(null);
        f_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f_login.setLayout(new BorderLayout());

    }

    public void nothr(){
        panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.X_AXIS));
        ImageIcon fondoa_irudia = new ImageIcon(".\\Irudiak\\kuadroa_erdia.jpg");
        ImageIcon fondo_aldatuta= new ImageIcon(fondoa_irudia.getImage().getScaledInstance(400, 80, java.awt.Image.SCALE_SMOOTH));
        JLabel irudia = new JLabel(fondo_aldatuta);
        panel2.add(irudia);
        f_login.add(panel2, BorderLayout.NORTH);
    }


    public void center1(){
        card1= new CardLayout();
        panel_1_4 = new JPanel();
        panel_1_4.setLayout(card1);

        panel1 = new JPanel(null);

        txertatu_izen = new JTextField(17);
        txertatu_izen.setEnabled(false);
        txertatu_izen.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txertatu_izen.setEnabled(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        txertatu_izen.setForeground(Color.GRAY);
        txertatu_izen.setText("Izena, NAN, Telefonoa edo Emaila");
        txertatu_izen.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (txertatu_izen.getText().equals("Izena, NAN, Telefonoa edo Emaila")) {
                        txertatu_izen.setText(" ");
                        txertatu_izen.setForeground(Color.BLACK);
                    }
                });
            }
            @Override
            public void focusLost(FocusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (txertatu_izen.getText().equals(" ")) {
                        txertatu_izen.setText("Izena, NAN, Telefonoa edo Emaila");
                        txertatu_izen.setForeground(Color.GRAY);
                    }
                });
            }
        });

        txertatu_izen.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (txertatu_izen.getText().isEmpty()) {
                        txertatu_izen.setText("Izena, NAN, Telefonoa edo Emaila");
                        txertatu_izen.setForeground(Color.GRAY);
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

        mezua = new JLabel("");

        txertatu_izen.setBounds(95,10,200, 25);
        mezua.setBounds(105, 30, 200, 50);

        panel1.add(txertatu_izen);
        panel1.add(mezua);

        panel4 = new JPanel();

        txertatupass = new JPasswordField(18);
        txertatupass.setEnabled(false);
        txertatupass.setEchoChar((char) 0);
        txertatupass.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txertatupass.setEnabled(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        txertatupass.setForeground(Color.GRAY);
        txertatupass.setText("Pasahitza");

        txertatupass.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    String pasahitza= new String(txertatupass.getPassword());
                    if (pasahitza.equals("Pasahitza")) {
                        txertatupass.setText("");
                        txertatupass.setForeground(Color.BLACK);
                        txertatupass.setEchoChar('*');
                    }
                });
            }
            @Override
            public void focusLost(FocusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    String pasahitza= new String(txertatupass.getPassword());
                    if (pasahitza.equals("")) {
                        txertatupass.setText("Pasahitza");
                        txertatupass.setForeground(Color.GRAY);
                        txertatupass.setEchoChar((char) 0);
                    }
                });
            }
        });

        txertatupass.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    String pasahitza= new String(txertatupass.getPassword());
                    if (pasahitza.isEmpty()) {
                        txertatupass.setText("Pasahitza");
                        txertatupass.setForeground(Color.GRAY);
                        txertatupass.setEchoChar((char) 0);
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
        card2= new CardLayout();
        panel_3_5= new JPanel();
        panel_3_5.setLayout(card2);

        panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3,BoxLayout.X_AXIS));

        JLabel tartea1= new JLabel("                    ");
        urrengoa = new JButton("Hurrengoa");
        erregistratu = new JButton("Erregistratu");
        itzuli1 = new JButton("Atzera");

        panel3.add(tartea1);
        panel3.add(urrengoa);
        panel3.add(erregistratu);
        panel3.add(itzuli1);

        itzuli1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Index().sortu();
                f_login.dispose();
            }
        });

        erregistratu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Erregistratu().sortu_Erregistratu();
            }
        });
        urrengoa.addActionListener(new ActionListener() {
            // Pasahitza jarri al izateko, aldatzen du.
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txertatu_izen.getText().isEmpty() && !txertatu_izen.getText().equals("Izena, NAN, Telefonoa edo Emaila")) {
                    identifikatzaile = txertatu_izen.getText();
                    card2.show(panel_3_5, "panel5");
                    card1.show(panel_1_4, "panel4");
                    mezua.setText("");
                }else {
                    mezua.setForeground(Color.red);
                    mezua.setText("Identifikatzailea sartu behar da");
                }
            }
        });



        panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5,BoxLayout.X_AXIS));

        JLabel tartea2= new JLabel("                          ");
        login = new JButton("Login");
        erregistratu = new JButton("Erregistratu");
        itzuli2 = new JButton("Atzera");

        panel5.add(tartea2);
        panel5.add(login);
        panel5.add(erregistratu);
        panel5.add(itzuli2);

        itzuli2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Pasahitzatik atzera bueltatzeko
                card2.show(panel_3_5, "panel3");
                card1.show(panel_1_4, "panel1");
                txertatu_izen.setText(identifikatzaile);
            }
        });

        erregistratu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Erregistroko funtzioa sortu gabe");
            }
        });
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (true){

                }
            }
        });


        panel_3_5.add(panel3, "panel3");
        panel_3_5.add(panel5, "panel5");
        f_login.add(panel_3_5, BorderLayout.SOUTH);

    }

    public static void main(String[] args){
        new Login().sortu_login();
    }


}
