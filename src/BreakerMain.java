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

    private Sprite lost;

    private Sprite ball;

    private Bouncer bouncer;

    private ArrayList<Sprite>brick;

    public BreakerMain(){
        brick = new ArrayList();
        keys = new boolean[512];

        startscreen = true;
        endgame = false;

        lost = new Loser();

        ball = new Ball((int)(Math.random() * 1200),200,20);

        bouncer = new Bouncer(600,600);

        int brickX = 0, brickY = 0, brickW = 75, brickH = 30;
        for(int r = 0; r < 4; r++) {
            brickX = 0;
            for (int c = 0; c < 16; c++) {
                brick.add(new Brick(brickX, brickY));
                brickX += brickW;
            }
            brickY += brickH + 3;
        }


        heart1 = new Heart(10, 670);
        heart2 = new Heart(30, 670);
        heart3 = new Heart(50, 670);




        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ball.update();

                movebouncer();
                boolean bounceVert = false;
                boolean bounceHorz = false;

                for (int i = 0; i < brick.size(); i++) {
                    Brick b = (Brick)(brick.get(i));
                    b.update();

                    if (collides(ball, b)) {
                        if (ball.getCenterPoint().y - (clamp(ball.getCenterPoint().y, b.getLoc().y, b.getLoc().y + b.getBoundingRectangle().height)) > 0 || ball.getCenterPoint().y - (clamp(ball.getCenterPoint().y, b.getLoc().y, b.getLoc().y + b.getBoundingRectangle().height)) < 0) {
                            bounceVert = true;

                        }
                        else if (ball.getCenterPoint().x - (clamp(ball.getCenterPoint().x, b.getLoc().x, b.getLoc().x + b.getBoundingRectangle().width)) > 0 || ball.getCenterPoint().x - (clamp(ball.getCenterPoint().x, b.getLoc().x, b.getLoc().x + b.getBoundingRectangle().width)) < 0) {
                             bounceHorz = true;

                        }
                        brick.remove(i);
                        i--;
                    }

                }
                if(bounceVert)
                    ball.setVy(-ball.getVy());
                if (bounceHorz)
                    ball.setVx(-ball.getVx());




                if (collides(ball, bouncer)) {
                    if (ball.getCenterPoint().y - (clamp(ball.getCenterPoint().y, bouncer.getLoc().y, bouncer.getLoc().y + bouncer.getBoundingRectangle().height)) > 0 || ball.getCenterPoint().y - (clamp(ball.getCenterPoint().y, bouncer.getLoc().y, bouncer.getLoc().y + bouncer.getBoundingRectangle().height)) < 0) {
                        ball.setVy(-ball.getVy());
                        if ((ball.getVx() > 0 && bouncer.getVx() < 0) || (ball.getVx() < 0 && bouncer.getVx() > 0))
                            ball.setVx(-ball.getVx());
                        else if (bouncer.getVx() == 0)
                            ball.setVx(ball.getVx());
                        else {
                            ball.setVx((int) (Math.random() * 15));
                        }

                    }
                    else if (ball.getCenterPoint().x - (clamp(ball.getCenterPoint().x, bouncer.getLoc().x, bouncer.getLoc().x + bouncer.getBoundingRectangle().width)) > 0 || ball.getCenterPoint().x - (clamp(ball.getCenterPoint().x, bouncer.getLoc().x, bouncer.getLoc().x + bouncer.getBoundingRectangle().width)) < 0) {
                        ball.setVx(-ball.getVx());

                    }
                }
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
                    startscreen = false;
                    endgame = true;

                }
                if (ball.getLoc().y > 700) {
                    if (endgame) {
                        startscreen = false;
                    }
                    else if (!endgame){
                        startscreen = true;
                    }

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
            g2.drawString("Click SPACE To Start", 200,350);

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
        if (endgame){
            timer.stop();
            lost.draw(g2);
            g2.setFont(new Font("Helvetica", Font.BOLD, 80));
            g2.setColor(Color.pink);
            g2.drawString("Click R To Restart", 200,350);


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
//    private boolean collides(Circle c1, Rectangle r1) {
//        float closestX = Calc.clamp(c1.getX(), r1.x, r1.x + r1.width);
//        float closestY = Calc.clamp(c1.getY(), r1.y - r1.height, r1.y);
//
//        float distanceX = c1.getX() - closestX;
//        float distanceY = c1.getY() - closestY;
//
//        return Math.pow(distanceX, 2) + Math.pow(distanceY, 2) < Math.pow(c1.getRadius(), 2);
//    }
//
//    public static float clamp(float value, float min, float max) {
//        float x = value;
//        if (x < min) {
//            x = min;
//        } else if (x > max) {
//            x = max;
//        }
//        return x;
//    }

    private boolean collides(Sprite c1, Sprite r1) {

//        return c1.intersects(r1);

        float closestX = clamp(c1.getCenterPoint().x, r1.getLoc().x, r1.getLoc().x + r1.getBoundingRectangle().width);
        float closestY = clamp(c1.getCenterPoint().y, r1.getLoc().y, r1.getLoc().y + r1.getBoundingRectangle().height);

        float distanceX = c1.getCenterPoint().x - closestX;
        float distanceY = c1.getCenterPoint().y - closestY;

        return Math.pow(distanceX, 2) + Math.pow(distanceY, 2) < Math.pow(c1.getBoundingRectangle().height/2, 2);
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
                if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE) {

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
                        int brickX = 0, brickY = 0, brickW = 75, brickH = 30;
                        for(int r = 0; r < 4; r++) {
                            brickX = 0;
                            for (int c = 0; c < 16; c++) {
                                brick.add(new Brick(brickX, brickY));
                                brickX += brickW;
                            }
                            brickY += brickH + 3;
                        }
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


