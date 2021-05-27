/**
 * Program: RoverObserver.java
 * Purpose: Handling all the observers for the event of rover
 */

package Assignment2.Observer;
import Assignment2.State.StateException;

public interface RoverObserver
{
    public String runCommand(String command) throws StateException, ObserverException;
}
