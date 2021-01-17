package NYP2;

import javax.swing.*;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args){

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                KullanıcıGirisi giris = new KullanıcıGirisi(); // kullanıcı giriş ekranı sınıfından üretiyoruz
                giris.setVisible(true); // ekranı görünür hale getiriyoruz
            }
        });



    }
}


