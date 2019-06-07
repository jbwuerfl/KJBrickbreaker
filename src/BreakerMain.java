import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.security.Key;

public class BreakerMain extends JPanel {

    public static final int FRAMEWIDTH = 1200, FRAMEHEIGHT = 700;
    private Timer timer;
    private boolean[] keys;
    private boolean startscreen;
    private Heart heart1, heart2, heart3;
    private int lives = 3;
    private boolean endgame;

    private Sprite ball;

    private Bouncer bouncer;

    private ArrayList<Sprite>brick;

    public BreakerMain(){
        brick = new ArrayList();
        keys = new boolean[512];

        startscreen = true;
        endgame = false;

        ball = new Ball((int)(Math.random() * 1200),200,20);

        bouncer = new Bouncer(600,600);

        brick.add(new Brick(0,0));
        brick.add(new Brick(75,0));
        brick.add(new Brick(150,0));
        brick.add(new Brick(225,0));
        brick.add(new Brick(300,0));
        brick.add(new Brick(375,0));
        brick.add(new Brick(450,0));
        brick.add(new Brick(525,0));
        brick.add(new Brick(600,0));
        brick.add(new Brick(675,0));
        brick.add(new Brick(750,0));
        brick.add(new Brick(825,0));
        brick.add(new Brick(900,0));
        brick.add(new Brick(975,0));
        brick.add(new Brick(1050,0));
        brick.add(new Brick(1125,0));
        brick.add(new Brick(1200,0));

        brick.add(new Brick(0,33));
        brick.add(new Brick(75,33));
        brick.add(new Brick(150,33));
        brick.add(new Brick(225,33));
        brick.add(new Brick(300,33));
        brick.add(new Brick(375,33));
        brick.add(new Brick(450,33));
        brick.add(new Brick(525,33));
        brick.add(new Brick(600,33));
        brick.add(new Brick(675,33));
        brick.add(new Brick(750,33));
        brick.add(new Brick(825,33));
        brick.add(new Brick(900,33));
        brick.add(new Brick(975,33));
        brick.add(new Brick(1050,33));
        brick.add(new Brick(1125,33));
        brick.add(new Brick(1200,33));

        brick.add(new Brick(0,66));
        brick.add(new Brick(75,66));
        brick.add(new Brick(150,66));
        brick.add(new Brick(225,66));
        brick.add(new Brick(300,66));
        brick.add(new Brick(375,66));
        brick.add(new Brick(450,66));
        brick.add(new Brick(525,66));
        brick.add(new Brick(600,66));
        brick.add(new Brick(675,66));
        brick.add(new Brick(750,66));
        brick.add(new Brick(825,66));
        brick.add(new Brick(900,66));
        brick.add(new Brick(975,66));
        brick.add(new Brick(1050,66));
        brick.add(new Brick(1125,66));
        brick.add(new Brick(1200,66));

        brick.add(new Brick(0,99));
        brick.add(new Brick(75,99));
        brick.add(new Brick(150,99));
        brick.add(new Brick(225,99));
        brick.add(new Brick(300,99));
        brick.add(new Brick(375,99));
        brick.add(new Brick(450,99));
        brick.add(new Brick(525,99));
        brick.add(new Brick(600,99));
        brick.add(new Brick(675,99));
        brick.add(new Brick(750,99));
        brick.add(new Brick(825,99));
        brick.add(new Brick(900,99));
        brick.add(new Brick(975,99));
        brick.add(new Brick(1050,99));
        brick.add(new Brick(1125,99));
        brick.add(new Brick(1200,99));

        heart1 = new Heart(10, 670);
        heart2 = new Heart(30, 670);
        heart3 = new Heart(50, 670);




        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ball.update();

                movebouncer();

                for (int i = 0; i < brick.size(); i++) {
                    Brick b = (Brick)(brick.get(i));
                    b.update();
                    if (collides(b,ball)) {
                        ball.setVy(-ball.getVy());

                        brick.remove(i);

                    }

                }




                if (collides(ball,bouncer))
                    ball.setVy(-(ball.getVy()));
////                    ball.setVy(-(ball.getVy()));
//                for(Sprite i: brick){
//
//                    if (i.intersects(ball)) {
//                        brick.remove(i);
//                        ball.setVy(-ball.getVy());
//
//                    }
//                }



                if (bouncer.getLoc().x < -75)
                    bouncer.setLoc(new Point(1200,625));

                if (bouncer.getLoc().x > 1200)
                    bouncer.setLoc(new Point(-75,625));

                if (lives == 0) {
                    endgame = true;
                    startscreen = false;
                }
                if (ball.getLoc().y > 700) {
                    startscreen = true;
                    lives--;
                }


                repaint();

            }
        });
        timer.start();


        setKeyListener();







    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.fillRect(0, 0, 1200, 750);

        if (startscreen){
            timer.stop();
            g2.setFont(new Font("Helvetica", Font.BOLD, 80));
            g2.setColor(Color.green);
            g2.drawString("Click S To Start", 200,350);

        }

        if (endgame){
            timer.stop();
            g2.setFont(new Font("Helvetica", Font.BOLD, 80));
            g2.setColor(Color.pink);
            g2.drawString("Click R To Start", 200,350);

        }



        ball.draw(g2);
        bouncer.draw(g2);
        for(Sprite b: brick){
            b.draw(g2);
        }

        if(lives > 0){
            heart1.draw(g2);
        }
        if (lives > 1){
            heart2.draw(g2);
        }
        if (lives > 2){
            heart3.draw(g2);
        }



    }
    public void movebouncer() {


        if (keys[KeyEvent.VK_LEFT]) {
            bouncer.setDir(Sprite.NORTH);
            bouncer.updateleft();
//            keys[KeyEvent.VK_A] = false;
        }
        if (keys[KeyEvent.VK_RIGHT]) {
            bouncer.setDir(Sprite.NORTH);
            bouncer.update();
//            keys[KeyEvent.VK_D] = false;
        }

    }

    private boolean collides(Sprite c1, Sprite r1) {
        float closestX = clamp(c1.getLoc().x, r1.getLoc().x, r1.getLoc().x + r1.getBoundingRectangle().width);
        float closestY = clamp(c1.getLoc().y, r1.getLoc().y - r1.getBoundingRectangle().height, r1.getLoc().y);

        float distanceX = c1.getLoc().x - closestX;
        float distanceY = c1.getLoc().y - closestY;

        return Math.pow(distanceX, 2) + Math.pow(distanceY, 2) < Math.pow(c1.getBoundingRectangle().height, 2);
    }

    public float clamp(float value, float min, float max) {
        float x = value;
        if (x < min) {
            x = min;
        } else if (x > max) {
            x = max;
        }
        return x;
    }


    public void setKeyListener(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {


            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] = true;
//                if (keyEvent.getKeyCode() == KeyEvent.VK_D) {
//                    bouncer.update();
//                    repaint();
//                }
                if (keyEvent.getKeyCode() == KeyEvent.VK_S) {

                    if (startscreen) {
                        ball.setLoc(new Point((int) (Math.random() * 1200), 200));
                        bouncer.setLoc(new Point(500, 625));
                    }
                    startscreen = false;
                    timer.start();
                }

                if (keys[KeyEvent.VK_R]) {

                    if (endgame) {
                        ball.setLoc(new Point((int) (Math.random() * 1200), 200));
                        bouncer.setLoc(new Point(500, 625));
                        lives = 3;
                    }
                    endgame = false;
                    timer.start();
                }


                repaint();


            }


            @Override
            public void keyReleased(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] = false;

            }
        });

    }




    public static void main(String[] args) {
        JFrame window = new JFrame("Brick Breaker!");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setBounds(0, 0, FRAMEWIDTH, FRAMEHEIGHT + 22); //(x, y, w, h) 22 due to title bar.

        BreakerMain panel = new BreakerMain();
        panel.setSize(FRAMEWIDTH, FRAMEHEIGHT);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}


