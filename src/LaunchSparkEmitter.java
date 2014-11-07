import java.util.ArrayList;

/**
 * Emitter for launch sparks.
 * @author Christina Chan
 */
public class LaunchSparkEmitter extends Emitter
{
	/**
	 * The LaunchSparkEmitter constructor.
	 * @param initialXPos The initial x position of the emitter.
	 * @param initialYPos The initial y position of the emitter.
	 * @param initialVX The initial x velocity component of the emitter.
	 * @param initialVY The initial y velocity component of the emitter.
	 * @param exitVel The launch velocity of the sparks from the emitter.
	 * @param firingA The launch angle of the emitter, from the vertical in degrees.
	 * @param var The random variation range for the launch angle in degrees.
	 * @throws EmitterException If the two angles are not legal.
	 */
	public LaunchSparkEmitter(double initialXPos, double initialYPos,
			double initialVX, double initialVY, double exitVel, double firingA,
			double var) throws EmitterException
	{
		super(initialXPos, initialYPos, initialVX, initialVY, exitVel, firingA, var);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Launches (returns) LaunchSpark objects at the supplied time.
	 * @param time The absolute launch time in seconds.
	 * @return An ArrayList containing LaunchSpark objects.
	 */
	@Override
	public ArrayList<LaunchSpark> launch(double time)
	{
		ArrayList<LaunchSpark> sparks = new ArrayList<LaunchSpark>();
		double vXInitial = getExitVelocity() * Math.sin(getLaunchAngle());
		double vYInitial = getExitVelocity() * Math.cos(getLaunchAngle());
		for(int i=0;i<20;i++)
            sparks.add(new LaunchSpark(time, 0, 0, vXInitial, vYInitial, "orange"));
		return sparks;
	}

} // end LaunchSparkEmitter
