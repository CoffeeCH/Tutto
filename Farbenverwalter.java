import processing.core.PApplet;

/**
 * A Farbenverwalter is the color administrator.
 * A Farbenverwalter changes and sets the color of every form.
 * 
 * @author Philippe Krüttli
 * 
 */
public class Farbenverwalter {
	PApplet parent; // The Parent PApplet that we will render ourselves onto
	boolean farbeAendern = false;
	
	/**
	 * Changes the color of the as parameter given form.
	 * 
	 * @param k:int keyCode of the pressedKey
	 * @param f:Form the form whose color the user wants to change
	 * @return:Form returns the form where the color has changed
	 */
	Form farbeAendern(int k, Form f) {
		switch (k) {
		case 96:
			f.setColor(255, 255, 255);
			break;
		case 97:
			f.setColor(255, 0, 0);
			break;
		case 98:
			f.setColor(255, 127, 0);
			break;
		case 99:
			f.setColor(255, 255, 0);
			break;
		case 100:
			f.setColor(0, 255, 0);
			break;
		case 101:
			f.setColor(0, 255, 255);
			break;
		case 102:
			f.setColor(0, 0, 255);
			break;
		case 103:
			f.setColor(127, 0, 255);
			break;
		case 104:
			f.setColor(255, 0, 127);
			break;
		case 105:
			f.setColor(0, 0, 0);
			break;
		default:
			break;
		}
		return f;
	}
}