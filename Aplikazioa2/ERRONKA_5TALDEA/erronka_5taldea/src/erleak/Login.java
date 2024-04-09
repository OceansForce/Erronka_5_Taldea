package erleak;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Login extends JFrame {
    JFrame f_login= new JFrame();
    JPanel panel1;
    JPanelConFondo panel2;
    public void sortu_login(){
        nothr();
        gehitu();
        login_orria();
    }
    public void login_orria(){

        f_login.setLayout(new FlowLayout());
        f_login.setTitle("Login");
        f_login.setVisible(true);
        f_login.setLocationRelativeTo(null);
        f_login.setSize(250, 200);
    }

    public void nothr(){
        panel2 = new JPanelConFondo(".\\kuadroa.jpg");
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(imagenFondo, 0, 0, null);
        }
    }


    public void gehitu(){


        Container content = getContentPane();

        panel1 = new JPanel(new GridLayout(4, 2)); //loginarentzat bi lerro eta 3 kolumna kalkulatzen dira elementu denentzat
        JLabel izena = new JLabel("Izena:");
        JTextField txertatuizen = new JTextField();

        JLabel pasahitza = new JLabel("Pasahitza:");
        JPasswordField txertatupass = new JPasswordField();
        JButton login = new JButton("Hasi saioa");


        JButton erregistratu = new JButton("Erregistratu");
        JButton itzuli = new JButton("Atzera");


        panel1.add(izena);
        panel1.add(txertatuizen);
        panel1.add(pasahitza);
        panel1.add(txertatupass);

        //ondorengoa betegarria gehitzeko da, hau gehituko ez bagenu
        //login botoia adibidez ezkerraldera desplazatuko zen

        panel1.add(login);
        panel1.add(erregistratu);
        panel1.add(itzuli);

        f_login.add(panel1, BorderLayout.CENTER);

        itzuli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Index().sortu();
                f_login.dispose();
            }
        });

        erregistratu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Erregistroko funtzioa sortu gabe");
            }
        });

    }

    public static void main(String[] args){
        new Login();
    }
}
class JPanelConFondo extends JPanel {
    private Image imagenFondo;

    public JPanelConFondo(String rutaImagen) {
        try {
            imagenFondo = ImageIO.read(getClass().getResource(rutaImagen));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return imagenFondo == null ? super.getPreferredSize() : new Dimension(imagenFondo.getWidth(this), imagenFondo.getHeight(this));
    }
}
