package entidades;

public class Alfil extends Trebejo {

	
	public Alfil(char t, int x, int y, boolean c, int d1, int d2){
		
		super(t, x, y, c, d1, d2);
	}
	
	
	public boolean movimientoPermitido(int posX, int posY, Trebejo treb){
		
		if (posY > 7 || posY < 0)
            return false;
		if (posX > 7 || posX < 0)
            return false;
		
		int difFil, difCol;
		difFil = Math.abs(this.posX - posX);
        difCol = Math.abs(this.posY - posY);
        if(difFil == difCol) 
        	if(this.color != treb.color)
			return true;
			return false;
        	
	}
}
