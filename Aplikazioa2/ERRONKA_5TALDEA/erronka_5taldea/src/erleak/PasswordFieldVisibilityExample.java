package erleak;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import java.awt.event.*;

public class PasswordFieldVisibilityExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JPasswordField Visibility Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPasswordField passwordField = new JPasswordField(20);

        // Botón para hacer visible el contenido del JPasswordField
        JButton showButton = new JButton("Mostrar Contenido");
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Establecer echoChar a null para hacer visible el contenido
                passwordField.setEchoChar((char) 0);
            }
        });

        // Botón para ocultar el contenido del JPasswordField nuevamente
        JButton hideButton = new JButton("Ocultar Contenido");
        hideButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Restaurar el carácter de bala para ocultar el contenido nuevamente
                passwordField.setEchoChar('*');
            }
        });

        JPanel panel = new JPanel();
        panel.add(passwordField);
        panel.add(showButton);
        panel.add(hideButton);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}

