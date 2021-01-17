package NYP2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class KullanıcıGirisi extends JFrame{
    private JLabel label;
    private JTextField kullanici_data;
    private JLabel kullanici_adi;
    private JLabel sifre;
    private JTextField sifre_data;
    private JButton girişButton;
    private JPanel panel;
    private JButton Yeni_kullanici;

    private Statement st;
    private Connection con;
    private ResultSet rs;

    ArrayList<String> kullanicilar = new ArrayList<String>(); //kullanıcı isimlerini tutan arraylist
    ArrayList<String> sifreler = new ArrayList<String>(); //kullanıcıların şifrelerini tutan arraylist

    public void getData() { //veriler okunuyor.

        try {
            String query = "SELECT * FROM sys.kullanicilar"; // sql sorgusu burada yazılıyor
            rs = st.executeQuery(query);
            while (rs.next()) {
                kullanicilar.add(rs.getString("kullanicilar")); // kullanıcı ve şifre sütunları alınıyor
                sifreler.add(rs.getString("sifreler"));

            }
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    public KullanıcıGirisi(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // driver'ın dosya yolunu veriyoruz
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "Umut1234!");
            st = con.createStatement(); //dosya yolu verirken timezone belirtmem gerekti çünkü hata verdi
        } catch (Exception e) {
            System.out.println("Error  : " + e);
        }

        getData(); // verileri getirdik
        add(panel); // panel eklendi
        setSize(400,200); // panel boyutu ayarlandı
        setTitle("Arac Kiralama Sistemi"); // Program başlığı atıldı
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Kapat tuşuna basıldığında ne olacağı belirlendi
        System.out.println(kullanicilar); //Hata olması ihtimaline karşı yazdırdım
        System.out.println(sifreler);
        girişButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String kullanici = kullanici_data.getText(); //JTextField'deki yazıyı string olarak alıyor
                String sifre = sifre_data.getText();
                int flag = 0;

                for(int i = 0; i < kullanicilar.size(); i++){
                    if(kullanici.equals(kullanicilar.get(i))){
                        if(sifre.equals(sifreler.get(i))){
                            flag = 1; //kullanıcı arraylistte varsa ve şifrelerde de varsa bayrağı değiştir.
                            break;
                        }
                    }
                }

                if(flag == 1) { // kullanıcı ve şifre bulunduysa formu kapat, butce ve kategori seçiminin olduğu sınıf oluştur.
                    dispose();
                    butce_kategori a = new butce_kategori();
                    a.setVisible(true);
                    a.pack();
                    a.setLocationRelativeTo(null);
                    a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else{ // aksi halde ekrana hata döndür
                    System.out.println("Yanlis Giris");
                    JOptionPane.showMessageDialog(null, "Bilgilerinizi kontrol edip tekrar deneyiniz.");
                }

            }
        });
        Yeni_kullanici.addActionListener(new ActionListener() {
            @Override   //yeni kullanıcı oluşturulmak istenirse yeni_kullanıcı sınıfını aç aktif olanı kapat
            public void actionPerformed(ActionEvent e) {
                dispose();
                yeni_kullanici a = new yeni_kullanici();
                a.setVisible(true);
                a.pack();
                a.setLocationRelativeTo(null);
                a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

    }


}
