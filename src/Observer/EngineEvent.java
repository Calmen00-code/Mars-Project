/**
 * Program: EngineEvent.java
 * Purpose: Handles the event that involves in the engine of the robot
 * Implements: RoverObserver.java
 */

package Assignment2.Observer;

import Assignment2.API.EngineSystem;
import Assignment2.State.RoverContext;
import Assignment2.State.StateException;

public class EngineEvent implements RoverObserver
{
    private EngineSystem engine;
    private RoverContext rover;

    public EngineEvent( EngineSystem inEngine, RoverContext inRover ) 
    { 
        // Dependency Injection
        engine = inEngine; 
        rover = inRover;
    }

    @Override
    public String runCommand( String command ) throws ObserverException
    {
        String msg = "";
        double distance = 0.0, angle = 0.0;

        String[] parseCommand = command.split(" "); // Parse the command

        if ( parseCommand[0].equals("D") ) { // Checking for drive
            try {
                if ( parseCommand.length < 2 )
                    throw new ObserverException("! Invalid distance");

                // Second argument must be a valid distance
                distance = Double.parseDouble(parseCommand[1]);
                rover.startDrive();
                msg += "Drive for " + distance + "km\n";
            } catch (NumberFormatException e) {
                throw new ObserverException("! Invalid distance");
            } catch (StateException e) {
                msg += e.getMessage();
                throw new ObserverException(msg);
            }
        } else if ( parseCommand[0].equals("T") ) { // Checking for turn
            try {
                // Second argument must be a valid angle
                angle = Double.parseDouble(parseCommand[1]);
                if ( angle >= -180 && angle <= 180 )
                    msg += "Rover turn for " + angle + "degree\n";
                else
                    throw new ObserverException("! Invalid angle");
            } catch (NumberFormatException e) {
                throw new ObserverException("! Invalid angle");
            }
        }
        return msg;
    } 
}
