package erleak;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

import static erleak.Login.*;
import static erleak.Datuak.*;

public class Index extends JFrame implements ActionListener {// Erabiliko diren aldagai guztiak definitu.
    private JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7;
    private JPanel centerPanela;
    private JMenu menu2, menu3;
    private JMenuItem mj1, mj2, mj3;
    private JButton loginButton, erregistratzeko_Button, sesioButton;
    private CardLayout card1;
    private DefaultTableModel eztia_model, besteak_model,material_model,sozioak_model,asoziazioak_model;
    private JFrame f_Index = new JFrame();

    /* Programa abiatzerakoan, sesio.txt-a irakurtzen du ea logeatuta gauden eta horrela bada honen id_sozioa artuko da sozioa logeatzen detean sesio.txt an gordetzen da informazioa eta.
    Ondoren datu basetik HashSet bat bueltauko da funtzioari deituz eta bertan gordetako id_sozioa dagoen jakingo da. Horrera bada onderen berriro HashSet bat bueltatuko du baina kasu honetan zuzendari guztiena eta ea sartutako id_langilea zuzendaria den jakingo da.
     Azkenik funtzo baten bidez sozioaren datu guztiak eskatuko dira txt an gordetako id_sozioaren bidez. */
    public static void main(String[] args){
        Datuak da = new Datuak();
        try {
            int ida=0;
            BufferedReader irakurri = new BufferedReader(new FileReader(".\\sesio.txt"));
            ida= Integer.parseInt(irakurri.readLine());// TXT irakurri eta id-a gorde.
            if (da.id_sozioak().contains(ida)){// Gordetako id-a datu baseko id guztiekin konparatu HashSet baten bidez.
                logeatua_dago=true;
                if (da.zuzendariak().contains(ida)){//Gordetako id-a datu baseko zuzendarien id-ekin guztiekin konparatu HashSet baten bidez.
                    zuzendaria_da=true;
                }
                da.izena_jarri(da.nan_atera(ida)); // Izenak_jarri funtzioari deituz sozioaren datu guztiak jaso.
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
            Datuak da= new Datuak();
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
                    String[] kolumna_Izenak_eztia= {"Izena", "Prezioa","Kantitatea", "Deskribapena"}; // String-en array bat hasieratu datu bateko taularen eremuen izenekin.
                    eztia_model.setDataVector(da.center_3_txertatu(), kolumna_Izenak_eztia);
                }
            });
            mj2 = new JMenuItem("Beste batzuk"); // Beste batzuk aukera sortu menuan.
            mj2.addActionListener(new ActionListener() { // Sortutako aukeran entzule bat gehitu.
                @Override
                public void actionPerformed(ActionEvent e) { // Click egiterakoan erdiko panelka aldatu.
                    card1.show(centerPanela, "panel4");
                    String[] kolumna_Izenak_besteak= {"Izena", "Prezioa","Kantitatea", "Deskribapena"};
                    besteak_model.setDataVector(da.center_4_txertatu(), kolumna_Izenak_besteak);
                }
            });

