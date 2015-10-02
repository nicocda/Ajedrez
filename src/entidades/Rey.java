package entidades;

public class Rey extends Trebejo {

	
	
	
	public Rey(char t, int x, int y, boolean c, int d1, int d2){
		
		super(t, x, y, c, d1, d2);
	}
	public boolean movimientoPermitido(int posX, int posY, Trebejo treb){
		if (posY > 7 || posY < 0 || posX > 7 || posX < 0)
            return false;
		else{
			if (treb==null){
			if(posX<=this.posX+1 && posX>=this.posX-1 && posY<=this.posY+1 && posY>=this.posY-1)
			return true;
        else return false;}
			else if(this.color != treb.color)
				return true;
			else return false;
		}
	}
}
