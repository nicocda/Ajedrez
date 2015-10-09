package entidades;

import java.util.ArrayList;

public class Partida {
	
	private Jugador blanco, negro;
	private ArrayList<Trebejo> fichas = new ArrayList<Trebejo>();
	private boolean turno;
	
	public Partida()
	{
	}
	
	public Partida(Jugador b, Jugador n, ArrayList<Trebejo> fichas, boolean turno){
		
		setBlanco(b);
		setNegro(n);
		setFichas(fichas);
		setTurno(turno);
	}
	
	public boolean getTurno() {
		return turno;
	}

	public void setTurno(boolean turno) {
		this.turno = turno;
	}

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
