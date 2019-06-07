public class Heart extends Sprite{

    public Heart(int x, int y){
        super(x, y, NORTH, 0);
        setPic("heart2.png", NORTH); //overrides the default "blank.png"
        setSpeed(0);
    }

    @Override
    public void update() {
        super.update();   //moves the Frog in the dir it's facing

        //Audio for hop.wav

    }
}