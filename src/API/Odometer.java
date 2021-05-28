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

    public Odometer( double inDistance, 
                     double finDistance ) 
    {
        initialDistance = inDistance;
        finalDistance = finDistance;
    }

    public void setInitialDistance( double setDistance )
    {
        initialDistance = setDistance;
    }

    public void setFinalDistance( double setDistance )
    {
        finalDistance = setDistance;
    }   
}
