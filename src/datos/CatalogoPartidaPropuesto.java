package datos;

import java.sql.*;
import java.util.ArrayList;

import conexion.ConexionPropuesta;
import entidades.Trebejo;
import entidades.Alfil;
import entidades.Caballo;
import entidades.Jugador;
import entidades.Partida;
import entidades.Peon;
import entidades.Reina;
import entidades.Rey;
import entidades.Torre;

public class CatalogoPartidaPropuesto
{	

	   private static CatalogoPartidaPropuesto instance = null;
	   public CatalogoPartidaPropuesto() {
	   }
	   public static CatalogoPartidaPropuesto getInstance() {
	      if(instance == null) {
	         instance = new CatalogoPartidaPropuesto();
	      }
	      return instance;
	   }
	
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
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	//Método para Obtener todas las partidas iniciadas de un jugador
	public Partida buscarUnaPartida(int blanco,int negro)
	{
		String sql=
				"select p.blanco, p.negro, p.turno, p.fin, blanco.nombre, blanco.apellido, negro.nombre, negro.apellido, "
				+ "t.tipo, t.posX, t.posY, t.color "
				+ "from partida p  "
				+ "inner join trebejos t "
				+ "on t.dni1 = p.blanco and t.dni2 = p.negro "
				+ "inner join jugadores blanco "
				+ "on blanco.dni = p.blanco "
				+ "inner join jugadores negro "
				+ "on negro.dni = p.negro "
				+ "where (p.blanco= ? and p.negro= ?) or (p.blanco= ? and p.negro= ?);";
		PreparedStatement sentencia=null;
		ResultSet rs=null;
		Connection con = ConexionPropuesta.getInstancia().getConn();
		
		Partida partida = new Partida();
		Jugador jugadorBlanco = new Jugador();
		Jugador jugadorNegro = new Jugador();
		Trebejo t = null;
		ArrayList<Trebejo> trebejos = new ArrayList<Trebejo>();
		try 
		{			
			sentencia= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, blanco);
			sentencia.setInt(2, negro);
			sentencia.setInt(3, negro);
			sentencia.setInt(4, blanco);
			rs= sentencia.executeQuery();
			//Si no existe ninguna, la lista queda nula, debido a que no se ingresa nunca al while
			//Tener en cuenta para futuras validaciones
			
			while (rs.next())
			{
				jugadorBlanco.setDni(rs.getInt("p.blanco"));
				jugadorBlanco.setNombre(rs.getString("blanco.nombre"));
				jugadorBlanco.setApellido(rs.getString("blanco.apellido"));
				partida.setBlanco(jugadorBlanco);
				jugadorNegro.setDni(rs.getInt("p.negro"));
				jugadorNegro.setNombre(rs.getString("negro.nombre"));
				jugadorNegro.setApellido(rs.getString("negro.apellido"));
				partida.setNegro(jugadorNegro);
				partida.setTurno(rs.getBoolean("p.turno"));
				partida.setFin(rs.getBoolean("p.fin"));
				char tipo = rs.getString("t.tipo").charAt(0);
				int posX = rs.getInt("t.posX");
				int posY = rs.getInt("t.posY");
				boolean color = rs.getBoolean("t.color");
				int dni1 = rs.getInt("p.blanco");
				int dni2 = rs.getInt("p.negro");
				
				//Según el tipo se crean (con sus respectivos constructores) cada pieza
				
				switch(tipo)
				{
				case 'P' : t= new Peon(tipo, posX, posY, color, dni1, dni2);
							break;
				case 'T' : t= new Torre(tipo, posX, posY, color, dni1, dni2);
							break;
				case 'C' : t= new Caballo(tipo, posX, posY, color, dni1, dni2);
							break;
				case 'A' : t= new Alfil(tipo, posX, posY, color, dni1, dni2);
							break;
				case 'K' : t= new Rey(tipo, posX, posY, color, dni1, dni2);
							break;
				case 'Q' : t=new Reina(tipo, posX, posY, color, dni1, dni2);
							break;
				default: t = null;
							break;
				}	
				trebejos.add(t);
				
			}
			partida.setFichas(trebejos);

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
				if(null!=sentencia && !sentencia.isClosed())
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
		return partida;	
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public void agregarPartida(int j1, int j2)
	{
		String sql="INSERT INTO partida (blanco, negro) VALUES (?, ?)";
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
				if(sentencia!=null && !sentencia.isClosed())
				{
					sentencia.close();
					}
				ConexionPropuesta.getInstancia().CloseConn();
			}
			catch (SQLException sqle){
				sqle.printStackTrace();
			}
			
		}
		
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public ArrayList<Integer> buscarOponente(int dni)
	{
		ArrayList<Integer> oponentes = new ArrayList<Integer>();
		
		String sql="select blanco, negro from partida where blanco = ? or negro = ?;";
		PreparedStatement sentencia=null;
		ResultSet rs=null;
		Connection con = ConexionPropuesta.getInstancia().getConn();
		try 
		{			
			sentencia= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, dni);
			sentencia.setInt(2, dni);
			rs= sentencia.executeQuery();
			
			while (rs.next())
			{			
				int blanco = rs.getInt("blanco"), negro = rs.getInt("negro");
				if(blanco == dni)
					oponentes.add(negro);
				else
					oponentes.add(blanco);
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
		return(oponentes);
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void actualizarPartida(int dni1, int dni2, boolean turno, boolean fin)
	{
		String sql;
		PreparedStatement sentencia=null;
		Connection con = ConexionPropuesta.getInstancia().getConn();
		sql = "UPDATE partida SET turno = ?, fin = ? WHERE (blanco = ? AND negro = ?) or negro = ? AND blanco = ?;";
		try 
		{
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setBoolean(1, turno);
			sentencia.setBoolean(2, fin);
			sentencia.setInt(3, dni1);
			sentencia.setInt(4, dni2);
			sentencia.setInt(5, dni1);
			sentencia.setInt(6, dni2);
			sentencia.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
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