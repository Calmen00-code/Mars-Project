/**
 * Program: RoverDemo.java
 * Purpose: Demonstration of the Rover
 */

package Assignment2.DemoTest;

import java.util.*;
import Assignment2.API.*;
import Assignment2.Observer.*;
import Assignment2.State.*;
import Assignment2.DemoTest.*;

public class RoverDemo
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args)    
    {
        List<String> testCommand = new ArrayList();
        String msg = "";
        EngineSystem engine;
        SoilAnalyser analyser;
        RoverContext rover;
        Sensors sensor;
        RoverObserver sensorEvent;
        RoverObserver engineEvent;
        RoverObserver analyserEvent;
        RobotRover robot;
        HashSet<RoverObserver> roverObs;
        ValidateCommand validate;
        Odometer odometer;
        
        engine = new EngineSystem();
        analyser = new SoilAnalyser();
        odometer = new Odometer();
        rover = new RoverContext( engine, analyser, odometer );
        analyserEvent = new AnalyserEvent( analyser, rover );
        engineEvent = new EngineEvent( engine, rover );
        sensor = new Sensors();
        sensorEvent = new SensorEvent( sensor );
        roverObs = new HashSet<RoverObserver>();
        robot = new RobotRover( roverObs );
        validate = new ValidateCommand();

        // Adding all event as observer
        robot.addObserver( sensorEvent );
        robot.addObserver( engineEvent );
        robot.addObserver( analyserEvent );

        testCommand.add("D 2");
        testCommand.add("T 45");
        testCommand.add("S");
        testCommand.add("E");
        testCommand.add("P");
        testCommand.add("S");
        testCommand.add("D 9");
        testCommand.add("T 45");
        testCommand.add("T -181");
        testCommand.add("T 181");
        testCommand.add("T 180");
        testCommand.add("T -180");
        // This will result in ! Invalid Command return message
        testCommand.add("Invalid");     
        
        int i = 0, sFlag = 0;
        double distCounter = 0.0;
        RoverObserver currentEvent = null;
        while( true ) {
            try {
                Thread.sleep(1000);
                if ( !validate.commandIsValid( testCommand.get(i) ) )
                    throw new ValidateException("! Command does not exist");
                // It will return the current event executing by rover
                currentEvent = robot.roverUpdate( testCommand.get(i) );
                // Retrieve the feedback gotten from this event / operation
                msg = robot.getEventMsg();
            } catch ( InterruptedException e ) {
                /* Do nothing */
            } catch( Exception e ) {
                msg = e.getMessage();
            }

            /**
             * P and E can still be running while rover is in Analysis
             * sFlag is a flag to allow the S to take sometimes before
             * the result is ready
             */
            if ( rover.getCurrentState() instanceof AnalysisState ) {
                ++sFlag;
                if ( msg.contains("P") || msg.contains("E") )
                    System.out.println(msg);
                else if ( msg.contains("!") )
                    System.out.println(msg);
                if ( sFlag == 4 ) { // Wait for 4 rounds for analysis
                    byte[] data = analyser.pollAnalysis();
                    msg = "S " + Base64.getEncoder().encodeToString(data);
                    System.out.println(msg);
                    // Reset for next
                    sFlag = 1;      
                    // Reset to idle once analysis is done
                    rover.setRoverState( rover.getIdleState() ); 
                }
            } else      // Print for Drive (D), Idle, and Turn (T) states
                System.out.println(msg);

            /** 
             * Increment of the command here to check for the next command
             * In actual, the program will call pollCommand and retrieve the
             * next available command from Earth.
             **/
            ++i;
            if ( i == testCommand.size() ) {    
                i = 0;  // Reset to allow circular rotation
            }

            /**
             * Controlling the total distance that a rover had travelled
             * Rover is not moving when State is Idle and Analysis
             */
            if ( rover.getCurrentState() instanceof DriveState )
                ++distCounter;
 
            if ( engine.getDistanceDriven() - distCounter <= odometer.getInitialDistance() && 
                 rover.getCurrentState() instanceof DriveState ) {
                System.out.println("D " + engineEvent.getDistance());
                rover.setRoverState( rover.getIdleState() );
            }
        }
    }
}
