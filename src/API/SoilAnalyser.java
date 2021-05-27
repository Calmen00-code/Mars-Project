package Assignment2.API;

public class SoilAnalyser
{
    /**
     * Begins a soil analysis. The soil analysis will complete some time later, 
     * and its result can be retrieved by calling pollAnalysis().
     *
     * If startAnalysis() is called while analysis is already underway, it will 
     * throw an exception.
     */
    public void startAnalysis() {}

    /**
     * Retrieves the results of a soil analysis, if they're ready yet. If no new 
     * results have been produced, this method returns null.
     */
    public byte[] pollAnalysis() 
    { 
        byte[] data = new byte[3];
        data[0] = 127;
        data[1] = 127;
        data[2] = 127;
        return data;
    }
}
