/**
 * Thrown by the Emitter object if an illegal angle is supplied.
 * @author Christina
 */
public class EmitterException extends Exception {
	/**
	 * Accepts a specific message about the problem.
	 * @param message
	 */
	public EmitterException(String message) {
		super(message);
	}

} // end Emitter Exception
