import java.util.ArrayList;

/**
 * Emitter for Roman candle stars.
 * @author Christina Chan
 */
public class LaunchTube extends Emitter
{	
	/**
	 * The LaunchTube constructor.
	 * @param initialXPos The initial x position of the emitter.
	 * @param initialYPos The initial y position of the emitter.
	 * @param initialVX The initial x velocity component of the emitter.
	 * @param initialVY The initial y velocity component of the emitter.
	 * @param exitVel The launch velocity of the sparks from the emitter.
	 * @param firingA The launch angle of the emitter, from the vertical in degrees.
	 * @param var The random variation range for the launch angle in degrees.
	 * @throws EmitterException If the two angles are not legal. The firing angle must
	 * be between -15 and 15 degrees and the variation must be between 0 and 10 degrees.
	 */
	public LaunchTube(double initialXPos, double initialYPos, double initialVX,
			double initialVY, double exitVel, double firingA, double var)
			throws EmitterException
	{
		super(initialXPos, initialYPos, initialVX, initialVY, exitVel, firingA, var);
		if (firingA <= -15 || firingA >= 15) {
			throw new EmitterException("Illegal firing angle: " + firingA);
		}
	}
	
	/**
	 * Creates and "launches" a star at the given time in seconds.
	 * @param time The aboslute time in seconds.
	 * @return An instance of a Star object in an ArrayList, which will contain its
	 * initial position, initial velocity components and the desired colour.
	 */
	public ArrayList<Star> launch(double time) {
		ArrayList<Star> stars = new ArrayList<Star>();
		double vXInitial = getExitVelocity() * Math.sin(getLaunchAngle());
		double vYInitial = getExitVelocity() * Math.cos(getLaunchAngle());
		String[] colours = {"blue", "orange", "purple", "yellow"};
        stars.add(new Star(time, 0, 0, vXInitial, vYInitial, colours[(int)Math.random()*4]));
		return stars;
	}
} // end LaunchTube
