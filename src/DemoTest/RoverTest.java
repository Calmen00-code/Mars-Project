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
    public static void main(String[] args)
    {
        String output = "";
        EngineSystem engine = new EngineSystem();
        SoilAnalyser analyser = new SoilAnalyser();
        RoverContext rover = new RoverContext( engine, analyser );

        System.out.println("=========================================\n");
        System.out.println("TEST Moving Successful: ");
        try { output = rover.startDrive(); }
        catch( Exception e ) { output = e.getMessage(); }
        if ( output.equals("Rover is already moving") )
            System.out.println("PASSED");
        else
            System.out.println("FAILED");
        
    }
}
