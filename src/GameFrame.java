import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

        //Creates the player
        crosshair = gamePanel.getComponent(24 * 50 + 24);
        crosshair.setBackground(Color.BLACK);

        addEntity(1);
        addEntity(1);
        addEntity(1);
        addEntity(0);
        addEntity(0);

        Thread deneme = new Thread(new MoveEntities());
        deneme.start();
        setVisible(true);

    }

    public static void main(String[] args) {
        GameFrame x = new GameFrame();
    }

    class MoveEntities implements Runnable {
        public void run() {
            while(true) {

                //An int array to store the locations of the red/green squares
                ArrayList<Integer> locations = new ArrayList<>(0);

                Component tempComponent = gamePanel.getComponent(0);
                for(int i = 0; i < 2499; i++) {
                    tempComponent = gamePanel.getComponent(i);

                    if (tempComponent.getBackground() == Color.GREEN) {
                        //Stores the location of the green color in locations array
                        locations.add(i);
                    }
                }

                for (int x : locations) {
                    gamePanel.getComponent(x).setBackground(Color.WHITE);
                    gamePanel.getComponent(x + 1).setBackground(Color.GREEN);
                    gamePanel.repaint();
                }

                System.out.println("sdglksşdlgksşdkgşlsdgklş");
                try{
                    Thread.sleep(10000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }
    }

    class GamePanel extends JPanel implements KeyListener {
        public GamePanel() {
            setVisible(true);
            addKeyListener(this);

            setLayout(new GridLayout(50,50));
            for (int i = 0; i < 50 * 50; i++) {
                JPanel temp = new JPanel();
                temp.setBackground(Color.WHITE);
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


    //If i==0: adds friendly to a random location on the map and if i !=0 adds an enemy to the same randomly selected locaion
    private void addEntity(int i) {
        //Get random x,y locations to add the friendly to
        Random rand = new Random();
        int tempRow = rand.nextInt(50);
        int tempCol = rand.nextInt(50);

        //In case the random location is the same with crosshair(player) or other enemies/friendlies
        //If an entity is already occupying the randomly selected position, selects a new position
        while (gamePanel.getComponent(tempRow * 49 + tempCol).getBackground() != Color.WHITE) {
            tempRow = rand.nextInt(50);
            tempCol = rand.nextInt(50);
        }

        Component friendlyTemp = gamePanel.getComponent(tempRow * 50 + tempCol);

        if (i == 0) {
            friendlyTemp.setBackground(Color.GREEN);
        } else {
            friendlyTemp.setBackground(Color.RED);
        }

        friendlyTemp.setVisible(true);
    }
}