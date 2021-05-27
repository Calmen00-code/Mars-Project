/**
 * Handle all the exceptions being thrown in the Observer Folder
 * @extends Exception
 */

package Assignment2.DemoTest;

public class ValidateException extends Exception
{
    public ValidateException( String msg ) { super(msg); }

    public ValidateException( String msg, 
                              Throwable cause ) { super(msg); }
}
