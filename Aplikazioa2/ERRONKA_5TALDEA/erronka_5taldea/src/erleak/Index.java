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

public class Index extends JFrame implements ActionListener {// Erabiliko diren aldagai guztiak definitu.
    private JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7;
    private JPanel centerPanela;
    private JMenu menu2, menu3;
    private JMenuItem mj1, mj2, mj3;
    private JButton loginButton, erregistratzeko_Button, sesioButton;
    private CardLayout card1;
    private JFrame f_Index = new JFrame();

    /* Programa abiatzerakoan, sesio.txt-a irakurtzen du ea logeatuta gauden eta horrela bada honen id_sozioa artuko da sozioa logeatzen detean sesio.txt an gordetzen da informazioa eta.
    Ondoren datu basetik HashSet bat bueltauko da funtzioari deituz eta bertan gordetako id_sozioa dagoen jakingo da. Horrera bada onderen berriro HashSet bat bueltatuko du baina kasu honetan zuzendari guztiena eta ea sartutako id_langilea zuzendaria den jakingo da.
     Azkenik funtzo baten bidez sozioaren datu guztiak eskatuko dira txt an gordetako id_sozioaren bidez. */
    public static void main(String[] args){
        try {
            int ida=0;
            BufferedReader irakurri = new BufferedReader(new FileReader(".\\sesio.txt"));
            ida= Integer.parseInt(irakurri.readLine());// TXT irakurri eta id-a gorde.
            if (Sozioak.id_sozioak().contains(ida)){// Gordetako id-a datu baseko id guztiekin konparatu HashSet baten bidez.
                logeatua_dago=true;
                if (Sozioak.zuzendariak().contains(ida)){//Gordetako id-a datu baseko zuzendarien id-ekin guztiekin konparatu HashSet baten bidez.
                    zuzendaria_da=true;
                }
                Datuak.izena_jarri(Sozioak.nan_atera(ida)); // Izenak_jarri funtzioari deituz sozioaren datu guztiak jaso.
            }
            new Index().sortu();//Horria sortu.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Horriaren egitura sortzeko funtzioei deitu.
    public void sortu(){
        center();
        north();
        orria();
    }

    public void orria(){

        f_Index.setTitle("Erleak");// Izenburuarn izena.
        f_Index.setSize(900, 450);// Izenburuaren tamaina ezarri.
        f_Index.setVisible(true);//Biztaratu.
        f_Index.setLocationRelativeTo(null);// Lehioa pantaila erdian agertzea.
        f_Index.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Horria ixterakoan programa bukatu.
        f_Index.setLayout(new BorderLayout());// Lehioari BorderLeyout mota ezarri.
    }

    public void north(){

            panel1 = new JPanel(); // Definitutako aldagaia erabiliz jpanel bat sortu.
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS)); // Sartutako panelari BoxLayout mota ezarri X ardatzean.

            //Logoa
            ImageIcon logoa = new ImageIcon(".\\Irudiak\\logo.png"); // Ordenagailuko logoa kargatu aldagaian.
            ImageIcon logoa_aldatuta = new ImageIcon(logoa.getImage().getScaledInstance(50, 35, java.awt.Image.SCALE_SMOOTH));// Logoari tamaina eman.
            JLabel logo_Irudia = new JLabel(logoa_aldatuta); // Logoarekin JLabel sortu
            logo_Irudia.addMouseListener(new MouseAdapter() { // Logoa klikagarria egin.
                @Override
                public void mouseClicked(MouseEvent e) {// Logoan click egiterakoan erdiko panel nagusia erakutsi.
                    card1.show(centerPanela, "panel2");
                }
            });

            panel1.add(logo_Irudia);// Irudia display nagusian txertatu.
            // Lehengo menua
            JMenuBar mb = new JMenuBar();// Menu barra sortu
            menu2 = new JMenu("Produktuak"); // Produktuak menua sortu.
            mb.add(menu2);// Menua menu barraran txertatu.

            mj1 = new JMenuItem("Eztia"); // Eztia izeneko aukera sortu.
            mj1.addActionListener(new ActionListener() {// Sortutako aukeran eztzule bat sortu click egiterakoan zerbait egiteko.
                @Override
                public void actionPerformed(ActionEvent e) {
                    card1.show(centerPanela, "panel3");// Eztia aukeran click egiterakoan erdiko panela aldatu.
                }
            });
            mj2 = new JMenuItem("Beste batzuk"); // Beste batzuk aukera sortu menuan.
            mj2.addActionListener(new ActionListener() { // Sortutako aukeran entzule bat gehitu.
                @Override
                public void actionPerformed(ActionEvent e) { // Click egiterakoan erdiko panelka aldatu.
                    card1.show(centerPanela, "panel4");
                }
            });

            mj3 = new JMenuItem("Materiala"); // Materiala izeneko beste aukera bat sortu.
            mj3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {// Sortutako haukerari entzule bat sortu.
                    card1.show(centerPanela, "panel5");
                }
            });

