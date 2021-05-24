/**
 * Program: RoverContext.java
 * Purpose: Class that handles the state pattern for the Rover
 */

package Assignment2.State;

import Assignment2.API.EngineSystem;

public class RoverContext
{
    private DriveState isDrive;
    private IdleState isIdle;
    private EngineSystem engine;
/*  
    FIXME 
    private SensorOnState sensorOn;
    private SensorIdleState sensorIdle;
*/

    private RoverState roverState;

    public RoverContext()
    {
        // Initialising all states to this current object
        isDrive = new DriveState(this);
        isIdle = new IdleState(this);
        engine = new EngineSystem();
/*
        FIXME
        sensorOn = new SensorOnState(this);
        sensorIdle = new SensorIdleState(this);
*/

        // Rover is not moving initially
        roverState = isIdle;

        // Rover is not reading any data initially
        // FIXME sensorIdle = 
    }

    public void setRoverState( RoverState newRoverState )
    {
        roverState = newRoverState;
    }

    public void startDrive()
    {
        roverState.startDrive();
        engine.startDriving();
    }

    public void stopDrive()
    {
        roverState.stopDrive();
        engine.stopDriving();
    }

    public DriveState getDriveState() { return isDrive; }
    public IdleState getIdleState() { return isIdle; }
    public EngineSystem getEngine() { return engine; }
}
