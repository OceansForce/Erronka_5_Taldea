package erleak;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Index extends JFrame implements ActionListener {
    private JPanel panel1, panel2;
    private JMenu menu1, menu2;
    private JMenuItem mi1, mj1, mj2;
    private JButton loginButton, erregistratzeko_Button;
    private JTextField bilatzaile;
    private JFrame f_Index = new JFrame();

    public static void main(String[] args){

        new Index().sortu();
    }
    public void sortu(){
        center();
        north();
        orria();
    }
    public void orria(){

        f_Index.setTitle("Erleak");
        f_Index.setSize(900, 450);
        f_Index.setVisible(true);
        f_Index.setLocationRelativeTo(null);
        f_Index.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f_Index.setLayout(new BorderLayout());
    }

    public void north(){
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1,BoxLayout.X_AXIS));


        // Lehengo menua
        JMenuBar mb = new JMenuBar();
        menu1 = new JMenu("Zerbitzuak");
        mb.add(menu1);

        mi1 = new JMenuItem("Erlauntzen instalazioak");
        menu1.add(mi1);
        mi1.addActionListener(this);

        //bigarren menua
        menu2 = new JMenu("Produktuak");
        mb.add(menu2);

        mj1 = new JMenuItem("Ezti naturala");
        mj2 = new JMenuItem("Erlauntzak");
        menu2.add(mj1);
        menu2.add(mj2);
        mj1.addActionListener(this);
        mj2.addActionListener(this);

        mb.setBorder(new EmptyBorder(0,0,0,500));
        panel1.add(mb);

        //bilatzailea gehitzeko
        /*bilatzaile = new JTextField();
        panel1.add(bilatzaile);

        ImageIcon lupaIrudia = new ImageIcon(".\\Irudiak\\lupa.png");
        ImageIcon lupa_TamainaAldatuta= new ImageIcon(lupaIrudia.getImage().getScaledInstance(25, 20, java.awt.Image.SCALE_SMOOTH));
        JButton lupa = new JButton(lupa_TamainaAldatuta);
        panel1.add(lupa);
        lupa.addActionListener(this);*/


        //login botoia
        ImageIcon pertsonaIrudia = new ImageIcon(".\\Irudiak\\pertsona_icon.png");
        ImageIcon pertsona_TamainaAldatuta= new ImageIcon(pertsonaIrudia.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        loginButton = new JButton("Login", pertsona_TamainaAldatuta);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login().sortu_login();
                f_Index.dispose();
            }
        });
        //mb.add(Box.createHorizontalGlue()); //botoia eskuinaldean jarriko du
        panel1.add(loginButton);

        ImageIcon pertsonaIrudia2 = new ImageIcon(".\\Irudiak\\Erregistratu.png");
        ImageIcon pertsona2_TamainaAldatuta= new ImageIcon(pertsonaIrudia2.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        erregistratzeko_Button = new JButton("Erregistratu", pertsona2_TamainaAldatuta);

        panel1.add(erregistratzeko_Button);

        f_Index.add(panel1, BorderLayout.NORTH);

    }

    public void center(){
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageIcon portada_irudia= new ImageIcon(".\\Irudiak\\abejas.jpg");
        JLabel portada= new JLabel(portada_irudia);
        panel2.add(portada);
        f_Index.add(panel2, BorderLayout.CENTER);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==mi1){

        } else if (e.getSource()==mj1) {

        } else if (e.getSource()==mj2) {

        }
    }




}
