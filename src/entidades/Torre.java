package entidades;

public class Torre extends Trebejo {

	
	
	public Torre(char t, int x, int y, boolean c, int d1, int d2){
		
		super(t, x, y, c, d1, d2);
	}
	
	
	public boolean movimientoPermitido(int posX, int posY, boolean estadoPosicionFinal){
		
		if (posY > 7 || posY < 0 || posX > 7 || posX < 0)
            return false;
		else{
			
			if(this.posX == posX || this.posY == posY)
			return true;
        else return false;}
			
	}
		
}
