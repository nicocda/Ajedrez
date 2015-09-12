package datos;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import conexion.ConexionPropuesta;
import entidades.Trebejo;
import entidades.Partida;

public class CatalogoPartidaPropuesto
{
	private ArrayList<Partida> listaPartidas;
	
	
	//Método para Obtener todas las partidas iniciadas de un jugador
	public void buscarPartidas(int j1)
	{
		listaPartidas = new ArrayList<Partida>();
		String sql="select p.blanco, p.negro from partida where blanco="+j1+" or negro="+j1;
		Statement sentencia=null;
		ResultSet rs=null;
		
		try 
		{			
			sentencia= ConexionPropuesta.getInstancia().getConn().createStatement();
			rs= sentencia.executeQuery(sql);
			//Si no existe ninguna, la lista queda nula, debido a que no se ingresa nunca al while
			//Tener en cuenta para futuras validaciones
			while (rs.next())
			{			
				//Si encuentor un registro creo una partida y la comienzo a cargar.
				Partida partida = new Partida();
				
				CatalogoJugadoresPropuesto cj = new CatalogoJugadoresPropuesto();
				CatalogoTrebejosPropuesto ct = new CatalogoTrebejosPropuesto();
				//Con el catalogo ya programado busco los 2 jugadores...
				cj.buscarPorDni(rs.getInt("p.blanco"));
				
				partida.setBlanco(cj.getJugador());
				
				cj.buscarPorDni(rs.getInt("p.negro"));
				
				partida.setNegro(cj.getJugador());
				//...y los trebejos
				ct.buscarTrebejos(rs.getInt("p.blanco"), rs.getInt("p.negro"));
				
				partida.setFichas(ct.getListaTrebejos());				
				listaPartidas.add(partida);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(sentencia!=null && !sentencia.isClosed())
				{
					sentencia.close();
				}
				ConexionPropuesta.getInstancia().CloseConn();
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}	
	}
	
}