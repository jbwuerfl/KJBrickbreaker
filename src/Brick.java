import java.awt.*;

public class Brick extends Sprite{

    private int red, blue, green;

    public Brick(int x, int y){
        super(x,y, NORTH, 0);
         red = (int)(Math.random() * 256);
         blue = (int)(Math.random() * 256);
         green = (int)(Math.random() * 256);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics2D g2) {

        g2.setColor(new Color(red, green, blue));
        g2.fillRect(getLoc().x, getLoc().y, 75, 30 );

    }
}
