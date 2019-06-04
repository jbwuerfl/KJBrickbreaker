public class Ball extends Sprite{

    public Ball(int x, int y, int direction){
        super(x, y, direction);
        setPic("ball.png", NORTH);
        setSpeed(25);
    }

    @Override
    public void update() {
        super.update();
        if (getLoc().x > 850)
            setDir(WEST);
        if (getLoc().x < 0)
            setDir(EAST);
    }
}

        //Audio for hop.wav



