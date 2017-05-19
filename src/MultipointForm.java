import processing.core.PApplet;

/**
 *  A MultipointForm is a Form with two additional points ('Punkte').
 *  A MultipointForm sets the values for all its children.
 *  
 *  @author Philippe Krüttli
 *  
 */

public abstract class MultipointForm extends Form {
	PApplet parent; // The Parent PApplet that we will render ourselves onto
	int difX;
	int difY;

	Punkt punktB;
	Punkt punktC;
	String name = "";

	MultipointForm(PApplet p) {
		super(p);
		parent = p;
	}

	MultipointForm(PApplet p, int newx1, int newy1, int newx2, int newy2, int newFarbe) {
		super(p);
		parent = p;
		punktA = new Punkt(p, newx1, newy1);
		punktB = new Punkt(p, newx2, newy2);

		fuellFarbe = newFarbe;
	}

	MultipointForm(PApplet p, int newx1, int newy1, int newx2, int newy2, int newx3, int newy3, int newFarbe) {
		super(p);
		parent = p;
		punktA = new Punkt(p, newx1, newy1);
		punktB = new Punkt(p, newx2, newy2);
		punktC = new Punkt(p, newx3, newy3);

		fuellFarbe = newFarbe;
	}

	abstract void verschiebe(float mausX, float mausY);

	abstract void zoomen(float zoom);
}
