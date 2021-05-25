/**
 * Handle all the exceptions being thrown in the Observer Folder
 * @extends Exception
 */

package Assignment2.Observer;

public class ObserverException extends Exception
{
    public ObserverException( String msg ) { super(msg); }

    public ObserverException( String msg, 
                              Throwable cause ) { super(msg); }
}
