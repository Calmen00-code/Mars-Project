/**
 * Program: RoverDemo.java
 * Purpose: Demonstration of the Rover
 */

package Assignment2.DemoTest;

import java.util.*;
import Assignment2.Observer.*;

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
        HashSet<RoverObservers> roverObs;
        
        engine = new EngineSystem();
        analyser = new SoilAnalyser();
        rover = RoverContext( engine, analyser );
        analyserEvent = new AnalyserEvent( analyser, rover );
        engineEvent = new EngineEvent( engine, rover );
        sensor = new Sensors();
        sensorEvent = new SensorEvent( sensor );
        roverObs = new HashSet<RoverObserver>();
        robot = new RobotRover( roverObs );

        // Adding all event as observer
        robot.addObserver( sensorEvent );
        robot.addObserver( engineEvent );
        robot.addObserver( analyserEvent );

        testCommand.add("D 10");
        testCommand.add("T 45");
        testCommand.add("S");
        testCommand.add("E");
        testCommand.add("P");
        testCommand.add("Invalid");
        
        int i = 0;
        while( true ) {
            try {
                robot.updateCommand( testCommand[i] );
                msg = robot.getEventMsg();
            } catch( Exception e ) {
                msg = e.getMessage();
            }
            System.out.println(msg);
            Thread.sleep(1000);
            ++i;
            if ( i == testCommand.length )
                i = 0;
        }
    }
}
