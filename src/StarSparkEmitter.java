import java.util.ArrayList;

/**
 * Emitter for Star sparks
 * @author Christina Chan
 */
public class StarSparkEmitter extends Emitter
{
	private String sparkColour;
	
	/**
	 * The StarSparkEmitter constructor.
	 * @param initialXPos The initial x position of the emitter.
	 * @param initialYPos The initial y position of the emitter.
	 * @param initialVX The initial x velocity component of the emitter.
	 * @param initialVY The initial y velocity component of the emitter.
	 * @param exitVel The launch velocity of the sparks from the emitter.
	 * @param firingA The launch angle of the emitter, from the vertical in degrees.
	 * @param var The random variation range for the launch angle in degrees.
	 * @throws EmitterException If the two angles are not legal.
	 */
	public StarSparkEmitter(double initialXPos, double initialYPos,
			double initialVX, double initialVY, double exitVel, double firingA,
			double var) throws EmitterException
	{
		super(initialXPos, initialYPos, initialVX, initialVY, exitVel, firingA, var);
	}

	/**
	 * Launches (returns) StarSpark objects at the supplied time.
	 * @param time The absolute launch time in seconds.
	 * @return An ArrayList of StarSpark objects.
	 */
	public ArrayList<Spark> launch(double time) {
		ArrayList<Spark> sparks = new ArrayList<Spark>();
		double vXInitial = getExitVelocity() * Math.sin(getLaunchAngle());
		double vYInitial = getExitVelocity() * Math.cos(getLaunchAngle());
		double[] position = getPosition();
		for(int i=0;i<20;i++)
            sparks.add(new Spark(time, position[0], position[1], vXInitial, vYInitial, sparkColour));
		return sparks;
	}
	
	/**
	 * A mutator for the colour of the StarSpark to be launched.
	 * @param col The desired StarSpark colour.
	 */
	public void setColour(String col)
	{
		sparkColour = col;
	}

} // end StarSparkEmitter
