package erleak;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import java.awt.event.*;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import javax.swing.*;
import java.awt.*;

public class EmailFormattedTextFieldExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JFormattedTextField Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creamos un JFormattedTextField para el correo electrónico
        JFormattedTextField formattedTextField = new JFormattedTextField();
        formattedTextField.setColumns(20); // Establecemos el ancho del campo de texto

        // Agregamos un InputVerifier para validar el correo electrónico
        formattedTextField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                JFormattedTextField textField = (JFormattedTextField) input;
                String text = textField.getText();
                // Verificamos si el texto contiene al menos un @ y un .
                return text.contains("@") && text.contains(".");
            }
        });

        // Agregamos el JFormattedTextField al contenido del JFrame
        frame.getContentPane().add(formattedTextField, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }
}

