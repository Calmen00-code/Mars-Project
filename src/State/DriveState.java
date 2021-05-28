/**
 * Program: DriveState.java
 * Purpose: Handling the rover when it is moving
 * Implements: RoverState.java
 */

package Assignment2.State;

import Assignment2.API.EngineSystem;
import Assignment2.API.Odometer;

public class DriveState implements RoverState
{
    private RoverContext rover;
    private EngineSystem engine;
    private Odometer odometer;
 

    public DriveState( RoverContext inRoverContext, 
                       EngineSystem inEngine, Odometer inOdometer )
    {
        rover = inRoverContext;
        engine = inEngine;
        odometer = inOdometer
    }

    @Override
    public String startDrive() throws StateException
    {
        String feedback = "Rover is already moving... Update new distance";

        if ( engine.getDistanceDriven() - <=  ) {
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
