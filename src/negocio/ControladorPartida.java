package negocio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import datos.*;
import entidades.Jugador;
import entidades.Partida;
import entidades.Trebejo;

public class ControladorPartida {
		CatalogoPartidaPropuesto cp = new CatalogoPartidaPropuesto();
		CatalogoTrebejosPropuesto ct = new CatalogoTrebejosPropuesto();
		CatalogoJugadoresPropuesto cj = new CatalogoJugadoresPropuesto();
	public Partida cargarPartida(int dni1, int dni2) {
		//CatalogoPartida cp = new CatalogoPartida();
		//CatalogoTrebejos ct = new CatalogoTrebejos();
		//CatalogoJugadores cj = new CatalogoJugadores();
		
		
		Jugador j1 = cj.buscarJugador(dni1);
		Jugador j2 = cj.buscarJugador(dni2);
		
		if (!cp.existePartida(dni1, dni2))
		{
			cp.agregarPartida(dni1, dni2);
			ct.addTrebejos(dni1, dni2);
		}
		ArrayList<Trebejo> trebejos = ct.buscarTrebejos(dni1,dni2);
		Partida p = new Partida(j1, j2, trebejos);
		return (p);
		
	}

	public ArrayList<Partida> buscarPartidas(int dni){
		ArrayList<Partida> listaPartidas = new ArrayList<Partida>();
		//ArrayList<Jugador>	jugadores = new ArrayList<Jugador>();
		listaPartidas = cp.buscarPartidas(dni);
		/*for (Partida p : listaPartidas){
			if(dni == p.getBlanco().getDni()){
				jugadores.add(p.getNegro());
			}else{
				jugadores.add(p.getBlanco());
			}
		};*/
		return(listaPartidas);
		/*String query="select  p.blanco, p.negro, jn.nombre, jn.apellido, jb.nombre, jb.apellido from partida p inner join jugadores jb on p.blanco=jb.dni inner join jugadores jn on p.negro=jn.dni where blanco=? or negro=?";
		cp.buscarPartidas(dni);
		
	
			try {
				while(resultado.next())
					{
					if(Integer.parseInt(resultado.getString("p.blanco")) != Integer.parseInt(dni)){
						textArea.append(resultado.getString("p.blanco")+" "+resultado.getString("jb.nombre")+" "+resultado.getString("jb.apellido")+"\n");
					}
					else{
						textArea.append(resultado.getString("p.negro")+" "+resultado.getString("jn.nombre")+" "+resultado.getString("jn.apellido")+"\n");
					}
					}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		*/
		
	}


}
