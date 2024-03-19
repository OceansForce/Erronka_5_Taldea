package erleak;

import javax.swing.*;
import java.awt.*;


public class Login extends JFrame {
    public Login(){
        super("Login");
        setSize(250, 200);
        Container content = getContentPane();

        JPanel panel = new JPanel(new GridLayout(3, 2)); //loginarentzat bi lerro eta 3 kolumna kalkulatzen dira elementu denentzat
        JLabel izena = new JLabel("Izena:");
        JTextField txertatuizen = new JTextField();

        JLabel pasahitza = new JLabel("Pasahitza:");
        JPasswordField txertatupass = new JPasswordField();
        JButton login = new JButton("Hasi saioa");



        panel.add(izena);
        panel.add(txertatuizen);
        panel.add(pasahitza);
        panel.add(txertatupass);

        //ondorengoa rellenoa gehitzeko da, hau gehituko ez bagenu
        //login botoia adibidez ezkerraldera desplazatuko zen
        panel.add(new JLabel());
        panel.add(login);

        getContentPane().add(panel);

        setVisible(true);
    }

    public static void main(String[] args){
        new Login();
    }
}