            mj3 = new JMenuItem("Materiala"); // Materiala izeneko beste aukera bat sortu.
            mj3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {// Sortutako haukerari entzule bat sortu.
                    card1.show(centerPanela, "panel5");
                    String[] kolumna_Izenak_besteak= {"Izena", "Prezioa","Kantitatea", "Deskribapena"};// String-en array bat hasieratu datu baseko taularen eremuen izenekin.
                    besteak_model.setDataVector(da.center_5_txertatu(),kolumna_Izenak_besteak);
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
                        String[] kolumna_Izenak_sozio= {"ID","Zuzendaria","Erle KANT", "Erlauntz","Izena","Abizena","NAN","Telefonoa","Jaiote Data","Email"};// String bat hasieratu datu baseko sozioak taularen eremuen izenekin.
                        sozioak_model.setDataVector(da.sozio_Array(), kolumna_Izenak_sozio);
                    }
                });
                JMenuItem mj5 = new JMenuItem("Asoziazioak"); // Beste aukera bat sortu.
                mj5.addActionListener(new ActionListener() {// Aukerari entzulea dortu.
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        card1.show(centerPanela, "panel7");// Aukeran click egiterakoan erdiko panela aldatu CardLayout..
                        String[] kolumna_Izenak_asoziazioak= {"ID", "Izena","Herrialdea"};// String bat hasieratu datu baseko  asoziazioak taularen eremuen izenekin.
                        asoziazioak_model.setDataVector(da.sozietate_Arraya(), kolumna_Izenak_asoziazioak);
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
            f_Index.add(panel1, BorderLayout.NORTH); // Sortutako menu guztiak eta argazkiak panelaren goiko aldea txertatu.
    }

    public void center(){
        Datuak da = new Datuak();
        card1= new CardLayout();// card berri bat sortu.
        centerPanela= new JPanel();// panela sortu.
        centerPanela.setLayout(card1);// panelari card mota ezarri.

        ImageIcon lupa_irudia = new ImageIcon(".\\Irudiak\\lupa.png");
        ImageIcon lupa_irudia_aldatuta = new ImageIcon(lupa_irudia.getImage().getScaledInstance(10,10, java.awt.Image.SCALE_SMOOTH));

        panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));// panel bat sortu erdian kokatzen dena.
        ImageIcon portada_irudia= new ImageIcon(".\\Irudiak\\abejas.jpg"); // irudia aldagaian kargatu.
        JLabel portada= new JLabel(portada_irudia);// irudia jlabel batean jarri.
        panel2.add(portada);// irudia erdian kokatutako panelean txertatu.

        panel3 = new JPanel(new BorderLayout());// panel bat sortu borderlayout motakoa.

        ImageIcon eztia_irudia = new ImageIcon(".\\Irudiak\\kuadroa_eztia.jpg");// irudia aldagai batean txertatu.
        ImageIcon eztia_irudia_aldatuta= new ImageIcon(eztia_irudia.getImage().getScaledInstance(900, 100, java.awt.Image.SCALE_SMOOTH));// irudiari tamaina jarri.
        JLabel eztia_Lirudi= new JLabel(eztia_irudia_aldatuta); // irudia jlabel batean jarri.
        panel3.add(eztia_Lirudi, BorderLayout.NORTH);// aurreko jlabel-a  sortutako panelaren goiko partean jarri.

        SpringLayout layout3 = new SpringLayout();
        JPanel panel3_1 = new JPanel(layout3);
        JTextField bilatzailea3 = new JTextField(15);
        JButton L_lupa_irudia3 = new JButton(lupa_irudia_aldatuta);
        JButton desegin3 = new JButton("Desegin");
        da.textuGrixa(bilatzailea3,"Eztia-ren Izena");

        panel3_1.add(bilatzailea3);
        layout3.putConstraint(SpringLayout.WEST, bilatzailea3,  10, SpringLayout.WEST, panel3);

        panel3_1.add(L_lupa_irudia3);
        layout3.putConstraint(SpringLayout.WEST, L_lupa_irudia3,  170, SpringLayout.WEST, bilatzailea3);

        panel3_1.add(desegin3);
        layout3.putConstraint(SpringLayout.WEST, desegin3, 80, SpringLayout.WEST, L_lupa_irudia3);

        panel3.add(panel3_1, BorderLayout.CENTER);

        String[] kolumna_Izenak_eztia= {"Izena", "Prezioa","Kantitatea", "Deskribapena"}; // String-en array bat hasieratu datu bateko taularen eremuen izenekin.
        eztia_model = new DefaultTableModel();
        JTable ezti_tabla= new JTable(eztia_model);//aldatu ezin den taula bat sortu, array-eko zutabeekin.
        ezti_tabla.setPreferredScrollableViewportSize(new Dimension(700, 225));// taulari tamaina ezarri.
        JScrollPane ezti_sp= new JScrollPane(ezti_tabla);//taulan scroll bat sortu.

        panel3.add(ezti_sp, BorderLayout.SOUTH);// sortutako taula eta scroll-a panelaren erdian txertatu.

        panel4= new JPanel(new BorderLayout());// Panel berri bat sortu.

        ImageIcon besteak_irudia = new ImageIcon(".\\Irudiak\\kuadroa_erdia.jpg");// irudia aldagai batean hasieratu.
        ImageIcon besteak_irudia_aldatuta= new ImageIcon(besteak_irudia.getImage().getScaledInstance(900, 100, java.awt.Image.SCALE_SMOOTH));// irudiari tamaina bat eman.
        JLabel besteak_Lirudi= new JLabel(besteak_irudia_aldatuta);// irudia jlabel batean jarri.
        panel4.add(besteak_Lirudi, BorderLayout.NORTH);// sortutako labela panelean txertatu.

        SpringLayout layout4 = new SpringLayout();
        JPanel panel4_1 = new JPanel(layout4);
        JTextField bilatzailea4 = new JTextField(15);
        JButton L_lupa_irudia4 = new JButton(lupa_irudia_aldatuta);
        JButton desegin4 = new JButton("Desegin");
        da.textuGrixa(bilatzailea4,"Produktua-ren Izena");

        panel4_1.add(bilatzailea4);
        layout4.putConstraint(SpringLayout.WEST, bilatzailea4,  10, SpringLayout.WEST, panel4);

        panel4_1.add(L_lupa_irudia4);
        layout4.putConstraint(SpringLayout.WEST, L_lupa_irudia4,  170, SpringLayout.WEST, bilatzailea4);

        panel4_1.add(desegin4);
        layout4.putConstraint(SpringLayout.WEST, desegin4, 80, SpringLayout.WEST, L_lupa_irudia4);

        panel4.add(panel4_1, BorderLayout.CENTER);

        String[] kolumna_Izenak_besteak= {"Izena", "Prezioa","Kantitatea", "Deskribapena"};// String-en array bat hasieratu datu baseko taularen eremuen izenekin.
        besteak_model = new DefaultTableModel();
        JTable besteak_tabla= new JTable(besteak_model);//aldatu ezin den taula bat sortu, array-eko zutabeekin.
        besteak_tabla.setPreferredScrollableViewportSize(new Dimension(700, 225));// taularen tamaina ezarri.
        JScrollPane bestak_sp= new JScrollPane(besteak_tabla);// taulari scroll bat sortu.

        panel4.add(bestak_sp, BorderLayout.SOUTH);// taula panelean txertatu.

        panel5= new JPanel(new BorderLayout());// panel bat sortu.

        JLabel materiala_Lirudi2= new JLabel(besteak_irudia_aldatuta);// irudia jlabel batean jarri.
        panel5.add(materiala_Lirudi2, BorderLayout.NORTH);//jlabel-a irudiarekin panelaren goiko aldean jarri.

        SpringLayout layout5 = new SpringLayout();
        JPanel panel5_1 = new JPanel(layout5);
        JTextField bilatzailea5 = new JTextField(15);
        JButton L_lupa_irudia5 = new JButton(lupa_irudia_aldatuta);
        JButton desegin5 = new JButton("Desegin");
        da.textuGrixa(bilatzailea5,"Materiala-ren Izena");

        panel5_1.add(bilatzailea5);
        layout5.putConstraint(SpringLayout.WEST, bilatzailea5,  10, SpringLayout.WEST, panel5);

        panel5_1.add(L_lupa_irudia5);
        layout5.putConstraint(SpringLayout.WEST, L_lupa_irudia5,  170, SpringLayout.WEST, bilatzailea5);

        panel5_1.add(desegin5);
        layout5.putConstraint(SpringLayout.WEST, desegin5, 80, SpringLayout.WEST, L_lupa_irudia5);

        panel5.add(panel5_1, BorderLayout.CENTER);

        String[] kolumna_Izenak_material= {"Izena", "Prezioa","Kantitatea", "Deskribapena"};// String bat hasieratu datu baseko taularen eremuen izenekin.
        material_model = new DefaultTableModel(da.center_5_txertatu(), kolumna_Izenak_material);
        JTable material_tabla= new JTable(material_model);//aldatu ezin den taula bat sortu, array-eko zutabeekin.
        material_tabla.setPreferredScrollableViewportSize(new Dimension(700, 225));//taulari tamaina ezarri.
        JScrollPane material_sp= new JScrollPane(material_tabla);//taulari scroll bat sortu.

        panel5.add(material_sp, BorderLayout.SOUTH);// taula panelean txertatu.

        panel6 = new JPanel(new BorderLayout());// panel bat sortu.

        ImageIcon sozio_irudia = new ImageIcon(".\\Irudiak\\kuadroa_eztia.jpg");// irudia aldagai batean hasieratu.
        ImageIcon sozio_irudia_aldatuta= new ImageIcon(sozio_irudia.getImage().getScaledInstance(900, 100, java.awt.Image.SCALE_SMOOTH));// irudiari tamaina bat eman.
        JLabel sozio_Lirudi= new JLabel(sozio_irudia_aldatuta);// irudia jlabel batean jarri.
        panel6.add(sozio_Lirudi, BorderLayout.NORTH);//jlabel-a irudiarekin panelaren goiko aldean jarri.

        SpringLayout layout6 = new SpringLayout();
        JPanel panel6_1 = new JPanel(layout6);
        JTextField bilatzailea6 = new JTextField(15);
        JButton L_lupa_irudia6 = new JButton(lupa_irudia_aldatuta);
        JButton desegin6 = new JButton("Desegin");
        da.textuGrixa(bilatzailea6,"Sozio Izena");

        panel6_1.add(bilatzailea6);
        layout6.putConstraint(SpringLayout.WEST, bilatzailea6,  10, SpringLayout.WEST, panel6);

        panel6_1.add(L_lupa_irudia6);
        layout6.putConstraint(SpringLayout.WEST, L_lupa_irudia6,  170, SpringLayout.WEST, bilatzailea6);

        panel6_1.add(desegin6);
        layout6.putConstraint(SpringLayout.WEST, desegin6, 80, SpringLayout.WEST, L_lupa_irudia6);

        panel6.add(panel6_1, BorderLayout.CENTER);

        String[] kolumna_Izenak_sozio= {"ID","Zuzendaria","Erle KANT", "Erlauntz","Izena","Abizena","NAN","Telefonoa","Jaiote Data","Email"};// String bat hasieratu datu baseko sozioak taularen eremuen izenekin.
        sozioak_model = new DefaultTableModel();
        JTable sozio_tabla= new JTable(sozioak_model);//aldatu ezin den taula bat sortu, array-eko eremuekin.
        sozio_tabla.setPreferredScrollableViewportSize(new Dimension(700, 225));//taulari tamaina ezarri.
        JScrollPane sozio_sp= new JScrollPane(sozio_tabla);//taulari scroll bat sortu.

        panel6.add(sozio_sp, BorderLayout.SOUTH);// taula panelaren erdian txertatu.

        panel7 = new JPanel(new BorderLayout());// panel bat sortu.
        ImageIcon sozietateak_irudia = new ImageIcon(".\\Irudiak\\kuadroa_eztia.jpg");// irudia aldagai batean hasieratu.
        ImageIcon sozietateak_irudia_aldatuta= new ImageIcon(sozietateak_irudia.getImage().getScaledInstance(900, 100, java.awt.Image.SCALE_SMOOTH));// irudiari tamaina bat eman.
        JLabel sozietateak_Lirudi= new JLabel(sozietateak_irudia_aldatuta);// irudia jlabel batean jarri.
        panel7.add(sozietateak_Lirudi, BorderLayout.NORTH);//jlabel-a irudiarekin panelaren goiko aldean jarri.

        SpringLayout layout7 = new SpringLayout();
        JPanel panel7_1 = new JPanel(layout7);
        JTextField bilatzailea7 = new JTextField(15);
        JButton L_lupa_irudia7 = new JButton(lupa_irudia_aldatuta);
        JButton desegin7 = new JButton("Desegin");
        da.textuGrixa(bilatzailea7,"Asoziazio Izena");

        ArrayList<Sozietateak> sozietateak = da.sozietate_Arrayalist();
        String[] hirriak_lista = new String[sozietateak.size()];

        int x=0;
        for (Sozietateak a: sozietateak) {
            hirriak_lista[x]=a.getHerrialdea();
            x++;
        }
        JComboBox herriak = new JComboBox<>(hirriak_lista);

        panel7_1.add(bilatzailea7);
        layout7.putConstraint(SpringLayout.WEST, bilatzailea7,  10, SpringLayout.WEST, panel7);

        panel7_1.add(L_lupa_irudia7);
        layout7.putConstraint(SpringLayout.WEST, L_lupa_irudia7,  170, SpringLayout.WEST, bilatzailea7);

        panel7_1.add(herriak);
        layout7.putConstraint(SpringLayout.WEST, herriak, 50, SpringLayout.WEST, L_lupa_irudia7);

        panel7_1.add(desegin7);
        layout7.putConstraint(SpringLayout.WEST, desegin7, 80, SpringLayout.WEST, herriak);

        panel7.add(panel7_1, BorderLayout.CENTER);


        String[] kolumna_Izenak_asoziazioak= {"ID", "Izena","Herrialdea"};// String bat hasieratu datu baseko  asoziazioak taularen eremuen izenekin.
        asoziazioak_model = new DefaultTableModel(da.sozietate_Arraya(), kolumna_Izenak_asoziazioak);
        JTable asoziazioak_tabla= new JTable(asoziazioak_model);//aldatu ezin den taula bat sortu, array-eko eremuekin.
        asoziazioak_tabla.setPreferredScrollableViewportSize(new Dimension(700, 225));//taulari tamaina ezarri.
        JScrollPane asoziazioak_sp= new JScrollPane(asoziazioak_tabla);//taulari scroll bat sortu.

        panel7.add(asoziazioak_sp, BorderLayout.SOUTH);// taula panelaren erdian txertatu.

        // panel guztiak card-en gorde bakoitza bere identifikatzailearekin.

        centerPanela.add(panel2,"panel2");
        centerPanela.add(panel3,"panel3");
        centerPanela.add(panel4, "panel4");
        centerPanela.add(panel5, "panel5");
        centerPanela.add(panel6, "panel6");
        centerPanela.add(panel7, "panel7");

        f_Index.add(centerPanela,BorderLayout.CENTER);//panel nagusiko erdialdean gehitu card panela.
        L_lupa_irudia3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eztia_model.setDataVector(da.ezti_bilatzailea(bilatzailea3.getText()),kolumna_Izenak_eztia);
            }
        });

        desegin3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eztia_model.setDataVector(da.center_3_txertatu(), kolumna_Izenak_eztia);
            }
        });
        L_lupa_irudia4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                besteak_model.setDataVector(da.besteak_bilatu(bilatzailea4.getText()),kolumna_Izenak_besteak);
            }
        });

        desegin4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                besteak_model.setDataVector(da.center_4_txertatu(), kolumna_Izenak_besteak);
            }
        });
        L_lupa_irudia5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                material_model.setDataVector(da.materiala_bilatu(bilatzailea5.getText()),kolumna_Izenak_material);
            }
        });

        desegin5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                material_model.setDataVector(da.center_5_txertatu(), kolumna_Izenak_material);
            }
        });

        L_lupa_irudia6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sozioak_model.setDataVector(da.sozio_Array_bilatzailea(bilatzailea6.getText()),kolumna_Izenak_sozio);
            }
        });

        desegin6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sozioak_model.setDataVector(da.sozio_Array(), kolumna_Izenak_sozio);
            }
        });

        L_lupa_irudia7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                asoziazioak_model.setDataVector(da.sozietate_Arraya_bilatzailea(bilatzailea7.getText()),kolumna_Izenak_asoziazioak);
            }
        });

        desegin7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                asoziazioak_model.setDataVector(da.sozietate_Arraya(), kolumna_Izenak_asoziazioak);
            }
        });

        herriak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                asoziazioak_model.setDataVector( da.sozietate_Arraya_herria((String) herriak.getSelectedItem()),kolumna_Izenak_asoziazioak);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
