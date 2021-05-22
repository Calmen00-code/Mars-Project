/**
 * Program: DriveState.java
 * Purpose: Handling the rover when it is NOT moving
 * Implements: RoverState.java
 */

public class IdleState implements RoverState
{
    private RoverContext rover;

    public IdleState( RoverContext inRoverContext )
    {
        rover = inRoverContext;
    }

    @Override
    public String startDrive()
    {
        String feedback = "Rover starting to move";
        EngineSystem engine = rover.getEngineSystem();

        if ( engine.getDistanceDriven() == TOL )
            feedback = "Rover had already reached its destination";
        else
            rover.setRoverState( rover.getDriveState() );
        return feedback;
    }

    @Override
    public String stopDrive()
    {
        String feedback = "Rover is currently not moving";
        return feedback;
    }
}
