package NYP2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class van_kategori extends JFrame {

    private JLabel vanlar;
    private JTable table1;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JButton kirala_butonu;
    private JButton geri_butonu;
    private JPanel panel;
    private JScrollPane sp;
    private JLabel butce_display;
    private JButton cikis_butonu;
    private JLabel sure_display;
    private JTextArea textArea1;

    public void kiralama(ArrayList butceDurumu, String kiralama_suresi){
        int column = 1; //kira
        int row = table1.getSelectedRow();
        String gunluk_kira = table1.getModel().getValueAt(row, column).toString();
        int toplam_butce = Integer.parseInt(String.valueOf(butceDurumu.get(0)));
        int toplam_kira = Integer.parseInt(kiralama_suresi) * Integer.parseInt(gunluk_kira);

        if( 0 < (toplam_butce - toplam_kira)){
            toplam_butce = toplam_butce - toplam_kira;
            System.out.println("Kiralama basariyla tamamlandi, kalan paraniz: " + toplam_butce);
            JOptionPane.showMessageDialog(null, "Kiralama başarıyla tamamlandı, kalan paranız: " + toplam_butce);
            butceDurumu.remove(0);
            butceDurumu.add(String.valueOf(toplam_butce));
            butce_display.setText("Butce: " + butceDurumu.get(0) );
        }
        else{
            JOptionPane.showMessageDialog(null, "Paranız yetmediği için kiralama başarısız oldu,  kalan paranız: " + toplam_butce);
        }

    }


    public van_kategori(String butce, String kiralama_suresi){

        DefaultTableModel tblModel = (DefaultTableModel)table1.getModel();
        tblModel.addColumn("arac ismi");
        tblModel.addColumn("kira");
        tblModel.addColumn("yatak sayısı");
        tblModel.addColumn("ağırlık kapasitesi");
        tblModel.addColumn("su kapasitesi");
        tblModel.addColumn("yolcu sayısı");

        try {
            //Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql:localhost:3306/new_schma","root","denizalt123");
            //Statement myStart = (Statement) myConn.createStatement();
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/new_schma","root","denizalt123");
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","Umut1234!");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from sys.van");
            while (rs.next()) {
                String arac_ismi = rs.getString("arac_ismi");
                String kira = rs.getString ("kira"); //"username" is column name
                String yatak_sayisi = rs.getString ("yatak_sayisi");
                String agirlik_kapasitesi = rs.getString("agirlik_kapasitesi");
                String su_kapasitesi = rs.getString("su_kapasitesi");
                String yolcu_sayisi = rs.getString("yolcu_sayisi");

                //string array for store data into jtable
                String tbData[] = {arac_ismi,kira,yatak_sayisi,agirlik_kapasitesi,su_kapasitesi,yolcu_sayisi};

                //add string array data into jtable
                tblModel.addRow(tbData);



            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String> butceDurumu = new ArrayList<String>();
        butceDurumu.add(butce);

        System.out.println(butce + " " + kiralama_suresi);
        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(radioButton1);
        bgroup.add(radioButton2);
        bgroup.add(radioButton3);

        butce_display.setText("Butce: " + butceDurumu.get(0) );
        sure_display.setText("Kiralama Süresi: " + kiralama_suresi);

        add(panel);
        setSize(400,200);
        setTitle("Arac Kiralama Sistemi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        geri_butonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                butce_kategori a = new butce_kategori();
                a.setVisible(true);
                a.pack();
                dispose();
                a.setLocationRelativeTo(null);
                a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        kirala_butonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kiralama(butceDurumu,kiralama_suresi);
            }
        });
        cikis_butonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KullanıcıGirisi a = new KullanıcıGirisi();
                dispose();
                a.setVisible(true);
                a.pack();
                a.setLocationRelativeTo(null);
                a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
