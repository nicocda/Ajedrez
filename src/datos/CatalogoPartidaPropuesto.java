package datos;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.ConexionPropuesta;
import conexion.DataConnection;
import entidades.Trebejo;
import entidades.Partida;

public class CatalogoPartidaPropuesto
{
	private ArrayList<Partida> listaPartidas;
	
	
	public boolean existePartida(int j1, int j2) {
		
	
	ResultSet rs = null;
	Statement sentencia=null;
	String query = "select * "
			+ "from partida p "
			+ "where (blanco="+j1+" and negro="+j2+") or (blanco="+j2+" and negro="+j1+") ";
	boolean existePartida = false;
	try{
		sentencia=ConexionPropuesta.getInstancia().getConn().createStatement();
		rs = sentencia.executeQuery(query);
		existePartida = rs.next();
		
	}
	catch(SQLException e)
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
	return(existePartida);
}
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
			
				partida.setBlanco(cj.buscarJugador(rs.getInt("p.blanco")));
				partida.setNegro(cj.buscarJugador(rs.getInt("p.blanco")));
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
	

	public void agregarPartida(int j1, int j2)
	{
		String sql="INSERT INTO partida (blanco, negro) VALUES (?,?)";
		PreparedStatement sentencia=null;
		Connection conn=ConexionPropuesta.getInstancia().getConn();
		
		try {
			sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, j1);			
			sentencia.setInt(2, j2);
			sentencia.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally{
			try{
				if(sentencia!=null && !sentencia.isClosed()){sentencia.close();}
				ConexionPropuesta.getInstancia().CloseConn();
			}
			catch (SQLException sqle){
				sqle.printStackTrace();
			}
			
		}
		
	}
	
	
	
	
}