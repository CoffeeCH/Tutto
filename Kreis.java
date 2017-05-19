import processing.core.PApplet;

/**
 * A Kreis is a form with only one point.
 * A Kreis is a circle or an ellipse
 * 
 * @author Philippe Krüttli
 * 
 */

public class Kreis extends SimpleForm {
	PApplet parent; // The Parent PApplet that we will render ourselves onto
	//Ganz neuer Kommentar
	String name = "kreis";

	Kreis(PApplet p) {
		super(p, 400, 400);
		parent = p;
	}

	Kreis(PApplet p, int newMitteX, int newMitteY, int newBreite, int newHoehe, int newFarbe) {
		super(p, newMitteX, newMitteY, newBreite, newHoehe, newFarbe);
		parent = p;
	}
	
	/**
	 * Draws the circle / ellipse.
	 * Sets border to black, all other values to already set values.
	 */
	void myDraw() {
		initDraw();
		parent.stroke(0, 0, 0);
		parent.ellipse(punktA.xPosInPix, punktA.yPosInPix, breite, hoehe);
	}
	
	/**
	 * Checks if user clicked on Kreis.
	 * If yes, it changes its color to red.
	 */
	void klickDarauf(float mausX, float mausY) {
		float testRadius = breite / 2;

		if (breite > hoehe) {
			testRadius = hoehe / 2;
			float dX = mausX - punktA.xPosInPix;
			dX *= hoehe / breite;
			mausX = dX + punktA.xPosInPix;
		} else if (hoehe > breite) {

			float dY = mausY - punktA.yPosInPix;
			dY *= breite / hoehe;
			mausY = dY + punktA.yPosInPix;
		}
		// else = circle

		if (parent.dist(punktA.xPosInPix, punktA.yPosInPix, mausX, mausY) <= testRadius) {
			istAusgewaehlt = true;
		}
	}
	
	/**
	 * Rotates the circle / ellipse ('Kreis').
	 * Makes a 90° turn.
	 */
	void drehen(int k) {
		float alteBreite = breite;

		if (k == 37 || k == 39) {
			breite = hoehe;
			hoehe = alteBreite;
		}
	}
	
	/**
	 * Calculates the area of the form.
	 */
	float flaecheBerechnen() {
		float flaeche = 0;
		flaeche = ((breite / 2) * (hoehe / 2)) * parent.PI;
		return flaeche;
	}
}
