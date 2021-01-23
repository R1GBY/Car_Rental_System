package NYP2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class yeni_kullanici extends JFrame{
    private JLabel kayit_ol;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel kullanici_data;
    private JLabel sifre_data;
    private JButton kayit_ol_butonu;
    private JButton geri_butonu;
    private JPanel panel;

    private Statement st;
    private Connection con;
    private ResultSet rs;

    public void insertData(String kullanici, String sifre) { //veriler okunuyor.
        //https://alvinalexander.com/java/java-mysql-insert-example-preparedstatement/ kodda buradan faydalandım
        try
        {
            // create a mysql database connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Class.forName(myDriver);
            con = DriverManager.getConnection(myUrl, "root", "password");

            // the mysql insert statement
            String query = " insert into sys.kullanicilar (kullanicilar, sifreler)" //verileri yerleştireceğimiz yerin sql sorgusu
                    + " values (?,?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, kullanici);
            preparedStmt.setString (2, sifre);

            // execute the preparedstatement
            preparedStmt.execute();
            con.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public yeni_kullanici(){

        add(panel);
        setTitle("Arac Kiralama Sistemi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        geri_butonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                KullanıcıGirisi a = new KullanıcıGirisi();
                a.setVisible(true);
            }
        });
        kayit_ol_butonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kullanici = textField1.getText();
                String sifre = textField2.getText();

                insertData(kullanici,sifre);    // veri tabanına bilgileri yerleştiriyor
                dispose();
                KullanıcıGirisi a = new KullanıcıGirisi(); // ardından giriş ekranına yönlendiriyor
                a.setVisible(true);
                a.pack();
                a.setLocationRelativeTo(null);
                a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }

}
