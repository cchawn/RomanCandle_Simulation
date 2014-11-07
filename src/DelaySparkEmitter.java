import java.util.ArrayList;

/**
 * Emitter for delay charge sparks.
 * @author Christina Chan
 */
public class DelaySparkEmitter extends Emitter
{	
	/**
	 * The DelaySparkEmitter constructor.
	 * @param initialXPos The initial x position of the emitter.
	 * @param initialYPos The initial y position of the emitter.
	 * @param initialVX The initial x velocity component of the emitter.
	 * @param initialVY The initial y velocity component of the emitter.
	 * @param exitVel The launch velocity of the sparks from the emitter.
	 * @param firingA The launch angle of the emitter, from the vertical in degrees.
	 * @param var The random variation range for the launch angle in degrees.
	 * @throws EmitterException If the two angles are not legal.
	 */
	public DelaySparkEmitter(double initialXPos, double initialYPos,
			double initialVX, double initialVY, double exitVel, double firingA,
			double var) throws EmitterException
	{
		super(initialXPos, initialYPos, initialVX, initialVY, exitVel, firingA, var);
	} // end constructor

	/**
	 * Launches (returns) Spark objects in an ArrayList at the supplied time.
	 * @param time The absolute launch time in seconds.
	 * @return An ArrayList containting Sparks.
	 */
	public ArrayList<Spark> launch(double time)
	{
		ArrayList<Spark> sparks = new ArrayList<Spark>();
		double vXInitial = getExitVelocity() * Math.sin(getLaunchAngle());
		double vYInitial = getExitVelocity() * Math.cos(getLaunchAngle());
		for(int i=0;i<5;i++)
            sparks.add(new LaunchSpark(time, 0, 0, vXInitial, vYInitial, "orange"));
		return sparks;
	} // end launch

} // end DelaySparkEmitter
