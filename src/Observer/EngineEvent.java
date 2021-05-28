/**
 * Program: EngineEvent.java
 * Purpose: Handles the event that involves in the engine of the robot
 *
 * @implements RoverObserver.java
 * @aggregate DriveState when the command is 'D'
 */

package Assignment2.Observer;

import Assignment2.API.EngineSystem;
import Assignment2.API.Odometer;
import Assignment2.State.*;

public class EngineEvent extends RoverObserver
{
    private EngineSystem engine;
    private RoverContext rover;
    private double distance;

    public EngineEvent( EngineSystem inEngine, RoverContext inRover ) 
    { 
        // Dependency Injection
        engine = inEngine; 
        rover = inRover;
        distance = 0.0;
    }

    @Override
    public String runCommand( String command ) throws ObserverException
    {
        String msg = "";
        double parseDistance = 0.0, angle = 0.0;

        String[] parseCommand = command.split(" "); // Parse the command

        if ( parseCommand[0].equals("D") ) { // Checking for drive
            try {
                if ( parseCommand.length < 2 )
                    throw new ObserverException("! No distance given");

                // Second argument must be a valid distance
                parseDistance = Double.parseDouble(parseCommand[1]);
                distance = parseDistance;
                if ( rover.getCurrentState() instanceof IdleState )
                rover.startDrive();

                msg += "Drive for " + distance + "km";
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

                // Cannot turn when rover is doing analysis
                if ( rover.getCurrentState() instanceof AnalysisState )
                    throw new ObserverException("! Cannot turn when rover is performing analysis");

                if ( angle >= -180 && angle <= 180 )
                    msg += "Rover turn for " + angle + " degree";
                else
                    throw new ObserverException("! Invalid angle");
            } catch (NumberFormatException e) {
                throw new ObserverException("! Invalid angle");
            }
        }
        return msg;
    } 

    @Override
    public double getDistance()
    {
        return distance;
    }
}
