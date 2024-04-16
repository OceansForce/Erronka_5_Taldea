package erleak;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;

public class Erregistratu {
    private JFrame f_Erregistratu= new JFrame();
    private JPanel panel1, panel2;
    private JTextField email, izena, abizena;
    private JFormattedTextField nan, telefonoa,jaio_eguna;
    private JLabel nan_textua;

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
        panel2= new JPanel(null);

        email= new JTextField();
        textuGrixa(email, "Emaila");
        String emailT= email.getText();

        if (!emailT.matches("-+@.+..+")){
            System.out.println("MAL");
        }

        izena = new JTextField();
        textuGrixa(izena, "Izena");

        abizena = new JTextField();
        textuGrixa(abizena, "Abizena");

        try {
            nan_textua= new JLabel("NAN:");
            MaskFormatter nan_formatua= new MaskFormatter("########?");
            nan = new JFormattedTextField(nan_formatua);
            nan.setEnabled(false);

            MaskFormatter telefono_formatua = new MaskFormatter("+34-###-###-###");
            telefonoa = new JFormattedTextField(telefono_formatua);
            telefonoa.setEnabled(false);

            MaskFormatter jaio_eguna_Formatua = new MaskFormatter("####/##/##");
            jaio_eguna = new JFormattedTextField(jaio_eguna_Formatua);
            jaio_eguna.setEnabled(false);

        }catch (ParseException e){
            System.err.println("MaskFormatter-ekin errorea. Erregistratu.cente()");
        }
        email.setBounds(90,10,210,20);
        nan_textua.setBounds(90,38, 100, 10);
        nan.setBounds(90,50,100,20);
        telefonoa.setBounds(200, 50, 100, 20);
        izena.setBounds(90,90,100,20);
        abizena.setBounds(200,90,100,20);
        jaio_eguna.setBounds(90, 200, 100, 20);

        panel2.add(email);
        panel2.add(nan_textua);
        panel2.add(nan);
        panel2.add(telefonoa);
        panel2.add(izena);
        panel2.add(abizena);
        panel2.add(jaio_eguna);

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
                        kuadroa.setText("");
                        kuadroa.setForeground(Color.BLACK);
                    }
                });
            }
            @Override
            public void focusLost(FocusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (kuadroa.getText().equals("")) {
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
