/**
 * Program: RoverObserver.java
 * Purpose: Handling all the observers for the event of rover
 */

package Assignment2.Observer;
import Assignment2.State.StateException;

public abstract class RoverObserver
{
    public String runCommand(String command) throws StateException, ObserverException;
    public double getDistance();
}
