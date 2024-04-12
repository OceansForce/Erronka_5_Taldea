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
    private JPanel panel1;

    public static void main(String[] args){
        new Erregistratu().sortu_Erregistratu();
    }
    public void sortu_Erregistratu() {
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

}
