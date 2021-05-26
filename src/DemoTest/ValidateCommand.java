/**
 * Program: ValidateCommand.java
 * Purpose: Checking the command passed from Earth
 */

public class ValdiateCommand
{
    public boolean commandExist( String command )
    {
        boolean exist = false;
        char mode = command.charAt(0);
        exist = mode == 'D' || mode == 'T' || mode == 'P' ||
                mode == 'E' || mode == 'S';
        return exist;
    }
}
