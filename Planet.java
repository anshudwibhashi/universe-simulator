public class Planet {

	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, 
					double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}

	public Planet( Planet p ) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance( Planet p ) {
		double xd = p.xxPos - this.xxPos;
		double yd = p.yyPos - this.yyPos;
		return Math.sqrt(xd*xd + yd*yd);
	}

	public double calcForceExertedBy( Planet p ) {
		double g = (6.67*Math.pow(10,-11));
		return ( g*this.mass*p.mass / ( calcDistance(p)*calcDistance(p) ) );
	}

	public double calcForceExertedByX( Planet p ) {
		return ( calcForceExertedBy(p) * (p.xxPos - this.xxPos) / calcDistance(p)  );
	}

	public double calcForceExertedByY ( Planet p ) {
		return ( calcForceExertedBy(p) * (p.yyPos - this.yyPos) / calcDistance(p)  );
	}

	public double calcNetForceExertedByX( Planet[] planets ) {
		double xForce = 0;
		for (Planet body : planets) {
			if ( !this.equals(body) ) {
				xForce += calcForceExertedByX(body);
			}
		}
		return xForce;
	}

	public double calcNetForceExertedByY ( Planet[] planets ) {
		double yForce = 0;
		for (Planet body : planets) {
			if ( !this.equals(body) ) {
				yForce += calcForceExertedByY(body);
			}
		}
		return yForce;
	}

	public void update( double dt, double fX, double fY ) {
		double xAcc = (fX / mass);
		double yAcc = (fY / mass);

		xxVel += dt * xAcc;
		yyVel += dt * yAcc;

		xxPos += dt * xxVel;
		yyPos += dt * yyVel;

	}

	public void draw () {
		StdDraw.picture ( xxPos, yyPos, "images/" + imgFileName);
	}


}