package entidades;

public class Peon extends Trebejo {
	private boolean seMovio;

	public Peon(int x, int y, boolean c, boolean m, boolean s){
		
		super(x, y, c, m);
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
