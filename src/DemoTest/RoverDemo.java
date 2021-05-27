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
        testCommand.add("E");
        testCommand.add("P");
        testCommand.add("Invalid");
        
        int i = 0;
        while( true ) {
            try {
                Thread.sleep(1000);
                if ( !validate.commandExist( testCommand.get(i) ) )
                    throw new ValidateException("Command does not exist");
                robot.roverUpdate( testCommand.get(i) );
                msg = robot.getEventMsg();
            } catch (InterruptedException e ) {
                /* Do nothing */
            } catch( Exception e ) {
                msg = e.getMessage();
            }
            System.out.println(msg);
            ++i;
            if ( i == testCommand.size() ) {
                i = 0;
                engine.setDistance(0);
            }
        }
    }
}
