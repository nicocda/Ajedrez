package entidades;

public abstract class Trebejo{
	protected int posX, posY,dni1,dni2;
	protected char tipo;
	protected boolean color;
	
	@Override
	public String toString(){
		return "Tipo: "+this.tipo+"  Posicion: ("+this.posX+"),("+this.posY+")";
	}
	
	public Trebejo(char t, int x, int y, boolean c, int d1, int d2){

		setTipo(t);
		setPosX(x);
		setPosY(y);
		setColor(c);
		setDni1(d1);
		setDni2(d2);
		
	}
	
	
	public abstract boolean movimientoPermitido(int posX, int posY, boolean estadoPosicionFinal);	
		
	//Get-Set
		public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public boolean getColor() {
		return color;
	}
	public void setColor(boolean color) {
		this.color = color;
	}
	public int getDni1() {
		return dni1;
	}
	public void setDni1(int dni1) {
		this.dni1 = dni1;
	}
	public int getDni2() {
		return dni2;
	}
	public void setDni2(int dni2) {
		this.dni2 = dni2;
	}
	public char getTipo() {
		return tipo;
	}
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	
}

