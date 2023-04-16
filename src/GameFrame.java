import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame {
    private JPanel gamePanel;
    private int[] crosshairLocation = {25,25};
    private Component crosshair;

    public GameFrame() {
        setTitle("My Game Frame");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println(getBackground());

        gamePanel = new GamePanel();
        add(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus(true);

        crosshair = gamePanel.getComponent(24 * 50 + 24);
        crosshair.setBackground(Color.BLACK);

        setVisible(true);

    }

    public static void main(String[] args) {
        GameFrame x = new GameFrame();
    }

    class GamePanel extends JPanel implements KeyListener {
        public GamePanel() {
            setVisible(true);
            addKeyListener(this);

            setLayout(new GridLayout(50,50));
            for (int i = 0; i < 50 * 50; i++) {
                JPanel temp = new JPanel();
                add(temp);
            }
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
                //Removes the current crosshair
                crosshair.setBackground(new Color(238, 238, 238));
                //Gets the new crosshair location
                crosshairLocation[1] = crosshairLocation[1] - 1;
                //Sets the new crosshair
                crosshair = gamePanel.getComponent((crosshairLocation[0] - 1) * 50 + (crosshairLocation[1] - 1));
                //Paints the new crosshair
                crosshair.setBackground(Color.BLACK);
                repaint();
                System.out.println("A");
            }
        }

        private void userPressedD() {
            //If it is possible to move the crosshair left(the index stays between [0,25]), moves the crosshair to left
            if (crosshairLocation[1] + 1 < 51) {
                //Removes the current crosshair
                crosshair.setBackground(new Color(238, 238, 238));
                //Gets the new crosshair location
                crosshairLocation[1] = crosshairLocation[1] + 1;
                //Sets the new crosshair
                crosshair = gamePanel.getComponent((crosshairLocation[0] - 1) * 50 + (crosshairLocation[1] - 1));
                //Paints the new crosshair
                crosshair.setBackground(Color.BLACK);
                repaint();
                System.out.println("D");
            }
        }

        private void userPressedS() {
            //If it is possible to move the crosshair left(the index stays between [0,25]), moves the crosshair to left
            if (crosshairLocation[0] + 1 < 51) {
                //Removes the current crosshair
                crosshair.setBackground(new Color(238, 238, 238));
                //Gets the new crosshair location
                crosshairLocation[0] = crosshairLocation[0] + 1;
                //Sets the new crosshair
                crosshair = gamePanel.getComponent((crosshairLocation[0] - 1) * 50 + (crosshairLocation[1] - 1));
                //Paints the new crosshair
                crosshair.setBackground(Color.BLACK);
                repaint();
                System.out.println("S");
            }
        }

        private void userPressedW() {
            //If it is possible to move the crosshair left(the index stays between [0,25]), moves the crosshair to left
            if (crosshairLocation[0] - 1 > 0) {
                //Removes the current crosshair
                crosshair.setBackground(new Color(238, 238, 238));
                //Gets the new crosshair location
                crosshairLocation[0] = crosshairLocation[0] - 1;
                //Sets the new crosshair
                crosshair = gamePanel.getComponent((crosshairLocation[0] - 1) * 50 + (crosshairLocation[1] - 1));
                //Paints the new crosshair
                crosshair.setBackground(Color.BLACK);
                repaint();
                System.out.println("W");
            }
        }
    }
}