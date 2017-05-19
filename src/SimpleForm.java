import processing.core.PApplet;

/**
 * A SimpleForm is a form with only two points ('Punkte').
 * A SimpleForm has a width ('breite') and a height ('hoehe').
 * 
 * @author Philippe Krüttli
 *
 */

public abstract class SimpleForm extends Form {
	PApplet parent; // The Parent PApplet that we will render ourselves onto
	float breite;
	float hoehe;

	SimpleForm(PApplet p) {
		super(p);
		parent = p;
	}

	SimpleForm(PApplet p, int newx, int newy, int newBreite, int newHoehe, int newFarbe) {
		super(p);
		parent = p;
		punktA = new Punkt(p, newx, newy);
		breite = newBreite;
		hoehe = newHoehe;
		fuellFarbe = newFarbe;
	}

	SimpleForm(PApplet p, int newX, int newY) {
		super(p);
		parent = p;
		punktA = new Punkt(p, newX, newY);
		breite = 50;
		hoehe = 50;
		fuellFarbe = parent.color(120, 120, 120);
	}
	
	/**
	 * Creates a point ('Punkt') with the mouse-positions.
	 * This point ('Punkt') is used to move a form.
	 */
	void verschiebe(float mausX, float mausY) {
		punktA = new Punkt(parent, mausX, mausY);
	}
	
	/**
	 * Zooms a form, using its height ('hoehe') and width ('breite').
	 * Can't shrink smaller than 20px * 20px.
	 */
	void zoomen(float zoom) {
		if ((breite > 20 && hoehe > 20 && zoom < 0) || (breite > 0 && hoehe > 0 && zoom > 0)) {
			breite += (10 * zoom);
			hoehe += (10 * zoom);
		}
	}
}
