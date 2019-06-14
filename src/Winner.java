public class Winner extends Sprite {

    public Winner(){
        super(100,0, NORTH, 0);
        setPic("IMG_4345.jpg", NORTH); //overrides the default "blank.png"
        setSpeed(0);
    }

    @Override
    public void update() {
        super.update();   //moves the Frog in the dir it's facing

        //Audio for hop.wav

    }


}