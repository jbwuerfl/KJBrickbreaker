public class Bouncer extends Sprite {

    public Bouncer(int x, int y) {
        super(x, y, NORTH,12);
        setPic("bouncer.png", NORTH);
    }

    @Override
    public void update() {

        setVy(0);
        setVx(getSpeed());

        super.update();
    }

    public void updateleft() {
        setVy(0);
        setVx(-getSpeed());
        super.update();

    }

    public void doubleleft() {
        setVy(0);
        setVx(-getSpeed());
        super.update();

    }

}