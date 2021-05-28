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
        
        engine = new EngineSystem();
        analyser = new SoilAnalyser();
        rover = new RoverContext( engine, analyser );
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

        testCommand.add("D 10");
        testCommand.add("T 45");
        testCommand.add("S");
        testCommand.add("S");
        testCommand.add("D 100");
        testCommand.add("T 45");
        testCommand.add("E");
        testCommand.add("P");
        testCommand.add("T -181");
        testCommand.add("T 181");
        testCommand.add("T 180");
        testCommand.add("T -180");
        // This will result in ! Invalid Command return message
        testCommand.add("Invalid");     
        
        int i = 0, sFlag = 1;
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
            if ( rover.getCurrentState() instanceof AnalysisState ) {
                ++sFlag;
                if ( msg.contains("P") || msg.contains("E") )
                    System.out.println(msg);
                else if ( msg.contains("!") )
                    System.out.println(msg);
                else {
                    if ( sFlag == 4 ) { // Wait for 4 seconds
                        byte[] data = analyser.pollAnalysis();
                        msg += "S " + Base64.getEncoder().encodeToString(data);
                        System.out.println(msg);
                        // Reset for next
                        sFlag = 1;      
                        // Reset to idle once analysis is done
                        rover.setRoverState( rover.getIdleState() ); 
                    }
                }
            } else      // Print for Drive (D), Idle, and Turn (T) states
                System.out.println(msg);
            ++i;
            if ( i == testCommand.size() ) {
                i = 0;
                // TODO Implement the checking for getDistanceDriven()
                engine.setDistance(0);  
                System.out.println("D " + engineEvent.getDistance());
            }
        }
    }
}
