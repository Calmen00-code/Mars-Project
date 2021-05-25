/**
 * Program: EngineEvent.java
 * Purpose: Handles the event that involves in the engine of the robot
 * Implements: RoverObserver.java
 */

package Assignment2.Observer;

import Assignment2.API.EngineSystem;
import Assignment2.State.RoverContext;

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
    public String checkCommand( String command ) throws ObserverException
    {
        String msg = "";
        double distance = 0.0, angle = 0.0;

        String[] parseCommand = command.split(" "); // Parse the command

        if ( parseCommand[0].equals("D") ) { // Checking for drive
            try {
                // Second argument must be a valid distance
                distance = Double.parseDouble(parseCommand[1]);
                if ( engine.getDistanceDriven() <= 0 ) {
                    msg += "Rover has reached its destination";
                    rover.stopDrive();
                } else {
                    msg += "Drive for " + distance;
                    rover.startDrive();
                }
            } catch (Exception e) {
                throw new ObserverException("Invalid distance");
            }
        } else if ( parseCommand[0].equals("T") ) { // Checking for turn
            try {
                // Second argument must be a valid angle
                angle = Double.parseDouble(parseCommand[1]);
                if ( angle >= -180 && angle <= 180 )
                    msg += "Rover turn for " + angle + "degree";
                else
                    throw new ObserverException("Invalid angle");
            } catch (Exception e) {
                throw new ObserverException("Invalid angle");
            }
        }
        return msg;
    } 
}
