/**
 * Program: RoverContext.java
 * Purpose: Class that handles the state pattern for the Rover
 */

package Assignment2.State;

import Assignment2.API.EngineSystem;
import Assignment2.API.SoilAnalyser;
import Assignment2.API.Odometer;

public class RoverContext
{
    private DriveState isDrive;
    private IdleState isIdle;
    private AnalysisState isAnalyse;
    private EngineSystem engine;
    private SoilAnalyser analyser;
    private Odometer odometer;

    private RoverState roverState;

    public RoverContext( EngineSystem inEngine, 
                         SoilAnalyser inAnalyser, Odometer inOdometer )
    {
        // Initialising all states to this current object
        odometer = inOdometer;
        isDrive = new DriveState(this, inEngine, odometer);
        isIdle = new IdleState(this, inEngine, odometer);
        isAnalyse = new AnalysisState(this);
        engine = inEngine;
        analyser = inAnalyser;

        // Rover is not moving initially
        roverState = isIdle;
    }

    public void setRoverState( RoverState newRoverState )
    {
        roverState = newRoverState;
    }

    public String startDrive( double distance ) throws StateException
    {
        String feedback = "";
        feedback = roverState.startDrive( distance );    // State Transition
        engine.startDriving();                 // Actual stop
        return feedback;
    }

    public String stopDrive()
    {
        String feedback = "";
        feedback = roverState.stopDrive();     // State Transition
        engine.stopDriving();                  // Actual stop
        return feedback;
    }

    public String startAnalyse() throws StateException
    {
        String feedback = "";

        feedback = roverState.startAnalyse();  // State Transition
        analyser.startAnalysis();   // Start analysis

        return feedback;
    }

    public DriveState getDriveState() { return isDrive; }
    public IdleState getIdleState() { return isIdle; }
    public AnalysisState getAnalysisState() { return isAnalyse; }
    public RoverState getCurrentState() { return roverState; }
    public EngineSystem getEngine() { return engine; }
}
