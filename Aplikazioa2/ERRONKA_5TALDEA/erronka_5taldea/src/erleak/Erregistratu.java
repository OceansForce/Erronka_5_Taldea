package erleak;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class Erregistratu {
    private JFrame f_Erregistratu= new JFrame();
    private JPanel panel1, panel2;
    private JTextField email, nan, telefonoa, izena, abizena, jaio_eguna;

    public static void main(String[] args){
        new Erregistratu().sortu_Erregistratu();
    }
    public void sortu_Erregistratu() {
        nothr();
        center();
        erregistratu_orria();
    }
    public void erregistratu_orria(){
        f_Erregistratu.setTitle("Erregistratu");
        f_Erregistratu.setSize(400, 600);
        f_Erregistratu.setVisible(true);
        f_Erregistratu.setLocationRelativeTo(null);
        f_Erregistratu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f_Erregistratu.setLayout(new BorderLayout());
    }

    public void nothr(){
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1,BoxLayout.X_AXIS));
        ImageIcon fondoa_irudia = new ImageIcon(".\\Irudiak\\kuadroa_erdia.jpg");
        ImageIcon fondo_aldatuta= new ImageIcon(fondoa_irudia.getImage().getScaledInstance(400, 80, java.awt.Image.SCALE_SMOOTH));
        JLabel irudia = new JLabel(fondo_aldatuta);
        panel1.add(irudia);
        f_Erregistratu.add(panel1, BorderLayout.NORTH);
    }

    public void center(){
        panel2= new JPanel();
        email= new JTextField(17);
        textuGrixa(email, "Emaila");

        nan= new JTextField(17);
        textuGrixa(nan,"NAN");

        telefonoa
        panel2.add(email);
        f_Erregistratu.add(panel2, BorderLayout.CENTER);
    }

    public void textuGrixa(JTextField kuadroa , String textua){
        kuadroa.setEnabled(false);
        kuadroa.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                kuadroa.setEnabled(true);
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

        kuadroa.setForeground(Color.GRAY);
        kuadroa.setText(textua);
        kuadroa.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (kuadroa.getText().equals(textua)) {
                        kuadroa.setText(" ");
                        kuadroa.setForeground(Color.BLACK);
                    }
                });
            }
            @Override
            public void focusLost(FocusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (kuadroa.getText().equals(" ")) {
                        kuadroa.setText(textua);
                        kuadroa.setForeground(Color.GRAY);
                    }
                });
            }
        });

        kuadroa.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (kuadroa.getText().isEmpty()) {
                        kuadroa.setText(textua);
                        kuadroa.setForeground(Color.GRAY);
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
    }

}
