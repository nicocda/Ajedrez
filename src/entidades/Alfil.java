package entidades;

public class Alfil extends Trebejo {

	
	public Alfil(char t, int x, int y, boolean c, int d1, int d2){
		
		super(t, x, y, c, d1, d2);
	}
	
	
	public boolean movimientoPermitido(int posX, int posY, Trebejo treb){
		
		if (posY > 7 || posY < 0 || posX > 7 || posX < 0)
            return false;
		else{
			int movX =Math.abs(this.posX - posX);
			int movY = Math.abs(this.posY - posY);
			if (treb==null){
			if(movX == movY)
			return true;
        else return false;}
			else if(this.color != treb.color)
				return true;
			else return false;
		}
	}
}
