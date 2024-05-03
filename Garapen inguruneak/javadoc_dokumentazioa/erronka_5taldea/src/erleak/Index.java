package erleak;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import static erleak.Login.*;
/**
 * Aplikazioaren lehio nagusiaren kodea
 * @author Julen
 */
public class Index extends JFrame implements ActionListener {
    private JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7;
    private JPanel centerPanela;
    private JMenu menu2, menu3;
    private JMenuItem mj1, mj2, mj3;
    private JButton loginButton, erregistratzeko_Button, sesioButton;
    private CardLayout card1;
    private JFrame f_Index = new JFrame();

    /**
     * Erabiltzaile motaren arabera orri desberdina erakutsiko du
     * {@link Login#logeatua_dago}
     * {@link Login#zuzendaria_da}
     */

    public static void main(String[] args){
        try {
            int ida=0;
            BufferedReader irakurri = new BufferedReader(new FileReader(".\\sesio.txt"));
            ida= Integer.parseInt(irakurri.readLine());
            if (Sozioak.id_sozioak().contains(ida)){
                logeatua_dago=true;
                if (Sozioak.zuzendariak().contains(ida)){
                    zuzendaria_da=true;
                }
                Datuak.izena_jarri(Sozioak.nan_atera(ida));
            }
            new Index().sortu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    /**
     * Pantaila nagusian logoa kargatu eta erabiltzaile mota desberdinei
     * menu desberdinak erakutsi
     * {@link Index#card1}
     * {@link Index#panel1}
     * {@link Index#loginButton}
     * {@link Index#sesioButton}
     * {@link Index#menu2}
     * {@link Index#menu3}
     */
    public void north(){

            panel1 = new JPanel();
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

            //Logoa
            ImageIcon logoa = new ImageIcon(".\\Irudiak\\logo.png");
            ImageIcon logoa_aldatuta = new ImageIcon(logoa.getImage().getScaledInstance(50, 35, java.awt.Image.SCALE_SMOOTH));
            JLabel logo_Irudia = new JLabel(logoa_aldatuta);
            logo_Irudia.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    card1.show(centerPanela, "panel2");
                }
            });

            panel1.add(logo_Irudia);
            // Lehengo menua
            JMenuBar mb = new JMenuBar();

            //bigarren menua
            menu2 = new JMenu("Produktuak");
            mb.add(menu2);

