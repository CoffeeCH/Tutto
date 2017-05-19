import processing.core.PApplet;

/**
 * A Dreieck is a form with three points ('Punkte').
 * A Dreieck is a triangle.
 * 
 * @author Philippe Krüttli
 *
 */

public class Dreieck extends MultipointForm {
	PApplet parent; // The Parent PApplet that we will render ourselves onto
	String name = "dreieck";

	Dreieck(PApplet p, int newx1, int newy1, int newx2, int newy2, int newx3, int newy3, int newFarbe) {
		super(p, newx1, newy1, newx2, newy2, newFarbe);
		punktC = new Punkt(p, newx3, newy3);
		parent = p;
	}

	Dreieck(PApplet p) {
		super(p, 200, 300, 350, 400, 300, 300, p.color(100, 100, 100));
		parent = p;
	}

	void myDraw() {
		initDraw();
		parent.stroke(0, 0, 0);
		parent.triangle(punktA.xPosInPix, punktA.yPosInPix, punktB.xPosInPix, punktB.yPosInPix, punktC.xPosInPix,
				punktC.yPosInPix);
	}
	
	/**
	 * Checks if vertical mouse-position is inside the Dreieck.
	 * @param y:float vertical mouse-position
	 * @return:boolean true if it is inside, no if not
	 */
	boolean yInRange(float y) {
		float yMin = punktA.yPosInPix;
		float yMax = yMin;

		if (punktB.yPosInPix > yMax) {
			yMax = punktB.yPosInPix;
		}

		if (punktB.yPosInPix < yMin) {
			yMin = punktB.yPosInPix;
		}

		if (punktC.yPosInPix > yMax) {
			yMax = punktC.yPosInPix;
		}

		if (punktC.yPosInPix < yMin) {
			yMin = punktC.yPosInPix;
		}

		if (y >= yMin && y <= yMax) {
			return true;
		}

		return false;
	}
	
	/**
	 * Checks if horizontal mouse-position is inside the Dreieck.
	 * @param x:float horizontal mouse-position
	 * @param y:float vertical mouse-position
	 * @return:boolean true if it is inside, no if not
	 */
	boolean xInRange(float x, float y) {
		Kante k = new Kante(parent, punktA, punktB);
		float minX = k.getXfromY(y);
		float maxX = minX;
		Kante k2 = new Kante(parent, punktB, punktC);

		float tempX = k2.getXfromY(y);

		if (tempX > maxX) {
			maxX = tempX;
		}

		if ((tempX < minX && tempX != 0) || minX == 0) {
			minX = tempX;
		}

		Kante k3 = new Kante(parent, punktA, punktC);

		tempX = k3.getXfromY(y);

		if (tempX > maxX) {
			maxX = tempX;
		}

		if ((tempX < minX && tempX != 0) || minX == 0) {
			minX = tempX;
		}

		if (x >= minX && x <= maxX) {
			return true;
		}

		return false;
	}
	
	/**
	 * Checks if user clicked on Dreieck.
	 * Uses yInRange(mausY) and xInRange(mausX) to check it.
	 */
	void klickDarauf(float mausX, float mausY) {
		if (yInRange(mausY)) {
			if (xInRange(mausX, mausY)) {
				istAusgewaehlt = true;
			}
		}
	}
	
	/**
	 * Moves each point ('Punkt') of the Dreieck, using the mouse-positions.
	 * @param pMaus:Punkt created point ('Punkt') using mouse-positions
	 */
	void verschiebeNach(Punkt pMaus) {
		Punkt abstand = punktC.abstandZu(pMaus);

		punktB.verschiebeUm(abstand);
		punktA.verschiebeUm(abstand);
		punktC.verschiebeUm(abstand);
	}
	
	/**
	 * Starts verschiebeNach.
	 * Creates point using mouse-positions.
	 */
	void verschiebe(float mausX, float mausY) {
		verschiebeNach(new Punkt(parent, mausX, mausY));
		// punktB = getLinkerPunkt();
	}
	
	/**
	 * Enlarges or shrinks the form, using the zoom-factor.
	 */
	void zoomen(float zoom) {
		float xM = (punktA.xPosInPix + punktB.xPosInPix) / 2;
		float yM = (punktA.yPosInPix + punktB.yPosInPix) / 2;
		float r = parent.dist(xM, yM, punktA.xPosInPix, punktA.yPosInPix);
		float r2 = r + (10 * zoom);
		float zf = r2 / r;

		float dXA = (punktA.xPosInPix - xM) * zf;
		float dYA = (punktA.yPosInPix - yM) * zf;
		float dXB = (punktB.xPosInPix - xM) * zf;
		float dYB = (punktB.yPosInPix - yM) * zf;
		float dXC = (punktC.xPosInPix - xM) * zf;
		float dYC = (punktC.yPosInPix - yM) * zf;

		punktA.xPosInPix = dXA + xM;
		punktA.yPosInPix = dYA + yM;
		punktB.xPosInPix = dXB + xM;
		punktB.yPosInPix = dYB + yM;
		punktC.xPosInPix = dXC + xM;
		punktC.yPosInPix = dYC + yM;
	}
	
	/**
	   * Rotates a form using the key-arrows.
	   * <b>Doesn't work yet.</b>
	*/
	void drehen(int k) {
	}
	
	/**
	 * Returns this object.
	 * 
	 * @return:Object now used Object (this 'Dreieck')
	 */
	public Object returnObject() {
		return this;
	}
	
	/**
	 * Calculates the area of this Dreieck.
	 */
	float flaecheBerechnen() {
		float flaeche = 0;
		float winkelAlphaAusSinus = 0;
		float groessererWinkel = 0;
		float kleinererWinkel = 0;
		float groessereSteigung = 0;
		float kleinereSteigung = 0;

		float temp1 = ((punktA.yPosInPix) - (punktB.yPosInPix)) / ((punktA.xPosInPix) - (punktB.xPosInPix));
		float temp2 = ((punktA.yPosInPix) - (punktC.yPosInPix)) / ((punktA.xPosInPix) - (punktC.xPosInPix));

		if (temp1 > temp2) {
			groessereSteigung = temp1;
			kleinereSteigung = temp2;
		} else {
			groessereSteigung = temp2;
			kleinereSteigung = temp1;
		}

		groessererWinkel = parent.atan(groessereSteigung);
		kleinererWinkel = parent.atan(kleinereSteigung);

		winkelAlphaAusSinus = groessererWinkel - kleinererWinkel;

		flaeche = ((parent.dist(punktA.xPosInPix, punktA.yPosInPix, punktC.xPosInPix, punktC.yPosInPix)
				* parent.dist(punktA.xPosInPix, punktA.yPosInPix, punktB.xPosInPix, punktB.yPosInPix)) / 2)
				* parent.sin(winkelAlphaAusSinus);
		return flaeche;
	}
}