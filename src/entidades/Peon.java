package entidades;

public class Peon extends Trebejo {


	public Peon(char t, int x, int y, boolean c, int d1, int d2){
		
		super(t, x, y, c, d1, d2);
	
	}
		
	public boolean movimientoPermitido(int posX, int posY, boolean estadoPosicionFinal){
		if (posY > 7 || posY < 0 || posX > 7 || posX < 0)
            return false;
		else
		{
			if (estadoPosicionFinal)
			{
				if(this.getColor())
				{
					if(this.posY+1==posY && (this.posX +1==posX || this.posX-1== posX))
						return true;
					else
						return false;
				}
				else
				{
					if(this.posY-1==posY && (this.posX +1==posX || this.posX-1== posX))
						return true;
					else
						return false;
				}
			}
			else{
				if(this.getColor())
				{
					if(this.posY==1)
					{
						if(this.posX==posX && (this.posY+2==posY || this.posY+1==posY))
							return true;
						else return false;
					}
					else 
					{
						if(this.posX==posX &&  this.posY+1==posY)
								return true;
						else return false;
					}
				}
				else
				{
					if(this.posY==6)
					{
						if(this.posX==posX && (this.posY-2==posY || this.posY-1==posY))
							return true;
						else return false;
					}
					else 
					{
						if(this.posX==posX &&  this.posY-1==posY)
								return true;
						else return false;
					}
				}
				
			}
	
		}
	}
	
	
}
