package erleak;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class Index extends JFrame implements ActionListener {
    private JPanel panel1, panel2, panel3, panel4, panel5;
    private JPanel panel_2_3_4_5;
    private JMenu menu2;
    private JMenuItem mj1, mj2, mj3;
    private JButton loginButton, erregistratzeko_Button;
    private CardLayout card1;
    private JFrame f_Index = new JFrame();

    static String ip_eta_portua="10.14.4.124";
    static String erabiltzailea="T5_2";
    static String pazaitza="123";
    static String datu_base_IZENA="ORCLCDB";
    static final String url= "jdbc:oracle:thin:@"+ip_eta_portua+":1521"+":"+datu_base_IZENA;
    static final String driver= "oracle.jdbc.driver.OracleDriver";
    public static Connection con;
    public static int identifikatzaile_mota;
    public static String izena_login, abizena_login, nan_login, telefonoa_login, email_login;

    public static void main(String[] args){

        new Index().sortu();
    }

    public void konexioa(){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, erabiltzailea, pazaitza);
        }catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void sortu(){
        konexioa();
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


        //Logoa
        ImageIcon logoa= new ImageIcon(".\\Irudiak\\logo.png");
        ImageIcon logoa_aldatuta= new ImageIcon(logoa.getImage().getScaledInstance(50, 35, java.awt.Image.SCALE_SMOOTH));
        JLabel logo_Irudia= new JLabel(logoa_aldatuta);
        logo_Irudia.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                card1.show(panel_2_3_4_5, "panel2");
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
                card1.show(panel_2_3_4_5, "panel3");
            }
        });
        mj2 = new JMenuItem("Beste batzuk");
        mj2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card1.show(panel_2_3_4_5, "panel4");
            }
        });

        mj3= new JMenuItem("Materiala");
        mj3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card1.show(panel_2_3_4_5, "panel5");
            }
        });
        menu2.add(mj1);
        menu2.add(mj2);
        menu2.add(mj3);
        mj1.addActionListener(this);
        mj2.addActionListener(this);

        mb.setBorder(new EmptyBorder(0,0,0,530));
        panel1.add(mb);

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
        erregistratzeko_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Erregistratu().sortu_Erregistratu();
                f_Index.dispose();
            }
        });

        panel1.add(erregistratzeko_Button);

        f_Index.add(panel1, BorderLayout.NORTH);

    }

    public void center(){
        card1= new CardLayout();
        panel_2_3_4_5= new JPanel();
        panel_2_3_4_5.setLayout(card1);

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
        final JTable ezti_tabla= new JTable(center_3_txertatu(),kolumna_Izenak_eztia);
        ezti_tabla.setPreferredScrollableViewportSize(new Dimension(700, 225));
        JScrollPane ezti_sp= new JScrollPane(ezti_tabla);

        panel3.add(ezti_sp, BorderLayout.CENTER);

        panel4= new JPanel(new BorderLayout());

        ImageIcon besteak_irudia = new ImageIcon(".\\Irudiak\\kuadroa_erdia.jpg");
        ImageIcon besteak_irudia_aldatuta= new ImageIcon(besteak_irudia.getImage().getScaledInstance(900, 100, java.awt.Image.SCALE_SMOOTH));
        JLabel besteak_Lirudi= new JLabel(besteak_irudia_aldatuta);
        panel4.add(besteak_Lirudi, BorderLayout.NORTH);

        String[] kolumna_Izenak_besteak= {"Izena", "Prezioa","Kantitatea", "Deskribapena"};
        final JTable besteak_tabla= new JTable(center_4_txertatu(),kolumna_Izenak_besteak);
        besteak_tabla.setPreferredScrollableViewportSize(new Dimension(700, 225));
        JScrollPane bestak_sp= new JScrollPane(besteak_tabla);

        panel4.add(bestak_sp);

        panel5= new JPanel(new BorderLayout());

        ImageIcon besteak_irudia2 = new ImageIcon(".\\Irudiak\\kuadroa_erdia.jpg");
        ImageIcon besteak_irudia_aldatuta2= new ImageIcon(besteak_irudia2.getImage().getScaledInstance(900, 100, java.awt.Image.SCALE_SMOOTH));
        JLabel besteak_Lirudi2= new JLabel(besteak_irudia_aldatuta2);
        panel5.add(besteak_Lirudi2, BorderLayout.NORTH);


        String[] kolumna_Izenak_material= {"Izena", "Prezioa","Kantitatea", "Deskribapena"};
        final JTable material_tabla= new JTable(center_5_txertatu(),kolumna_Izenak_material);
        material_tabla.setPreferredScrollableViewportSize(new Dimension(700, 225));
        JScrollPane material_sp= new JScrollPane(material_tabla);

        panel5.add(material_sp);


        panel_2_3_4_5.add(panel2,"panel2");
        panel_2_3_4_5.add(panel3,"panel3");
        panel_2_3_4_5.add(panel4, "panel4");
        panel_2_3_4_5.add(panel5, "panel5");

        f_Index.add(panel_2_3_4_5,BorderLayout.CENTER);
    }
    public String[][] center_4_txertatu( ){
        try {

            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery("SELECT Produktu_mota, prezioa, kantitatea, deskribapena FROM Produktuak where Produktu_mota not like 'eztia'");
            rs.last();
            String[][] besteak_datuak=new String[rs.getRow()][4];
            rs.beforeFirst();
            int x= 0;
            while (rs.next()){
                String izena= rs.getString("Produktu_mota");
                String prezioa= rs.getString("prezioa");
                String kantitatea= rs.getString("kantitatea");
                String deskribapena= rs.getString("deskribapena");
                besteak_datuak[x][0]=izena;
                besteak_datuak[x][1]=prezioa;
                besteak_datuak[x][2]=kantitatea;
                besteak_datuak[x][3]=deskribapena;
                x++;
            }
            return besteak_datuak;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String[][] center_3_txertatu( ){
        try {

            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Statement stmt2= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery("SELECT id_produktua, prezioa, kantitatea, deskribapena FROM Produktuak where Produktu_mota='eztia'");
            ResultSet rs2 = stmt2.executeQuery("SELECT EZTIA_MOTA FROM Eztia ORDER BY id_produktua");
            rs.last();
            rs2.last();
            String[] id_produktuak= new String[rs2.getRow()];
            String[][] besteak_datuak=new String[rs.getRow()][4];
            rs.beforeFirst();
            rs2.beforeFirst();
            int x= 0;
            while (rs.next()){
                String prezioa= rs.getString("prezioa");
                String kantitatea= rs.getString("kantitatea");
                String deskribapena= rs.getString("deskribapena");
                String id_produktua= rs.getString("id_produktua");
                id_produktuak[x]=id_produktua;
                besteak_datuak[x][1]=prezioa;
                besteak_datuak[x][2]=kantitatea;
                besteak_datuak[x][3]=deskribapena;
                x++;
            }
            x=0;
            while (rs2.next()){
                String izena= rs2.getString("EZTIA_MOTA");
                besteak_datuak[x][0]=izena;
                x++;
            }

            return besteak_datuak;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String[][] center_5_txertatu(){
        try {

            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery("SELECT materiala_izena, prezioa, kantitatea, deskribapena FROM Materiala");
            rs.last();
            String[][] besteak_datuak=new String[rs.getRow()][4];
            rs.beforeFirst();
            int x= 0;
            while (rs.next()){
                String izena= rs.getString("materiala_izena");
                String prezioa= rs.getString("prezioa");
                String kantitatea= rs.getString("kantitatea");
                String deskribapena= rs.getString("deskribapena");
                besteak_datuak[x][0]=izena;
                besteak_datuak[x][1]=prezioa;
                besteak_datuak[x][2]=kantitatea;
                besteak_datuak[x][3]=deskribapena;
                x++;
            }
            return besteak_datuak;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean izena_jarri(String identifikatzailea){
        try {
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs= stmt.executeQuery("SELECT sozio_izena, sozio_abizena, nan, telefonoa,email FROM sozioak");
            while (rs.next()) {
                String izen_abizen= " "+rs.getString("sozio_izena")+" "+rs.getString("sozio_abizena");
                if (izen_abizen.equals(identifikatzailea) || (" "+rs.getString("nan")).equals(identifikatzailea) || (" "+rs.getString("telefonoa")).equals(identifikatzailea) || (" "+rs.getString("email")).equals(identifikatzailea)) {
                    if (izen_abizen.equals(identifikatzailea)){

                        izena_login= rs.getString("sozio_izena");
                        abizena_login= rs.getString("sozio_abizena");

                        identifikatzaile_mota=1;
                    }else if ((" "+rs.getString("nan")).equals(identifikatzailea)){

                        nan_login=rs.getString("nan");

                        identifikatzaile_mota=2;
                    }else if ((" "+rs.getString("telefonoa")).equals(identifikatzailea)) {

                        telefonoa_login= rs.getString("telefonoa");

                        identifikatzaile_mota=3;
                    } else if ((" "+rs.getString("email")).equals(identifikatzailea)) {

                        email_login= rs.getString("email");

                        identifikatzaile_mota=4;
                    }
                    return true;
                }else {
                    return false;
                }
            }
        } catch (SQLException e ) {
            return false;
        }
        return false;
    }
    public static String pazaitza_jarri(){
        try {
            Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            switch (identifikatzaile_mota){
                case 1:
                    ResultSet rs1= stmt.executeQuery("SELECT pasahitza FROM sozioak where sozio_izena='"+izena_login+"' and sozio_abizena='"+abizena_login+"'");

                    return rs1.getString("pasahitza");
                case 2:
                    ResultSet rs2= stmt.executeQuery("SELECT pasahitza FROM sozioak where nan='"+nan_login+"'");
                    return rs2.getString("pasahitza");
                case 3:
                    ResultSet rs3= stmt.executeQuery("SELECT pasahitza FROM sozioak where telefonoa="+telefonoa_login);
                    return rs3.getString("pasahitza");
                case 4:
                    ResultSet rs4= stmt.executeQuery("SELECT pasahitza FROM sozioak where email='"+email_login+"'");
                    return rs4.getString("pasahitza");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "ERROR";
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
