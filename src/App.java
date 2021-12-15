import javax.swing.JFrame;


public class App {
    public static void main(String[] args) throws Exception {
        JFrame f = new JFrame();
        Second s = new Second();

        
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(s);
        f.pack();
    }

}
