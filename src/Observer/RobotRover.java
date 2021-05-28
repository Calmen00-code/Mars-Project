/**
 * Program: RobotRover.java
 * Purpose: Act as a controller/caller to all the observers
 *          Observer will not care about the details of the operation
 *          It is just responsible to call every single observer for update
 *          
 * @implements RobotRoverSubject.java
 * @aggregate Each event class will perform what the rover should be doing
 */

package Assignment2.Observer;

import java.util.*;
import Assignment2.State.StateException;

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
    public RoverObserver roverUpdate( String command ) throws ObserverException
    {
        String tmpMsg = "";
        RoverObserver currentEvent = null;

        // Appending every event message returned by each observers
        for ( RoverObserver ob : roverObservers ) {
            try {
                tmpMsg = ob.runCommand( command );
            } catch ( StateException e ) {
                eventMsg = "";
                throw new ObserverException(e.getMessage());
            }

            eventMsg = tmpMsg;

            // Found the correct operation when tmpMsg is no longer empty
            if ( !tmpMsg.equals("") ) {
                currentEvent = ob;
                break;
            }
        }
        return currentEvent;
    }

    public String getEventMsg() { return eventMsg; }
}
