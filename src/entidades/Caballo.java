package entidades;

public class Caballo extends Trebejo {

	
	public Caballo(char t, int x, int y, boolean c, int d1, int d2){
		
		super(t, x, y, c, d1, d2);
	}
	
	
	
		public boolean movimientoPermitido(int posX, int posY, boolean estadoPosicionFinal){
			 if (posY > 7 || posY < 0 || posX > 7 || posX < 0)
		            return false;
				else{
					if((this.posX ==posX+2 && this.getPosY() == posY+1) ||
							(this.getPosX()==posX+2 && this.getPosY() == posY-1) ||
							(this.getPosX()==posX-2 && this.getPosY() == posY+1) ||
							(this.getPosX()==posX-2 && this.getPosY() == posY-1)||
						(this.getPosY()==posY+2 && this.getPosX() == posX+1) ||
						(this.getPosY()==posY+2 && this.getPosX() == posX-1) ||
						(this.getPosY()==posY-2 && this.getPosX() == posX+1) ||
						(this.getPosY()==posY-2 && this.getPosX() == posX-1))
					return true;
		        else return false;}
					
		}			
}
