import java.awt.*;

public class Saab95 extends Car {

    private boolean turboOn;

    public Saab95() {
        super(0,100,2, 125, Color.red, "Saab95");
        turboOn = false;
    }

    public void setTurboOn() {
        turboOn = true;
    }

    public void setTurboOff() {
        turboOn = false;
    }

    public boolean getTurbo(){return turboOn;}
    @Override
    public double speedFactor() {
        double turbo = 1;
        if (getTurbo()) turbo = 3;
        return getEnginePower() * 0.01 * turbo;
    }

}
