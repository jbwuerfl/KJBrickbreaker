import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class BreakerMain extends JPanel {

    public static final int FRAMEWIDTH = 1000, FRAMEHEIGHT = 700;
    private Timer timer;
    private boolean[] keys;

    private Sprite ball;

    private Sprite bouncer;






    public BreakerMain(){
        keys = new boolean[512];

        ball = new Ball(300,200,30);

        bouncer = new Bouncer(300,500);

        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ball.update();

                movebouncer();

                repaint();

            }
        });
        timer.start();







    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.fillRect(0, 0, 1000, 750);
        ball.draw(g2);
        bouncer.draw(g2);
    }
    public void movebouncer() {

        if (keys[KeyEvent.VK_UP]) {
            bouncer.setDir(Sprite.NORTH);
            bouncer.update();
            keys[KeyEvent.VK_UP] = false;
        }
        if (keys[KeyEvent.VK_LEFT]) {
            bouncer.setDir(Sprite.WEST);
            bouncer.update();
            keys[KeyEvent.VK_LEFT] = false;
        }
        if (keys[KeyEvent.VK_DOWN]) {
            bouncer.setDir(Sprite.SOUTH);
            bouncer.update();
            keys[KeyEvent.VK_DOWN] = false;
        }
        if (keys[KeyEvent.VK_RIGHT]) {
            bouncer.setDir(Sprite.EAST);
            bouncer.update();
            keys[KeyEvent.VK_RIGHT] = false;
        }
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


