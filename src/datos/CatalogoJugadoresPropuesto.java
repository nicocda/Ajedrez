package datos;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import conexion.ConexionPropuesta;
import entidades.Jugador;

public class CatalogoJugadoresPropuesto 
{
	//Trabajamos con listas o volcamos directamente de la DB?
	//Acá uso listas.
	private ArrayList<Jugador> listaJugadores;
	
	//El constructor llena el ArrayList con todos los jugadores existentes
	public CatalogoJugadoresPropuesto()
	{
		cargarLista();
	}
	
	public ArrayList<Jugador> getListaJugadores()
	{
		return this.listaJugadores;
	}
	
	//Obtiene todos los jugadores existentes
	private void cargarLista()
	{
		listaJugadores = new ArrayList<Jugador>();
		String sql="select * from jugadores";
		Statement sentencia=null;
		ResultSet rs=null;
		
		try 
		{			
			sentencia= ConexionPropuesta.getInstancia().getConn().createStatement();
			rs= sentencia.executeQuery(sql);
			
			while(rs.next())
			{
				Jugador j = new Jugador(rs.getInt(1), rs.getString(2), rs.getString(3));
				listaJugadores.add(j);
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
	
	//Se sobrecarga el método cargar lista, para poder así pasarle un atributo
	//y haecer una búsqueda específica en la base de datos (en este caso por nombre, se puede usar dni).
	//El resultado es la misma lista que antes, pero con un filtrado de nombres (por ejemplo, todos los que empiezan con A)
	public void cargarLista(String filtro)
	{
		listaJugadores = new ArrayList<Jugador>();
		String sql="select * from jugadores where nombre like '" + filtro + "%'";
		Statement sentencia=null;
		ResultSet rs=null;
		
		try 
		{			
			sentencia= ConexionPropuesta.getInstancia().getConn().createStatement();
			rs= sentencia.executeQuery(sql);
			
			while(rs.next())
			{
				Jugador j = new Jugador(rs.getInt(1), rs.getString(2), rs.getString(3));
				listaJugadores.add(j);
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
	
	public void modificarJugador(Jugador J)
	{
		String sql="update jugadores set nombre = ?, apellido = ? where dni = ?";
		PreparedStatement sentencia=null;
		Connection conn=ConexionPropuesta.getInstancia().getConn();
		
		try {
			sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, J.getNombre());
			sentencia.setString(2, J.getApellido());
			sentencia.setInt(3, J.getDni());
		
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
		//Siempre que modifique, quiero que se me vuelva a actualizar la lista
		cargarLista("");
		
	}
	

	public void nuevoJugador (Jugador J)
	{
		String sql="insert into jugadores(nombre, apellido, dni) values (?,?,?)";
		PreparedStatement sentencia=null;
		Connection conn=ConexionPropuesta.getInstancia().getConn();
		
		try {
			sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, J.getNombre());
			sentencia.setString(2, J.getApellido());
			sentencia.setInt(3, J.getDni());			

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
		//Igual que en el update, siempre que cree un nuevo jugador quiero actualizar mi lista
		cargarLista("");
	}

}