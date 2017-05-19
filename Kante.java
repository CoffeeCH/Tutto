import processing.core.PApplet;

/**
 * A Kante is a form with two points ('Punkte').
 * A Kante is a line.
 * 
 * @author Philippe Krüttli
 * 
 */

//Ganz neuer Kommentar			

public class Kante extends MultipointForm {
	PApplet parent; // The Parent PApplet that we will render ourselves onto
	String name = "kante";
	// Punkt punktRechts; = PunktA
	// Punkt punktLinks; = PunktB = left bottom point is favorite

	Kante(PApplet p, int newx1, int newy1, int newx2, int newy2, int newDicke, int newFarbe) {
		this(p, new Punkt(p, newx1, newy1), new Punkt(p, newx2, newy2));
		parent = p;
		dicke = newDicke;
		fuellFarbe = newFarbe;
	}

	Kante(PApplet p) {
		this(p, new Punkt(p, 500, 400), new Punkt(p, 600, 700));
		parent = p;
		fuellFarbe = parent.color(0, 0, 0);
	}

	Kante(PApplet p, Punkt punkt1, Punkt punkt2) {
		super(p);
		parent = p;
		if (punkt1.xPosInPix > punkt2.xPosInPix) {
			punktA = punkt1;
			punktB = punkt2;
		} else if (punkt1.xPosInPix < punkt2.xPosInPix) {
			punktA = punkt2;
			punktB = punkt1;
		} else {
			if (punkt1.yPosInPix > punkt2.yPosInPix) {
				punktA = punkt2;
				punktB = punkt1;
			} else {
				punktA = punkt1;
				punktB = punkt2;
			}
		}
	}
	
	/**
	 * Moves both points ('Punkte') using the mouse-positions.
	 * 
	 * @param pMaus:Punkt a point created, using the mouse-positions
	 */
	void verschiebeNach(Punkt pMaus) {
		Punkt abstand = punktB.abstandZu(pMaus);

		punktB.verschiebeUm(abstand);
		punktA.verschiebeUm(abstand);
	}

	/**
	 * Checks which point ('Punkt') is on the left.
	 * 
	 * @return:Punkt returns the left point
	 */
	Punkt getLinkerPunkt() {
		return punktB;
	}
	
	/**
	 * Checks if a point is on the line ('Kante').
	 * 
	 * @param xP:float horizontal position to check
	 * @param yP:float vertical position to check
	 * @param breite:float range, so allowed value is not only on Kante's pixels
	 * @return:boolean returns yes if it is on the line, no if not
	 */
	boolean hatPunkt(float xP, float yP, float breite) {
		float x = getXfromY(yP);

		if (parent.dist(xP, yP, x, yP) < breite) {
			return true;
		}

		return false;
	}
	
	/**
	 * Calculates a horizontal value of a point on the line ('Kante').
	 * 
	 * @param y:float given vertical value
	 * @return:float calculated horizontal value
	 */
	float getXfromY(float y) {

		float steigung = -(punktB.yPosInPix - punktA.yPosInPix) / (punktB.xPosInPix - punktA.xPosInPix);
		float x = 0;

		if ((y > punktB.yPosInPix && y < punktA.yPosInPix) || (y > punktA.yPosInPix && y < punktB.yPosInPix)) {
			x = punktB.xPosInPix - ((y - punktB.yPosInPix) / steigung);

			if (!((x > punktB.xPosInPix && x < punktA.xPosInPix) || (x > punktA.xPosInPix && x < punktB.xPosInPix))) {
				x = 0;
			}
		}

		return x;
	}

	/**
	 * Calculates a vertical value of a point on the line ('Kante').
	 * 
	 * @param x:float given horizontal value
	 * @return:float calculated vertical value
	 */
	float getYfromX(float x) {
		float steigung = -(punktB.yPosInPix - punktA.yPosInPix) / (punktB.xPosInPix - punktA.xPosInPix);
		float y = 0;

		if ((x > punktB.xPosInPix && x < punktA.xPosInPix) || (x > punktA.xPosInPix && x < punktB.xPosInPix)) {
			y = punktB.yPosInPix - ((x - punktB.xPosInPix) * steigung);
			if (!((y > punktB.yPosInPix && y < punktA.yPosInPix) || (y > punktA.yPosInPix && y < punktB.yPosInPix))) {
				y = 0;
			}
		}

		return y;
	}
	
	/**
	 * Checks if user clicked on line ('Kante').
	 */
	void klickDarauf(float mausX, float mausY) {
		if (hatPunkt(mausX, mausY, dicke)) {
			istAusgewaehlt = true;
		}
	}
	
	/**
	 * Moves a point using another point, created with the mouse-positions.
	 */
	void verschiebe(float mausX, float mausY) {
		// punktA = new Punkt(mausX, mausY);
		verschiebeNach(new Punkt(parent, mausX, mausY));
		punktB = getLinkerPunkt();
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

		punktA.xPosInPix = dXA + xM;
		punktA.yPosInPix = dYA + yM;
		punktB.xPosInPix = dXB + xM;
		punktB.yPosInPix = dYB + yM;
	}
	
	
	/**
	 * Draws the line ('Kante') using the already set values.
	 */
	void myDraw() {
		initDraw();

		if (istAusgewaehlt) {
			parent.stroke(200, 0, 0);
		} else {
			parent.stroke(fuellFarbe);
		}

		parent.line(punktA.xPosInPix, punktA.yPosInPix, punktB.xPosInPix, punktB.yPosInPix);
	}
	
	/**
	   * Rotates a form using the key-arrows.
	   * <b>Doesn't work yet.</b>
	*/
	void drehen(int k) {
	}

	/**
	 * Calculates the area of the line ('Kante').
	 */
	float flaecheBerechnen() {
		float flaeche = 0;
		flaeche = parent.dist(punktA.xPosInPix, punktA.yPosInPix, punktB.xPosInPix, punktB.yPosInPix) * dicke;
		return flaeche;
	}
}
