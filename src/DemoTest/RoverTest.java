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
    private static RobotRover robot;        // Controller for Observer Pattern
    private static HashSet<RoverObserver> roverObs;

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
        sensor = new Sensors();
        sensorEvent = new SensorEvent( sensor );
        roverObs = new HashSet<RoverObserver>();
        robot = new RobotRover( roverObs );

        // Adding all event as observer
        robot.addObserver( sensorEvent );
        robot.addObserver( engineEvent );
        robot.addObserver( analyserEvent );
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
        analyserEvent = null;
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
        System.out.println("TEST Cases for Soil Analysis (STATE)\n");

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
        System.out.print("From Moving to Analysis: ");
        
        // Give some distance otherwise the rover cannot drive
        engine.setDistance(1);  
        try { 
            rover.startDrive(); 
            output = rover.startAnalyse();
        } catch( Exception e ) { output = e.getMessage(); }

        if ( output.equals("! Cannot do analysing when rover is moving") )
            System.out.println("PASSED");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output);
        }
    }

    public static void idleToAnalysis()
    {
        System.out.print("From Idle to Analysis: ");

        // Make rover to idle and test for analysis again
        rover.setRoverState( rover.getIdleState() );
        try { output = rover.startAnalyse(); }
        catch( Exception e ) { output = e.getMessage(); }
        if ( output.equals("Rover starting to do soil analysis") )
            System.out.println("PASSED");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output);
        }
    }

    public static void analysisToMove()
    {
        System.out.print("From Analysis to Driving: ");

        try { 
            rover.startAnalyse();
            output = rover.startDrive();
        } catch( Exception e ) { output = e.getMessage(); }
        if ( output.equals("! Rover cannot move while performing soil analysis") )
            System.out.println("PASSED");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output);
        }
    }

    public static void analysisToAnalysis()
    {
        System.out.print("From Analysis to Analysis: ");

        try { 
            rover.startAnalyse();
            output = rover.startAnalyse();
        } catch( Exception e ) { output = e.getMessage(); }
        if ( output.equals("! Rover is already performing analysis") )
            System.out.println("PASSED\n");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output + "\n");
        }
    }

    public static void idleToMoveObs()
    {
        System.out.print("From Idle to Moving: ");

        try { 
            robot.roverUpdate( "D 12.5" );
            output = robot.getEventMsg();
        } catch( Exception e ) { output = e.getMessage(); }

        if ( output.equals("Drive for 12.5km") )
            System.out.println("PASSED");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output);
        }
    }
}
