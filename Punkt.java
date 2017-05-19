import processing.core.PApplet;

/**
 * A Punkt is part of every form.
 * A Punkt is a point.
 * 
 * @author Philippe Krüttli
 *
 */

public class Punkt {
	PApplet parent; // The Parent PApplet that we will render ourselves onto
	float xPosInPix;
	float yPosInPix;

	Punkt(PApplet p) {
		parent = p;
	}

	Punkt(PApplet p, float newX, float newY) {
		parent = p;
		xPosInPix = newX;
		yPosInPix = newY;
	}

	Punkt abstandZu(Punkt p) {
		return new Punkt(parent, xPosInPix - p.xPosInPix, yPosInPix - p.yPosInPix);
	}
	
	/**
	 * Moves a point, using the point ('Punkt') 'p'.
	 * @param p:Punkt given point ('Punkt') to move another point
	 */
	void verschiebeUm(Punkt p) {
		xPosInPix -= p.xPosInPix;
		yPosInPix -= p.yPosInPix;
	}
}
