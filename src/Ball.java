public class Ball extends Sprite{

    public Ball(int x, int y, int direction){
        super(x, y, direction, 10);
        setPic("ball.png", NORTH);
    }

    @Override
    public void update() {
        super.update();
        if(getLoc().x > 980 || getLoc().x < 0){
            setVx(-getVx());
        }

        if(getLoc().y > 680 || getLoc().y < 0){
            setVy(-getVy());
        }

//        if (getLoc().x > 900) {
//            System.out.println(getDir());
//            //setDir(180-getDir());
//            if (getDir() > 180)
//                setDir(180 - getDir());
//            else
//                setDir(360-getDir());
//        }
//        if (getLoc().x < 0) {
//            //setDir(180-getDir());
//            if (getDir() > 180)
//                setDir(180 - getDir());
//            else
//                setDir(360-getDir());
//        }
//        if (getLoc().y > 600) {
//            //setDir(180-getDir());
//            if (getDir() > 180)
//                setDir(180 - getDir());
//            else
//                setDir(360-getDir());
//        }
//        if (getLoc().y < 0){
//            if (getDir() > 180)
//                setDir(180-getDir());
//            else
//                setDir(360-getDir());
//        }


    }

}

        //Audio for hop.wav



