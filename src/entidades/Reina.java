package entidades;

public class Reina extends Trebejo {

	
	
	public Reina(char t, int x, int y, boolean c, int d1, int d2){
		
		super(t, x, y, c, d1, d2);
	}
	public boolean movimientoPermitido(int posX, int posY, boolean estadoPosicionFinal){
		return true;
	}
}
