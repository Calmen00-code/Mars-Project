/**
 * Program: RoverDemo.java
 * Purpose: Testing the value for the Rover Demonstration
 */

package Assignment2.DemoTest;

import java.util.*;
import Assignment2.State.*;
import Assignment2.Observer.*;
import Assignment2.API.*;

public class RoverTest
{
    private static String output; 
    private static EngineSystem engine;
    private static SoilAnalyser analyser;
    private static RoverContext rover;      // Controller for State Pattern
    private static Sensors sensor;
    private static AnalysisState analyseState;
    private static RoverObserver sensorEvent;
    private static RoverObserver engineEvent;
    private static RoverObserver analyserEvent;

    public static void setUp()
    {
        output = "";
        engine = new EngineSystem();
        analyser = new SoilAnalyser();
        rover = new RoverContext( engine, analyser );
        analyseState = new AnalysisState( rover );
        analyserEvent = new AnalyserEvent( analyser, analyseState );
        engine = new EngineSystem();
        engineEvent = new EngineEvent( engine, rover );
        Sensors sensor = new Sensors();
        sensorEvent = new SensorEvent( sensor );
    }

    public static void tearDown()
    {
        output = null;
        engine = null;
        analyser = null;
        rover = null;
        sensor = null;
        analyseState = null;
        sensorEvent = null;
        engineEvent = null;
        analyseEvent = null;
    }

    public static void main(String[] args)
    {
        System.out.println("=========================================");
        System.out.println("TEST Cases for Moving and Idle (STATE)\n");

        setUp();
        idleToMove();
        tearDown();

        setUp();
        moveToMove();
        tearDown();

        setUp();
        moveToIdle();
        tearDown();

        setUp();
        reachDest();
        tearDown(); 

/* ------------------------------------------------------------------------------------------------------ */

        System.out.println("=========================================");
        System.out.println("TEST Cases for Soil Analysis (STATE)\n");   // FIXME

        setUp();
        moveToAnalysis();
        tearDown(); 

        setUp();
        idleToAnalysis();
        tearDown(); 
 
        setUp();
        analysisToMove();
        tearDown(); 

        setUp();
        analysisToAnalysis();
        tearDown();

/* ------------------------------------------------------------------------------------------------------ */

        System.out.println("=========================================");
        System.out.println("TEST Cases for Analyse Event (Observer)\n");   // FIXME
        System.out.print("From : ");          // FIXME

        rover.setRoverState( rover.getIdleState() );            // FIXME
        try { 
            rover.startDrive(); 
            output = rover.startAnalyse();                  // FIXME
        } catch( Exception e ) { output = e.getMessage(); }

        if ( output.equals("! Cannot do analysing when rover is moving") ) // FIXME
            System.out.println("PASSED");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output);
        }
 
        // Make rover to idle and test for analysis again
        rover.setRoverState( rover.getIdleState() );            // FIXME
        System.out.print("From Idle to Analysis: ");              // FIXME
        try { output = rover.startAnalyse(); }                  // FIXME
        catch( Exception e ) { output = e.getMessage(); }
        if ( output.equals("Rover starting to do soil analysis") )         // FIXME
            System.out.println("PASSED");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output);
        }

        System.out.print("From Analysis to Driving: ");              // FIXME
        try { output = rover.startDrive(); }                  // FIXME
        catch( Exception e ) { output = e.getMessage(); }
        if ( output.equals("! Rover cannot move while performing soil analysis") )         // FIXME
            System.out.println("PASSED");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output);
        }

        System.out.print("From Analysis to Analysis: ");          // FIXME
        try { output = rover.startAnalyse(); }                 // FIXME
        catch( Exception e ) { output = e.getMessage(); }
        if ( output.equals("! Rover is already performing analysis") ) // FIXME
            System.out.println("PASSED\n");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output + "\n");
        }
    }

    public static void idleToMove()
    {
        System.out.print("From Idle to Moving: ");

        try { output = rover.startDrive(); }
        catch( StateException e ) { output = e.getMessage(); }
        if ( output.equals("Rover starting to move") )
            System.out.println("PASSED");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output);
        }
    }

    public static void moveToMove()
    {
        System.out.print("From Moving to Moving: ");
        
        try { 
            rover.startDrive(); 
            output = rover.startDrive(); 
        } catch( StateException e ) { output = e.getMessage(); }
        if ( output.equals("Rover is already moving") )
            System.out.println("PASSED");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output);
        }
    }

    public static void moveToIdle()
    {
        System.out.print("From Moving to Idle: ");

        try { 
            rover.startDrive();
            output = rover.stopDrive(); 
        } catch( Exception e ) { output = e.getMessage(); }
        if ( output.equals("Rover stopped moving") )
            System.out.println("PASSED");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output);
        }
    }

    public static void reachDest()
    {
        System.out.print("Rover reached destination: ");

        engine.setDistance(0);
        try { output = rover.startDrive(); }
        catch( Exception e ) { output = e.getMessage(); }
        if ( output.equals("! Rover had already reached its destination") )
            System.out.println("PASSED\n");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output + "\n");
        }
    }

    public static void moveToAnalysis()
    {
        System.out.print("From Moving to Analysis: ");          // FIXME
        
        // Give some distance otherwise the rover cannot drive
        engine.setDistance(1);  
        try { 
            rover.startDrive(); 
            output = rover.startAnalyse();                  // FIXME
        } catch( Exception e ) { output = e.getMessage(); }

        if ( output.equals("! Cannot do analysing when rover is moving") ) // FIXME
            System.out.println("PASSED");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output);
        }
    }

    public static void idleToAnalysis()
    {
        System.out.print("From Idle to Analysis: ");              // FIXME

        // Make rover to idle and test for analysis again
        rover.setRoverState( rover.getIdleState() );            // FIXME
        try { output = rover.startAnalyse(); }                  // FIXME
        catch( Exception e ) { output = e.getMessage(); }
        if ( output.equals("Rover starting to do soil analysis") )         // FIXME
            System.out.println("PASSED");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output);
        }
    }

    public static void analysisToMove()
    {
        System.out.print("From Analysis to Driving: ");              // FIXME

        try { 
            rover.startAnalyse();
            output = rover.startDrive(); // FIXME
        } catch( Exception e ) { output = e.getMessage(); }
        if ( output.equals("! Rover cannot move while performing soil analysis") )         // FIXME
            System.out.println("PASSED");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output);
        }
    }

    public static void analysisToAnalysis()
    {
        System.out.print("From Analysis to Analysis: ");          // FIXME

        try { 
            rover.startAnalyse();
            output = rover.startAnalyse(); // FIXME
        } catch( Exception e ) { output = e.getMessage(); }
        if ( output.equals("! Rover is already performing analysis") ) // FIXME
            System.out.println("PASSED\n");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output + "\n");
        }
    }
}
