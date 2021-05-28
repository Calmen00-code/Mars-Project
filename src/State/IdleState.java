/**
 * Program: DriveState.java
 * Purpose: Handling the rover when it is NOT moving
 * Implements: RoverState.java
 */

package Assignment2.State;

import Assignment2.API.EngineSystem;

public class IdleState implements RoverState
{
    private RoverContext rover;
    private EngineSystem engine;
    private Odometer odometer;

    public IdleState( RoverContext inRoverContext, 
                      EngineSystem inEngine, Odometer inOdometer )
    {
        rover = inRoverContext;
        engine = inEngine;
        odometer = inOdometer;
    }

    @Override
    public String startDrive() throws StateException
    {

        String feedback = "Rover starting to move";
        EngineSystem engine = rover.getEngine();

        if ( engine.getDistanceDriven() - odometer.getFinalDistance() 
             <= odometer.getInitialDistance() ) {
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
        String feedback = "Rover is currently not moving";
        return feedback;
    }

    @Override
    public String startAnalyse() throws StateException
    {
        String feedback = "Rover starting to do soil analysis";
        rover.setRoverState( rover.getAnalysisState() );
        return feedback;
    }
}
