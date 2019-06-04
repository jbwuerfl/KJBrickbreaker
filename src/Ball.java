public class Ball extends Sprite{

    public Ball(int x, int y, int direction){
        super(x, y, direction);
        setPic("ball.png", NORTH);
        setSpeed(15);
    }

    @Override
    public void update() {
        super.update();
        if (getLoc().x > 900) {
            //setDir(180-getDir());
            if (getDir() > 180)
                setDir(180 - getDir());
            else
                setDir(getDir() + 270);
        }
        if (getLoc().x < 0) {
            //setDir(180-getDir());
            if (getDir() > 180)
                setDir(180 - getDir());
            else
                setDir(getDir() + 270);
        }
        if (getLoc().y > 600) {
            //setDir(180-getDir());
            if (getDir() > 180)
                setDir(180 - getDir());
            else
                setDir(getDir() + 270);
        }
        if (getLoc().y < 0){
            if (getDir() > 180)
                setDir(180-getDir());
            else
                setDir(getDir() + 270);
        }

    }

}

        //Audio for hop.wav



