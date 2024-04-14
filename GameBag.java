
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class GameBag extends JFrame implements Runnable {

    public void editar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(976, 684);
        setResizable(false);
        setLocationRelativeTo(null);
        // add(new Intro());

        setVisible(true);
    }
    
    public void run() {
        editar();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GameBag());
    }
}
