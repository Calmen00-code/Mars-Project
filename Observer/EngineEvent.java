/**
 * Program: EngineEvent.java
 * Purpose: Handles the event that involves in the engine of the robot
 * Implements: RoverObserver.java
 */

import Assignment2.State;

public class EngineEvent implements RoverObserver
{
    private EngineSystem engine;
    private RoverContext rover;

    public EngineEvent( EngineSystem inEngine, RoverContext inRover ) 
    { 
        engine = inEngine; 
        rover = inRover;
    }

    @Override
    public String checkCommand( String command )
    {
        String msg = "", angle = "";
        double distance = 0.0;
        String[] parseCommand = command.split(" ");

        if ( parseCommand[0].equals("D") ) {
            try {
                distance = Double.parseDouble(parseCommand[1]);
                if ( distance <= 0 )
                    msg += ""
                    msg += "Drive for " + distance;
            } catch (Exception e) {
                throw new EngineEventException("Invalid distance");
            }
        } else if ( parseCommand[1].equals(""
