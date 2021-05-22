/**
 * Program: DriveState.java
 * Purpose: Handling the rover when it is moving
 * Implements: RoverState.java
 */

public class DriveState implements RoverState
{
    public static final int TOL = 0.01;
    private RoverContext rover;

    public DriveState( RoverContext inRoverContext )
    {
        rover = inRoverContext;
    }

    public String startDrive()
    {
        String feedback = "Rover is already moving";
        EngineSystem engine = rover.getEngineSystem();

        if ( engine.getDistanceDriven() == TOL ) {
            feedback = "Rover stop moving as distance has reached";
            rover.setRoverState( rover.getIdleState() );
        } 
        return feedback;
    }

    public String stopDrive()
    {
        String feedback = "Rover stopped moving";
        rover.setRoverState( rover.getIdleState() );
        return feedback;
    }
}
