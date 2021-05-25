/**
 * Program: RoverContext.java
 * Purpose: Class that handles the state pattern for the Rover
 */

package Assignment2.State;

import Assignment2.API.EngineSystem;
import Assignment2.API.SoilAnalyser;

public class RoverContext
{
    private DriveState isDrive;
    private IdleState isIdle;
    private AnalysisState isAnalyse;
    private EngineSystem engine;
    private SoilAnalyser analyser;
/*  
    FIXME 
    private SensorOnState sensorOn;
    private SensorIdleState sensorIdle;
*/

    private RoverState roverState;

    public RoverContext( EngineSystem inEngine, 
                         SoilAnalyser inAnalyser )
    {
        // Initialising all states to this current object
        isDrive = new DriveState(this);
        isIdle = new IdleState(this);
        isAnalyse = new AnalysisState(this);
        engine = inEngine;
        analyser = inAnalyser;
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

    public String startDrive()
    {
        String feedback = "";

        try { 
            roverState.startDrive();    // State Transition
            engine.startDriving();      // Actual stop
        } catch (StateException e) { 
            feedback = e.getMessage(); 
        }

        return feedback;
    }

    public void stopDrive()
    {
        roverState.stopDrive();     // State Transition
        engine.stopDriving();       // Actual stop
    }

    public String startAnalyse()
    {
        String feedback = "";

        try { 
            roverState.startAnalyse();  // State Transition
            analyser.startAnalysis();   // Start analysis
        } catch (StateException e) { 
            feedback = e.getMessage(); 
        }

        return feedback;
    }

    public DriveState getDriveState() { return isDrive; }
    public IdleState getIdleState() { return isIdle; }
    public AnalysisState getAnalysisState() { return isAnalyse; }
    public EngineSystem getEngine() { return engine; }
}
