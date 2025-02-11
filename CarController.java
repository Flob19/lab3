import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;




/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:
    /*private WindowSettings windowSettings = new WindowSettings();
    static private int carWidth = 100;
    static private int carHeight = 60;
    static private int controllerHeight = 200;
    static private int distanceConstantY = 100 + carHeight;*/
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();
    ArrayList<AutoRepairShop<Volvo240>> auto = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());
        cc.auto.add(new AutoRepairShop<Volvo240>(0,400,10,"Helmia"));

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                car.move();
                int x = (int) Math.round(car.getXCoordinate());
                int y = (int) Math.round(car.getYCoordinate());
                if(y > 500){
                    car.setYCoordinate(500);
                    car.stopEngine();
                    car.turnRight();
                    car.turnRight();
                    car.startEngine();
                } else if (y < 0) {
                    car.setYCoordinate(0);
                    car.stopEngine();
                    car.turnRight();
                    car.turnRight();
                    car.startEngine();
                }

                frame.drawPanel.moveit(car.getModell(),x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double fixed_amount = ((double) amount) / 100;
        for (Car car : cars
                ) {
            car.gas(fixed_amount);
        }
    }
    void brake(int amount) {
        double fixed_amount = ((double) amount) / 100;
        for (Car car : cars
        ) {
            car.brake(fixed_amount);
        }
    }
    void startEngine() {
        for (Car car : cars
        ) {
            car.startEngine();
        }
    }
    void stopEngine() {
        for (Car car : cars
        ) {
            car.stopEngine();
        }
    }
    void turboOff() {
        for (Car car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff(); // Cast to Saab95 before calling setTurboOff()
            }
        }
    }
    void turboOn() {
        for (Car car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn(); // Cast to Saab95 before calling setTurboOff()
            }
        }
    }


}
