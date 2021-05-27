/**
 * Program: RobotRover.java
 * Purpose: Act as a controller/caller to all the observers
 * Implements: RobotRoverSubject.java
 */

package Assignment2.Observer;

import java.util.*;

public class RobotRover implements RobotRoverSubject
{
    private Set<RoverObserver> roverObservers;
    private String eventMsg;

    public RobotRover( HashSet<RoverObserver> inRoverObservers )
    { 
        roverObservers = inRoverObservers;
        eventMsg = "";
    }

    @Override
    public void addObserver( RoverObserver o ) { roverObservers.add(o); }

    @Override
    public void removeObserver( RoverObserver o ) { roverObservers.remove(o); }

    @Override
    public void roverUpdate( String command ) throws ObserverException
    {
        String tmpMsg = "";

        // Appending every event message returned by each observers
        for ( RoverObserver ob : roverObservers ) {
            tmpMsg = ob.runCommand( command );
            eventMsg += tmpMsg;

            // Found the correct opeartion when tmpMsg is no longer empty
            if ( !tmpMsg.equals("") )
                break;
        }
    }

    public String getEventMsg() { return eventMsg; }
}
