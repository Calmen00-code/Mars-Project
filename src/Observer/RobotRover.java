/**
 * Program: RobotRover.java
 * Purpose: Act as a controller/caller to all the observers
 * Implements: RobotRoverSubject.java
 */

package Assignment2.src.Observer;

import java.util.*;

public class RobotRover implements RobotRoverSubject
{
    private Set<Observer> observers;
    private String eventMsg;

    public RobotRover( HashSet<Observer> inObservers )
    { 
        observers = inObservers;
        eventMsg = "";
    }

    @Override
    public void addObserver( Observer o ) { observers.add(o); }

    @Override
    public void removeObserver( Observer o ) { observers.remove(o); }

    @Override
    public void roverUpdate( String command )
    {
        // Appending every event message returned by each observers
        for ( Observer ob : observers )
            eventMsg += ob.checkCommand( command ) + "\n";
    }

    public String getEventMsg() { return eventMsg; }
}
