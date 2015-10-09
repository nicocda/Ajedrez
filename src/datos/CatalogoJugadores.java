package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.Conexion;
import entidades.Jugador;


public class CatalogoJugadores 
{
	  private static CatalogoJugadores instance = null;
	  public CatalogoJugadores() {
	   }
	   public static CatalogoJugadores getInstance() {
	      if(instance == null) {
	         instance = new CatalogoJugadores();
	      }
	      return instance;
	   }

	
	//Busqueda por DNI (no es necesaria si usamos lista y viceversa)
	public Jugador buscarJugador(int dni)
	{
		//listaJugadores = new ArrayList<Jugador>();
		String sql="select * from jugadores where dni = "+ Integer.toString(dni);
		Statement sentencia=null;
		ResultSet res=null;
		Jugador j = null;
		
		try 
		{			
			sentencia= Conexion.getInstancia().getConn().createStatement();
			res= sentencia.executeQuery(sql);
			
			while(res.next())
			{
				j = new Jugador(res.getInt(1), res.getString(2), res.getString(3));
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
				if(res!=null && !res.isClosed())
				{
					res.close();
				}
				if(sentencia!=null && !sentencia.isClosed())
				{
					sentencia.close();
				}
				Conexion.getInstancia().CloseConn();
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}
	return(j);
	}
	
	}



