import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BreakerMain extends JPanel {

    public static final int FRAMEWIDTH = 1200, FRAMEHEIGHT = 700;
    private Timer timer;
    private boolean[] keys;

    private Sprite ball;

    private Sprite bouncer;

    private ArrayList<Sprite>brick;






    public BreakerMain(){
        brick = new ArrayList();
        keys = new boolean[512];

        ball = new Ball(300,200,30);

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

        brick.add(new Brick(0,20));
        brick.add(new Brick(75,20));
        brick.add(new Brick(150,20));
        brick.add(new Brick(225,20));
        brick.add(new Brick(300,20));
        brick.add(new Brick(375,20));
        brick.add(new Brick(450,20));
        brick.add(new Brick(525,20));
        brick.add(new Brick(600,20));
        brick.add(new Brick(675,20));
        brick.add(new Brick(750,20));
        brick.add(new Brick(825,20));
        brick.add(new Brick(900,20));
        brick.add(new Brick(975,20));
        brick.add(new Brick(1050,20));
        brick.add(new Brick(1125,20));
        brick.add(new Brick(1200,20));




        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ball.update();

//                movebouncer();

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
        ball.draw(g2);
        bouncer.draw(g2);
        for(Sprite b: brick){
            b.draw(g2);
        }
    }
//    public void movebouncer() {
//
//        if (keys[KeyEvent.VK_UP]) {
//            bouncer.setDir(Sprite.NORTH);
//            bouncer.update();
//            keys[KeyEvent.VK_UP] = false;
//        }
//        if (keys[KeyEvent.VK_LEFT]) {
//            bouncer.setDir(Sprite.WEST);
//            bouncer.update();
//            keys[KeyEvent.VK_LEFT] = false;
//        }
//        if (keys[KeyEvent.VK_DOWN]) {
//            bouncer.setDir(Sprite.SOUTH);
//            bouncer.update();
//            keys[KeyEvent.VK_DOWN] = false;
//        }
//        if (keys[KeyEvent.VK_RIGHT]) {
//            bouncer.setDir(Sprite.EAST);
//            bouncer.update();
//            keys[KeyEvent.VK_RIGHT] = false;
//        }
//    }

    public void setKeyListener(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {


            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_D) {
                    bouncer.update();
                    repaint();
                }


            }

            @Override
            public void keyReleased(KeyEvent e) {

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


