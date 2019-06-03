public class Ball extends Sprite{

    public Ball(){
        super(500, 650, NORTH);
        setPic("ball.png", NORTH); //overrides the default "blank.png"
        setSpeed(25);
    }

    @Override
    public void update() {
        super.update();   //moves the Frog in the dir it's facing

        //Audio for hop.wav

    }



}