//Hiru aukerak menuan gehitu.
            menu2.add(mj1);
            menu2.add(mj2);
            menu2.add(mj3);
// Panel nagusiari menu barra gehitu.
            panel1.add(mb);
//Logeatuta badago eta zuzendaria bada  menu barra berri bat sortuko da kasu honetan menu bat gehiagorekin sozioak eta sozietateak deiturikoa. Bertan bi haukera egongo dira sozioak eta sozietateak.
        if (logeatua_dago) {// logeatuta dagoela zihurtatu.
            boolean atera= true;
            if(zuzendaria_da) {// Zuzendaria dela zihurtatu.
                JMenuBar mb2 = new JMenuBar();// Menu barra sortu.
                menu3 = new JMenu("Sozioak eta Asoziazioak"); //Menu barran menua sortu.
                mb2.add(menu3);// Menu barra lehioan txertatu.

                JMenuItem mj4 = new JMenuItem("Sozioak"); // Menuko aukera sortu.
                mj4.addActionListener(new ActionListener() { // Aukerari entzulea sortu.
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        card1.show(centerPanela, "panel6");// aukeran click egiterakoan erdiko panela aldatu.
                    }
                });
                JMenuItem mj5 = new JMenuItem("Asoziazioak"); // Beste aukera bat sortu.
                mj5.addActionListener(new ActionListener() {// Aukerari entzulea dortu.
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        card1.show(centerPanela, "panel7");// Aukeran click egiterakoan erdiko panela aldatu CardLayout..
                    }
                });
                // Aukerak menuan txertatu.
                menu3.add(mj4);
                menu3.add(mj5);

                mb2.setBorder(new EmptyBorder(0, 0, 0, 520));// Menu barrari ezkumineko utsunea ezarri.
                panel1.add(mb2);// Menu barra display nagusian gehitu.
                atera=false; // Atera aldagaia false jarri ondoren menubarreko ezkumineko tartea kontrolatzeko.
            }
            ImageIcon pertsonaIrudia = new ImageIcon(".\\Irudiak\\pertsona_icon.png"); // Profileko irudia aldagaian kargatu.
            ImageIcon pertsona_TamainaAldatuta = new ImageIcon(pertsonaIrudia.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));// Irudiaren tamaina ezarri.
            sesioButton = new JButton(pertsona_TamainaAldatuta); // Prifilaren botoiari irudia txertatu.
            sesioButton.addActionListener(new ActionListener() { // Sesio botoiari entzulea sortu.
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Sesioa().sortu_login();// Profilean clickatzerakoan profileko display-a sortu eta era
                    f_Index.dispose();
                }
            });

            if (atera) {// logeatuta badago baldintza.
                mb.setBorder(new EmptyBorder(0, 0, 0, 668));//menubarrak ezkuminean utziko duen utsunea ezarri.
            }
            panel1.add(sesioButton);

        }else {// logeatuta ez badago.
            mb.setBorder(new EmptyBorder(0, 0, 0, 512));//menubarrak ezkuminean utziko duen utsunea ezarri.
            //login botoia
            ImageIcon pertsonaIrudia = new ImageIcon(".\\Irudiak\\pertsona_icon.png");// Irudia aldagaian kargatu.
            ImageIcon pertsona_TamainaAldatuta = new ImageIcon(pertsonaIrudia.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));// Irudiari tamaina eman.
            loginButton = new JButton("Login", pertsona_TamainaAldatuta);// irudia login botoian txertatu.
            loginButton.addActionListener(new ActionListener() { // botoiari entzulea sortu.
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Login().sortu_login();// login-a sortu eta pantailaratu.
                    f_Index.dispose(); // frame-a itxi.
                }
            });

            panel1.add(loginButton); // login botoia display nagusian gehitu.

            ImageIcon pertsonaIrudia2 = new ImageIcon(".\\Irudiak\\Erregistratu.png"); // irudia kargatu.
            ImageIcon pertsona2_TamainaAldatuta = new ImageIcon(pertsonaIrudia2.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));// irudiari tamaina eman.
            erregistratzeko_Button = new JButton("Erregistratu", pertsona2_TamainaAldatuta); // Erregistratu botoia sortu eta honi irudia txertatu.
            erregistratzeko_Button.addActionListener(new ActionListener() { // botoiari entzulea gehitu.
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Erregistratu().sortu_Erregistratu(); // erregistratu lehioa sortu eta biztaratu.
                    f_Index.dispose();//frame-a itxi.
                }
            });

            panel1.add(erregistratzeko_Button);// display nagusiara erregistratu botoia gehitu.
        }
            f_Index.add(panel1, BorderLayout.NORTH); // Sortutako panel guztia 
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
