import javax.swing.*;

public class BreakerMain extends JPanel {

    public static final int FRAMEWIDTH = 1000, FRAMEHEIGHT = 700;
    private Timer timer;




    public BreakerMain(){



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


