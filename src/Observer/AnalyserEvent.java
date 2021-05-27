/**
 * Program: AnalyserEvent.java
 * Purpose: Handles the operation that involved in the Analyser
 * Implements: RoverObserver.java
 */

package Assignment2.Observer;

import Assignment2.API.SoilAnalyser;
import Assignment2.State.AnalysisState;
import java.util.Base64;

public class AnalyserEvent implements RoverObserver
{
    private SoilAnalyser analyser;
    private AnalysisState analyseState;

    public AnalyserEvent( SoilAnalyser inAnalyser, 
                    AnalysisState inAnalyseState ) 
    { 
        analyser = inAnalyser; 
        analyseState = inAnalyseState;
    }

    @Override
    public String runCommand( String command )
    {
        String msg = "", byteStr = "";
        if ( command.equals("S") ) {
            try {
                analyseState.startAnalyse();
                analyser.startAnalysis();

                byte[] data = analyser.pollAnalysis();
                msg += "S ";
                // Appending all the data of byte into the string 
                for ( int i = 0; i < data.length; ++i )
                    byteStr += data[i];
                msg += Base64.getEncoder().encodeToString(byteStr);
            } catch (Exception e) {
                msg = e.getMessage();
            }
        }
        return msg;
    }
}
