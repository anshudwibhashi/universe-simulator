public class NBody {

	/* TO EXECUTE:
	 *	java NBody 15778800.0 25000.0 data/planets.txt
	 */

	public static double readRadius( String fileName ) {
		In in = new In(fileName);
		int firstItemInFile = in.readInt();
		double radius = in.readDouble();

		return radius;
	}

	public static Planet[] readPlanets( String fileName ) {
		In in = new In(fileName);
		int numPlanets = in.readInt();
		in.readDouble();
		Planet[] planets = new Planet[numPlanets];

		for (int i = 0; i < numPlanets; i++) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();

			planets[i] = new Planet ( xP, yP, xV, yV, mass, img);

		}

		return planets;

	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]); //stack overflow
		double dt = Double.parseDouble(args[1]); //stack overflow
		String filename = args[2];

		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		/** Finished parsing universe info from input file **/

		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		
		for (Planet body : planets) {
			body.draw();
		}

		
		double time = 0;

		while ( time < T) {

			

			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];

			for (int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for (int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");
			
		
			for (Planet body : planets) {
				body.draw();
			}

			sleep(10);
			time += dt;

		}
		

	}

	/**
     * A method that sleeps for x milliseconds.
     * Cite: was written by me for an earlier class - taken then from stack overflow
     */
    public static void sleep(int x) {
        try { Thread.sleep(x);
            } 
        catch (InterruptedException ie){
                ie.printStackTrace();
        }
    }


}