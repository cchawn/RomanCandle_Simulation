/**
 * An exception thrown by RungeKuttaSolver.
 * @author Christina Chan
 */
public class SolverException extends Exception {

	/**
	 * The constructor accepts a specific message about the problem.
	 * @param message
	 */
	public SolverException(String message) {
		super(message);
	}
	
} // end SolverException