import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame {
    private JPanel gamePanel;
    private int[] crosshairLocation = {25,25};

    public GameFrame() {
        gamePanel = new GamePanel();
        setTitle("My Game Frame");
        setSize(500,500);
        add(gamePanel);
        setVisible(true);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        GameFrame x = new GameFrame();
    }

    class GamePanel extends JPanel implements KeyListener {

        public GamePanel() {
            setVisible(true);
            addKeyListener(this);
        }

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                userPressedA();
            }else if (e.getKeyCode() == KeyEvent.VK_S) {
                userPressedS();
            }else if(e.getKeyCode() == KeyEvent.VK_D) {
                userPressedD();
            }else if (e.getKeyCode() == KeyEvent.VK_W) {
                userPressedW();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}

        private void userPressedA() {
            //If it is possible to move the crosshair left(the index stays between [0,25]), moves the crosshair to left
            if (crosshairLocation[1] - 1 > 0) {
                crosshairLocation[1] = crosshairLocation[1] - 1;
                System.out.println("A");
            }
        }

        private void userPressedD() {
            //If it is possible to move the crosshair left(the index stays between [0,25]), moves the crosshair to left
            if (crosshairLocation[1] + 1 < 51) {
                crosshairLocation[1] = crosshairLocation[1] + 1;
                System.out.println("D");
            }
        }

        private void userPressedS() {
            //If it is possible to move the crosshair left(the index stays between [0,25]), moves the crosshair to left
            if (crosshairLocation[0] + 1 < 51) {
                crosshairLocation[0] = crosshairLocation[0] + 1;
                System.out.println("S");
            }
        }

        private void userPressedW() {
            //If it is possible to move the crosshair left(the index stays between [0,25]), moves the crosshair to left
            if (crosshairLocation[0] - 1 > 0) {
                crosshairLocation[0] = crosshairLocation[0] - 1;
                System.out.println("W");
            }
        }
    }
}