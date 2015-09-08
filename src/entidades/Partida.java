package entidades;

import java.util.ArrayList;

public class Partida {
	
	private int id;
	private Jugador blanco, negro;
	private ArrayList<Trebejo> fichas = new ArrayList<Trebejo>();
	
	//Get-Set
	public Jugador getBlanco() {
		return blanco;
	}
	public void setBlanco(Jugador blanco) {
		this.blanco = blanco;
	}
	public Jugador getNegro() {
		return negro;
	}
	public void setNegro(Jugador negro) {
		this.negro = negro;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Trebejo> getFichas() {
		return fichas;
	}
	
	
}
