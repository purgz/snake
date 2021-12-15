import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Second extends JPanel implements ActionListener, KeyListener{
    Timer t = new Timer(10,this);
    int velx = 20, vely = 0;
    int blockSize = 20;
    LinkedList<Integer> snakex = new LinkedList<Integer>(Arrays.asList(100,80,60,40));
    LinkedList<Integer> snakey = new LinkedList<Integer>(Arrays.asList(0,0,0,0));
    int foodx = 380;
    int foody = 380;

    public Second(){
        t.start(); 
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setPreferredSize(new Dimension(800,800));
    }
        
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < snakex.size(); ++i){
            g2.setColor(new Color(100,100,100));
            g2.fill(new Rectangle2D.Double(snakex.get(i),snakey.get(i),blockSize,blockSize));
        }
        g2.setColor(new Color(200,200,200));
        g2.fill(new Rectangle2D.Double(foodx,foody,blockSize,blockSize));
    }

    public void actionPerformed(ActionEvent e){
        repaint();
        int newx = snakex.get(0) + velx;
        int newy = snakey.get(0) + vely;
        snakex.addFirst(newx);
        snakey.addFirst(newy);
        snakex.removeLast();
        snakey.removeLast();
        collision();
        edge();

    }
    //movement methods
    public void up(){
        if (vely == 0){
            vely = -blockSize;
            velx = 0;
        }
    }
    public void down(){
        if (vely == 0){
            vely = blockSize;
            velx = 0;
        }
    }
    public void right(){
        if (velx == 0){
            vely = 0;
            this.velx = blockSize;
        }
    }
    public void left(){
        if (velx == 0){
            vely = 0;
            velx = -blockSize;
        }
    }

   

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        System.out.println(code);
        switch (code){
            case 38:
                up();
                break;
            case 40:
                down();
                break;
            case 39:
                right();
                break;
            case 37:
                left();
                break;
            default:
                break;
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public void generateFood(){
        Random random = new Random();
        int rand1 = random.nextInt(40) * 20;
        int rand2 = random.nextInt(40) * 20;
        foodx = rand1;
        foody = rand2;
    }

    public void collision(){
        if (snakex.get(0) == foodx && snakey.get(0) == foody){
            System.out.println("Collision");
            generateFood();
            int newx = snakex.get(0) + velx;
            int newy = snakey.get(0) + vely;
            snakex.addFirst(newx);
            snakey.addFirst(newy);
        }

        for (int i = 1; i < snakex.size();++i){
            int headx = snakex.get(0);
            int heady = snakey.get(0);
            if (headx == snakex.get(i) && heady == snakey.get(i)){
                t.stop();
                velx = 0;
                vely = 0;
            }
        }
    }
    public void edge(){
        if (snakex.get(0) > 780){
            snakex.set(0,0);
        } else if (snakex.get(0) < 0){
            snakex.set(0,780);
        }
        if (snakey.get(0) < 0){
            snakey.set(0,780);
        } else if (snakey.get(0) > 780){
            snakey.set(0,0);
        }
    }
}
