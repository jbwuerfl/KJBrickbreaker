public class Bouncer extends Sprite {

    public Bouncer(int x, int y) {
        super(x, y, NORTH,15);
        setPic("bouncer.png", NORTH);
    }

    @Override
    public void update() {
        super.update();
        setVy(0);
        setVx(getSpeed());


    }

    public void updateleft() {
        super.update();
        setVy(0);
        setVx(-getSpeed());

    }

}