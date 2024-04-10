package erleak;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextFieldConPlaceholder {
    private JTextField textField;
    private String placeholder;
    private Color placeholderColor = Color.GRAY;

    public TextFieldConPlaceholder(JTextField textField, String placeholder) {
        this.textField = textField;
        this.placeholder = placeholder;

        // Establecer el color del texto como el color del marcador de posición
        textField.setForeground(placeholderColor);
        textField.setText(placeholder);

        // Agregar un FocusListener para controlar cuándo mostrar u ocultar el marcador de posición
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (textField.getText().equals(placeholder)) {
                        textField.setText("");
                        textField.setForeground(Color.BLACK); // Cambiar el color del texto al escribir
                    }
                });
            }

            @Override
            public void focusLost(FocusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (textField.getText().isEmpty()) {
                        textField.setText(placeholder);
                        textField.setForeground(placeholderColor); // Restaurar el color del texto al perder el foco
                    }
                });
            }
        });

        // Agregar un DocumentListener para detectar cambios en el texto
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (textField.getText().isEmpty()) {
                        textField.setText(placeholder);
                        textField.setForeground(placeholderColor); // Restaurar el color del texto si el campo está vacío
                    }
                });
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (textField.getText().isEmpty()) {
                        textField.setText(placeholder);
                        textField.setForeground(placeholderColor); // Restaurar el color del texto si el campo está vacío
                    }
                });
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ejemplo JTextField con Placeholder");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JTextField textField = new JTextField(20);
            // Texto de marcador de posición
            String placeholderText = "Ingrese texto aquí";
            TextFieldConPlaceholder placeholderTextField = new TextFieldConPlaceholder(textField, placeholderText);

            frame.getContentPane().add(textField, BorderLayout.CENTER);

            frame.pack();
            frame.setVisible(true);
        });
    }
}
