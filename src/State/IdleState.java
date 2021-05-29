/**
 * Program: DriveState.java
 * Purpose: Handling the rover when it is NOT moving
 * Implements: RoverState.java
 */

package Assignment2.State;

import Assignment2.API.EngineSystem;
import Assignment2.API.Odometer;

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
    public String startDrive( double newDistance ) throws StateException
    {
        String feedback = ""; 

        // Check if rover reached its destitation 
        if ( engine.getDistanceDriven() - odometer.getFinalDistance() 
             <= odometer.getInitialDistance() ) {
            rover.setRoverState( rover.getIdleState() );
            feedback = "D " + odometer.getFinalDistance();
            // throw new StateException( "Rover had reached its destination" );
        }
        else {   // Otherwise, start driving
            rover.setRoverState( rover.getDriveState() );
            feedback = "Rover starting to move";
        }
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
