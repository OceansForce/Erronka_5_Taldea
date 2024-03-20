package erleak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame {

    public Login(){
        super("Login");
        setSize(250, 200);
        Container content = getContentPane();

        JPanel panel = new JPanel(new GridLayout(4, 2)); //loginarentzat bi lerro eta 3 kolumna kalkulatzen dira elementu denentzat
        JLabel izena = new JLabel("Izena:");
        JTextField txertatuizen = new JTextField();

        JLabel pasahitza = new JLabel("Pasahitza:");
        JPasswordField txertatupass = new JPasswordField();
        JButton login = new JButton("Hasi saioa");


        JButton erregistratu = new JButton("Erregistratu");
        JButton itzuli = new JButton("Atzera");


        panel.add(izena);
        panel.add(txertatuizen);
        panel.add(pasahitza);
        panel.add(txertatupass);

        //ondorengoa rellenoa gehitzeko da, hau gehituko ez bagenu
        //login botoia adibidez ezkerraldera desplazatuko zen
        panel.add(new JLabel());
        panel.add(login);
        panel.add(erregistratu);
        panel.add(itzuli);

        getContentPane().add(panel);

        itzuli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        erregistratu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Erregistroko funtzioa sortu gabe");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args){
        new Login();
    }


}
