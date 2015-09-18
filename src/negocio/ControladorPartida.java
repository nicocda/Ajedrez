package negocio;
import java.sql.SQLException;
import java.util.ArrayList;

import datos.*;
import entidades.Jugador;
import entidades.Partida;
import entidades.Trebejo;

public class ControladorPartida {

	public Partida cargarPartida(int dni1, int dni2) throws Exception{
		//CatalogoPartida cp = new CatalogoPartida();
		//CatalogoTrebejos ct = new CatalogoTrebejos();
		//CatalogoJugadores cj = new CatalogoJugadores();
		CatalogoPartidaPropuesto cp = new CatalogoPartidaPropuesto();
		CatalogoTrebejosPropuesto ct = new CatalogoTrebejosPropuesto();
		CatalogoJugadoresPropuesto cj = new CatalogoJugadoresPropuesto();
		Jugador j1 = cj.buscarJugador(dni1);
		Jugador j2 = cj.buscarJugador(dni2);
		
		if (!cp.existePartida(dni1, dni2))
		{
			cp.agregarPartida(dni1, dni2);
			//ct.addTrebejos(dni1, dni2);
		}
		ArrayList<Trebejo> trebejos = ct.buscarTrebejos(dni1,dni2);
		Partida p = new Partida(j1, j2, trebejos);
		return (p);
		
	}




}
