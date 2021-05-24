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
    public String startDrive()
    {
        String feedback = "Rover is already moving";
        EngineSystem engine = rover.getEngineSystem();

        if ( engine.getDistanceDriven() <= 0.00 ) {
            feedback = "Rover stop moving as distance has reached";
            rover.setRoverState( rover.getIdleState() );
        } 
        return feedback;
    }

    @Override
    public String stopDrive()
    {
        String feedback = "Rover stopped moving";
        rover.setRoverState( rover.getIdleState() );
        return feedback;
    }
}
