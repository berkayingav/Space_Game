
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import jdk.nashorn.internal.parser.TokenType;

//extends miras

class Ates{
    private int x;
    private int y;

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}

class Roket{
    private int x;
    private int y;

    public Roket(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}

class FireBall{
    private int x;
    private int y;

    public FireBall(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}




public class Oyun extends JPanel implements KeyListener,ActionListener{
    
    Timer timer = new Timer(5, this);
    private Timer fireballTimer;
    
    private int gecen_sure = 0;
    private int harcanan_roket = 0;
    private int harcanan_jet_roket = 0;
    
    private BufferedImage image;
    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage image3;
    private BufferedImage image4;
    private BufferedImage image5;
    
    
    private ArrayList<Ates> atesler = new ArrayList<Ates>();
    private ArrayList<Roket> roketler = new ArrayList<Roket>();
    private ArrayList<FireBall> fireball = new ArrayList<FireBall>();
    
    private int atesdirY = 7;
    private int roketdirY = 10;
    private int firedirY = 7;
    
    private int topX = 0;
    
    private int topdirX = 5;
    
    private int uzayGemisiX = 0;
    
    private int dirUzayX = 20;
    
    public boolean Hit(){
        
        for (Ates ates : atesler){
            
            if(new Rectangle(ates.getX(),ates.getY(),10,20).intersects(new Rectangle(topX,0,getWidth()/4,getHeight()/4))){
                
                return true;
                
            }
            
        }
            
        for (Roket roket : roketler){
            
            if(new Rectangle(roket.getX(),roket.getY(),10,20).intersects(new Rectangle(topX,0,getWidth()/4,getHeight()/4))){
                
                return true;
                
            }
            
            
        }
        
        return false;
    }
    
    public boolean Hit1(){
        
        
        for(FireBall fireball : fireball){
            
            if(new Rectangle(fireball.getX(),fireball.getY(),10,20).intersects(new Rectangle(uzayGemisiX,460,image.getWidth()/2,image.getHeight()/2))){
                
                return true;
                
            }
            
            
        }
        
        return false;     
            
        
    }

    public Oyun() {
        
         fireballTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ateş zamanlayıcısı için işlemleri burada gerçekleştirin
                fireball.add(new FireBall(topX,20));
        
            }
        });
        fireballTimer.start();
        
        
        try {
            image = ImageIO.read(new FileImageInputStream(new File("iha2.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            image1 = ImageIO.read(new FileImageInputStream(new File("ben10.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            image2 = ImageIO.read(new FileImageInputStream(new File("roket_1.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            image3 = ImageIO.read(new FileImageInputStream(new File("fast_rocket.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            image4 = ImageIO.read(new FileImageInputStream(new File("throw_fireball.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            image5 = ImageIO.read(new FileImageInputStream(new File("effect.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   
        setBackground(Color.BLACK);
        
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        gecen_sure +=5;
        
        g.drawImage(image, uzayGemisiX, 460,image.getWidth()/2,image.getHeight()/2, this);
        g.drawImage(image1, topX,0,image1.getWidth()/4,image1.getHeight()/4, this);
        
        for (Ates ates : atesler){
            
            if(ates.getY() < 0) {
                atesler.remove(ates);
            }
            
            
        }
        
        for(Ates ates : atesler){
            
            g.drawImage(image2, ates.getX(),ates.getY(),10,20,this);
            
        }
        
        for(Roket roket : roketler){
            g.drawImage(image3, roket.getX(), roket.getY(),10,20,this);
        }
        
        for(FireBall fireball : fireball){
            g.drawImage(image4,fireball.getX(),fireball.getY(),10,20,this);
        }
        
        for(FireBall fireballs : fireball){
            if (fireballs.getY() == 100){
                g.drawImage(image5,fireballs.getX(),fireballs.getY(),10,20,this);
            }
            fireballs.setY(fireballs.getY() + firedirY);
        }
        
        if(Hit()){
            timer.stop();
            String winmessage = "You are WİNNER !" + 
                                "\nRocket's number = " + harcanan_roket +
                                "\nFast Rocket's number = " + harcanan_jet_roket +
                                "\nTime = " + gecen_sure/1000;
            
            JOptionPane.showMessageDialog(this, winmessage);
            System.exit(0);
        }
        
        if(Hit1()){
            timer.stop();
            String losemessage = "You are LOSER!";
            JOptionPane.showMessageDialog(this, losemessage);
            System.exit(0);
        }
        
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public void keyTyped(KeyEvent e) {
        
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int c = e.getKeyCode();
        
        if(c == KeyEvent.VK_LEFT){
            
            if(uzayGemisiX <= 0){
                uzayGemisiX = 0;
            }
            
            else{
                uzayGemisiX -= dirUzayX;
            }
                   
        }
        
        if(c == KeyEvent.VK_RIGHT){
            
            if(uzayGemisiX >= 650){
                uzayGemisiX = 650;
            }
            
            else{
                uzayGemisiX += dirUzayX;
            }
            
        }
        
        if(c== KeyEvent.VK_Z){
            
            atesler.add(new Ates(uzayGemisiX+65,445));
            
            harcanan_roket++;
            
            
        }
        
        if(c == KeyEvent.VK_X){
            roketler.add(new Roket(uzayGemisiX+65,445));
            
            harcanan_jet_roket++;
        }
        
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        for(Ates ates : atesler){
            
            ates.setY(ates.getY() - atesdirY);
        }
        
        for(Roket roket : roketler){
            roket.setY(roket.getY() - roketdirY);
        }
        
        for(FireBall fireballs : fireball){
            fireballs.setY(fireballs.getY() + firedirY);
        }
        
        for(FireBall fireballs : fireball){
            if(fireballs.getY() == 100){
                   
            }
        }
        
        
        topX += topdirX;
        
        if(topX>=720){
            topdirX = -topdirX;
        }
        if(topX<=0) {
            topdirX = -topdirX;
        }
        
        
       repaint();
         
    }

    private void intersects(Rectangle rectangle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
