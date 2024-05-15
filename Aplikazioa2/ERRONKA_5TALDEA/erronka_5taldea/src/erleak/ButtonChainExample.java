package erleak;

import javax.swing.*;
import javax.swing.SpringLayout;

import javax.swing.*;
import javax.swing.SpringLayout;

import javax.swing.*;
import javax.swing.SpringLayout;

public class ButtonChainExample {
    public static void main(String[] args) {
        // Crear un nuevo marco
        JFrame frame = new JFrame("Button Chain Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un panel con SpringLayout
        JPanel panel = new JPanel();
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);

        // Crear un arreglo de nombres para los botones
        String[] buttonNames = {"Botón 1", "Botón 2", "Botón 3", "Botón 4", "Botón 5"};

        // Crear botones y agregarlos al panel con SpringLayout
        JButton[] buttons = new JButton[buttonNames.length];
        for (int i = 0; i < buttonNames.length; i++) {
            buttons[i] = new JButton(buttonNames[i]);
            panel.add(buttons[i]);
        }

        // Definir restricciones de diseño para la cadena de botones
        for (int i = 0; i < buttons.length; i++) {
            if (i == 0) {
                // El primer botón se coloca alineado con el borde izquierdo del panel
                layout.putConstraint(SpringLayout.WEST, buttons[i], 10, SpringLayout.WEST, panel);
            } else {
                // Los botones subsiguientes tienen una separación de 10 píxeles respecto al botón anterior
                layout.putConstraint(SpringLayout.WEST, buttons[i], 10, SpringLayout.EAST, buttons[i - 1]);
            }
        }

        // Alinear el último botón con el borde derecho del panel
        layout.putConstraint(SpringLayout.EAST, buttons[buttons.length - 1], -10, SpringLayout.EAST, panel);

        // Agregar el panel al marco
        frame.add(panel);

        // Ajustar el tamaño del marco y hacerlo visible
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
