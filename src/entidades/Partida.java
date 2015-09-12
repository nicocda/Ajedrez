package entidades;

import java.util.ArrayList;

public class Partida {
	
	private Jugador blanco, negro;
	private ArrayList<Trebejo> fichas = new ArrayList<Trebejo>();
	
	public Partida()
	{
	}
	
	public Partida(Jugador b, Jugador n, ArrayList<Trebejo> fichas){
		
		setBlanco(b);
		setNegro(n);
		setFichas(fichas);
	}
	
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
	public ArrayList<Trebejo> getFichas() {
		return fichas;
	}
	public void setFichas(ArrayList<Trebejo> fichas){
		this.fichas = fichas;
	}
	
	
}