            mj1 = new JMenuItem("Eztia");
            mj1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    card1.show(centerPanela, "panel3");
                }
            });
            mj2 = new JMenuItem("Beste batzuk");
            mj2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    card1.show(centerPanela, "panel4");
                }
            });

            mj3 = new JMenuItem("Materiala");
            mj3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    card1.show(centerPanela, "panel5");
                }
            });

            menu2.add(mj1);
            menu2.add(mj2);
            menu2.add(mj3);

            panel1.add(mb);

        if (logeatua_dago) {
            boolean atera= true;
            if(zuzendaria_da) {
                JMenuBar mb2 = new JMenuBar();
                menu3 = new JMenu("Sozioak eta Sozietateak");
                mb2.add(menu3);

                JMenuItem mj4 = new JMenuItem("Sozioak");
                mj4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        card1.show(centerPanela, "panel6");
                    }
                });
                JMenuItem mj5 = new JMenuItem("Sozietateak");
                mj5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        card1.show(centerPanela, "panel7");
                    }
                });
                menu3.add(mj4);
                menu3.add(mj5);

                mb2.setBorder(new EmptyBorder(0, 0, 0, 520));
                panel1.add(mb2);
                atera=false;
            }
            ImageIcon pertsonaIrudia = new ImageIcon(".\\Irudiak\\pertsona_icon.png");
            ImageIcon pertsona_TamainaAldatuta = new ImageIcon(pertsonaIrudia.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
            sesioButton = new JButton(pertsona_TamainaAldatuta);
            sesioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Sesioa().sortu_login();
                    f_Index.dispose();
                }
            });

            if (atera) {
                mb.setBorder(new EmptyBorder(0, 0, 0, 668));
            }
            panel1.add(sesioButton);

        }else {
            mb.setBorder(new EmptyBorder(0, 0, 0, 512));
            //login botoia
            ImageIcon pertsonaIrudia = new ImageIcon(".\\Irudiak\\pertsona_icon.png");
            ImageIcon pertsona_TamainaAldatuta = new ImageIcon(pertsonaIrudia.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
            loginButton = new JButton("Login", pertsona_TamainaAldatuta);
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Login().sortu_login();
                    f_Index.dispose();
                }
            });

            panel1.add(loginButton);

            ImageIcon pertsonaIrudia2 = new ImageIcon(".\\Irudiak\\Erregistratu.png");
            ImageIcon pertsona2_TamainaAldatuta = new ImageIcon(pertsonaIrudia2.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
            erregistratzeko_Button = new JButton("Erregistratu", pertsona2_TamainaAldatuta);
            erregistratzeko_Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Erregistratu().sortu_Erregistratu();
                    f_Index.dispose();
                }
            });

            panel1.add(erregistratzeko_Button);
        }
            f_Index.add(panel1, BorderLayout.NORTH);
    }

    public void center(){
        card1= new CardLayout();
        centerPanela= new JPanel();
        centerPanela.setLayout(card1);

        panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageIcon portada_irudia= new ImageIcon(".\\Irudiak\\abejas.jpg");
        JLabel portada= new JLabel(portada_irudia);
        panel2.add(portada);

        panel3 = new JPanel(new BorderLayout());

        ImageIcon eztia_irudia = new ImageIcon(".\\Irudiak\\kuadroa_eztia.jpg");
        ImageIcon eztia_irudia_aldatuta= new ImageIcon(eztia_irudia.getImage().getScaledInstance(900, 100, java.awt.Image.SCALE_SMOOTH));
        JLabel eztia_Lirudi= new JLabel(eztia_irudia_aldatuta);
        panel3.add(eztia_Lirudi, BorderLayout.NORTH);


        String[] kolumna_Izenak_eztia= {"Izena", "Prezioa","Kantitatea", "Deskribapena"};
        final JTable ezti_tabla= new JTable(Datuak.center_3_txertatu(),kolumna_Izenak_eztia);
        ezti_tabla.setPreferredScrollableViewportSize(new Dimension(700, 225));
        JScrollPane ezti_sp= new JScrollPane(ezti_tabla);

        panel3.add(ezti_sp, BorderLayout.CENTER);

        panel4= new JPanel(new BorderLayout());

        ImageIcon besteak_irudia = new ImageIcon(".\\Irudiak\\kuadroa_erdia.jpg");
        ImageIcon besteak_irudia_aldatuta= new ImageIcon(besteak_irudia.getImage().getScaledInstance(900, 100, java.awt.Image.SCALE_SMOOTH));
        JLabel besteak_Lirudi= new JLabel(besteak_irudia_aldatuta);
        panel4.add(besteak_Lirudi, BorderLayout.NORTH);

        String[] kolumna_Izenak_besteak= {"Izena", "Prezioa","Kantitatea", "Deskribapena"};
        final JTable besteak_tabla= new JTable(Datuak.center_4_txertatu(),kolumna_Izenak_besteak);
        besteak_tabla.setPreferredScrollableViewportSize(new Dimension(700, 225));
        JScrollPane bestak_sp= new JScrollPane(besteak_tabla);

        panel4.add(bestak_sp);

        panel5= new JPanel(new BorderLayout());

        ImageIcon besteak_irudia2 = new ImageIcon(".\\Irudiak\\kuadroa_erdia.jpg");
        ImageIcon besteak_irudia_aldatuta2= new ImageIcon(besteak_irudia2.getImage().getScaledInstance(900, 100, java.awt.Image.SCALE_SMOOTH));
        JLabel besteak_Lirudi2= new JLabel(besteak_irudia_aldatuta2);
        panel5.add(besteak_Lirudi2, BorderLayout.NORTH);


        String[] kolumna_Izenak_material= {"Izena", "Prezioa","Kantitatea", "Deskribapena"};
        final JTable material_tabla= new JTable(Datuak.center_5_txertatu(),kolumna_Izenak_material);
        material_tabla.setPreferredScrollableViewportSize(new Dimension(700, 225));
        JScrollPane material_sp= new JScrollPane(material_tabla);

        panel5.add(material_sp);

        panel6 = new JPanel(new BorderLayout());

        ImageIcon sozio_irudia = new ImageIcon(".\\Irudiak\\kuadroa_eztia.jpg");
        ImageIcon sozio_irudia_aldatuta= new ImageIcon(sozio_irudia.getImage().getScaledInstance(900, 100, java.awt.Image.SCALE_SMOOTH));
        JLabel sozio_Lirudi= new JLabel(sozio_irudia_aldatuta);
        panel6.add(sozio_Lirudi, BorderLayout.NORTH);


        String[] kolumna_Izenak_sozio= {"ID","Zuzendaria","Erle KANT", "Erlauntz","Izena","Abizena","NAN","Telefonoa","Jaiote Data","Email"};
        final JTable sozio_tabla= new JTable(Sozioak.sozio_Array(),kolumna_Izenak_sozio);
        sozio_tabla.setPreferredScrollableViewportSize(new Dimension(700, 225));
        JScrollPane sozio_sp= new JScrollPane(sozio_tabla);

        panel6.add(sozio_sp, BorderLayout.CENTER);

        panel7 = new JPanel(new BorderLayout());

        ImageIcon sozietateak_irudia = new ImageIcon(".\\Irudiak\\kuadroa_eztia.jpg");
        ImageIcon sozietateak_irudia_aldatuta= new ImageIcon(sozietateak_irudia.getImage().getScaledInstance(900, 100, java.awt.Image.SCALE_SMOOTH));
        JLabel sozietateak_Lirudi= new JLabel(sozietateak_irudia_aldatuta);
        panel7.add(sozietateak_Lirudi, BorderLayout.NORTH);


        String[] kolumna_Izenak_sozietateak= {"ID", "Izena","Herrialdea"};
        final JTable sozietateak_tabla= new JTable(Sozietateak.sozietate_Arraya(),kolumna_Izenak_sozietateak);
        sozietateak_tabla.setPreferredScrollableViewportSize(new Dimension(700, 225));
        JScrollPane sozietateak_sp= new JScrollPane(sozietateak_tabla);

        panel7.add(sozietateak_sp, BorderLayout.CENTER);

        centerPanela.add(panel2,"panel2");
        centerPanela.add(panel3,"panel3");
        centerPanela.add(panel4, "panel4");
        centerPanela.add(panel5, "panel5");
        centerPanela.add(panel6, "panel6");
        centerPanela.add(panel7, "panel7");

        f_Index.add(centerPanela,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
