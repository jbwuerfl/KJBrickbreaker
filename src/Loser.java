public class Loser extends Sprite {

    public Loser(){
        super(0,-600, NORTH, 0);
        setPic("IMG_4474.PNG", NORTH); //overrides the default "blank.png"
        setSpeed(0);
    }

    @Override
    public void update() {
        super.update();   //moves the Frog in the dir it's facing

        //Audio for hop.wav

    }


}