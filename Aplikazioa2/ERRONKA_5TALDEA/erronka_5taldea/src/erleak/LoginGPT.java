package erleak;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGPT extends JFrame {

    public LoginGPT(){
        super("Login");
        setSize(250, 200);
        Container content = getContentPane();
        JPanel panel = new JPanel(new GridBagLayout()); // Usar GridBagLayout para mejor organización

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margen para los componentes

        JLabel izenaLabel = new JLabel("Izena:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(izenaLabel, gbc);

        JTextField izenaField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(izenaField, gbc);

        JLabel pasahitzaLabel = new JLabel("Pasahitza:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(pasahitzaLabel, gbc);

        JPasswordField pasahitzaField = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(pasahitzaField, gbc);

        JButton loginButton = new JButton("Hasi saioa");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(loginButton, gbc);

        JButton registerButton = new JButton("Erregistratu");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(registerButton, gbc);

        JButton goBackButton = new JButton("Atzera");
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(goBackButton, gbc);

        getContentPane().add(panel);

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "¡Función de registro aún no implementada!");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args){
        new Login();
    }
}
