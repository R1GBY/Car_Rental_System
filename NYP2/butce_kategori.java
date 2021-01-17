package NYP2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class butce_kategori extends JFrame {

    private JTextField textField1;
    private JTextField textField2;
    private JRadioButton binekAracRadioButton;
    private JRadioButton otobusRadioButton;
    private JRadioButton vanRadioButton;
    private JLabel Kategori;
    private JLabel Kiralama_suresi;
    private JLabel Butce;
    private JPanel panel;
    private JButton button1;

    private Statement st;
    private Connection con;
    private ResultSet rs;



    public butce_kategori(){
        ButtonGroup bgroup = new ButtonGroup(); // radyo butonları gruplandırıyorum
        bgroup.add(otobusRadioButton); // böylece birisi seçildiğinde diğeri aktif olamayacak
        bgroup.add(binekAracRadioButton);
        bgroup.add(vanRadioButton);

        add(panel);
        setSize(400,200);
        setTitle("Arac Kiralama Sistemi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(otobusRadioButton.isSelected()){ // otobüs kategorisi seçilirse...

                    String butce = textField1.getText(); //bütçe ve kiralama süresini textfielddan aldık
                    String kiralama_suresi = textField2.getText();

                    int sayi_butce = Integer.parseInt(butce); //string'den int'e çevirdik
                    int sayi_kiralama_suresi = Integer.parseInt(kiralama_suresi);

                    if(sayi_butce > 0 && sayi_kiralama_suresi > 0){ //girilen sayılar 0'dan büyük mü ?
                        dispose();
                        otobus_kategori a = new otobus_kategori(butce,kiralama_suresi);
                        a.setVisible(true);
                        a.pack();
                        a.setLocationRelativeTo(null);
                        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                    else{ // büyük değilse hata döndür
                        JOptionPane.showMessageDialog(null, "Bilgilerinizi kontrol edip tekrar deneyiniz.");
                    }
                }
                else if(binekAracRadioButton.isSelected()){ // binek araç kategorisi seçilirse...

                    String butce = textField1.getText();
                    String kiralama_suresi = textField2.getText();

                    int sayi_butce = Integer.parseInt(butce);
                    int sayi_kiralama_suresi = Integer.parseInt(kiralama_suresi);

                    if(sayi_butce > 0 && sayi_kiralama_suresi > 0){
                        dispose();
                        binek_kategori a = new binek_kategori(butce,kiralama_suresi);
                        a.setVisible(true);
                        a.pack();
                        a.setLocationRelativeTo(null);
                        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Bilgilerinizi kontrol edip tekrar deneyiniz.");
                    }
                }
                else if(vanRadioButton.isSelected()){ // van kategorisi seçilirse...

                    String butce = textField1.getText();
                    String kiralama_suresi = textField2.getText();

                    int sayi_butce = Integer.parseInt(butce);
                    int sayi_kiralama_suresi = Integer.parseInt(kiralama_suresi);

                    if(sayi_butce > 0 && sayi_kiralama_suresi > 0){
                        dispose();
                        van_kategori a = new van_kategori(butce,kiralama_suresi);
                        a.setVisible(true);
                        a.pack();
                        a.setLocationRelativeTo(null);
                        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Bilgilerinizi kontrol edip tekrar deneyiniz.");
                    }
                }
            }
        });
    }
}
