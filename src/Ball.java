public class Ball extends Sprite{

    public Ball(int x, int y, int direction){
        super(x, y, direction);
        setPic("ball.png", NORTH);
        setSpeed(15);
    }

    @Override
    public void update() {
        super.update();
        if (getLoc().x > 900)
            setDir(180-getDir());
        if (getLoc().x < 0)
            setDir(getDir()+90);
        if (getLoc().y > 600)
            setDir(getDir()+90);
        if (getLoc().y < 0)
            setDir(getDir()+90);
    }

}

        //Audio for hop.wav



