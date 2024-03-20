package erleak;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Index extends JFrame implements ActionListener {
    private JMenuBar mb;
    private JMenu menu1, menu2;
    private JMenuItem mi1, mj1, mj2;
    private JButton loginButton;
    public Index(){
        super("Erleak");
        mb = new JMenuBar();
        setJMenuBar(mb);

        //lehengo menua
        menu1 = new JMenu("Zerbitzuak");
        mb.add(menu1);
        mi1 = new JMenuItem("Kolmenen instalazioak");
        mi1.addActionListener(this);
        menu1.add(mi1);

        //bigarren menua
        menu2 = new JMenu("Produktuak");
        mb.add(menu2);
        mj1 = new JMenuItem("Ezti naturala");
        mj1.addActionListener(this);
        mj2 = new JMenuItem("Erleen kolmenak");
        mj2.addActionListener(this);
        menu2.add(mj1);
        menu2.add(mj2);

        //login botoia
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //login lehioa ireki
                Login loginWindow = new Login();
                loginWindow.setVisible(true);
            }
        });
        mb.add(Box.createHorizontalGlue()); //botoia eskuinaldean jarriko du
        mb.add(loginButton);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args){
        Index index1 = new Index();
        index1.setBounds(10, 20, 300, 200);
        index1.setVisible(true);
        index1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
