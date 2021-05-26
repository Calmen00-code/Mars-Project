/**
 * Program: DriveState.java
 * Purpose: Handling the rover when it is moving
 * Implements: RoverState.java
 */

package Assignment2.State;

import Assignment2.API.EngineSystem;

public class DriveState implements RoverState
{
    private RoverContext rover;

    public DriveState( RoverContext inRoverContext )
    {
        rover = inRoverContext;
    }

    @Override
    public String startDrive() throws StateException
    {
        String feedback = "Rover starting to move";
        EngineSystem engine = rover.getEngine();

        if ( engine.getDistanceDriven() <= 0 ) {
            rover.setRoverState( rover.getIdleState() );
            throw new StateException("! Rover had already reached its destination");
        }
        else
            rover.setRoverState( rover.getDriveState() );
        return feedback;
    }

    @Override
    public String stopDrive()
    {
        String feedback = "Rover stopped moving";
        rover.setRoverState( rover.getIdleState() );
        return feedback;
    }

    @Override
    public String startAnalyse() throws StateException
    {  
        throw new StateException(
                "! Cannot do analysing when rover is moving");
    }
}
