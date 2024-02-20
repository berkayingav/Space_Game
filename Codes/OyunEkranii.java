
import java.awt.HeadlessException;
import javax.swing.JFrame;


public class OyunEkranii extends JFrame {

    public OyunEkranii(String title) throws HeadlessException {
        super(title);
    }
    
    
    
    public static void main(String[] args) {
        
        OyunEkranii ekran = new OyunEkranii("Uzay Oyunu");
        
        ekran.setResizable(false);
        ekran.setFocusable(false);
        
        ekran.setSize(800,600);  //Full Screen 
        
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//  Pencere kapatıldığında uygulamada kapanır
        
        Oyun oyun = new Oyun();
        
        oyun.requestFocus();   // Klavye tuşlarını anlaması için
        
        oyun.addKeyListener(oyun);
        
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false);
        
        ekran.add(oyun);
        
        ekran.setVisible(true);
        
        
    }
}

// JFrame Pencere(boyutları vb) , JPanel Pencerenin içi
