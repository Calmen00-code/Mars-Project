/**
 * Program: Odometer.java
 * Purpose: Keep records of the total distance traveled
 *          by the rover at every round
 */

package Assignment2.API;

public class Odometer
{
    private double initialDistance;
    private double finalDistance;

    public Odometer() 
    { 
        initialDistance = 0;
        finalDistance = 0;
    }

    public void setInitialDistance( double setDistance )
    {
        initialDistance = setDistance;
    }

    public void setFinalDistance( double setDistance )
    {
        finalDistance = setDistance;
    }

    public double getInitialDistance() { return initialDistance; }
    public double getFinalDistance() { return finalDistance; }
}
