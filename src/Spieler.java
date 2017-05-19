import processing.core.PApplet;

/**
 *  Ein Spieler verwaltet die anzahl Spieler.
 */
class Spieler {
	
	 PApplet parent;
	 
	
  int spielerPunkte = 0;
  String name;
  int spielerNummer;

  Spieler(int newnr,PApplet p) {
	  parent = p;
    spielerNummer=newnr -1;
    name = "Spieler "+ newnr;
  }
  /**
   *  zeichnet die Spieler
   */
  void myDraw() {
    // System.out.println(name);
    parent.textSize(20);
    parent.text(name+" Punktzahl "+ spielerPunkte, 50, 450+25*spielerNummer);
  }
  
/**
   *  summiert die aktuelle Punkte mit der Spielerpunktzahl.
   */
  void addPunkte(int neuePunkte) {
    spielerPunkte += neuePunkte;
  }
}