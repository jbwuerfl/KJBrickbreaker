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

    private Sprite ball;

    private Bouncer bouncer;

    private ArrayList<Sprite>brick;

    public BreakerMain(){
        brick = new ArrayList();
        keys = new boolean[512];

        startscreen = true;

        ball = new Ball(300,200,20);

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




        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ball.update();

                movebouncer();

                if (ball.intersects(bouncer))
                    ball.setVy(-(ball.getVy()));
                for(int i = brick.size()-1; i > 0; i--){
                    brick.get(i).update();
                    if (brick.get(i).getBoundingRectangle().intersects(ball.getBoundingRectangle())) {
                        brick.remove(i);
                        ball.setVy(-ball.getVy());

                    }
                }

//                movebouncer();


                if (bouncer.getLoc().x < -75)
                    bouncer.setLoc(new Point(1200,625));

                if (bouncer.getLoc().x > 1200)
                    bouncer.setLoc(new Point(-75,625));

                if (ball.getLoc().y > 700)
                    startscreen = true;

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
        ball.draw(g2);
        bouncer.draw(g2);
        for(Sprite b: brick){
            b.draw(g2);
        }
    }
    public void movebouncer() {


        if (keys[KeyEvent.VK_A]) {
            bouncer.setDir(Sprite.NORTH);
            bouncer.updateleft();
//            keys[KeyEvent.VK_A] = false;
        }
        if (keys[KeyEvent.VK_D]) {
            bouncer.setDir(Sprite.NORTH);
            bouncer.update();
//            keys[KeyEvent.VK_D] = false;
        }

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
                        ball.setLoc(new Point(300, 200));
                        bouncer.setLoc(new Point(500,625));
                    }
                    startscreen = false;
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


