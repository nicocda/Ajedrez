package entidades;

public class Peon extends Trebejo {
	private boolean seMovio;

	public Peon(char t, int x, int y, boolean c, int d1, int d2){
		
		super(t, x, y, c, d1, d2);
		boolean s;
		if(y== 1 || y==6)s=false;
		else s=true;
		setSeMovio(s);
	
	}
		
	
	//Get-Set
	public boolean isSeMovio() {
		return seMovio;
	}

	public void setSeMovio(boolean seMovio) {
		this.seMovio = seMovio;
	}

	
	
}
