/**
 * The base class for Emitter objects.
 * @author Christina Chan
 */
public abstract class Emitter extends Firework
{
	private double exitVelocity;			// m/s
	private double firingAngle;				// m/s
	private double variation;				// m/s
	
	/**
	 * The constructor for an Emitter Object
	 * @param initialXPos The inital x position of the emitter.
	 * @param initialYPos The initial y position of the emitter.
	 * @param initialVX The initial x velocity of the emitter.
	 * @param initialVY The initial y velocity of the emitter.
	 * @param exitVel The launch velocity of the sparks from the emitter.
	 * @param firingA The launch angle of the emitter, from the vertical in degrees.
	 * @param var The random variation range for the launch angle in degrees.
	 * 
	 * @throws EmitterException If the two angles are not legal.
	 * The firing angle must lie between -180 and 180 degrees, and
	 * the variation angle between 0 and 180 degrees, inclusive.
	 */
	public Emitter(double initialXPos, double initialYPos, double initialVX, 
			double initialVY, double exitVel, double firingA, 
			double var) throws EmitterException
	{
		super(initialXPos, initialYPos, initialVX, initialVY);
		exitVelocity = exitVel;
		if (firingA < -180 || firingA > 180) {
			throw new EmitterException("Illegal firing angle: " + firingA);
		}
		else if (var < 0 || var > 180) {
			throw new EmitterException("Illegal variation angle: " + var);
		}
		else {
			firingAngle = firingA;	
			variation = var;
		} // end else if chain
	} // end constructor
	
	/**
	 * An accessor for the exit (or launch) velocity of the emitter.
	 * @return The exit velocity in m/sec.
	 */
	public double getExitVelocity()
	{
		double value = exitVelocity;
		return value;
	} // end getExitVelocity
	
	/**
	 * An accessor for the launch angle.
	 * @return The launch angle in degrees.
	 */
	public double getLaunchAngle()
	{
		double value = firingAngle;
		return value;
	} // end getLaunchAngle
	
	/**
	 * An accessor that calculates and returns an angle randomly generated between
	 * (firing angle - variation) and (firing angle + variation) in radians.
	 * @return The launch angle in radians.
	 */
	public double getRandomLaunchAngle()
	{
		double min = firingAngle - variation;
		double max = firingAngle + variation;
		return (min + Math.random()*(max - min))*(Math.PI/180); // multiplying by last part converts to radians
	} // end getRandomLaunchAngle
	
	/**
	 * Launches particles at the supplied time.
	 * @param time Time in seconds.
	 * @return A collection of launched particles.
	 * If the list is empty then the emitter has expired.
	 */
	public abstract java.util.ArrayList<? extends Particle> launch(double time);
	
	/**
	 * A mutator for the exit (or launch) velocity.
	 * @param exitVel The desired exitVelocity in m/sec.
	 */
	public void setExitVelocity(double exitVel)
	{
		exitVelocity = exitVel;
	} // end setExitVelocity
} // end Emitter
