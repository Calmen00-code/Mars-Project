/**
 * Program: UnitTestState.java
 * Purpose: Test Harness for State Pattern
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import Assignment2.API.EngineSystem;
import Assignmetn2.API.SoilAnalyser;

public class UnitTestState
{
    ByteArrayOutputStream capOut = null;
    String output = "";
    RoverContext rover;
    EngineSystem engine;
    SoilAnalyser analyser;

    @Before
    public void setUp()
    {
        capOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut));
        engine = new EngineSystem();
        analyser = new SoilAnalyser();
        rover = new RoverContext( engine, analyser );
    }

    @After
    public void tearDown()
    {
        output = "";
        capOut = null;
        engine = null;
        analyser = null;
        rover = null;
    }

    @Test
    public void testAnalysisState()
    {
        rover.startDrive();
        
    }
}
