import processing.core.PApplet;

public class Karte {
	
	PApplet parent;
	
	Karte(PApplet p){
		 parent = p;
	}
	/**
	 *  Karte erstellt die verschiedenen Kartentypen.
	 *  Karte verwaltet die Position der Karten.
	 *  Karte verwaltet die Werte.
	 */

	
	
	  int xpos = 50;  //anzeigeXPos
	  int ypos = 600;  //anzeigeYPos
	  int wert;
	  Kartentyp typ;
	  /**
	   *  zieht zufällig die 56 Karten, mit unterschiedlichen Wahrscheinlichkeitswerten.
	   *  zieht im Kartentyp BONUS wieder zufällig 5 Karten mit gleichen Wahrscheinlichkeitswerten.
	   */
	  void ziehen() {
	    int z = (int) parent.random(56) + 1;  
	    if (z <= 25) {
	      int w = (int) parent.random(5);
	      typ = Kartentyp.BONUS;
	      wert = 200 + w * 100;
	    } else if (z > 25 && z <= 30) {
	      typ = Kartentyp.STRASSE;
	    } else if (z > 30 && z <= 35) {
	      typ = Kartentyp.PLUS_MINUS;
	    } else if (z > 35 && z <= 40) {
	      typ = Kartentyp.KIRSCHE;
	    } else if (z > 40 && z <= 45) {
	      typ = Kartentyp.FEUERWERK;
	    } else if (z > 45 && z <= 46) {
	      typ = Kartentyp.KLEEBLATT;
	    } else if (z > 46 && z <= 56) {
	      typ = Kartentyp.STOP;
	    }
	  }
	  /**
	   *  zeigt die Karten an
	   */
	  void anzeigen() {
	    parent.fill(255,0,0);
	    parent.textSize(25);
	    switch (typ) {      
	    case BONUS:
	      parent.text("Aktuelle Karte: " + typ + " " + wert, xpos, ypos);
	      break;
	    case STRASSE:
	      parent.text("Aktuelle Karte: " + typ + " Bonus 2000", xpos, ypos);
	      break;
	    case KLEEBLATT:
	      parent.text("Aktuelle Karte: " + typ + " 2x Tutto", xpos, ypos);
	      break;
	    case KIRSCHE:
	      parent.text("Aktuelle Karte: " + typ + " x2", xpos, ypos);
	      break;
	    case FEUERWERK:
	      parent.text("Aktuelle Karte: " + typ + " Kein Verlust", xpos, ypos);
	      break;
	    case PLUS_MINUS:
	      parent.text("Aktuelle Karte: " + typ + " +/- 1000", xpos, ypos);
	      break;
	    case STOP:
	      parent.text("Aktuelle Karte: " + typ + " Spielzug beendet", xpos, ypos);
	      break;
	    }
	  }
	

}
