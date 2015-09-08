package entidades;

public abstract class Trebejo{
	private int posX, posY;
	private boolean color, muerte;
	
	
	
	public Trebejo(int x, int y, boolean c, boolean m){
		
		setPosX(x);
		setPosY(y);
		setColor(c);
		setMuerte(m);
		
	}
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
	public boolean isColor() {
		return color;
	}
	public void setColor(boolean color) {
		this.color = color;
	}
	public boolean isMuerte() {
		return muerte;
	}
	public void setMuerte(boolean muerte) {
		this.muerte = muerte;
	}
	
}

