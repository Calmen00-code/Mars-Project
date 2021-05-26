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

        System.out.println("=========================================");
        System.out.println("TEST Cases for Moving (STATE)\n");
        System.out.print("From Idle to Moving: ");
        try { output = rover.startDrive(); }
        catch( Exception e ) { output = e.getMessage(); }
        if ( output.equals("Rover starting to move") )
            System.out.println("PASSED");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output);
        }
        
        System.out.print("From Moving to Moving: ");
        try { output = rover.startDrive(); }
        catch( Exception e ) { output = e.getMessage(); }
        if ( output.equals("Rover is already moving") )
            System.out.println("PASSED\n");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output + "\n");
        }

        System.out.println("=========================================");
        System.out.println("TEST Cases for Soil Analysis (STATE)\n");   // FIXME
        System.out.print("From Moving to Analysis: ");          // FIXME
        try { output = rover.startAnalyse(); }                 // FIXME
        catch( Exception e ) { output = e.getMessage(); }
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
            System.out.println("PASSED\n");
        else {
            System.out.println("FAILED");
            System.out.println("Message Returned: " + output + "\n");
        }
    }
}
